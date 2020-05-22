package com.projet.korector.controller;

import com.projet.korector.Exceptions.ResourceAlreadyExistsException;
import com.projet.korector.Exceptions.ResourceNotFoundException;
import com.projet.korector.entity.Criteria;

import com.projet.korector.services.CriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/Criteria")
public class CriteriaController {

    @Autowired
    private CriteriaService service;

    @PostMapping("/createCriteria")
    public String createCriteria(@RequestBody Criteria criteria)
    {
        return service.createCriteria(criteria);
    }

    @PutMapping("/updateCriteria/{id}")
    public ResponseEntity<Criteria> updateCriteria(@PathVariable Long id, @RequestBody Criteria criteria)
    {
        return service.updateCriteria(id,criteria);
    }

    @GetMapping("/allCriteria")
    public List<Criteria> getAllCriteria()
    {
        return service.getAllCriteria();
    }

    @GetMapping("/id={id}")
    public ResponseEntity<Criteria> getCriteriaById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
            return service.getCriteriaById(id);
    }

    @DeleteMapping("/deleteCriteria/{id}")
    public void deleteCriteria(@PathVariable Long id)
    {
        service.deleteCriteria(id);
    }

    @GetMapping("/researchCriteria/{name}&{type}")
    public List<Criteria> researchCriteria(@PathVariable String name,@PathVariable String type ) throws ResourceNotFoundException {
       return service.researchCriteria(name, type);
    }
    @GetMapping("/researchCriteria/name={name}")
    public List <Criteria> researchCriteriaByName(@PathVariable String name ) throws ResourceNotFoundException {
        return service.researchCriteriaByName(name);
    }

    @GetMapping("/researchCriteria/type={type}")
    public List <Criteria> researchCriteriaByType(@PathVariable String type ) throws ResourceNotFoundException {
        return service.researchCriteriaByType(type);
    }

}
