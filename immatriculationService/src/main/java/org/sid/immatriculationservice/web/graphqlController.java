package org.sid.immatriculationservice.web;



import org.sid.immatriculationservice.entities.Proprietaire;
import org.sid.immatriculationservice.entities.Vehicule;
import org.sid.immatriculationservice.repositories.ProprietaireRepository;
import org.sid.immatriculationservice.repositories.VehiculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class graphqlController {
    @Autowired
    private VehiculeRepository vehiculeRepository ;
    @Autowired
    private ProprietaireRepository  proprietaireRepository;
    @QueryMapping
    public List<Vehicule> vehicules(){
        return  vehiculeRepository.findAll();
    }
    @QueryMapping
    public Vehicule vehiculeById(@Argument String id){
        return  vehiculeRepository.findById(id)
            .orElseThrow();
    }
    @QueryMapping
    public List<Proprietaire> proprietaires(){
        return  proprietaireRepository.findAll();
    }
    @MutationMapping
    public void deleteVehicule(@Argument  String id){
        vehiculeRepository.deleteById(id);
    }
}
