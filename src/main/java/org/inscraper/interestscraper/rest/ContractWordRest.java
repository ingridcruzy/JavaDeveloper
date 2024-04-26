package org.inscraper.interestscraper.rest;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.inscraper.interestscraper.dto.AttachmentData;
import org.inscraper.interestscraper.dto.DataContratWord;
import org.inscraper.interestscraper.service.ContractWordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.OutputStream;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Slf4j
public class ContractWordRest {

    private final ContractWordService contractWordService;

    @PostMapping(value = "/generate-contract-word", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<?> generateContract(@RequestBody DataContratWord request, HttpServletResponse response) {
        try {
            log.info("Datos del trabajador recibidos: " + request);
            AttachmentData attachmentData = contractWordService.generateDocWord(request);
            attachmentData.setDataDownload(response);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(attachmentData.getContent());
            outputStream.flush();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            log.error("[Generate Contract - POST]. Error al generar el contrato. Detalle del error: " + ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
