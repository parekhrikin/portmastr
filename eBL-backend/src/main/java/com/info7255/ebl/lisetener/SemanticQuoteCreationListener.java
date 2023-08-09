package com.info7255.ebl.lisetener;

import com.fasterxml.jackson.databind.JsonNode;
import com.info7255.ebl.entity.User;
import com.info7255.ebl.event.QuoteEvent;
import com.info7255.ebl.repository.FreightDAO;
import com.info7255.ebl.repository.UserRepository;
import com.info7255.ebl.service.QuoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class SemanticQuoteCreationListener {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuoteService quoteService;

    @Autowired
    private FreightDAO dao;

    @Async
    @EventListener
    public void onRateQuotationEvent(QuoteEvent event) {

        Optional<User> user = userRepository.findById(String.valueOf(event.getQuote().get("customerID")));

        log.info("Inside SemanticQuoteCreationListener", event);

        JsonNode rate = dao.findRateById(String.valueOf(event.getQuote().get("rateID")));

        quoteService.generateRDF(rate, user, event.getQuote());

    }
}
