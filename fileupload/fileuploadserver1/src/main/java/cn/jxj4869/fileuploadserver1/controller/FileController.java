package cn.jxj4869.fileuploadserver1.controller;

import com.sun.javafx.scene.shape.PathUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

import java.util.UUID;

@Controller
public class FileController {

    @Value("${file.upload.save-path}")
    private String savePath;

    @PostMapping("/fileupload")
    @ResponseBody
    private String fileupload(HttpServletRequest request, @RequestParam("upload")MultipartFile[] uploads) throws IOException {
        System.out.println("文件上传");

        String path= ResourceUtils.getURL("classpath:").getPath()+savePath;


        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }

        for (MultipartFile upload : uploads) {
            String filename = upload.getOriginalFilename();

            String uuid = UUID.randomUUID().toString().replace("-", "");
            filename=uuid+"_"+filename;

            upload.transferTo(new File(path,filename));
        }
        System.out.println("aa");
        return "success";

    }
}
