package com.amrit.wordhelper.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileProcessService {

    void process(MultipartFile multipartFile);
}
