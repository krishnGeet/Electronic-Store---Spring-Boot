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
    List<ProductDto> getProductById(String productId);

//    get all
    List<ProductDto> getAllProduct();

//    get all live
    List<ProductDto> getAllLiveProduct();

//    search product
    List<ProductDto> searchProduct(String keyword);
}
