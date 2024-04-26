package org.inscraper.interestscraper.service;

import org.inscraper.interestscraper.dto.AttachmentData;
import org.inscraper.interestscraper.dto.DataContratWord;

import java.io.IOException;

public interface ContractWordService {
    AttachmentData generateDocWord(DataContratWord contrato) throws IOException;
}
