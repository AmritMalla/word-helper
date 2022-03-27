package com.amrit.wordhelper.service.impl;

import com.amrit.wordhelper.service.FileProcessService;
import com.amrit.wordhelper.service.MongoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileProcessServiceImpl implements FileProcessService {

    private static final String CSV_EXTENSION = "csv";

    private static final String XLSX_EXTENSION = "xlsx";

    public static final String XLS_EXTENSION = "xls";

    private final Path root = Paths.get("uploads");

    private final MongoService mongoService;

    @Override
    public void process(MultipartFile multipartFile) {
        String filename = multipartFile.getOriginalFilename();
        String extension = FilenameUtils.getExtension(filename);
        if (extension == null) {
            throw new RuntimeException("Invalid File Name");
        }
        if (extension.toLowerCase().equals(CSV_EXTENSION)) {
            processCSVFile(filename);
        } else if (extension.toLowerCase().equals(XLSX_EXTENSION)) {
            processExcelFile(filename);
        } else {
            throw new RuntimeException("Invalid File extension");
        }

    }

    public void processCSVFile(String filename) {
        try {
            FileInputStream inputStream = new FileInputStream(new File(this.root + File.pathSeparator + filename));

        } catch (FileNotFoundException e) {

        }

    }

    public void processExcelFile(String filename) {
        try {
            FileInputStream inputStream = new FileInputStream(new File(this.root + File.separator + filename));
            Set<String> words = new HashSet<>();
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(0);
            int i = 0;
            while (row != null){
                String stringCellValue = row.getCell(0).getStringCellValue();
                if(stringCellValue.length()> 0){
                    words.add(stringCellValue.toLowerCase().trim());
                }
                i++;
                row = sheet.getRow(i);
            }
            log.info("Words :: {}", words);
            mongoService.saveAll(words);
        } catch (Exception e) {
            log.error("Error occurred while reading the excel sheet :: {}", e.getMessage());
        }

    }
}
