package com.ifd.mijnapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/werkbakje")
public class WerkbakjeController {

    @Value("${baseUrl}")
    private String baseUrl;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value="", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getWerkbakje()
    {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> entity = new HttpEntity<>("parameters", Util.createHeaders());
        ResponseEntity<String> result = restTemplate.exchange(this.baseUrl + "/runtime/tasks?candidateGroup=Medewerker Afstamming&includeProcessVariables=true", HttpMethod.GET, entity, String.class);

        return result;
    }

    @RequestMapping(value="/mijn", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getMijnTaken()
    {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> entity = new HttpEntity<>("parameters", Util.createHeaders());
        ResponseEntity<String> result = restTemplate.exchange(this.baseUrl + "/runtime/tasks?assignee=Yuri Burger&includeProcessVariables=true", HttpMethod.GET, entity, String.class);

        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateTask(@PathVariable("id") String id, @RequestBody String body) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> entity = new HttpEntity<>(body, Util.createHeaders());
        ResponseEntity<String> result = restTemplate.exchange(this.baseUrl + "/runtime/tasks/" + id , HttpMethod.PUT, entity, String.class);

        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> completeTask(@PathVariable("id") String id, @RequestBody String body) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> entity = new HttpEntity<>(body, Util.createHeaders());
        ResponseEntity<String> result = restTemplate.exchange(this.baseUrl + "/runtime/tasks/" + id , HttpMethod.POST, entity, String.class);

        return new ResponseEntity(result, HttpStatus.OK);
    }


}
