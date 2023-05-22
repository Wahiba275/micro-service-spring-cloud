package org.sid.immatriculationservice.web;


import org.sid.immatriculationservice.entities.Vehicule;
import org.sid.immatriculationservice.repositories.VehiculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/web")
public class VehiculeRestCotroller {
    @Autowired
    private VehiculeRepository vehiculeRepository  ;
    @GetMapping("/Vehicules")
    public List<Vehicule> vehicules(){
        return vehiculeRepository.findAll();
    }
    @GetMapping("/Vehicules/{id}")
    public Vehicule getVehicule(@PathVariable String id){
        return vehiculeRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Account Not Found :)" , id)));
    }

}
