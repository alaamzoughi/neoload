package com.alaa.auth2.controller;


import com.alaa.auth2.controller.api.UtilisateurApi;
import com.alaa.auth2.service.UtilisateurService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UtilisateurController implements UtilisateurApi {
    private UtilisateurService utilisateurService ;
}
