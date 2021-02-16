package com.cow.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    /**
     * 上传
     * @param file
     * @return 文件路径
     */
    String uploadFile(MultipartFile file);
}
