package org.inscraper.interestscraper.util;

import net.sf.jasperreports.engine.*;
import org.inscraper.interestscraper.entity.EstadoCuenta;
import org.inscraper.interestscraper.entity.EstadoCuentaDetalle;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EstadoCuentaReportGenerator {
    public byte[] exportToPdf(EstadoCuenta estadoCuenta) throws JRException, FileNotFoundException {
        return JasperExportManager.exportReportToPdf( getReport (estadoCuenta));
    }

    private JasperPrint getReport(EstadoCuenta estadoCuenta) throws FileNotFoundException, JRException {
        List<Map<String, Object>> pagadosDataSet = buildDataSet (estadoCuenta.getPagados());
        List<Map<String, Object>> pendientesDataSet = buildDataSet (estadoCuenta.getPendientes());

        Map<String, Object> params = new HashMap<>();
        params.put("COMPANY_IDENTIFICATION", estadoCuenta.getCompanyIdentification());
        params.put("CLIENT_IDENTIFICATION", estadoCuenta.getClientIdentification());
        params.put("COMPANY_NAME", estadoCuenta.getCompanyName());
        params.put("CLIENT_NAME",estadoCuenta.getClientName());
        params.put("PAGADOS", pagadosDataSet );
        params.put("PENDIENTES", pendientesDataSet );

        JasperPrint report = JasperFillManager.fillReport(JasperCompileManager.compileReport(
                ResourceUtils.getFile("classpath:EstadoCuenta.jrxml")
                        .getAbsolutePath()), params, new JREmptyDataSource());
        return report;
    }

    private List<Map<String, Object>> buildDataSet(List<EstadoCuentaDetalle> list) {
        return list.stream()
                .map( item -> {

                    Map<String, Object> itemParameter = new HashMap<>();
                    itemParameter.put( "SUMINISTRO", item.getSuministro() );
                    itemParameter.put( "DOCUMENTO", item.getDocumento() );
                    itemParameter.put( "FECHA_EMISION", item.getFechaEmision() );
                    itemParameter.put( "FECHA_VENCIMIENTO", item.getFechaVencimiento() );
                    itemParameter.put( "DIAS_VENCIDOS", item.getDiasVencidos() );
                    itemParameter.put( "MONEDA", item.getMoneda() );
                    itemParameter.put( "MONTO_PENDIENTE", item.getMontoPendiente() );
                    itemParameter.put( "DESCRIPCION_SERVICIO", item.getDescripcionServicio() );
                    itemParameter.put( "MONTO_DOLARIZADO", item.getMontoDolarizado() );
                    itemParameter.put( "EJECUTIVO_COMERCIAL", item.getEjecutivoComercial() );
                    itemParameter.put( "FECHA_CAUSAL", item.getFechaCausal() );

                    return itemParameter;


                } ).toList();

    }
}
