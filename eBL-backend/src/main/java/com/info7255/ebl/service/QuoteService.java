package com.info7255.ebl.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.info7255.ebl.entity.User;
import com.info7255.ebl.event.QuoteEvent;
import com.info7255.ebl.repository.FreightDAO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuoteService {

//    private final User user;

//    private final EmailService emailService;

    private final ApplicationEventPublisher publisher;

    public void quote(JsonNode quote) {

//        emailService.sendQuotationEmail(quote);

        publisher.publishEvent(new QuoteEvent(quote));

    }

}
