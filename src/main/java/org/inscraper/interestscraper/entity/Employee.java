package org.inscraper.interestscraper.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String tipo;
    private String numero;
    private String cargo;
    private String estado;

    @Lob
    private byte[] imagen;
}
