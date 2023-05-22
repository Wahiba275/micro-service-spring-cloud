package org.sid.immatriculationservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder @ToString
public class Proprietaire {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idp ;
    private String nom ;
    private String date_Naissance ;
    private String email ;
    @OneToMany(mappedBy = "proprietaire")
    private List<Vehicule> vehiculeList ;


}
