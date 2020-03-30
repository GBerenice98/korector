package com.projet.korector.controller;

import com.projet.korector.entity.Section;
import com.projet.korector.services.SectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/ressource")
public class SectionController {

    final static Logger log = LoggerFactory.getLogger(SectionController.class);

    @Autowired
    private SectionService service;

    @RequestMapping(value = "/createSection", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Section createSection(@Valid @RequestBody Section section) {
        return service.createSection(section);
    }

    @RequestMapping(value = "/allSections", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Section> getAllSections() { return service.getAllSections(); }

    @RequestMapping(value = "/deleteSection/{sectionId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteSection(@PathVariable Long sectionId)
    {
        service.deleteSection(sectionId);
    }

    @RequestMapping(value = "/SectionByName/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Section getSectionByName(@PathVariable Long name)
    {
        return service.getSectionByName(name);
    }

    @RequestMapping(value = "/SectionById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Section getSectionById(@PathVariable Long id)
    {
        return service.getSectionById(id);
    }

}
