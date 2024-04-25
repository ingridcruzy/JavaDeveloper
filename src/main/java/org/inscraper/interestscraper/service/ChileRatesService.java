package org.inscraper.interestscraper.service;

import org.inscraper.interestscraper.dto.InterestRatesResponseDto;

public interface ChileRatesService {
    InterestRatesResponseDto retrieveLatestInterestRates();
}
