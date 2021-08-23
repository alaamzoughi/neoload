package com.alaa.auth2.service.implementation;

import com.alaa.auth2.model.UploadedOrginalFile;
import com.alaa.auth2.repo.UploadedOrginalFileRepository;
import com.alaa.auth2.service.UploadedOrginalFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UploadedOrginalFileServiceImpl implements UploadedOrginalFileService {

    @Autowired
    private UploadedOrginalFileRepository uploadedOrginalFileRepository ;
    @Override
    public UploadedOrginalFile save(UploadedOrginalFile uploadedOrginalFile) {
        return uploadedOrginalFileRepository.save(uploadedOrginalFile);
    }

    @Override
    public Optional<UploadedOrginalFile> findById(Integer id) {
        return uploadedOrginalFileRepository.findById(id);
    }
}
