package org.inscraper.interestscraper.rest;

import lombok.RequiredArgsConstructor;
import org.inscraper.interestscraper.dto.InterestRatesResponseDto;
import org.inscraper.interestscraper.service.ChileRatesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/interest-chile")
public class ChileInterestRatesRest {

    private final ChileRatesService chileRatesService;

    @GetMapping(value = "/fetch-latest")
    public ResponseEntity<InterestRatesResponseDto> fetchLatestChileanMarketInterestRates() {
        try {
            InterestRatesResponseDto response = chileRatesService.retrieveLatestInterestRates();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
