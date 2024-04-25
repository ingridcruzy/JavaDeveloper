package org.inscraper.interestscraper.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Data
public class InterestRatesResponseDto {
    Map<String, List<InterestRateDto>> listCategories;
}
