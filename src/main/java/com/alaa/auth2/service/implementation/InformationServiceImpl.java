package com.alaa.auth2.service.implementation;

import com.alaa.auth2.dto.ResponseDto;
import com.alaa.auth2.service.InformationService;
import org.springframework.web.multipart.MultipartFile;

public class InformationServiceImpl  implements InformationService {
    @Override
    public ResponseDto generateDoc(MultipartFile file, MultipartFile image, String s) {
        return null;
    }
}
