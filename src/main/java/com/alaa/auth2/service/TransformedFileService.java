package com.alaa.auth2.service;

import com.alaa.auth2.model.TransformedFile;

import java.util.Optional;

public interface TransformedFileService {

    TransformedFile save(TransformedFile transformedFile) ;
    Optional<TransformedFile> findById (Integer id) ;

}
