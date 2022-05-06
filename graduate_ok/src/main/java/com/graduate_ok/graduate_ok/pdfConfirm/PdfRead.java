package com.graduate_ok.graduate_ok.pdfConfirm;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PdfRead {

    public static void main(String[] args) throws IOException {
        String fileName = "C:/Users/수빈/Desktop/um72_0272003_r01.pdf";
        File source = new File(fileName);
        PDDocument pdfDoc = PDDocument.load(source);
        List<String> pdf2 = new ArrayList<>();
        String text = new PDFTextStripper().getText(pdfDoc);
        pdf2.add(text);
        System.out.println(text);

        for (String str : pdf2) {
            if (str.startsWith("총 취득학점")) {
                System.out.println(str);
                String[] str2 = str.split(" ");
                int total = Integer.parseInt(str2[2]);
                System.out.println("total : " + total);
            }
        }
    }
}
