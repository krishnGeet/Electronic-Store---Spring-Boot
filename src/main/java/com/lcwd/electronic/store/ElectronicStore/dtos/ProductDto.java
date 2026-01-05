package com.lcwd.electronic.store.ElectronicStore.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String productId;
    private String title;
    private String description;
    private int price;
    private int discountedPrice;
    private int availableQuantity;
    private Date addedDate;
    private boolean isLive;
    private boolean inStock;
}