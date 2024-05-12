package org.inscraper.interestscraper.rest;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.inscraper.interestscraper.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class EmployeeRest {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<?> listAll() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/genrate-pdf")
    public ResponseEntity<byte[]> exportPdf() throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("Ejemplo", "primerPdf.pdf");
        return ResponseEntity.ok().headers(headers).body(employeeService.exportPDF());
    }
}
