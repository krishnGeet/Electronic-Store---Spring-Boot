package com.lcwd.electronic.store.ElectronicStore.service.interf;

import com.lcwd.electronic.store.ElectronicStore.dtos.CategoryDto;
import com.lcwd.electronic.store.ElectronicStore.dtos.PageableResponse;

public interface CategoryService {
//    create
    CategoryDto createCategory(CategoryDto categoryDto);

//    update
    CategoryDto updateCategory(CategoryDto categoryDto, String categoryId);

//    delete
    void deleteCategory(String categoryId);

//    get all
    PageableResponse<CategoryDto> getAllCategory(int pageNumber, int pageSize, String sortBy, String sortDir);

    //    get single category details
    CategoryDto getCategoryById(String categoryId);
//    search
}
