package com.lcwd.electronic.store.ElectronicStore.service.impls;

import com.lcwd.electronic.store.ElectronicStore.exception.BadApiRequest;
import com.lcwd.electronic.store.ElectronicStore.service.interf.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
    @Override
    public String uploadFile(MultipartFile multipartFile, String path) throws IOException {
        String originalFileName = multipartFile.getOriginalFilename();
        logger.info("File name {}", originalFileName);
        String fileName = UUID.randomUUID().toString();
        assert originalFileName != null;
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String fileNameWithExtension = fileName + extension;
        String fullPathWithFileName = path + File.separator + fileNameWithExtension;

        logger.info("Full image path {}", fullPathWithFileName);
        if(extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".jpeg")){
            File folder = new File(path);
            if(!folder.exists()){
//                Create the folder
                logger.info("File extension is {}", extension);
                folder.mkdirs();
            }
            Files.copy(multipartFile.getInputStream(), Paths.get(fullPathWithFileName));
        } else{
            throw new BadApiRequest("File with this " + extension + " not allowed");
        }
        return fileNameWithExtension;
    }

    @Override
    public InputStream getResource(String path, String name) throws FileNotFoundException {
        String fullPath = path + File.separator + name;
        return new FileInputStream(fullPath);
    }

}
