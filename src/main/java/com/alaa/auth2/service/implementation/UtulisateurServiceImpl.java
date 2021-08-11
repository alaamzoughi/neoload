package com.alaa.auth2.service.implementation;

import com.alaa.auth2.repo.UtilisateurRepository;
import com.alaa.auth2.service.UtilisateurService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UtulisateurServiceImpl implements UtilisateurService {

    private UtilisateurRepository utilisateurRepository ;
}
