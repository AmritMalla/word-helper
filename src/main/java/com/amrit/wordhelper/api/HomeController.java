package com.amrit.wordhelper.api;

import com.amrit.wordhelper.config.MediaTypeUtils;
import com.amrit.wordhelper.service.PdfGenerationService;
import com.amrit.wordhelper.service.WebScrappingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {

    private final WebScrappingService webScrappingService;

    private final PdfGenerationService pdfGenerationService;

    private final ServletContext servletContext;


    @GetMapping("/")
    public String defaultController(ModelMap modelMap){
        return "home";
    }

    @GetMapping("/home")
    public String homeController(ModelMap modelMap){
        return "home";
    }

    @GetMapping("/table")
    public String register(ModelMap modelMap){
        return "table";
    }

    @GetMapping("/get-meaning")
    public String webScrap(ModelMap modelMap){
        webScrappingService.getTheMeaning();

        return "table";
    }

    @GetMapping("/get-pdf")
    public ResponseEntity<InputStreamResource> generatePdf(ModelMap model) throws FileNotFoundException {
        pdfGenerationService.generateDocx();
        pdfGenerationService.generatePdf();
        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, "meaning.pdf");

        File file = new File("pdf/meaning.pdf");
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename="+file.getName())
                .contentType(mediaType)
                .contentLength(file.length())
                .body(resource);
    }

}
