package com.info7255.ebl.resource;

import org.apache.jena.rdf.model.* ;

/** VCARD vocabulary class for namespace http://www.w3.org/2001/vcard-rdf/3.0#
 */
public class VCARD {

    /**
     * The namespace of the vocabulary as a string
     */
    public static final String uri ="portmastr#";

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
    public static final Property QUOTE = m.createProperty(uri, "QUOTE");
    public static final Property QUOTER = m.createProperty(uri, "QUOTER");
    public static final Property QUOTEE = m.createProperty(uri, "QUOTEE");
    public static final Property POL = m.createProperty(uri, "POL");
    public static final Property POD = m.createProperty(uri, "POD");
    public static final Property ID = m.createProperty(uri, "ID");
    public static final Property LINE = m.createProperty(uri, "LINE");
}
