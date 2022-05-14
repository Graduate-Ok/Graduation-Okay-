package com.graduate_ok.graduate_ok.controller;

import com.graduate_ok.graduate_ok.pdfCheck.PdfCheck;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;

@RestController
public class PdfRestController {

    @PostMapping("/fileUpload")
    public Integer upload(MultipartHttpServletRequest mRequest) {

        try {
            MultipartFile file = mRequest.getFile("file");

            assert file != null;
            File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+file.getOriginalFilename());
            file.transferTo(convFile);

            return PdfCheck.execute(convFile.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
