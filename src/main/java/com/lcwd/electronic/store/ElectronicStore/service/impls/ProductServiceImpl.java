package com.lcwd.electronic.store.ElectronicStore.service.impls;

import com.lcwd.electronic.store.ElectronicStore.dtos.ProductDto;
import com.lcwd.electronic.store.ElectronicStore.entities.Product;
import com.lcwd.electronic.store.ElectronicStore.exception.ResourceNotFoundException;
import com.lcwd.electronic.store.ElectronicStore.repositories.ProductRepository;
import com.lcwd.electronic.store.ElectronicStore.service.interf.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product productToCreate = mapper.map(productDto, Product.class);
        return mapper.map(productRepository.save(productToCreate), ProductDto.class);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, String productId) {
        Product productToUpdate = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found by given ID"));
        productToUpdate.setTitle(productDto.getTitle());
        productToUpdate.setDescription(productDto.getDescription());
        productToUpdate.setPrice(productDto.getPrice());
        productToUpdate.setDiscountedPrice(productDto.getDiscountedPrice());
        productToUpdate.setAvailableQuantity(productDto.getAvailableQuantity());
        productToUpdate.setLive(productDto.isLive());
        productToUpdate.setInStock(productDto.isInStock());
        productToUpdate.setAddedDate(productDto.getAddedDate());
        Product updatedProduct = productRepository.save(productToUpdate);
        return mapper.map(updatedProduct, ProductDto.class);
    }

    @Override
    public void deleteProduct(String productId) {

    }

    @Override
    public List<ProductDto> getProductById(String productId) {
        return List.of();
    }

    @Override
    public List<ProductDto> getAllProduct() {
        return List.of();
    }

    @Override
    public List<ProductDto> getAllLiveProduct() {
        return List.of();
    }

    @Override
    public List<ProductDto> searchProduct(String keyword) {
        return List.of();
    }
}
