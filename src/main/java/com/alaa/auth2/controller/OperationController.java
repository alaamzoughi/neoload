package com.alaa.auth2.controller;

import com.alaa.auth2.controller.api.OperationApi;
import com.alaa.auth2.dto.ResponseDto;
import com.alaa.auth2.service.OperationService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.List;

@Api
@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class OperationController implements OperationApi {
    private OperationService operationService ;

    @PostMapping("/generate")
    public ResponseDto generateDoc() {
        try {
            XWPFDocument document = new XWPFDocument();
            FileOutputStream out = new FileOutputStream(new File("/home/alaa/Bureau/lib/test1.docx"));



            XWPFParagraph title = document.createParagraph();

            XWPFRun run_img = title.createRun();
            XWPFRun run_img2 = title.createRun();
            String imgFile = "/home/alaa/Images/adservio.jpg";
            String imgFile2 = "/home/alaa/Images/ratp.png";
            FileInputStream adservio = new FileInputStream(imgFile);
            FileInputStream ratp = new FileInputStream(imgFile2);

            run_img.addPicture(adservio, XWPFDocument.PICTURE_TYPE_JPEG, imgFile, Units.toEMU(150), Units.toEMU(75)); // 200x200 pixel title.setSpacingBetween(5);
            run_img2.addPicture(ratp, XWPFDocument.PICTURE_TYPE_PNG, imgFile, Units.toEMU(75), Units.toEMU(75)); // 200x200 pixel title.setSpacingBetween(5);
            for (int i = 0 ; i<5 ; i++) {
                run_img.addTab() ;
            }
            run_img.addTab() ;
            title.setAlignment(ParagraphAlignment.BOTH);
            title.setSpacingAfterLines(500);


            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setBold(true);
            run.setText("Projet : COPPELIA SEM");
            run.setFontSize(20);
            run.setColor("2E8BC0");
            paragraph.setAlignment(ParagraphAlignment.CENTER);

            paragraph.setBorderTop(Borders.BASIC_BLACK_DASHES);
            paragraph.setSpacingBeforeLines(500);

            paragraph.setSpacingBetween(2.5);
            XWPFParagraph paragraph2 = document.createParagraph();


            XWPFRun run2 = paragraph2.createRun();
            run2.setBold(true);
            run2.setText("Rapport Journalier ");
            run2.setColor("2E8BC0");
            run2.setFontSize(12);
            paragraph2.setAlignment(ParagraphAlignment.CENTER);
            paragraph2.setSpacingBetween(2.5);
            paragraph2.setBorderBottom(Borders.BASIC_BLACK_DASHES);

            XWPFParagraph paragraph3 = document.createParagraph();

            XWPFRun run3 = paragraph3.createRun();
            run3.setBold(true);
            run3.setText("Test Unitaire  ");
            run2.setFontSize(20);

            paragraph3.setAlignment(ParagraphAlignment.CENTER);
            paragraph3.setSpacingBetween(2.5);

            XWPFParagraph paragraph4 = document.createParagraph();

            XWPFRun run4 = paragraph4.createRun();
            run4.setBold(true);
            run4.setFontSize(10);
            run4.setText("Du");
            paragraph4.setAlignment(ParagraphAlignment.CENTER);
            paragraph4.setSpacingBetween(2.5);

            XWPFParagraph paragraph5= document.createParagraph();


            XWPFRun run5 = paragraph5.createRun();
            run.setBold(true);
            run2.setFontSize(20);
            run5.setText("17/04/2018");
            paragraph5.setAlignment(ParagraphAlignment.CENTER);
            paragraph5.setSpacingBetween(2.5);




            //create table
            XWPFTable table = document.createTable();

            //create first row
            XWPFTableRow tableRowOne = table.getRow(0);
            tableRowOne.getCell(0).setText("statut de Test");
            tableRowOne.addNewTableCell().setText("statut de l'application");
            tableRowOne.addNewTableCell().setText("poursuite des tests");

            //create second row

            //insert a valid logo in the first column
            XWPFTableRow tableRowTwo = table.createRow();
            XWPFParagraph paragraph_images = tableRowTwo.getCell(0).addParagraph();
            XWPFRun run_images = paragraph_images.createRun();
            FileInputStream fis = new FileInputStream("/home/alaa/Images/valid.png");
            XWPFPicture picture = run_images.addPicture(fis, XWPFDocument.PICTURE_TYPE_PNG, "/home/alaa/Images/valid.png", Units.pixelToEMU(150), Units.pixelToEMU(100));

            //insert a valid logo in the second column
            XWPFParagraph paragraph_images2 = tableRowTwo.getCell(1).addParagraph();
            XWPFRun run_images2 = paragraph_images2.createRun();
            FileInputStream fis2 = new FileInputStream("/home/alaa/Images/valid.png");
            XWPFPicture picture2 = run_images2.addPicture(fis2, XWPFDocument.PICTURE_TYPE_PNG, "/home/alaa/Images/valid.png", Units.pixelToEMU(150), Units.pixelToEMU(100));

            //insert a valid logo in the thrid column

            XWPFParagraph paragraph_images3 = tableRowTwo.getCell(2).addParagraph();
            XWPFRun run_images3 = paragraph_images3.createRun();
            FileInputStream fis3 = new FileInputStream("/home/alaa/Images/valid.png");
            XWPFPicture picture3 = run_images3.addPicture(fis3, XWPFDocument.PICTURE_TYPE_PNG, "/home/alaa/Images/valid.png", Units.pixelToEMU(150), Units.pixelToEMU(100));



            //align the cases of the table in the Center
            tableRowOne.getCell(0).getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
            tableRowOne.getCell(1).getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
            tableRowOne.getCell(2).getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);



            //Color the first row of the table
                List<XWPFTableRow> rows = table.getRows();
                int rowCt = 0;
                int colCt = 0;
                int nRows = 3;
                int nCols = 3;
                for (XWPFTableRow row : rows) {
                    // get table row properties (trPr)
                    CTTrPr trPr = row.getCtRow().addNewTrPr();
                    // set row height; units = twentieth of a point, 360 = 0.25"
                    CTHeight ht = trPr.addNewTrHeight();
                    ht.setVal(BigInteger.valueOf(360));
                    // get the cells in this row
                    List<XWPFTableCell> cells = row.getTableCells();
                    // add content to each cell
                    for (XWPFTableCell cell : cells) {
                        // get a table cell properties element (tcPr)
                        CTTcPr tcpr = cell.getCTTc().addNewTcPr();
                        // set vertical alignment to "center"
                        CTVerticalJc va = tcpr.addNewVAlign();
                        va.setVal(STVerticalJc.CENTER);
                        // create cell color element
                        CTShd ctshd = tcpr.addNewShd();
                        ctshd.setColor("auto");
                        ctshd.setVal(STShd.CLEAR);
                        if (rowCt == 0) {
                            // header row
                            ctshd.setFill("A7BFDE");
                        }
                        colCt++;
                    }
                    // for cell
                    colCt = 0;
                    rowCt++;
                }

                // add Some spaces between tables
            XWPFParagraph paragraph6= document.createParagraph();


            XWPFRun run6 = paragraph6.createRun();
            paragraph6.setSpacingAfterLines(500);


                // Create the table of the consultant who did the tests

            //create table
            XWPFTable table_consultants = document.createTable();

            //create first row
            XWPFTableRow table_consultant_RowOne = table_consultants.getRow(0);
            table_consultant_RowOne.getCell(0).setText("Nom consultant");
            table_consultant_RowOne.addNewTableCell().setText("Poste consultant");
            table_consultant_RowOne.addNewTableCell().setText("Email RATP Consultant ");
            table_consultant_RowOne.addNewTableCell().setText("Ligne directe RATP ");

            //create second row
            XWPFTableRow tableRowTwo_consultant = table_consultants.createRow();
            tableRowTwo_consultant.getCell(0).setText("Abir BOUCHNAK");
            tableRowTwo_consultant.getCell(1).setText("Ingénieur Performance");
            tableRowTwo_consultant.getCell(2).setText("Abir.bouchenak@adservio.f");
            tableRowTwo_consultant.getCell(3).setText("01 587 79368");

            //create third row
            XWPFTableRow tableRowThree_consultant = table_consultants.createRow();
            tableRowThree_consultant.getCell(0).setText("Tanguy LAPEYRE");
            tableRowThree_consultant.getCell(1).setText("Consultant Performance");
            tableRowThree_consultant.getCell(2).setText("Tanguy.lapereye@adservio.fr");
            tableRowThree_consultant.getCell(3).setText("01 587 79368");



            // Adding a footer

            CTP ctp = CTP.Factory.newInstance();
            CTR ctr = ctp.addNewR();
            CTRPr rpr = ctr.addNewRPr();
            CTText textt = ctr.addNewT();
            textt.setStringValue( " Page 1" );
            XWPFParagraph codePara = new XWPFParagraph( ctp, document );
            codePara.setBorderBottom(Borders.BASIC_BLACK_DASHES);
            codePara.setAlignment(ParagraphAlignment.RIGHT);
            XWPFParagraph[] newparagraphs = new XWPFParagraph[1];
            newparagraphs[0] = codePara;
            CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
            XWPFHeaderFooterPolicy headerFooterPolicy = new  XWPFHeaderFooterPolicy( document, sectPr );
            headerFooterPolicy.createFooter( STHdrFtr.DEFAULT, newparagraphs );


            adservio.close();
            document.write(out);

            out.close();
            System.out.println("createparagraph.docx written successfully");

        } catch (Exception e) {
            // TODO: handle exception
            return  ResponseDto.builder().message(e.getMessage()).build() ;
        }
        return  ResponseDto.builder().message("success").build() ;
    }
    }


