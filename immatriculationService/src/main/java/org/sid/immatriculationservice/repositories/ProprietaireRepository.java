package org.sid.immatriculationservice.repositories;


import org.sid.immatriculationservice.entities.Proprietaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProprietaireRepository extends JpaRepository<Proprietaire, Long> {
}
