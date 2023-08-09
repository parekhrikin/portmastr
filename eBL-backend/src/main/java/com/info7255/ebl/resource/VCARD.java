package com.info7255.ebl.resource;

import org.apache.jena.rdf.model.* ;

/** VCARD vocabulary class for namespace http://www.w3.org/2001/vcard-rdf/3.0#
 */
public class VCARD {

    /**
     * The namespace of the vocabulary as a string
     */
    public static final String uri ="http://www.w3.org/2001/vcard-rdf/3.0#";

    /** returns the URI for this schema
     * @return the URI for this schema
     */
    public static String getURI() {
        return uri;
    }

    private static final Model m = ModelFactory.createDefaultModel();

    public static final Property TWENTY = m.createProperty(uri, "TWENTY" );
    public static final Property FORTY = m.createProperty(uri, "FORTY" );
    public static final Property FORTYHQ = m.createProperty(uri, "FORTYHQ" );
    public static final Property BUY = m.createProperty(uri, "BUY");
    public static final Property SELL = m.createProperty(uri, "SELL");
}
