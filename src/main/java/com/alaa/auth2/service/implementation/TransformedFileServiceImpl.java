package com.alaa.auth2.service.implementation;

import com.alaa.auth2.model.TransformedFile;
import com.alaa.auth2.repo.TransformedFileRepository;
import com.alaa.auth2.service.TransformedFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransformedFileServiceImpl implements TransformedFileService {
    @Autowired
    private TransformedFileRepository transformedFileRepository ;
    @Override
    public TransformedFile save(TransformedFile transformedFile) {
        return transformedFileRepository.save(transformedFile);
    }

    @Override
    public Optional<TransformedFile> findById(Integer id) {
        return transformedFileRepository.findById(id);
    }
}
