package com.qhit.netstorage.controller; 

import com.qhit.common.CommonUtil;
import com.qhit.netstorage.pojo.Netstorage;
import com.qhit.netstorage.service.INetstorageService; 
import com.qhit.netstorage.service.impl.NetstorageServiceImpl; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.RequestMapping; 
import javax.annotation.Resource; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;



@Controller 
@RequestMapping("/netstorage") 
public class NetstorageController { 

    @Resource 
    INetstorageService netstorageService; 

    @RequestMapping("/insert") 
    public String insert(@RequestParam(value = "filename2")CommonsMultipartFile[] files, HttpServletRequest request, HttpSession session) {
//        创建对象，用于存储数据
        Netstorage netstorage = new Netstorage();
//        获取upload文件夹的实际路径
        String path = request.getServletContext().getRealPath("upload");
//        遍历文件数组
        for (CommonsMultipartFile f:files){
//            设置文件的名称
            netstorage.setFilename(f.getOriginalFilename());
//            用户id
            netstorage.setUid(CommonUtil.getUserId(session));
//            上传时间
            netstorage.setUploaddate(CommonUtil.dateToStr(new Date()));
//            文件大小
            netstorage.setFilesize((int) f.getSize());
//            将数据保存到数据库中
            netstorageService.insert(netstorage);
//            文件的全路径
            File file = new File(path+"/"+f);
            try {
//              transferTo是springmvc中封装的方法，用于文件上传时，将内存中的数据写入硬盘
                f.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "forward:list.action";
    }

    @RequestMapping("/delete") 
    public String delete(Integer fileid, HttpServletResponse response) throws IOException { 
        netstorageService.delete(fileid); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/update") 
    public String update(Netstorage netstorage) { 
        netstorageService.update(netstorage); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/updateSelective") 
    public String updateSelective(Netstorage netstorage) { 
        netstorageService.updateSelective(netstorage); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/load") 
    public String load(Integer fileid, Model model) { 
        Netstorage netstorage = netstorageService.findById(fileid); 
        model.addAttribute("netstorage",netstorage); 
        return "netstorage/edit"; 
    } 
 
    @RequestMapping("/list") 
    public String list(Model model) throws IOException { 
        List<Netstorage> list = netstorageService.findAll(); 
        model.addAttribute("list",list); 
        return "netstorage/list"; 
    } 
 
    @RequestMapping("/ajaxList") 
    public void ajaxList(HttpServletResponse response) throws IOException { 
        List<Netstorage> list = netstorageService.findAll(); 
        String s = JSON.toJSONString(list); 
        response.getWriter().write(s); 
    } 
 
} 
