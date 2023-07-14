package com.info7255.ebl.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.info7255.ebl.entity.User;
import com.info7255.ebl.repository.FreightDAO;
import com.info7255.ebl.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository repository;
    static ObjectMapper mapper = new ObjectMapper();


    @PostMapping(path = "/register")
    public ResponseEntity<String> save(@RequestBody User user, @RequestHeader HttpHeaders requestHeaders) throws Exception {
        repository.save(user);
        return ResponseEntity.ok(user.getId());
    }

    @GetMapping("/users")
    public ResponseEntity getAllUsers(){
        return ResponseEntity.ok(repository.findAll());

    }

    @GetMapping("/user/{id}")
    public ResponseEntity findUser(@PathVariable String id, @RequestHeader HttpHeaders requestHeaders){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Optional<User> user = repository.findById(id);

        return ResponseEntity.ok(user);

    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity remove(@PathVariable String id, @RequestHeader HttpHeaders headers){
        repository.deleteById(id);
        return ResponseEntity.ok("User with id " + id + " has been deleted.");

    }

    @PutMapping("/user/{id}")
    public ResponseEntity update(@RequestBody User user, @RequestHeader HttpHeaders headers){

        repository.save(user);

        return ResponseEntity.ok(user.getUserType() + " with id " + user.getId() + " has been updated.");

    }

}
