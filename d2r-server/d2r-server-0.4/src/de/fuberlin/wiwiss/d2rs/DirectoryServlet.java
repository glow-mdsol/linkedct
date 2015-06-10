package de.fuberlin.wiwiss.d2rs;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.servlet.VelocityServlet;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.vocabulary.RDFS;

import de.fuberlin.wiwiss.d2rq.GraphD2RQ;

public class DirectoryServlet extends VelocityServlet {
	
	public Template handleRequest(HttpServletRequest request,
			HttpServletResponse response,
			Context context) throws IOException {
		if (request.getPathInfo() == null) {
			response.sendError(404);
			return null;
		}
		String classMapName = request.getPathInfo().substring(1);
		Model resourceList = graphD2RQ().classMapInventory(classMapName);
		if (resourceList == null) {
			response.sendError(404, "Sorry, class map '" + classMapName + "' not found.");
			return null;
		}
		Map resources = new TreeMap();
		ResIterator subjects = resourceList.listSubjects();
		while (subjects.hasNext()) {
			Resource resource = subjects.nextResource();
			if (!resource.isURIResource()) {
				continue;
			}
			String uri = resource.getURI();
			Statement labelStmt = resource.getProperty(RDFS.label);
			String label = (labelStmt == null) ? resource.getURI() : labelStmt.getString();
			resources.put(uri, label);
		}
		D2RServer server = D2RServer.fromServletContext(getServletContext());
		Map classMapLinks = new TreeMap();
		Iterator it = graphD2RQ().classMapNames().iterator();
		while (it.hasNext()) {
			String name = (String) it.next();
			classMapLinks.put(name, server.baseURI() + "directory/" + name);
		}
		context.put("truncated_results", new Boolean(server.hasTruncatedResults()));
		context.put("server_name", server.serverName());
		context.put("home_link", server.baseURI());
		context.put("rdf_link", server.baseURI() + "all/" + classMapName);
		context.put("classmap", classMapName);
		context.put("classmap_links", classMapLinks);
		context.put("resources", resources);
		response.addHeader("Content-Type", "application/xhtml+xml; charset=utf-8");
		response.addHeader("Cache-Control", "no-cache");
		response.addHeader("Pragma", "no-cache");
		setContentType(request, response);
		try {
			return getTemplate("directory_page.vm");
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	private GraphD2RQ graphD2RQ() {
		return (GraphD2RQ) D2RServer.fromServletContext(getServletContext()).currentGraph();
	}

	private static final long serialVersionUID = 8398973058486421941L;
}
