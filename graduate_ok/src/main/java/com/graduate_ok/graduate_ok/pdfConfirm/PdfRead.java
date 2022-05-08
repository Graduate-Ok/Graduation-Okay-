package com.graduate_ok.graduate_ok.pdfConfirm;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PdfRead {
    // pdf 읽기
    public static String[] PDFRead(String fileName) throws IOException {

        File source = new File(fileName);
        PDDocument pdfDoc = PDDocument.load(source);
        String text = new PDFTextStripper().getText(pdfDoc);
        String[] pdf = text.split("\n");

        return pdf;
    }
}
