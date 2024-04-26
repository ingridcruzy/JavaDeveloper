package org.inscraper.interestscraper.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.inscraper.interestscraper.dto.AttachmentData;
import org.inscraper.interestscraper.dto.DataContratWord;
import org.inscraper.interestscraper.entity.ContractWord;
import org.inscraper.interestscraper.repository.ContractWordRepository;
import org.inscraper.interestscraper.service.ContractWordService;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;



@Service
@RequiredArgsConstructor
@Log4j2
public class ContractWordServiceImpl implements ContractWordService {

    private final ContractWordRepository contractWordRepository;
    @Override
    public AttachmentData generateDocWord(DataContratWord contrato) throws IOException {
        ContractWord contractDocente = contractWordRepository.findContratoByTypeContract("c_docente");
        String titulo = contractDocente.getTitulo();
        String contratoPartes = contractDocente.getContrato();
        String clausulaPrimera = contractDocente.getClausula();

        XWPFDocument document = new XWPFDocument();

        String paragraphTitulo = titulo.replace("{TITULO}", contrato.getTitulo());

        String paragraphContratoPartes = contratoPartes
                .replace("{INTITUCION}", contrato.getInstitucion())
                .replace("{DOMICILIO_DIRECTOR}", contrato.getDomicilioDirector())
                .replace("{NOMBRE_DIRECTOR}", contrato.getNombreDirector())
                .replace("{DNI_DIRECTOR}", contrato.getDniDirector())
                .replace("{NUMERO_RESOLUCION}", contrato.getNumeroResolucion())
                .replace("{NOMBRE_DOCENTE}", contrato.getNombreDocente())
                .replace("{DNI_DOCENTE}", contrato.getDniDocente())
                .replace("{DOMICILIO_DOCENTE}", contrato.getDomicilioDocente())
                .replace("{CORREO_DOCENTE}", contrato.getCorreoDocente());

        String paragraphClausulaPrimera = clausulaPrimera
                .replace("{NOMBRE_DOCENTE}", contrato.getNombreDocente());

        createTitleParagraph(document, paragraphTitulo);
        createParagraph(document, paragraphContratoPartes, ParagraphAlignment.BOTH);
        createParagraph(document, paragraphClausulaPrimera, ParagraphAlignment.BOTH);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        document.write( outputStream );

        log.info("Generación de archivo Word");

        return AttachmentData.builder()
                .content( outputStream.toByteArray() )
                .contentType( "application/vnd.openxmlformats-officedocument.wordprocessingml.document" )
                .name( "Contract.docx" )
                .extension( "docx" )
                .build();
    }

    private void createParagraph(XWPFDocument document, String text, ParagraphAlignment alignment) {
        createParagraph(document, text, alignment, false);
    }

    private void createTitleParagraph(XWPFDocument document, String titleText) {
        XWPFParagraph title = document.createParagraph();
        title.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleRun = title.createRun();
        titleRun.setText(titleText);
        titleRun.setColor("1C1B1A");
        titleRun.setBold(true);
        titleRun.setFontFamily("Arial");
        titleRun.setFontSize(20);
    }

    private void createParagraph(XWPFDocument document, String text, ParagraphAlignment alignment, boolean italic) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(alignment);

        XWPFRun paragraphRun = paragraph.createRun();
        paragraphRun.setText(text);
        if (italic) {
            paragraphRun.setItalic(true);
        }
    }
}
