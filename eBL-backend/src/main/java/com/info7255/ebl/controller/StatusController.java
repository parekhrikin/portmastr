package com.info7255.ebl.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @CrossOrigin
    @GetMapping(path = {"/api/status"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> status() {
        return ResponseEntity.status(HttpStatus.OK)
                .body("status: UP");
    }
}
