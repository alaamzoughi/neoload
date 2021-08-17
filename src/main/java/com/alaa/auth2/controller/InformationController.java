package com.alaa.auth2.controller;

import com.alaa.auth2.controller.api.OperationApi;
import com.alaa.auth2.dto.InformationDto;
import com.alaa.auth2.dto.ResponseDto;
import com.alaa.auth2.handlers.ErrorDto;
import com.alaa.auth2.service.OperationService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;


@Api
@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
@Slf4j
public class InformationController implements OperationApi {
    private OperationService operationService ;

    @PostMapping(value = "/generate/doc", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseDto generateDoc(@RequestBody  @Valid  InformationDto informationDto) {
        try {
            XWPFDocument document = new XWPFDocument();
            FileOutputStream out = new FileOutputStream(new File("src/main/resources/files/test.docx"));
            XWPFParagraph title = document.createParagraph();
            String imgFile = "src/main/resources/images/adservio.jpg";
            //TODO rendre dynamique
            String imgFile2 = "/home/alaa/Images/ratp.png";

            //creation du deux photos
            XWPFRun run_img = title.createRun();
            XWPFRun run_img2 = title.createRun();
            FileInputStream adservio = new FileInputStream(imgFile);
            FileInputStream logo_client = new FileInputStream(imgFile2);

            run_img.addPicture(adservio, XWPFDocument.PICTURE_TYPE_JPEG, imgFile, Units.toEMU(150), Units.toEMU(75)); // 200x200 pixel title.setSpacingBetween(5);
            run_img2.addPicture(logo_client, XWPFDocument.PICTURE_TYPE_PNG, imgFile, Units.toEMU(75), Units.toEMU(75)); // 200x200 pixel title.setSpacingBetween(5);
            //Ajout de l'espace entre le logo d adservio et le logo client et les aligner
            for (int i = 0 ; i<6 ; i++) {
                run_img.addTab() ;
            }
            title.setAlignment(ParagraphAlignment.BOTH);
            title.setSpacingAfterLines(500);

            //Creation du rubrique nom du projet
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setBold(true);
            run.setText("Projet : " + informationDto.getNom_projet());
            run.setFontSize(20);
            run.setColor("2E8BC0");
            paragraph.setAlignment(ParagraphAlignment.CENTER);
            paragraph.setBorderTop(Borders.BASIC_BLACK_DASHES);
            paragraph.setSpacingBeforeLines(500);

            //creation du rubrique rapport journalier
            XWPFParagraph paragraph2 = document.createParagraph();


            XWPFRun run2 = paragraph2.createRun();
            run2.setBold(true);
            run2.setText("Rapport Journalier ");
            run2.setColor("2E8BC0");
            run2.setFontSize(12);
            paragraph2.setAlignment(ParagraphAlignment.CENTER);
            paragraph2.setSpacingBetween(2.5);
            paragraph2.setBorderBottom(Borders.BASIC_BLACK_DASHES);

            // creation du rubrique rapport des tests
            XWPFParagraph paragraph3 = document.createParagraph();

            XWPFRun run3 = paragraph3.createRun();
            run3.setBold(true);
            run3.setText("Test Unitaire  ");
            run2.setFontSize(20);

            paragraph3.setAlignment(ParagraphAlignment.CENTER);
            paragraph3.setSpacingBetween(2.5);

            // creation du rubrique du
            XWPFParagraph paragraph4 = document.createParagraph();

            XWPFRun run4 = paragraph4.createRun();
            run4.setBold(true);
            run4.setFontSize(10);
            run4.setText("Du");
            paragraph4.setAlignment(ParagraphAlignment.CENTER);
            paragraph4.setSpacingBetween(2.5);


            // creation du rubrique date du test
            XWPFParagraph paragraph5= document.createParagraph();


            XWPFRun run5 = paragraph5.createRun();
            run.setBold(true);
            run2.setFontSize(20);
            run5.setText(informationDto.getDate_test());
            paragraph5.setAlignment(ParagraphAlignment.CENTER);
            paragraph5.setSpacingBetween(2.5);


            // creation du tableau de l'etat du test

            //create table
            XWPFTable table = document.createTable();

            //create first row
            XWPFTableRow tableRowOne = table.getRow(0);
            tableRowOne.getCell(0).setText("statut de Test");
            tableRowOne.addNewTableCell().setText("statut de l'application");
            tableRowOne.addNewTableCell().setText("poursuite des tests");

            //create the second Row

            XWPFTableRow tableRowTwo = table.createRow();


            for (int i=0 ; i<3 ; i++) {
                XWPFParagraph paragraph_images = tableRowTwo.getCell(i).addParagraph();
                XWPFRun run_text = paragraph_images.createRun();
                XWPFRun run_images = paragraph_images.createRun();
                paragraph_images.setAlignment(ParagraphAlignment.BOTH);
                run_text.addTab();
                FileInputStream fis = new FileInputStream("src/main/resources/images/valid.png");
                XWPFPicture picture = run_images.addPicture(fis, XWPFDocument.PICTURE_TYPE_PNG, "src/main/resources/images/valid.png", Units.pixelToEMU(75), Units.pixelToEMU(75));
                tableRowOne.getCell(i).getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);

            }
            // add borders to table
            table.setInsideHBorder(XWPFTable.XWPFBorderType.SINGLE, 8, 0, "2E8BC0");
            table.setInsideVBorder(XWPFTable.XWPFBorderType.SINGLE , 8, 0, "2E8BC0");
            table.setTopBorder(XWPFTable.XWPFBorderType.SINGLE, 8, 0, "2E8BC0");
            table.setBottomBorder(XWPFTable.XWPFBorderType.SINGLE, 8, 0, "2E8BC0");
            table.setLeftBorder(XWPFTable.XWPFBorderType.SINGLE, 8, 0, "2E8BC0");
            table.setRightBorder(XWPFTable.XWPFBorderType.SINGLE, 8, 0, "2E8BC0");


            // add Some spaces between tables
            XWPFParagraph paragraph6= document.createParagraph();
            XWPFRun run6 = paragraph6.createRun();
            paragraph6.setSpacingAfterLines(500);


            // Create the table of the consultant who did the tests
            XWPFTable table_consultants = document.createTable();
            //create first row
            XWPFTableRow table_consultant_RowOne = table_consultants.getRow(0);
            table_consultant_RowOne.getCell(0).setText("Nom consultant");
            table_consultant_RowOne.addNewTableCell().setText("Poste consultant");
            table_consultant_RowOne.addNewTableCell().setText("Email RATP Consultant ");
            table_consultant_RowOne.addNewTableCell().setText("Ligne directe RATP ");
            //align first row
            for (int i = 0 ; i<3 ; i++ ) {
                table_consultant_RowOne.getCell(i).getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
            }


            for (int i =0 ; i<informationDto.getNombre_consultants() ; i++) {
                XWPFTableRow tableRow_consultant = table_consultants.createRow();
                int j =0 ;
                tableRow_consultant.getCell(j).setText(informationDto.getConsultants().get(i).getNom()) ;
                tableRow_consultant.getCell(j+1).setText(informationDto.getConsultants().get(i).getPoste());
                tableRow_consultant.getCell(j+2).setText(informationDto.getConsultants().get(i).getEmail_RATP());
                tableRow_consultant.getCell(j+3).setText(informationDto.getConsultants().get(i).getLigne_directe_RATP().toString());
                for (int k = 0 ; k<4 ; k++ ) {
                    tableRow_consultant.getCell(k).getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
                }

            }

            // Setting border for the consultants table
            table_consultants.setInsideHBorder(XWPFTable.XWPFBorderType.SINGLE, 8, 0, "2E8BC0");
            table_consultants.setInsideVBorder(XWPFTable.XWPFBorderType.SINGLE, 8, 0, "2E8BC0");
            table_consultants.setTopBorder(XWPFTable.XWPFBorderType.SINGLE, 8, 0, "2E8BC0");
            table_consultants.setBottomBorder(XWPFTable.XWPFBorderType.SINGLE, 8, 0, "2E8BC0");
            table_consultants.setLeftBorder(XWPFTable.XWPFBorderType.SINGLE, 8, 0, "2E8BC0");
            table_consultants.setRightBorder(XWPFTable.XWPFBorderType.SINGLE, 8, 0, "2E8BC0");


            //Create a footer
            // create header-footer
            XWPFHeaderFooterPolicy headerFooterPolicy = document.getHeaderFooterPolicy();
            if (headerFooterPolicy == null) headerFooterPolicy = document.createHeaderFooterPolicy();
            XWPFFooter footer = headerFooterPolicy.createFooter(XWPFHeaderFooterPolicy.DEFAULT);

            XWPFParagraph f =footer.createParagraph() ;
            XWPFRun run_footer_img = f.createRun() ;
            /**
             String imgFile_adservio = "/home/alaa/Images/adservio.jpg";
             FileInputStream ad = new FileInputStream(imgFile_adservio);

             run_footer_img.addPicture(ad, XWPFDocument.PICTURE_TYPE_JPEG, imgFile_adservio, Units.toEMU(75), Units.toEMU(75)); // 200x200 pixel title.setSpacingBetwee

             **/

            XWPFRun run_footer_pagename = f.createRun();
            run_footer_pagename.setText(" Page ");
            f.getCTP().addNewFldSimple().setInstr("PAGE \\* MERGEFORMAT");
            XWPFRun run_footer_pageNumebr = f.createRun();
            run_footer_pageNumebr.setText(" of ");
            f.getCTP().addNewFldSimple().setInstr("NUMPAGES \\* MERGEFORMAT");
            f.setAlignment(ParagraphAlignment.RIGHT);
            run_footer_pageNumebr.setColor("2E8BC0");
            run_footer_pagename.setColor("2E8BC0");
            run_footer_pageNumebr.setBold(true);
            run_footer_pagename.setBold(true);























            adservio.close();
            document.write(out);

            out.close();








        } catch (Exception e) {
            // TODO: handle exception
            return  ResponseDto.builder().message(e.getMessage()).build() ;
        }
        return  ResponseDto.builder().message("success").build() ;
    }































    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex)   {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        final HttpStatus NOTVALIDARGUMENT = HttpStatus.BAD_REQUEST;
        final ErrorDto errorDto = ErrorDto.builder()
                .httpCode(NOTVALIDARGUMENT.value())
                .validationErrors(errors)
                .build();

        return new ResponseEntity<>(errorDto, NOTVALIDARGUMENT);
    }
}







