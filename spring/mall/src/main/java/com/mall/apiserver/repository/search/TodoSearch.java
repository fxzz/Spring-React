package com.mall.apiserver.repository.search;

import com.mall.apiserver.domain.Todo;
import com.mall.apiserver.dto.PageRequestDTO;
import org.springframework.data.domain.Page;

public interface TodoSearch {

    Page<Todo> search1(PageRequestDTO pageRequestDTO);
}
