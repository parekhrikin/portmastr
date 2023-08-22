package com.info7255.ebl.resource;

import lombok.RequiredArgsConstructor;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.query.*;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.RepositoryException;
import org.eclipse.rdf4j.repository.sparql.SPARQLRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class QueryBank {

    private Lock lock = new ReentrantLock(true);
    private Condition newDeposit = lock.newCondition();
    private Map<String, String> queryResultsMap;

    public QueryBank() {
        queryResultsMap = new LinkedHashMap<>();
    }

    public Map<String, String> getResultsMap() {
        return queryResultsMap;
    }

    public void storeQueryResult(String query, String result) {
        lock.lock();
        try {
            queryResultsMap.put(query, result);
        } finally {
            lock.unlock();
        }
    }

    public void queryDBpedia(String query) {
        String sparqlEndpoint = "http://dbpedia.org/sparql";
        Repository endpoint = new SPARQLRepository(sparqlEndpoint);

        try (RepositoryConnection conn = endpoint.getConnection()) {
            TupleQuery tupleQuery = conn.prepareTupleQuery(QueryLanguage.SPARQL, query);

            lock.lock();
            try (TupleQueryResult result = tupleQuery.evaluate()) {
                while (result.hasNext()) {
                    BindingSet bindingSet = result.next();
                    IRI portIRI = (IRI) bindingSet.getValue("entity");
                    storeQueryResult(query, portIRI.stringValue());
                }
            } finally {
                lock.unlock();
            }
        } catch (QueryEvaluationException | RepositoryException | MalformedQueryException e) {
            e.printStackTrace();
        }
    }
}
