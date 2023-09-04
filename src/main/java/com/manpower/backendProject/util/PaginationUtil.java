package com.manpower.backendProject.util;

import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class PaginationUtil<T, U> {

    public ResponseEntity<Object> getPaginatedResponse(Function<T, U> toDao, Page<T> page) {
        Map<String, Object> response = new HashMap<>();
        response.put("content", page.getContent().stream().map(toDao).toList());
        response.put("currentPage", page.getNumber() + 1);
        response.put("pages", page.getTotalPages());
        response.put("count", page.getTotalElements());
        return ResponseEntity.ok(response);
    }

    public Pageable getPageable(int pageNo, int pageSize, String sortBy) {
        if (pageNo < 0) pageNo = 0;
        if (pageSize < 1) pageSize = 10;
        return PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
    }

    public Page<T> getListAsPage(List<T> entityList, Pageable pageable) {
        return new PageImpl<>(entityList, pageable, entityList.size());
    }
}
