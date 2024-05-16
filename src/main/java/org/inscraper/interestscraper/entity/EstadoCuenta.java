package org.inscraper.interestscraper.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstadoCuenta {
    private String companyIdentification;
    private String clientIdentification;
    private String companyName;
    private String clientName;

    private List<EstadoCuentaDetalle> pagados;
    private List<EstadoCuentaDetalle> pendientes;
}
