package org.inscraper.interestscraper.dto;

import jakarta.servlet.http.HttpServletResponse;
import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentData implements Serializable {
    private String name;
    private  byte[] content;
    private String extension;
    private String contentType;

    public void setDataDownload(HttpServletResponse response) {
        response.setContentType(contentType);
        response.setContentLength(content.length);
        response.setHeader("Expires", "0");
        response.setHeader("Content-Disposition", "attachment; filename=" + name);
    }
}
