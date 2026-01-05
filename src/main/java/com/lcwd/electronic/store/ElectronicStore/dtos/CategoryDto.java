package com.lcwd.electronic.store.ElectronicStore.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {
    private String categoryId;
    @Min(4)
    private String title;
    @NotNull(message = "Description is required!!")
    private String description;
    @NotNull(message = "Image is needed")
    private String coverImage;
}
