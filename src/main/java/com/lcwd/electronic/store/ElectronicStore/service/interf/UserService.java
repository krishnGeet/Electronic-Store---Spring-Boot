package com.lcwd.electronic.store.ElectronicStore.service.interf;

import com.lcwd.electronic.store.ElectronicStore.dtos.PageableResponse;
import com.lcwd.electronic.store.ElectronicStore.dtos.UserDto;

import java.util.List;

public interface UserService {

//  create
    UserDto createUser(UserDto userDto);

//  update
    UserDto updateUser(UserDto userDto, String userId);

//  delete
    void deleteUser(String userId);

//  get all user
    PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir);

//  get user by id
    UserDto getuserById(String userId);

//  get user by email
    UserDto getUserByEmail(String userEmail);

//  search user
    List<UserDto> searchUser(String keyword);


}