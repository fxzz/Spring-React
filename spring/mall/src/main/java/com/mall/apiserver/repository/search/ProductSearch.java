package com.mall.apiserver.repository.search;

import com.mall.apiserver.dto.PageRequestDTO;
import com.mall.apiserver.dto.PageResponseDTO;
import com.mall.apiserver.dto.ProductDTO;

public interface ProductSearch {

    PageResponseDTO<ProductDTO> searchList(PageRequestDTO pageRequestDTO);
}
