package com.lcwd.electronic.store.ElectronicStore.service.interf;

import com.lcwd.electronic.store.ElectronicStore.dtos.ApiResponseMessage;
import com.lcwd.electronic.store.ElectronicStore.dtos.PageableResponse;
import com.lcwd.electronic.store.ElectronicStore.dtos.ProductDto;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface ProductService {
//    create
    ProductDto createProduct(ProductDto productDto);

//    update
    ProductDto updateProduct(ProductDto productDto, String productId);

//    delete
    void deleteProduct(String productId);

//    get single
    ProductDto getProductById(String productId);

//    get all
    PageableResponse<ProductDto> getAllProduct(int pageNumber, int pageSize, String sortBy, String sortDir);

//    get all live
    PageableResponse<ProductDto> getAllLiveProduct(int pageNumber, int pageSize, String sortBy, String sortDir);

//    search product
    PageableResponse<ProductDto> searchProduct(String keyword, int pageNumber, int pageSize, String sortBy, String sortDir);
}
