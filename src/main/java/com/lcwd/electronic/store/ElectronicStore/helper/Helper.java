package com.lcwd.electronic.store.ElectronicStore.helper;

import com.lcwd.electronic.store.ElectronicStore.dtos.PageableResponse;
import com.lcwd.electronic.store.ElectronicStore.dtos.UserDto;
import com.lcwd.electronic.store.ElectronicStore.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;

public class Helper {
    public static <U, V> PageableResponse<V> getPageableResponse(Page<U> page, Class<V> type){
        List<U> users = page.getContent();
        List<V> dtoList = users.stream().map((object) -> new ModelMapper().map(object, type)).toList();

        PageableResponse<V> response = new PageableResponse<>();
        response.setContent(dtoList);
        response.setPageSize(page.getSize());
        response.setPageNumber(page.getNumber());
        response.setTotalElements(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setLastPage(page.isLast());
        return response;
    }
}
