package com.info7255.ebl.config;

import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.sparql.SPARQLRepository;
import org.eclipse.rdf4j.spring.RDF4JConfig;
import org.eclipse.rdf4j.spring.support.RDF4JTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(RDF4JConfig.class)
class RDFConfig {

    @Bean
    public RDFBeanClass getMyBean(@Autowired RDF4JTemplate template){
        return new RDFBeanClass(template);
    }

    @Bean
    public Repository getRepository() {
        String sparqlEndpoint = "http://dbpedia.org/sparql";
        return new SPARQLRepository(sparqlEndpoint);
    }

}
