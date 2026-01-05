package com.lcwd.electronic.store.ElectronicStore.service.impls;

import com.lcwd.electronic.store.ElectronicStore.dtos.PageableResponse;
import com.lcwd.electronic.store.ElectronicStore.dtos.UserDto;
import com.lcwd.electronic.store.ElectronicStore.entities.User;
import com.lcwd.electronic.store.ElectronicStore.exception.ResourceNotFoundException;
import com.lcwd.electronic.store.ElectronicStore.helper.Helper;
import com.lcwd.electronic.store.ElectronicStore.repositories.UserRepository;
import com.lcwd.electronic.store.ElectronicStore.service.interf.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;
    @Value("${user.profile.image.path}")
    private String imagePath;
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public UserDto createUser(UserDto userDto) {
        String userId = UUID.randomUUID().toString();
        userDto.setUserId(userId);
//        Dto -> Entity
//        User user = DtoToEntity(userDto);
        User user = mapper.map(userDto, User.class);
        User savedUser = userRepository.save(user);
//        Entity -> Dto
        return mapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        user.setGender(userDto.getGender());
        user.setUserImage(userDto.getUserImage());
        return mapper.map(userRepository.save(user), UserDto.class);
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found"));
//        Delete user profile image
        String imageFullPath = imagePath + user.getUserImage();
        try {
            Path path = Paths.get(imageFullPath);
            Files.delete(path);
        } catch (NoSuchFileException e){
            logger.info("User image not found in folder");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        userRepository.delete(user);
    }

    @Override
    public PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir) {
//        Creating pageable object
//        Page number starts from 0
        Sort sort = (sortBy.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort); // Pageable object is now created holding page number and page size
        Page<User> page = userRepository.findAll(pageable);
        return Helper.getPageableResponse(page, UserDto.class);
    }

    @Override
    public UserDto getuserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        return mapper.map(user, UserDto.class);
    }

    @Override
    public UserDto getUserByEmail(String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new ResourceNotFoundException("User not found by given email"));
        return EntityToDto(user);
    }

    @Override
    public List<UserDto> searchUser(String keyword) {
        List<User> users = userRepository.findByNameContaining(keyword);
        return users.stream().map(this::EntityToDto).toList();

    }

    private UserDto EntityToDto(User savedUser) {
        return UserDto.builder()
                .userId(savedUser.getUserId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .about(savedUser.getAbout())
                .password(savedUser.getPassword())
                .gender(savedUser.getGender())
                .userImage(savedUser.getUserImage()).build();

    }

    private User DtoToEntity(UserDto userDto) {
        return User.builder()
                .userId(userDto.getUserId())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .about(userDto.getAbout())
                .password(userDto.getPassword())
                .gender(userDto.getGender())
                .userImage(userDto.getUserImage()).build();
    }
}
