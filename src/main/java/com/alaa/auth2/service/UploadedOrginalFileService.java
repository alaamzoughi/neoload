package com.alaa.auth2.service;

import com.alaa.auth2.model.UploadedOrginalFile;

import java.util.Optional;

public interface UploadedOrginalFileService {
    UploadedOrginalFile save(UploadedOrginalFile uploadedOrginalFile) ;
    Optional<UploadedOrginalFile> findById (Integer id) ;
}
