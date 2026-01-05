package com.lcwd.electronic.store.ElectronicStore.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
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
