package com.churchmutual.restclient.controllers;

import com.churchmutual.restclient.entities.Site;
import com.churchmutual.restclient.services.GeocoderService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/sites")
@Validated
public class SiteController {
    private GeocoderService service;

    public SiteController(GeocoderService service) {
        this.service = service;
    }

    @GetMapping(produces = "application/json")
    public List<Site> getAllSites() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Site> getSite(@PathVariable int id) {
        return ResponseEntity.of(service.findById(id));
    }

//    @PostMapping
//    public ResponseEntity<Site> addSite(
//            @RequestParam String city, @RequestParam String state) {
//        Site site = service.addSite(city, state);
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(site.getId())
//                .toUri();
//        return ResponseEntity.created(uri).body(site);
//    }

    @PostMapping
    public ResponseEntity<Site> addSite(@Valid @RequestBody Address address, BindingResult errors) {
        if (errors.hasErrors()) {
            System.out.println(errors.getAllErrors());
        }
        Site site = service.addSite(address.getCity(), address.getState());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(site.getId())
                .toUri();
        return ResponseEntity.created(uri).body(site);
    }

}
