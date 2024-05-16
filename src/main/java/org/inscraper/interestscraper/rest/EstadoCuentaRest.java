package org.inscraper.interestscraper.rest;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.inscraper.interestscraper.dto.AttachmentData;
import org.inscraper.interestscraper.entity.EstadoCuenta;
import org.inscraper.interestscraper.service.EstadoCuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.OutputStream;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Slf4j
public class EstadoCuentaRest {

    @Autowired
    private EstadoCuentaService estadoCuentaService;

    @PostMapping(value = "/generar-estado-cuenta-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> generateEstadoCuentaPDF(@RequestBody EstadoCuenta request, HttpServletResponse response) {
        try {
            log.info("Datos del trabajador recibidos: " + request);
            AttachmentData attachmentData = estadoCuentaService.generarPDF(request);
            attachmentData.setDataDownload(response);
            OutputStream outStream = response.getOutputStream();
            outStream.write(attachmentData.getContent());
            outStream.flush();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            log.error("[Generate Contract - POST]. Error al generar el estado de cuenta. " +
                    "Detalle del error: " + ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
