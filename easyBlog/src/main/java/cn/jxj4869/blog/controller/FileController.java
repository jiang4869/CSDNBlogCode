package cn.jxj4869.blog.controller;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller


public class FileController {
    @Value("${file.upload.path.editormd}")
    private String editormd;

    @Value("${file.upload.path.simditor}")
    private String simditor;


    @Value("${file.upload.path}")
    private String path;


    @RequestMapping("/fileupload/editormd")
    @ResponseBody
    public String editormd(@RequestParam("editormd-image-file") MultipartFile[] uploads) throws IOException {
        System.out.println("跨服务器上传文件上传");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(editormd);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        for (MultipartFile upload : uploads) {
            String filename = upload.getOriginalFilename();
            if (filename == null || filename.equals(""))
                continue;
            builder.addBinaryBody("upload", upload.getBytes(), ContentType.MULTIPART_FORM_DATA, filename);
        }
        String s = null;
        try {
            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            System.out.println(response.getStatusLine().getStatusCode());
            s = EntityUtils.toString(response.getEntity(), "utf8");

            System.out.println(s);
        } catch (Exception e) {

        } finally {
            httpClient.close();
        }
        return s;
    }


    @RequestMapping("/fileupload")
    @ResponseBody
    public String fileupload(@RequestParam("upload") MultipartFile[] uploads,@RequestParam(value = "blob",defaultValue = "false",required = false) Boolean blob) throws IOException {
        System.out.println("跨服务器上传文件上传");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(path);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        for (MultipartFile upload : uploads) {
            String filename = upload.getOriginalFilename();
            if (filename == null || filename.equals(""))
                continue;
            if(blob) {
                filename=filename+".jpg";
            }
            builder.addBinaryBody("upload", upload.getBytes(), ContentType.MULTIPART_FORM_DATA, filename);
        }
        String s = null;
        try {
            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            System.out.println(response.getStatusLine().getStatusCode());
            s = EntityUtils.toString(response.getEntity(), "utf8");

            System.out.println(s);
        } catch (Exception e) {

        } finally {
            httpClient.close();
        }

        return s;
    }



    @RequestMapping("/fileupload/simditor")
    @ResponseBody
    public String simditor(@RequestParam("upload") MultipartFile[] uploads) throws IOException {
        System.out.println("simditor跨服务器上传文件上传");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(simditor);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        for (MultipartFile upload : uploads) {
            String filename = upload.getOriginalFilename();
            if (filename == null || filename.equals(""))
                continue;
            builder.addBinaryBody("upload", upload.getBytes(), ContentType.MULTIPART_FORM_DATA, filename);
        }
        String s = null;
        try {
            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            System.out.println(response.getStatusLine().getStatusCode());
            s = EntityUtils.toString(response.getEntity(), "utf8");

            System.out.println(s);
        } catch (Exception e) {

        } finally {
            httpClient.close();
        }
        return s;
    }
}
