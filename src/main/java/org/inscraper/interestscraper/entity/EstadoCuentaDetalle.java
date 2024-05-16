package org.inscraper.interestscraper.entity;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstadoCuentaDetalle {
    private String suministro;
    private String documento;
    private String fechaEmision;
    private String fechaVencimiento;
    private String diasVencidos;
    private String moneda;
    private String montoPendiente;
    private String descripcionServicio;
    private String montoDolarizado;
    private String ejecutivoComercial;
    private String fechaCausal;
}
