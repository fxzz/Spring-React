package com.mall.apiserver.controller;

import com.mall.apiserver.dto.PageRequestDTO;
import com.mall.apiserver.dto.PageResponseDTO;
import com.mall.apiserver.dto.ProductDTO;
import com.mall.apiserver.service.ProductService;
import com.mall.apiserver.util.CustomFileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Log4j2
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final CustomFileUtil customFileUtil;

    private final ProductService productService;

    @PostMapping("/")
    public Map<String, Long> register(ProductDTO productDTO) {

        log.info("register:" + productDTO);

        List<MultipartFile> files = productDTO.getFiles();
        List<String> uploadedFileNames = customFileUtil.saveFiles(files);
        productDTO.setUploadFileNames(uploadedFileNames);

        Long pno = productService.register(productDTO);


        log.info(uploadedFileNames);
        return Map.of("RESULT", pno);
    }

    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> viewFileGET(@PathVariable("fileName") String fileName) {
        return customFileUtil.getFile(fileName);
    }

    @GetMapping("/list")
    public PageResponseDTO<ProductDTO> list(PageRequestDTO pageRequestDTO) {
        PageResponseDTO<ProductDTO> list = productService.getList(pageRequestDTO);
        log.info(list);
        return list;
    }

    @GetMapping("/{pno}")
    public ProductDTO read(@PathVariable("pno")Long pno) {
        return productService.get(pno);
    }

    @PutMapping("/{pno}")
    public Map<String, String> modify(@PathVariable Long pno, ProductDTO productDTO) {

        productDTO.setPno(pno);

        ProductDTO oldProductDTO = productService.get(pno);

        //새로운 파일
        List<MultipartFile> files = productDTO.getFiles();
        List<String> currentUploadFileNames = customFileUtil.saveFiles(files);

        //유지가 되고 있는 파일
        List<String> uploadedFileNames = productDTO.getUploadFileNames();

        if (currentUploadFileNames != null && !currentUploadFileNames.isEmpty()) {
            uploadedFileNames.addAll(currentUploadFileNames);
        }

        productService.modify(productDTO);

        List<String> oldFileNames = oldProductDTO.getUploadFileNames();

        if (oldFileNames != null && oldFileNames.size() > 0) {
            List<String> removeFiles = oldFileNames.stream().filter(fileName -> uploadedFileNames.indexOf(fileName) == -1).collect(Collectors.toList());
            customFileUtil.deleteFiles(removeFiles);
        }

        return Map.of("RESULT", "SUCCESS");

    }


    @DeleteMapping("/{pno}")
    public Map<String, String> remove(@PathVariable Long pno) {
        List<String> oldFileNames = productService.get(pno).getUploadFileNames();
        productService.remove(pno);

        customFileUtil.deleteFiles(oldFileNames);

        return Map.of("RESULT", "SUCCESS");
    }


}
