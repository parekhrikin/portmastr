package com.info7255.ebl.lisetener;

import com.info7255.ebl.entity.User;
import com.info7255.ebl.event.QuoteEvent;
import com.info7255.ebl.repository.UserRepository;
import com.info7255.ebl.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmailListeners {

    @Autowired
    private UserRepository userRepository;
    private final EmailService emailService;

    @Async
    @EventListener
    public void onRateQuotationEvent(QuoteEvent event){

        Optional<User> user = userRepository.findById(String.valueOf(event.getQuote().get("customerID")));

        emailService.sendQuotationEmail(user, event.getQuote());
    }
}
