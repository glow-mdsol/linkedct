package de.fuberlin.wiwiss.d2rs;

import java.util.Map;

import org.joseki.DatasetDesc;

import com.hp.hpl.jena.query.DataSource;
import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 * A Joseki dataset description that returns a dataset
 * consisting only of the one ModelD2RQ passed in. We need
 * this because regular Joseki dataset descriptions are
 * initialized from a configuration Model, and we want
 * to initialize programmatically.
 * 
 * @author Richard Cyganiak (richard@cyganiak.de)
 * @version $Id: D2RQDatasetDesc.java,v 1.4 2006/09/02 11:44:45 cyganiak Exp $
 */
public class D2RQDatasetDesc extends DatasetDesc {
	private DataSource dataset;
	
	public D2RQDatasetDesc(DataSource dataset) {
		super(null);
		this.dataset = dataset;
	}

	public Dataset getDataset() {
		return this.dataset;
	}

	public void clearDataset() {
		this.dataset = null;
	}

	public void freeDataset() {
		this.dataset = null;
	}

	public void setDefaultGraph(Resource dftGraph) {
		throw new RuntimeException("D2RQDatasetDecl.setDefaultGraph is not implemented");
	}
	
	public Resource getDefaultGraph() {
		throw new RuntimeException("D2RQDatasetDecl.getDefaultGraph is not implemented");
	}

	public void addNamedGraph(String uri, Resource r) {
		throw new RuntimeException("D2RQDatasetDecl.addNamedGraph is not implemented");
	}

	public Map getNamedGraphs() {
		throw new RuntimeException("D2RQDatasetDecl.getNamedGraphs is not implemented");
	}

	public String toString() {
		return "D2RQDatasetDecl(" + this.dataset + ")";
	}
}
