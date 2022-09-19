package com.graduate_ok.graduate_ok.controller;

import com.graduate_ok.graduate_ok.pdfCheck.PdfCheck;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.tika.Tika;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;

@RestController
@CrossOrigin("*")
public class PdfRestController {

    @PostMapping("/Graduate")
    @ApiOperation(value = "PDF 관련 로직 API", notes = "관련 PDF 검사 관련 로직")
    @ApiParam("관련 PDF (중요)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok"),
            @ApiResponse(code = 500, message = "server error"),
            @ApiResponse(code = 404, message = "not found")
    })
    public HashMap<String, Object> upload(MultipartHttpServletRequest mRequest) {

        try {
            MultipartFile file = mRequest.getFile("file");
            assert file != null;
            InputStream is = file.getInputStream();
            String mimeType = new Tika().detect(is);

            if (!Objects.requireNonNull(file.getOriginalFilename()).toLowerCase(Locale.ROOT).endsWith(".pdf") || !isPdfMimeType(mimeType)) {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("result", -1);
                return hashMap;
            }

            File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
            file.transferTo(convFile);


            return PdfCheck.execute(convFile.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("result", -1);
            return hashMap;
        }
    }

    private boolean isPdfMimeType(String mimeType) {
        if (mimeType == null || mimeType.equals("")) {
            return false;
        }
        return mimeType.equals("application/pdf");
    }
}
