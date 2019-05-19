package com.qhit.utils;


import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by tp on 2019/5/7.
 */
@Component
@Configuration
public class Upload {

//    在启动类App.class文件中配置Bean来设置文件大小
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大
        factory.setMaxFileSize("10240KB"); //KB,MB10240kb=10mb
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("102400KB");
        return factory.createMultipartConfig();
    }
    // input type='file' name='file'
    //多文件上传
    public List<String> handleFileUpload(HttpServletRequest request, HttpServletResponse response, String name) throws Exception {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles(name);
        //返回动态文件路径
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        List<String> fileNameList = new ArrayList<>();
//        String str="";
            for (int i = 0; i < files.size(); ++i) {
                file = files.get(i);
                if (!file.isEmpty()||files.get(i).getOriginalFilename()!=null) {
                    try {
                        String fileName = file.getOriginalFilename();  // 文件名
                        byte[] bytes = file.getBytes();
//                        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
//                        String preName = fileName.substring(0,fileName.length()-suffixName.length());//文件名字
                        fileName = UUID.randomUUID() + fileName; // 新文件名 文件名称进行加密
//                        UUID是通用唯一识别码是让分布式系统中的所有元素，都能有唯一的辨识信息，而不需要通过中央控制端来做辨识信息的指定
//                        包含32个16进制数字
                        stream=new BufferedOutputStream(new FileOutputStream(new File(path+"static/upload/" + fileName)));
                        fileNameList.add(fileName);//把文件名传入集合中
//                        str=str+fileName+",";//拼接多个文件的名称
                        stream.write(bytes);
                        stream.close();
                    } catch (Exception e) {
                        stream = null;
                    }
                } else {
                    return null;
                }
        }
        return fileNameList;
    }
    //文件下载
    public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
        //返回动态文件路径
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
       String fullpath= path+"static/Upload/";
        // 设置文件名，根据业务需要替换成要下载的文件名
        String fileName = "aim_test.txt";
        if (fileName != null) {
            //设置文件路径
            String realPath = fullpath;
            File file = new File(realPath , fileName);
            if (file.exists()) {
                // 设置强制下载不打开
                response.setContentType("application/force-download");
                // 设置文件名
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("下载成功！");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }


}
