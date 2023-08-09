package com.info7255.ebl.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.info7255.ebl.entity.User;
import com.info7255.ebl.event.QuoteEvent;
import com.info7255.ebl.repository.FreightDAO;
import com.info7255.ebl.resource.VCARD;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.jena.rdf.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class QuoteService {

    ObjectMapper objectMapper = new ObjectMapper();

    private final ApplicationEventPublisher publisher;

    public void quote(JsonNode quote) {

        publisher.publishEvent(new QuoteEvent(quote));

    }

    public void generateRDF(JsonNode rate, Optional<User> user, JsonNode quote){

        Model model = ModelFactory.createDefaultModel();

        JsonNode ppJson = objectMapper.createObjectNode();

        ((ObjectNode) ppJson).put("id", UUID.randomUUID().toString());
        ((ObjectNode) ppJson).put("pol", rate.get("pol"));
        ((ObjectNode) ppJson).put("pod", rate.get("pod"));

        Resource portPair = model.createResource((Resource) ppJson);

        portPair.addProperty(VCARD.BUY, model.createResource()
                                            .addProperty(VCARD.TWENTY, rate.get("twenty").asText())
                                            .addProperty(VCARD.FORTY, rate.get("forty").asText())
                                            .addProperty(VCARD.FORTYHQ, rate.get("fortyhq").asText()))
                .addProperty(VCARD.SELL, model.createResource()
                                            .addProperty(VCARD.TWENTY, quote.get("sellRate20").asText())
                                            .addProperty(VCARD.FORTY, quote.get("sellRate40").asText())
                                            .addProperty(VCARD.FORTYHQ, rate.get("sellRate40HQ").asText()));

        log.info("RDF created", portPair);

    }

}
