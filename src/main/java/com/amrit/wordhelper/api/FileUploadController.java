package com.amrit.wordhelper.api;


import com.amrit.wordhelper.dto.ResponseMessage;
import com.amrit.wordhelper.service.FileProcessService;
import com.amrit.wordhelper.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequiredArgsConstructor
@Controller
public class FileUploadController {

    private final FileStorageService fileStorageService;
    private final FileProcessService fileProcessService;


    @PostMapping("/upload")
    public ModelAndView uploadFile(@RequestParam("file") MultipartFile file, ModelMap model) {
        try {
            fileStorageService.save(file);
            fileProcessService.process(file);
            model.addAttribute("success", true);
            return new ModelAndView("redirect:/home", model);

        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("success", false);
            return new ModelAndView("redirect:/home", model);
        }
    }

}
