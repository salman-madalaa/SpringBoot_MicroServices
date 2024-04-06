package com.embarkx.firstjobapp.companie.controller;

import com.embarkx.firstjobapp.companie.model.Company;
import com.embarkx.firstjobapp.companie.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    CompanyService companieService;

    @GetMapping
    public ResponseEntity<List<Company>> getAll() {
        List<Company> companies = companieService.getAll();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Company company) {
        Company res = companieService.create(company);
        return new ResponseEntity<>("Company created successfully", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Company company) {
        boolean res = companieService.update(id, company);
        if (res) {
            return new ResponseEntity<>("Company Successfully updated", HttpStatus.OK);
        }
        return new ResponseEntity<>("Company  failure in update", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        boolean res = companieService.delete(id);
        if (res) {
            return new ResponseEntity<>("Company Successfully deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Company  failure in deletion", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getById(@PathVariable Long id) {

        Company company = companieService.getById(id);

        return new ResponseEntity<>(company, HttpStatus.OK);
    }


}
