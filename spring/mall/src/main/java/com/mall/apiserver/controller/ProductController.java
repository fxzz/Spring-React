package com.mall.apiserver.controller;

import com.mall.apiserver.dto.ProductDTO;
import com.mall.apiserver.util.CustomFileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> viewFileGET(@PathVariable("fileName") String fileName) {
        return customFileUtil.getFile(fileName);
    }


}
