package com.info7255.ebl.event;


import com.fasterxml.jackson.databind.JsonNode;
import com.info7255.ebl.entity.User;
import lombok.Data;

@Data
public class QuoteEvent {

    private JsonNode quote;

    public QuoteEvent(JsonNode quote) {
        quote = this.quote;
    }

    public JsonNode getQuote() {
        return quote;
    }

    public void setQuote(JsonNode quote) {
        this.quote = quote;
    }
}
