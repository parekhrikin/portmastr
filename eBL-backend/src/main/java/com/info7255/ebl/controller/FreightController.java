package com.info7255.ebl.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.hash.Hashing;
import com.info7255.ebl.MyToken;
import com.info7255.ebl.repository.FreightDAO;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:3000")
public class FreightController {

    @Autowired
    private FreightDAO dao;
    static ObjectMapper mapper = new ObjectMapper();
    static String jws;


    @PostMapping("/schema")
    public ResponseEntity saveSchema(@RequestBody JsonNode schema, @RequestHeader HttpHeaders headers) throws IOException {

        if (!validateToken(headers.get(HttpHeaders.AUTHORIZATION))) {
            return ResponseEntity.badRequest().body("Invalid Token!");
        } else {
            return ResponseEntity.ok(dao.save(schema));
        }


    }

    @GetMapping("/schema")
    public ResponseEntity getSchema(@RequestHeader HttpHeaders headers) {
        if(!validateToken(headers.get(HttpHeaders.AUTHORIZATION))) {
            return ResponseEntity.badRequest().body("Invalid Token!");
        } else {
            return ResponseEntity.ok(dao.getSchema());
        }

    }

    @PostMapping(path = "/rate", produces = "application/json")
    public ResponseEntity save(@RequestBody String rate, @RequestHeader HttpHeaders requestHeaders) throws Exception {

//        if (!validateToken(requestHeaders.get(HttpHeaders.AUTHORIZATION))) {
//            return ResponseEntity.badRequest().body("Invalid Token!");
//        } else {
            JsonNode json = mapper.readTree(rate);
            validatePayload(json);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            String etag = getETag(json);
            if (!verifyETag(json, requestHeaders.getIfNoneMatch())) {
                headers.setETag(etag);
                return new ResponseEntity(dao.save(json), headers, HttpStatus.OK);
            } else {
                headers.setETag(etag);
                return new ResponseEntity(headers, HttpStatus.NOT_MODIFIED);
            }
//        }

    }

    @GetMapping("/rates")
    public ResponseEntity getRates(){

//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Access-Control-Allow-Origin", "http://localhost:3000");

//        return new ResponseEntity<>(dao.findAll(), headers, HttpStatus.OK);

        return ResponseEntity.ok(dao.findAll());

    }

    @GetMapping ("/rate/{id}")
    public ResponseEntity findRate(@PathVariable String id, @RequestHeader HttpHeaders requestHeaders) {

        if (!validateToken(requestHeaders.get(HttpHeaders.AUTHORIZATION))) {
            return ResponseEntity.badRequest().body("Invalid Token!");
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            JsonNode rate = dao.findRateById(id);

            return ResponseEntity.ok().body(rate);
        }

    }

    @DeleteMapping("/rate/{id}")
    public ResponseEntity remove(@PathVariable String id, @RequestHeader HttpHeaders headers) {

//        if (!validateToken(headers.get(HttpHeaders.AUTHORIZATION))) {
//            return ResponseEntity.badRequest().body("Invalid Token!");
//        } else {
            return ResponseEntity.ok(dao.deleteRate(id));
//        }

    }

    @GetMapping("/getClientVersion")
    public ResponseEntity getClientVersion(@RequestHeader HttpHeaders headers) throws IOException, ExecutionException, InterruptedException {
        if (!validateToken(headers.get(HttpHeaders.AUTHORIZATION))) {
            return ResponseEntity.badRequest().body("Invalid Token!");
        } else {
            Web3j web3 = Web3j.build(new HttpService("http://localhost:30303"));  // defaults to http://localhost:8545/
            Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().sendAsync().get();
            String clientVersion = web3ClientVersion.getWeb3ClientVersion();
            return ResponseEntity.ok(clientVersion);
        }

    }

    @GetMapping("/deployContract")
    public ResponseEntity deployContract() throws Exception {

//        Web3j web3j = Web3j.build(new HttpService("http://localhost:30303"));
//
//
//        Credentials credentials = Credentials.create("105727f0e15cd889516fa9b231b114b28b2aef02cedacca493fe70e7a3016dac");
//        GasProvider gasProvider = (GasProvider) new DefaultGasProvider();
//
//        return ResponseEntity.ok(MyToken.deploy(web3j, credentials, new DefaultGasProvider()).send());

        Web3j web3j = Web3j.build(new HttpService("https://eth-goerli.g.alchemy.com/v2/yQ_ckqwHUcnHLl4jeriYmFQvp40wQnIy"));


        // Load your Ethereum account credentials
        Credentials credentials = Credentials.create("");

//        // Deploy your ERC20 contract
//        String contractAddress = "";
//        try {
//            ERC20 contract = ERC20.deploy(web3j, credentials, gasPrice, gasLimit, initialAmount, tokenName, decimalUnits, tokenSymbol).send();
//            contractAddress = contract.getContractAddress();
//        } catch (Exception e) {
//            // Handle exception
//        }
//        System.out.println(MyToken)

        // create a new gas provider with your desired gas values
        ContractGasProvider gasProvider = (ContractGasProvider) new DefaultGasProvider() {
            @Override
            public BigInteger getGasPrice(String contractFunc) {
                // return your desired gas price
                return BigInteger.valueOf(155701000L);
            }

            @Override
            public BigInteger getGasLimit(String contractFunc) {
                // return your desired gas limit
                return BigInteger.valueOf(6000000L);
            }
        };



        RemoteCall<MyToken> token = MyToken.deploy(web3j, credentials, (ContractGasProvider) gasProvider);
        token.send();
        System.out.println(token);
        return ResponseEntity.ok(token.toString());

    }


    private void validatePayload(JsonNode rate) throws Exception {


        JsonNode s = mapper.readTree(dao.getSchema());


        try {
            Schema schema = SchemaLoader.load(new JSONObject(s));
            schema.validate(new JSONObject(rate)); // throws a ValidationException if this object is invalid
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);

        }


    }

    public String getETag(JsonNode json) {

        // Used guava maven dependency here for hashing
        String sha256hex = Hashing.sha256()
                .hashString(json.toString(), StandardCharsets.UTF_8)
                .toString();


        return "W/" + sha256hex + "\"";
    }

    public boolean verifyETag(JsonNode json, List<String> etags) {
        if (etags.isEmpty())
            return false;
        String hashed = getETag(json);
        return etags.contains(hashed);

    }

    private boolean validateToken(List<String> strings) {

        String url = "https://openidconnect.googleapis.com/v1/userinfo?access_token=" + strings.get(0).substring(7);

        RestTemplate restTemplate = new RestTemplate();

        String userinfo = restTemplate.getForObject(url, String.class);

        JSONObject uinfo = new JSONObject(userinfo);

        if (uinfo.get("sub").equals("108252667498100250743")) {
            return true;
        } else {
            return false;
        }


    }


}
