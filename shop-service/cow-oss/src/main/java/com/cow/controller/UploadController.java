package com.cow.controller;

import com.cow.resp.R;
import com.cow.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    /**
     * 上传
     * @param file
     * @return 文件路径
     */
    @PostMapping
    public R uploadFile(MultipartFile file) {
        String url = uploadService.uploadFile(file);
        return R.ok(url);
    }

}
