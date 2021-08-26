package com.alaa.auth2.service;

import com.alaa.auth2.dto.ResponseDto;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface InformationService {

    public ResponseDto generateDoc(
             MultipartFile file, MultipartFile image, String s ) ;
}
