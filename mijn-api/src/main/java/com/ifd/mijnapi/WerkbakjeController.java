package com.ifd.mijnapi;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Arrays;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/werkbakje")
public class WerkbakjeController {

    @Value("${baseUrl}")
    private String baseUrl;

    @Value("${user}")
    private String user;

    @Value("${pass}")
    private String pass;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value="", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getWerkbakje()
    {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> entity = new HttpEntity<>("parameters", createHeaders(this.user, this.pass));
        ResponseEntity<String> result = restTemplate.exchange(this.baseUrl + "/runtime/tasks?candidateGroup=Medewerker Afstamming", HttpMethod.GET, entity, String.class);

        return result;
    }

    @RequestMapping(value="/mijn", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getMijnTaken()
    {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> entity = new HttpEntity<>("parameters", createHeaders(this.user, this.pass));
        ResponseEntity<String> result = restTemplate.exchange(this.baseUrl + "/runtime/tasks?assignee=Yuri Burger", HttpMethod.GET, entity, String.class);

        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateTask(@PathVariable("id") String id, @RequestBody String body) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> entity = new HttpEntity<>(body, createHeaders(this.user, this.pass));
        ResponseEntity<String> result = restTemplate.exchange(this.baseUrl + "/runtime/tasks/" + id , HttpMethod.PUT, entity, String.class);

        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> completeTask(@PathVariable("id") String id, @RequestBody String body) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> entity = new HttpEntity<>(body, createHeaders(this.user, this.pass));
        ResponseEntity<String> result = restTemplate.exchange(this.baseUrl + "/runtime/tasks/" + id , HttpMethod.POST, entity, String.class);

        return result;
    }

    HttpHeaders createHeaders(String username, String password){
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")) );
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );
            setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            setContentType(MediaType.APPLICATION_JSON);
        }};
    }
}
