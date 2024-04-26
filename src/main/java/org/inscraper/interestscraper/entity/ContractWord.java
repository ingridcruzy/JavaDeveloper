package org.inscraper.interestscraper.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
//@Table(name = "contract")
@Entity
public class ContractWord {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String tipo;
    private String titulo;
    @Column(columnDefinition = "TEXT")
    private String contrato;
    private String clausula;
}
