package org.inscraper.interestscraper.service;

import net.sf.jasperreports.engine.JRException;
import org.inscraper.interestscraper.dto.AttachmentData;
import org.inscraper.interestscraper.entity.EstadoCuenta;

import java.io.IOException;

public interface EstadoCuentaService {

    AttachmentData generarPDF(EstadoCuenta estadoCuenta) throws IOException, JRException;
}
