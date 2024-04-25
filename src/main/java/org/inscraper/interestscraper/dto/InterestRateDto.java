package org.inscraper.interestscraper.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class InterestRateDto {
    private String descripcion;
    private String currentInterest;
    private String conventionalMaximumInterest;
}
