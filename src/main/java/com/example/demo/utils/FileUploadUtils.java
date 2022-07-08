package com.example.demo.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

//文件上传相关的工具包
public class FileUploadUtils {

    // UUID可以避免重命名
    // 传入原始文件名可以得到  uuid+"_"+文件的原始名称 的返回值
    public static String getUuidFileName(String fileName) {
        //文件名以：uuid+"_"+文件的原始名称
        return UUID.randomUUID().toString() + "_" + fileName;
    }

    //保存到服务器，返回存储的位置
    public static String SaveServer(MultipartFile file, HttpServletRequest request) {
        System.out.println("正在上传文件");
        //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
        //String realpath = request.getServletContext().getRealPath("/WEB-INF/files");
        //暂时就不保存在WEB-INF那里，先放在files
        //这里保存在根目录中的file文件中
        String realpath = request.getServletContext().getRealPath("files");

        //获取文件名字，进行UUID重命名
        String fileName = getUuidFileName(file.getOriginalFilename());

        //文件上传
        File targetFile = new File(realpath, fileName);

        //如果不存在，创建文件
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        // 上传
        try {
            file.transferTo(targetFile);            //保存下来
            System.out.println("上传成功");
            //return realpath + '\\' + fileName;
            //返回相对路径就行
            return "\\files\\" + fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;                        //说明保存不成功
        }
    }

}
