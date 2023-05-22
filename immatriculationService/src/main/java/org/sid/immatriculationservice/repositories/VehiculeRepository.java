package org.sid.immatriculationservice.repositories;


import org.sid.immatriculationservice.entities.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface VehiculeRepository extends JpaRepository<Vehicule, String> {

}
