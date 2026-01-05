package com.lcwd.electronic.store.ElectronicStore.dtos;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageResponseMessage {
    private String imageName;
    private String message;
    private boolean isSuccess;
    private HttpStatus status;
}
