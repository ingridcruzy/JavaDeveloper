package org.inscraper.interestscraper.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class DataContratWord {
    private String titulo;
    private String institucion;
    private String domicilioDirector;
    private String nombreDirector;
    private String dniDirector;
    private String numeroResolucion;
    private String nombreDocente;
    private String dniDocente;
    private String correoDocente;
    private String codigoPlaza;
    private String domicilioDocente;
}
