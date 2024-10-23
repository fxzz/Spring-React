package com.mall.apiserver.controller;

import com.mall.apiserver.dto.ProductDTO;
import com.mall.apiserver.util.CustomFileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final CustomFileUtil customFileUtil;

    @PostMapping("/")
    public Map<String, String> register(ProductDTO productDTO) {

        log.info("register:" + productDTO);

        List<MultipartFile> files = productDTO.getFiles();
        List<String> uploadedFileNames = customFileUtil.saveFiles(files);
        productDTO.setUploadedFileNames(uploadedFileNames);

        log.info(uploadedFileNames);
        return Map.of("RESULT", "SUCCESS");
    }
}
