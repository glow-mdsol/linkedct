/* CVS $Id: D2R.java,v 1.2 2007/02/09 15:20:11 cyganiak Exp $ */
package de.fuberlin.wiwiss.d2rs.vocab; 
import com.hp.hpl.jena.rdf.model.*;
 
/**
 * Vocabulary definitions from file:doc/config.n3 
 * @author Auto-generated by schemagen on 09 Feb 2007 13:25 
 */
public class D2R {
    /** <p>The RDF model that holds the vocabulary terms</p> */
    private static Model m_model = ModelFactory.createDefaultModel();
    
    /** <p>The namespace of the vocabulary as a string</p> */
    public static final String NS = "http://sites.wiwiss.fu-berlin.de/suhl/bizer/d2r-server/config.rdf#";
    
    /** <p>The namespace of the vocabulary as a string</p>
     *  @see #NS */
    public static String getURI() {return NS;}
    
    /** <p>The namespace of the vocabulary as a resource</p> */
    public static final Resource NAMESPACE = m_model.createResource( NS );
    
    /** <p>A template resource whose properties will be attached as metadata to all RDF 
     *  documents published by a D2R Server installation.</p>
     */
    public static final Property documentMetadata = m_model.createProperty( "http://sites.wiwiss.fu-berlin.de/suhl/bizer/d2r-server/config.rdf#documentMetadata" );
    
    /** <p>The D2RQ-mapped database that is published by a D2R Server installation.</p> */
    public static final Property publishes = m_model.createProperty( "http://sites.wiwiss.fu-berlin.de/suhl/bizer/d2r-server/config.rdf#publishes" );
    
    /** <p>The TCP port on which a D2R Server installation listens.</p> */
    public static final Property port = m_model.createProperty( "http://sites.wiwiss.fu-berlin.de/suhl/bizer/d2r-server/config.rdf#port" );
    
    /** <p>Base URI for a D2R Server installation; the URI of the running server's start 
     *  page.</p>
     */
    public static final Property baseURI = m_model.createProperty( "http://sites.wiwiss.fu-berlin.de/suhl/bizer/d2r-server/config.rdf#baseURI" );
    
    /** <p>A configuration for a D2R Server installation.</p> */
    public static final Resource Server = m_model.createResource( "http://sites.wiwiss.fu-berlin.de/suhl/bizer/d2r-server/config.rdf#Server" );
    
}
