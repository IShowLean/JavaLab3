package com.example.lab3.Controller;

import com.example.lab3.DAL.entities.cat.CatDto;
import com.example.lab3.DAL.models.Color;
import com.example.lab3.Services.Cat.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class CatController {
    private final CatService catService;

    @Autowired
    public CatController(CatService currentCatService) {
        this.catService = currentCatService;
    }

    @PostMapping(value = "/cats")
    public ResponseEntity<?> save(@RequestBody CatDto cat) {
        catService.saveCat(cat);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/cats")
    public ResponseEntity<List<CatDto>> read(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "birthday", required = false) LocalDate birthday,
            @RequestParam(name = "type", required = false) String type,
            @RequestParam(name = "color", required = false) Color color
    ) {
        if (name != null || birthday != null || type != null || color != null) {
            var param = new CatParamFinder(name, birthday, type, color);
            final List<CatDto> cat = catService.findCatsByParam(param);

            return cat != null
                    ? new ResponseEntity<>(cat, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            final List<CatDto> cats = catService.findAll();

            return cats != null &&  !cats.isEmpty()
                    ? new ResponseEntity<>(cats, HttpStatus.OK)
                    : new ResponseEntity<>(cats, HttpStatus.NO_CONTENT);
        }
    }


    @GetMapping(value = "/cats/{id}")
    public ResponseEntity<CatDto> read(@PathVariable(name = "id") Integer id) {
        final CatDto cat = catService.findCat(id);

        return cat != null
                ? new ResponseEntity<>(cat, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/cats/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Integer id, @RequestBody CatDto cat) {
        final boolean updated = catService.update(cat, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/cats/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id) {
        final boolean deleted = catService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}