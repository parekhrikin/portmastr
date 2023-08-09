package com.info7255.ebl.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.info7255.ebl.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class EmailService {

    public EmailService() {
    }

    public void sendQuotationEmail(Optional<User> user, JsonNode quote){


        log.info("Email Quotation sent!", quote);
    }
}
