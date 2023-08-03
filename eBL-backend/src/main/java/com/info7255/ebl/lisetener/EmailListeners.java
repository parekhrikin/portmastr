package com.info7255.ebl.lisetener;

import com.info7255.ebl.event.QuoteEvent;
import com.info7255.ebl.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailListeners {

    private final EmailService emailService;

    @EventListener
    public void onRateQuotationEvent(QuoteEvent event){

        emailService.sendQuotationEmail(event.getQuote());
    }
}
