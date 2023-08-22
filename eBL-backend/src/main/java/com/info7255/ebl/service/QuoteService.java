package com.info7255.ebl.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.info7255.ebl.entity.User;
import com.info7255.ebl.event.QuoteEvent;
import com.info7255.ebl.repository.FreightDAO;
import com.info7255.ebl.resource.QueryBank;
import com.info7255.ebl.resource.VCARD;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.jena.rdf.model.*;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.query.*;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.RepositoryException;
import org.eclipse.rdf4j.repository.sparql.SPARQLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.lang.reflect.Method;

@Slf4j
@Component
@RequiredArgsConstructor
public class QuoteService {

    ObjectMapper objectMapper = new ObjectMapper();

    private final ApplicationEventPublisher publisher;

    public static QueryBank queryBank = new QueryBank();

    public void quote(JsonNode quote) {

        publisher.publishEvent(new QuoteEvent(quote));

    }

    public void generateRDF(JsonNode rate, Optional<User> user, JsonNode quote){

        Model model = ModelFactory.createDefaultModel();

        List<String> queries = new ArrayList<>();


        String polQuery = "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "SELECT ?entity\n" +
                "WHERE {\n" +
                "?entity rdfs:label \"Port of " + rate.get("pol").asText() + "\"@en .\n" +
                "}";

        queries.add(polQuery);

        String podQuery = "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "SELECT ?entity\n" +
                "WHERE {\n" +
                "?entity rdfs:label \"Port of " + rate.get("pod").asText() + "\"@en .\n" +
                "}";

        queries.add(podQuery);

        String containerLineQuery = "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "SELECT ?entity\n" +
                "WHERE {\n" +
                "?entity rdfs:label \"CMA CGM\"@en .\n" +
                "}";

        queries.add(containerLineQuery);

        Method[] methods = queryBank.getClass().getMethods();

        ExecutorService executor = Executors.newFixedThreadPool(queries.size()* (methods.length - 10));


        for(String q: queries){
            executor.execute(new DepositDBpediaQuery(q, queries.size()* (methods.length - 10)));
        }

        System.out.println("###### All tasks are submitted.");

        shutdownAndAwaitTermination(executor);

        System.out.println("###### All tasks are completed.");


//        log.info(podIRI, quote);

        String[] quoterIds = {"TESTID"};
        String[] quoteeIds = {quote.get("customerID").asText()};

        String uuid = UUID.randomUUID().toString();

        Resource portPair = model.createResource()
                .addProperty(VCARD.POL, model.createResource(queryBank.getResultsMap().get(polQuery)))
                .addProperty(VCARD.POD, model.createResource(queryBank.getResultsMap().get(podQuery)))
                .addProperty(VCARD.ID, uuid)
                .addProperty(VCARD.LINE, model.createResource(queryBank.getResultsMap().get(containerLineQuery)));

        RDFList quoteList = model.createList();
        for (int i = 0; i < quoterIds.length; i++) {
            Resource quoter = model.createResource()
                    .addProperty(VCARD.QUOTER, quoterIds[i])
                    .addProperty(VCARD.QUOTEE, quoteeIds[i]);
            quoteList = quoteList.cons(quoter);
        }
        quoteList = (RDFList) quoteList.inModel(model);

        portPair.addProperty(VCARD.BUY, model.createResource()
                                            .addProperty(VCARD.TWENTY, rate.get("twenty").asText())
                                            .addProperty(VCARD.FORTY, rate.get("forty").asText())
                                            .addProperty(VCARD.FORTYHQ, rate.get("fortyhq").asText()))
                .addProperty(VCARD.SELL, model.createResource()
                                            .addProperty(VCARD.TWENTY, quote.get("sellRate20").asText())
                                            .addProperty(VCARD.FORTY, quote.get("sellRate40").asText())
                                            .addProperty(VCARD.FORTYHQ, quote.get("sellRate40HQ").asText()))
                .addProperty(VCARD.QUOTE, quoteList);

        model.write(System.out, "TURTLE");




        log.info("RDF created", portPair);

    }

    public static class DepositDBpediaQuery implements Runnable {

        String query = "";
        int threads = 0;

        public DepositDBpediaQuery(String query, int threads) {
            this.query = query;
            this.threads = threads;
        }

        @Override
        public void run() {
            try {
                while(queryBank.getResultsMap().size() <= threads) {
                    queryBank.queryDBpedia(query);
                    Thread.sleep(1000);
                }
            }
            catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void shutdownAndAwaitTermination(ExecutorService executorService) {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException ie) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }



}


