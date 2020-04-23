package cn.jxj4869.fileupload.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Controller

public class FileController {
    @Value("${file.upload.path}")
    private String path;

    @Value("${file.upload.path1}")
    private String path1;

    @RequestMapping("/fileupload/method1")
    @ResponseBody
    private String method1(@RequestParam("upload") MultipartFile upload) throws IOException {
        System.out.println("跨服务器上传文件上传");

        String filename = upload.getOriginalFilename();
        // 把文件的名称设置唯一值，uuid
        String uuid = UUID.randomUUID().toString().replace("-", "");
        filename = uuid + "_" + filename;
        // 创建客户端的对象

        Client client = Client.create();

        // 和图片服务器进行连接
        WebResource webResource = client.resource(path + filename);

        webResource.put(upload.getBytes());
        return "success";
    }

    @RequestMapping("/fileupload/method2")
    @ResponseBody
    private String method2(@RequestParam("upload") MultipartFile[] uploads) throws IOException {
        System.out.println("跨服务器上传文件上传");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(path1);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        for (MultipartFile upload : uploads) {
            String filename = upload.getOriginalFilename();
            builder.addBinaryBody("upload", upload.getBytes(), ContentType.MULTIPART_FORM_DATA, filename);
        }
        try {

            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            System.out.println(response.getStatusLine().getStatusCode());
            String s = response.getEntity().toString();
            System.out.println(s);
        } catch (Exception e) {

        } finally {
            httpClient.close();
        }
        return "success";
    }


}
