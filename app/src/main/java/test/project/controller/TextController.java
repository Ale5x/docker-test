package test.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.project.entity.Text;
import test.project.service.TextService;

import java.util.List;

@RestController
@CrossOrigin({"http://localhost:3000", "http://localhost:8080"})
public class TextController {

    private TextService service;

    @Autowired
    public TextController(TextService service) {
        this.service = service;
    }

    @PostMapping(value = "text/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseStatus> save(@RequestBody Text text) {
        if (service.save(text)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "text/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> delete(@RequestBody Text text) {
        System.out.println("DELETING --> " + text);
        if (service.delete(text)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "text/list")
    public List<Text> getAll() {
        return service.findAll();
    }
}
