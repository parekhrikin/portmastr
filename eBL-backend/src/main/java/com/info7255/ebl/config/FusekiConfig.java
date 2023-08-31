package com.info7255.ebl.config;

import org.apache.jena.fuseki.main.FusekiServer;
import org.apache.jena.query.Dataset;
import org.apache.jena.tdb.TDBFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FusekiConfig {

    @Value("${fuseki.port}")
    private int port;

    @Value("${fuseki.datasetPath}")
    private String datasetPath;

    @Bean(initMethod = "start", destroyMethod = "stop")
    public FusekiServer fusekiServer() {
        Dataset dataset = TDBFactory.createDataset(datasetPath);
        return FusekiServer.create()
                .port(port)
                .add("/dataset", dataset)
                .build();
    }


}
