package com.alaa.auth2;

import com.alaa.auth2.dto.OperationDto;
import com.alaa.auth2.dto.TransformedFileDto;
import com.alaa.auth2.dto.UserDto;
import com.alaa.auth2.model.Operation;
import com.alaa.auth2.model.TransformedFile;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.Valid;

@SpringBootTest
class Auth2ApplicationTests {

    @Test
    void contextLoads() {


        TransformedFile f = TransformedFile.builder()
                .fileDownloadUri("qdqsdsqd")
                .fileName("dqsdsq")
                .build() ;



        ModelMapper modelMapper = new ModelMapper();
        TransformedFileDto transformedFileDto = modelMapper.map(f, TransformedFileDto.class);


        Assert.assertEquals(f.getFileDownloadUri() , transformedFileDto.getFileDownloadUri()) ;




    }

}
