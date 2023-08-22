package com.info7255.ebl.config;

import org.eclipse.rdf4j.spring.support.RDF4JTemplate;

public class RDFBeanClass {

    private final RDF4JTemplate rdf4jTemplate;

    public RDFBeanClass(RDF4JTemplate template){
        this.rdf4jTemplate = template;
    }

}
