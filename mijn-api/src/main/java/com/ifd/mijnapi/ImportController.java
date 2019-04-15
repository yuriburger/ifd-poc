package com.ifd.mijnapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.json.*;
import java.util.ArrayList;
import java.util.List;
import java.net.URI;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/import")
public class ImportController {

    @Autowired
    private HondRepo hondRepo;

    @GetMapping(path="", produces = "application/json")
    public ResponseEntity<Object> getHonden()
    {
        List<JSONObject> entities = new ArrayList<>();
        for (Hond hond : hondRepo.getAllHonden().getHondList()) {
            entities.add(new JSONObject(hond));
        }

        return new ResponseEntity<>(entities.toString(), HttpStatus.OK);
    }


    @PostMapping(path= "", consumes = "application/json")
    public ResponseEntity<Object> addImportHond(@RequestBody Hond hond)
    {
        Integer id = hondRepo.getAllHonden().getHondList().size() + 1;
        hond.setId(id);
        hond.setReason("NieuweImportHond");

        hondRepo.addHond(hond);

        return new ResponseEntity<>(hond, HttpStatus.OK);
    }
}
