package com.lcwd.electronic.store.ElectronicStore.controller;


import com.lcwd.electronic.store.ElectronicStore.dtos.ApiResponseMessage;
import com.lcwd.electronic.store.ElectronicStore.dtos.ImageResponseMessage;
import com.lcwd.electronic.store.ElectronicStore.dtos.PageableResponse;
import com.lcwd.electronic.store.ElectronicStore.dtos.UserDto;
import com.lcwd.electronic.store.ElectronicStore.service.interf.FileService;
import com.lcwd.electronic.store.ElectronicStore.service.interf.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.hibernate.query.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private FileService fileService;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Value("${user.profile.image.path}")
    private String imageUploadPath;
//    create
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto user = userService.createUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
//    update
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,
                                              @PathVariable("userId") String userId){
        UserDto updatedUser = userService.updateUser(userDto, userId);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);

    }
//    delete
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseMessage> deleteUser(@PathVariable("userId") String userId){
        ApiResponseMessage responseMessage = ApiResponseMessage.builder().message("User deleted successfully").isSuccess(true).status(HttpStatus.OK).build();

        userService.deleteUser(userId);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
//    get all
    @GetMapping
    public ResponseEntity<PageableResponse<UserDto>> getAllUer(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "gender", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        return new ResponseEntity<>(userService.getAllUser(pageNumber, pageSize, sortBy, "desc"), HttpStatus.OK);
    }
//    get single
    @GetMapping("/id/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") String userId){
        return new ResponseEntity<>(userService.getuserById(userId), HttpStatus.OK);
    }
//    get by email
    @GetMapping("/email/{userEmail}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable("userEmail") String userEmail){
        return new ResponseEntity<>(userService.getUserByEmail(userEmail), HttpStatus.OK);
    }
//    search user
    @GetMapping("/search/{keyword}")
    public ResponseEntity<PageableResponse<UserDto>> searchUser(
            @PathVariable String keyword,
            @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir
    ){
        return new ResponseEntity<>(userService.searchUser(keyword, pageNumber, pageSize, sortBy, sortDir), HttpStatus.OK);
    }

//    Upload user image
    @PostMapping("/image/{userId}")
    public ResponseEntity<ImageResponseMessage> uploadUserImage(
            @RequestParam("userImage") MultipartFile multipartFile,
            @PathVariable("userId") String userId
            ) throws IOException {
        String imageName = fileService.uploadFile(multipartFile, imageUploadPath);
        UserDto userDto = userService.getuserById(userId);
        userDto.setUserImage(imageName);
        UserDto updatedUserDto = userService.updateUser(userDto, userId);
        ImageResponseMessage imageResponseMessage = ImageResponseMessage.builder()
                .imageName(imageName)
                .message("Image created !!")
                .isSuccess(true)
                .status(HttpStatus.CREATED).build();
        return new ResponseEntity<>(imageResponseMessage, HttpStatus.CREATED);

    }
//    Serve user image
    @GetMapping("/image/{userId}")
    public void serveImage(
            @PathVariable("userId") String userId,
            HttpServletResponse httpServletResponse
    ) throws IOException {
        UserDto user = userService.getuserById(userId);
        logger.info("user image name {}", user.getUserImage());
        InputStream resource = fileService.getResource(imageUploadPath, user.getUserImage());
        httpServletResponse.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, httpServletResponse.getOutputStream());
    }
}

