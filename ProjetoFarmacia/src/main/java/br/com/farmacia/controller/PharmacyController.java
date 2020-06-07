package br.com.farmacia.controller;

import br.com.farmacia.model.Pharmacy;
import br.com.farmacia.service.PharmacyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * @author vinicius.montouro
 */
@RestController
@RequestMapping("v1/apontador/farmacia")
@Slf4j
@Api(value = "Endpoints to manage pharmacy")
public class PharmacyController {
    @Autowired
    private PharmacyService pharmacyService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "List all available pharmacys pageable", response = Pharmacy[].class)
    public ResponseEntity<Iterable<Pharmacy>> list(Pageable pageable) {
        return new ResponseEntity<>(pharmacyService.findPageable(pageable), HttpStatus.OK);
    }
    @GetMapping("/all")
    @ApiOperation(value = "List all available pharmacys", response = Pharmacy[].class)
    public Iterable<Pharmacy> findAll() {
        return pharmacyService.findAll();
    }

    @PutMapping
    @ApiOperation(value = "Update pharmacy", response = Pharmacy.class)
    public Pharmacy update(@RequestBody Pharmacy pharmacy) {
        return pharmacyService.update(pharmacy);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete pharmacy id", response = Pharmacy[].class)
    public ResponseEntity<Pharmacy> delete(@PathVariable("id") Long id) {
        pharmacyService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Search pharmacy by id", response = Pharmacy[].class)
    public Pharmacy findById(@PathVariable("id") Long id) {
        return pharmacyService.findById(id);
    }


    @PostMapping
    @ApiOperation(value = "Create pharmacy", response = Pharmacy[].class)
    public Pharmacy create(@RequestBody Pharmacy pharmacy) {
        return pharmacyService.create(pharmacy);
    }

}
