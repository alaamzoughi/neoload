package com.alaa.auth2.service.implementation;

import com.alaa.auth2.dto.InformationDto;
import com.alaa.auth2.dto.ResponseDto;
import com.alaa.auth2.fileManagement.FileStorageService;
import com.alaa.auth2.model.Operation;
import com.alaa.auth2.model.TransformedFile;
import com.alaa.auth2.model.UploadedOrginalFile;
import com.alaa.auth2.model.User;
import com.alaa.auth2.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class InformationServiceImpl  implements InformationService {
    @Autowired
    private OperationService operationService ;
    @Autowired
    private FileStorageService fileStorageService ;
    @Autowired
    private UploadedOrginalFileService uploadedOrginalFileService ;
    @Autowired
    private TransformedFileService transformedFileService ;
    @Autowired
    private UserService userService ;

    @Override
    public ResponseDto generateDoc(MultipartFile file, MultipartFile image, String s) {
        try {

            // upload du document original dans la base de données

            String fileName = fileStorageService.storeFile(file);

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/downloadFile/")
                    .path(fileName)
                    .toUriString();


            UploadedOrginalFile document_original = UploadedOrginalFile.builder()
                    .fileDownloadUri(fileDownloadUri)
                    .size(file.getSize())
                    .fileType(file.getContentType())
                    .fileName(fileName)
                    .build() ;

            UploadedOrginalFile d = uploadedOrginalFileService.save(document_original) ;
            ObjectMapper om = new ObjectMapper() ;
            InformationDto informationDto = null ;
            informationDto = om.readValue(s,InformationDto.class) ;
            XWPFDocument document = new XWPFDocument();
            int random_int = (int)Math.floor(Math.random());
            String modifiedFileName = informationDto.getNom_projet() +"_"+informationDto.getNom_client()+random_int+".docx" ;
            FileOutputStream out = new FileOutputStream(new File("src/main/resources/files/"+modifiedFileName));





            // creation du document modifié
            XWPFParagraph title = document.createParagraph();
            String imgFile = "src/main/resources/images/adservio.jpg";
            //TODO rendre dynamique
            String imgFile2 = "/home/alaa/Images/ratp.png";

            //creation du deux photos
            XWPFRun run_img = title.createRun();
            XWPFRun run_img2 = title.createRun();
            FileInputStream adservio = new FileInputStream(imgFile);

            //FileInputStream logo_client = new FileInputStream(imgFile2);
            InputStream logo_client = image.getInputStream();


            run_img.addPicture(adservio, XWPFDocument.PICTURE_TYPE_JPEG, imgFile, Units.toEMU(150), Units.toEMU(75)); // 200x200 pixel title.setSpacingBetween(5);
            run_img2.addPicture(logo_client, XWPFDocument.PICTURE_TYPE_PNG, null, Units.toEMU(75), Units.toEMU(75)); // 200x200 pixel title.setSpacingBetween(5);
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
            Date date = informationDto.getDate_test();
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            String strDate = dateFormat.format(date);
            run5.setText(strDate);
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

            //insertion des logo des etats de test
            String logo_a_inserer = null ;
            String VALID = "src/main/resources/images/valid.png" ;
            String ECHEC = "src/main/resources/images/croix.png" ;



            //create the second Row

            XWPFTableRow tableRowTwo = table.createRow();


            for (int i=0 ; i<3 ; i++) {
                XWPFParagraph paragraph_images = tableRowTwo.getCell(i).addParagraph();
                XWPFRun run_text = paragraph_images.createRun();
                XWPFRun run_images = paragraph_images.createRun();
                paragraph_images.setAlignment(ParagraphAlignment.BOTH);
                run_text.addTab();

                if (i==0) {
                    if (informationDto.isStatut_de_test()) {
                        logo_a_inserer = VALID;
                    } else {
                        logo_a_inserer = ECHEC;
                    }
                }

                if (i==1) {
                    if (informationDto.isStatut_de_application()) {
                        logo_a_inserer = VALID;
                    } else {
                        logo_a_inserer = ECHEC;
                    }
                }
                if (i==2) {
                    if (informationDto.isPoursuite_des_tests()) {
                        logo_a_inserer = VALID ;
                    }
                    else {
                        logo_a_inserer = ECHEC  ;
                    }
                }


                FileInputStream fis = new FileInputStream(logo_a_inserer);
                XWPFPicture picture = run_images.addPicture(fis, XWPFDocument.PICTURE_TYPE_PNG, null, Units.pixelToEMU(75), Units.pixelToEMU(75));
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

            // Sauter une page
            XWPFParagraph paragraph_break = document.createParagraph();
            paragraph_break.setPageBreak(true);

            //Creer la premiere partie  "Faits Marquants "

            XWPFParagraph paragraph_1 = document.createParagraph();
            XWPFRun run_paragraphe_1 = paragraph_1.createRun();
            run_paragraphe_1.setBold(true);
            run_paragraphe_1.setText("1. FAITS MARQUANTS");
            run_paragraphe_1.setFontSize(15);
            run_paragraphe_1.setColor("2E8BC0");
            paragraph_1.setAlignment(ParagraphAlignment.LEFT);
            paragraph_1.setBorderBottom(Borders.BASIC_BLACK_DASHES);

            // Sauter une page
            XWPFParagraph paragraph_break_2 = document.createParagraph();
            paragraph_break_2.setPageBreak(true);

            //Creer la deuxième partie  "2. ACTIVITES PRECEDENTES"

            XWPFParagraph paragraph_2 = document.createParagraph();
            XWPFRun run_paragraphe_2 = paragraph_2.createRun();
            run_paragraphe_2.setBold(true);
            run_paragraphe_2.setText("2. ACTIVITES PRECEDENTES");
            run_paragraphe_2.setFontSize(15);
            run_paragraphe_2.setColor("2E8BC0");
            paragraph_2.setAlignment(ParagraphAlignment.LEFT);
            paragraph_2.setBorderBottom(Borders.BASIC_BLACK_DASHES);
            paragraph_2.setSpacingAfterLines(300);


            // creer la table des activités précedentes
            XWPFTable table_activités = document.createTable();
            //create first row
            XWPFTableRow table_acctivités_RowOne = table_activités.getRow(0);
            table_acctivités_RowOne.getCell(0).setText("Phase");
            table_acctivités_RowOne.addNewTableCell().setText("Tâche");
            table_acctivités_RowOne.addNewTableCell().setText("Statut");
            table_acctivités_RowOne.addNewTableCell().setText("Date Initiale");
            table_acctivités_RowOne.addNewTableCell().setText("Date ");

            //align first row
            for (int i = 0 ; i<5 ; i++ ) {
                table_acctivités_RowOne.getCell(i).getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
            }
            //creer deux lignes vides

            for (int i =0 ; i<2 ; i++) {
                XWPFTableRow tableRow_activité = table_activités.createRow();
            }

            //set Spacing

            XWPFParagraph paragraph_space= document.createParagraph();
            XWPFRun run_space = paragraph_space.createRun();
            paragraph_space.setSpacingAfterLines(300);




            //Creer la troisème partie  "3. ACTIVITES EN COURS"

            XWPFParagraph paragraph_3 = document.createParagraph();
            XWPFRun run_paragraphe_3 = paragraph_3.createRun();
            run_paragraphe_3.setBold(true);
            run_paragraphe_3.setText("3. ACTIVITES EN COURS");
            run_paragraphe_3.setFontSize(15);
            run_paragraphe_3.setColor("2E8BC0");
            paragraph_3.setAlignment(ParagraphAlignment.LEFT);
            paragraph_3.setBorderBottom(Borders.BASIC_BLACK_DASHES);
            paragraph_3.setSpacingAfterLines(300);


            // creer la table des activités précedentes
            XWPFTable table_activités_en_cours = document.createTable();
            //create first row
            XWPFTableRow table_activités_en_cours_RowOne = table_activités_en_cours.getRow(0);
            table_activités_en_cours_RowOne.getCell(0).setText("Phase");
            table_activités_en_cours_RowOne.addNewTableCell().setText("Tâche");
            table_activités_en_cours_RowOne.addNewTableCell().setText("Statut");
            table_activités_en_cours_RowOne.addNewTableCell().setText("Date Initiale");
            table_activités_en_cours_RowOne.addNewTableCell().setText("Date ");

            //align first row
            for (int i = 0 ; i<5 ; i++ ) {
                table_activités_en_cours_RowOne.getCell(i).getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
            }
            //creer deux lignes vides

            for (int i =0 ; i<2 ; i++) {
                XWPFTableRow tableRow_activité_enc_cours_2 = table_activités_en_cours.createRow();
            }

            //set Spacing

            XWPFParagraph paragraph_space_2= document.createParagraph();
            XWPFRun run_space_2 = paragraph_space.createRun();
            paragraph_space_2.setSpacingAfterLines(300);


            //Creer la quatrième partie  "4. PROBLEMES RENCONTRES"

            XWPFParagraph paragraph_4 = document.createParagraph();
            XWPFRun run_paragraphe_4 = paragraph_4.createRun();
            run_paragraphe_4.setBold(true);
            run_paragraphe_4.setText("4. PROBLEMES RENCONTRES");
            run_paragraphe_4.setFontSize(15);
            run_paragraphe_4.setColor("2E8BC0");
            paragraph_4.setAlignment(ParagraphAlignment.LEFT);
            paragraph_4.setBorderBottom(Borders.BASIC_BLACK_DASHES);
            paragraph_4.setSpacingAfterLines(300);


            // creer la table des problèmes rencontrés
            XWPFTable table_problèmes_rencontrés = document.createTable();
            //create first row
            XWPFTableRow table_problèmes_rencontrés_RowOne = table_problèmes_rencontrés.getRow(0);
            table_problèmes_rencontrés_RowOne.getCell(0).setText("ID");
            table_problèmes_rencontrés_RowOne.addNewTableCell().setText("Description");
            table_problèmes_rencontrés_RowOne.addNewTableCell().setText("Statut");
            table_problèmes_rencontrés_RowOne.addNewTableCell().setText("Date");


            //align first row
            for (int i = 0 ; i<4 ; i++ ) {
                table_problèmes_rencontrés_RowOne.getCell(i).getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
            }
            //creer deux lignes vides

            for (int i =0 ; i<2 ; i++) {
                XWPFTableRow tableRow_problèmes_2 = table_problèmes_rencontrés.createRow();
            }

            //set Spacing

            XWPFParagraph paragraph_space_3= document.createParagraph();
            XWPFRun run_space_3 = paragraph_space_3.createRun();
            paragraph_space_3.setSpacingAfterLines(300);


            //Creer la cinquième partie  "Changements"

            XWPFParagraph paragraph_5 = document.createParagraph();
            XWPFRun run_paragraphe_5= paragraph_5.createRun();
            run_paragraphe_5.setBold(true);
            run_paragraphe_5.setText("5. CHANGEMENTS");
            run_paragraphe_5.setFontSize(15);
            run_paragraphe_5.setColor("2E8BC0");
            paragraph_5.setAlignment(ParagraphAlignment.LEFT);
            paragraph_5.setBorderBottom(Borders.BASIC_BLACK_DASHES);
            paragraph_5.setSpacingAfterLines(300);


            // creer la table des problèmes rencontrés
            XWPFTable table_changements = document.createTable();
            //create first row
            XWPFTableRow table_changements_RowOne = table_changements.getRow(0);
            table_changements_RowOne.getCell(0).setText("ID");
            table_changements_RowOne.addNewTableCell().setText("Description");
            table_changements_RowOne.addNewTableCell().setText("Date");


            //align first row
            for (int i = 0 ; i<2 ; i++ ) {
                table_changements_RowOne.getCell(i).getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
            }
            //creer deux lignes vides

            for (int i =0 ; i<2 ; i++) {
                XWPFTableRow tableRow_changements_2 = table_changements.createRow();
            }

            //set Spacing

            XWPFParagraph paragraph_space_4= document.createParagraph();
            XWPFRun run_space_4 = paragraph_space_4.createRun();
            paragraph_space_4.setSpacingAfterLines(300);



            //Creer la sixième  "6. PLANNING ACTUALISES"

            XWPFParagraph paragraph_6 = document.createParagraph();
            XWPFRun run_paragraphe_6= paragraph_6.createRun();
            run_paragraphe_6.setBold(true);
            run_paragraphe_6.setText("6. PLANNING ACTUALISES");
            run_paragraphe_6.setFontSize(15);
            run_paragraphe_6.setColor("2E8BC0");
            paragraph_6.setAlignment(ParagraphAlignment.LEFT);
            paragraph_6.setBorderBottom(Borders.BASIC_BLACK_DASHES);
            paragraph_6.setSpacingAfterLines(300);


            // creer la table des problèmes rencontrés
            XWPFTable table_planning = document.createTable();
            //create first row
            XWPFTableRow table_planning_RowOne = table_planning.getRow(0);
            table_planning_RowOne.getCell(0).setText("ID");
            table_planning_RowOne.addNewTableCell().setText("Description");
            table_planning_RowOne.addNewTableCell().setText("Date");


            //align first row
            for (int i = 0 ; i<2 ; i++ ) {
                table_planning_RowOne.getCell(i).getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
            }
            //creer deux lignes vides

            for (int i =0 ; i<2 ; i++) {
                XWPFTableRow tableRow_planning_2 = table_changements.createRow();
            }

            //set Spacing

            XWPFParagraph paragraph_space_5= document.createParagraph();
            XWPFRun run_space_5 = paragraph_space_5.createRun();
            paragraph_space_5.setSpacingAfterLines(300);


            //Creer la sixième  "7. ANNEXES – RESULTATS RAPIDES"

            XWPFParagraph paragraph_7 = document.createParagraph();
            XWPFRun run_paragraphe_7= paragraph_7.createRun();
            run_paragraphe_7.setBold(true);
            run_paragraphe_7.setText("7. ANNEXES – RESULTATS RAPIDES");
            run_paragraphe_7.setFontSize(15);
            run_paragraphe_7.setColor("2E8BC0");
            paragraph_7.setAlignment(ParagraphAlignment.LEFT);
            paragraph_7.setBorderBottom(Borders.BASIC_BLACK_DASHES);
            paragraph_7.setSpacingAfterLines(300);



            adservio.close();
            document.write(out);

            out.close();

            // upload du document modifié


            String modifiedfileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/downloadFile/")
                    .path(modifiedFileName)
                    .toUriString();


            TransformedFile document_transformé = TransformedFile.builder()
                    .fileDownloadUri(modifiedfileDownloadUri)
                    .fileType("docx")
                    .size(file.getSize())
                    .fileName(modifiedFileName)
                    .build() ;

            TransformedFile t = transformedFileService.save(document_transformé) ;


            //get authentificated username
            String loggedInUser =  SecurityContextHolder.getContext().getAuthentication().getName();

            User u = userService.getUser(loggedInUser) ;



            Operation operation = Operation.builder()
                    .dateTest(informationDto.getDate_test())
                    .nomClient(informationDto.getNom_client())
                    .nomDocument("ddqsddsq")
                    .uploadedOrginalFile(d)
                    .transformedFile(t)
                    .user(u)
                    .build() ;

            Operation operation_finale = operationService.save(operation) ;






        } catch (Exception e) {
            // TODO: handle exception
            return  ResponseDto.builder().message(e.getMessage()).build() ;
        }
        return  ResponseDto.builder().message("success").build() ;
    }
}
