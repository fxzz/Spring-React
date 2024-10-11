package com.mall.apiserver.repository;

import com.mall.apiserver.domain.Todo;
import com.mall.apiserver.repository.search.TodoSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoSearch {
}
