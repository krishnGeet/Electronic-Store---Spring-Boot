package com.lcwd.electronic.store.ElectronicStore.exception;

import com.lcwd.electronic.store.ElectronicStore.dtos.ApiResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandeller {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandeller.class);

//    handle ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseMessage> resourceNotFoundException(ResourceNotFoundException resourceNotFoundException){
        logger.info("Exception handeller invoked !!");
        ApiResponseMessage apiResponseMessage = ApiResponseMessage.builder()
                .message(resourceNotFoundException.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .isSuccess(true).build();
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.NOT_FOUND);
    }

//    Handling MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        List<ObjectError> allErrors = methodArgumentNotValidException.getBindingResult().getAllErrors();
        Map<String, Object> response = new HashMap<>();
        allErrors.forEach(objectError -> {
            String message = objectError.getDefaultMessage();
            String field = ((FieldError)objectError).getField();
            response.put(field, message);
        });
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

//    Handled Bad Api Request
    @ExceptionHandler(BadApiRequest.class)
    public ResponseEntity<ApiResponseMessage> badApiRequest(BadApiRequest badApiRequest){
        logger.info("Bad Api Request invoked !!");
        ApiResponseMessage apiResponseMessage = ApiResponseMessage.builder()
                .message(badApiRequest.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .isSuccess(false).build();
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.BAD_REQUEST);
    }



}
