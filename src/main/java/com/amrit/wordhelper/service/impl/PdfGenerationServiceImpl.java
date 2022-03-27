package com.amrit.wordhelper.service.impl;

import com.amrit.wordhelper.domain.WordWithMeaning;
import com.amrit.wordhelper.repository.WordRepository;
import com.amrit.wordhelper.service.PdfGenerationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.docx4j.Docx4J;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.swing.text.Document;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PdfGenerationServiceImpl implements PdfGenerationService {

    private final Path pdf = Paths.get("pdf");

    private final WordRepository wordRepository;

    @Override
    public void generatePdf() {
        try {
            File file = new File("pdf/meaning.docx");
            File pdfFile = new File("pdf/meaning.pdf");
            InputStream stream = new FileInputStream(file);
            WordprocessingMLPackage mlPackage = WordprocessingMLPackage.load(stream);
            MainDocumentPart documentPart = mlPackage.getMainDocumentPart();

            FileOutputStream outputStream = new FileOutputStream(pdfFile);
            Docx4J.toPDF(mlPackage, outputStream);
            outputStream.flush();
            outputStream.close();
            log.info("Pdf conversion completed");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void generateDocx() {

        try {
            log.info("Generating PDF Started ");
            List<WordWithMeaning> all = wordRepository.findAllByMeaningTextNotNull();
            XWPFDocument document = new XWPFDocument();
            File file = new File("pdf/meaning.docx");
            FileOutputStream out = new FileOutputStream(file);
            XWPFParagraph paragraph = document.createParagraph();

            int i = 1;
            for (WordWithMeaning meaning : all) {
                XWPFRun paragraphRun = paragraph.createRun();
                paragraphRun.setBold(true);
                paragraphRun.setText(i + ". " + StringUtils.capitalize(meaning.getId()));
                i++;
                paragraphRun.addCarriageReturn();
                paragraphRun = paragraph.createRun();
                String[] stringList = meaning.getMeaningText().split("\n");
                for (int j = 0; j < stringList.length; j++) {
                    String s = stringList[j];
                    if (j == 0 && s.equals("Approving")) continue;
                    paragraphRun.setText(StringUtils.capitalize(s.replaceAll("(\\p{Ll})(\\p{Lu})", "$1: $2")));
                    paragraphRun.addCarriageReturn();
                }
                paragraphRun.addCarriageReturn();
            }
            document.write(out);
            out.close();
            log.info("Generating PDF completed");
        } catch (IOException e) {
            log.error(e.getMessage());
        }

    }
}
