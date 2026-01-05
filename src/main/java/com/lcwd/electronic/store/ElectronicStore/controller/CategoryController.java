package com.lcwd.electronic.store.ElectronicStore.controller;

import com.lcwd.electronic.store.ElectronicStore.dtos.ApiResponseMessage;
import com.lcwd.electronic.store.ElectronicStore.dtos.CategoryDto;
import com.lcwd.electronic.store.ElectronicStore.dtos.PageableResponse;
import com.lcwd.electronic.store.ElectronicStore.service.interf.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(CategoryDto categoryToCreate){
        return new ResponseEntity<>(categoryService.createCategory(categoryToCreate), HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(
            @RequestBody CategoryDto categoryToUpdate,
            @PathVariable("categoryId") String categoryId
    ){
        CategoryDto updatedCategory = categoryService.updateCategory(categoryToUpdate, categoryId);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponseMessage> deleteCategory(
            @PathVariable("categoryId") String categoryId
    ){
        categoryService.deleteCategory(categoryId);
        ApiResponseMessage apiResponseMessage = ApiResponseMessage.builder()
                .message("Category deleted")
                .isSuccess(true)
                .status(HttpStatus.OK).build();
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryById(
            @PathVariable("categoryId") String categoryId
    ){
        CategoryDto categoryRetrieved = categoryService.getCategoryById(categoryId);
        return new ResponseEntity<>(categoryRetrieved, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PageableResponse<CategoryDto>> getAllCategory(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        return new ResponseEntity<>(categoryService.getAllCategory(pageNumber, pageSize, sortBy, "desc"), HttpStatus.OK);

    }
}
