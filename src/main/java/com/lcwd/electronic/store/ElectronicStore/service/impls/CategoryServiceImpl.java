package com.lcwd.electronic.store.ElectronicStore.service.impls;

import com.lcwd.electronic.store.ElectronicStore.dtos.CategoryDto;
import com.lcwd.electronic.store.ElectronicStore.dtos.PageableResponse;
import com.lcwd.electronic.store.ElectronicStore.entities.Category;
import com.lcwd.electronic.store.ElectronicStore.exception.ResourceNotFoundException;
import com.lcwd.electronic.store.ElectronicStore.helper.Helper;
import com.lcwd.electronic.store.ElectronicStore.repositories.CategoryRepository;
import com.lcwd.electronic.store.ElectronicStore.service.interf.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category categoryToSave = mapper.map(categoryDto, Category.class);
        Category categorySaved = categoryRepository.save(categoryToSave);
        return mapper.map(categorySaved, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, String categoryId) {
        Category categoryToUpdate = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found by given category id"));
        categoryToUpdate.setTitle(categoryDto.getTitle());
        categoryToUpdate.setDescription(categoryDto.getDescription());
        categoryToUpdate.setCoverImage(categoryDto.getCoverImage());
        Category updatedCategory = categoryRepository.save(categoryToUpdate);
        return mapper.map(updatedCategory, CategoryDto.class);
    }

    @Override
    public void deleteCategory(String categoryId) {
        Category categoryToDelete = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found by provided id"));
        categoryRepository.delete(categoryToDelete);
    }

    @Override
    public PageableResponse<CategoryDto> getAllCategory(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Category> page = categoryRepository.findAll(pageable);
        return Helper.getPageableResponse(page, CategoryDto.class);
    }

    @Override
    public CategoryDto getCategoryById(String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found by provided id"));
        return mapper.map(category, CategoryDto.class);
    }
}
