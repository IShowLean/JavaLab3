package com.example.lab3.Controller;
import com.example.lab3.DAL.entities.cat.CatDto;
import com.example.lab3.DAL.entities.owner.OwnerDto;
import com.example.lab3.Services.Owner.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class OwnerController {
    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService currentOwnerService) {
        this.ownerService = currentOwnerService;
    }

    @PostMapping(value = "/owners")
    public ResponseEntity<?> save(@RequestBody OwnerDto owner) {
        ownerService.saveOwner(owner);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/owners")
    public ResponseEntity<List<OwnerDto>> read(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "birthday", required = false) LocalDate birthday
    ) {
        if (name != null || birthday != null) {
            var param = new OwnerParamFinder(name, birthday);
            final List<OwnerDto> owner = ownerService.findOwnersByParam(param);

            return owner != null
                    ? new ResponseEntity<>(owner, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            final List<OwnerDto> owners = ownerService.findAllOwners();

            return owners != null &&  !owners.isEmpty()
                    ? new ResponseEntity<>(owners, HttpStatus.OK)
                    : new ResponseEntity<>(owners, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/owners/{id}")
    public ResponseEntity<OwnerDto> read(@PathVariable(name = "id") Integer id) {
        final OwnerDto owner = ownerService.findOwner(id);

        return owner != null
                ? new ResponseEntity<>(owner, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/owners/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Integer id, @RequestBody OwnerDto owner) {
        final boolean updated = ownerService.updateOwner(owner, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/owners/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id) {
        final boolean deleted = ownerService.deleteOwner(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}