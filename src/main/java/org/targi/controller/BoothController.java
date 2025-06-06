package org.targi.controller;

import org.targi.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.targi.model.Booth;
import org.targi.repository.BoothRepository;


import java.util.List;


    @RestController
    @RequestMapping("/api/booths")
    @CrossOrigin
    public class BoothController {

        @Autowired
        private BoothRepository boothRepo;

        @GetMapping
        public List<Booth> getAll() {
            return boothRepo.findAll();
        }

        @PostMapping("/{id}/reserve")
        public ResponseEntity<?> reserve(@PathVariable Long id, @RequestBody Person person) {
            Booth booth = boothRepo.findById(id).orElseThrow();
            if (booth.isReserved()) return ResponseEntity.status(409).body("Already reserved");
            booth.setReserved(true);
            booth.setReservedBy(person);
            boothRepo.save(booth);
            return ResponseEntity.ok(booth);
        }
    }


