package pinyougou.core.controller;

import entity.Result;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pinyougou.util.FastDFSClient;

import java.io.File;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Value("${FILE_SERVER_URL}")
    private String FILE_SERVER_URL;
    @RequestMapping("/upload")
    public Result uploadFile(MultipartFile file){//多媒体解析器
        try {
            String name = file.getOriginalFilename();
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:config/fdfs_client.conf");
            String ext = FilenameUtils.getExtension(name);//获得扩展名
            //需要文件的字节码，扩展名，大小
            String path = fastDFSClient.uploadFile(file.getBytes(), ext, file.getSize());
            String url = FILE_SERVER_URL + path;//域名 虚拟机的路径 + 文件的路径
            return new Result(true,"上传成功"+ url);
        } catch(Exception ex) {
            System.out.println(ex);
            return new Result(true,"上传失败");
        }
    }
}
