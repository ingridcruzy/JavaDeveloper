package org.inscraper.interestscraper.service.impl;

import net.sf.jasperreports.engine.JRException;
import org.inscraper.interestscraper.dto.AttachmentData;
import org.inscraper.interestscraper.entity.EstadoCuenta;
import org.inscraper.interestscraper.service.EstadoCuentaService;
import org.inscraper.interestscraper.util.EstadoCuentaReportGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EstadoCuentaServiceImpl implements EstadoCuentaService {

    @Autowired
    private EstadoCuentaReportGenerator estadoCuentaReportGenerator;
    @Override
    public AttachmentData generarPDF(EstadoCuenta estadoCuenta) throws IOException, JRException {
        byte pdfBytes[] = estadoCuentaReportGenerator.exportToPdf( estadoCuenta );

        return AttachmentData.builder()
                .content(pdfBytes)
                .contentType("application/octet-stream")
                .name("EstadoCuenta.pdf")
                .extension("pdf")
                .build();
    }
}
