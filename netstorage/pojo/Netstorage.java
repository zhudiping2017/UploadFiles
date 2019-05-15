package com.qhit.netstorage.pojo;


public class Netstorage {

    private Integer fileid;    //主键
    private String filename;    //文件名称
    private Integer filesize;    //文件大小
    private String uploaddate;    //上传时间
    private Integer uid;    //用户

    public Integer getFileid() { 
        return fileid;
    }
 
    public void setFileid(Integer fileid) { 
        this.fileid = fileid;
    }
 
    public String getFilename() { 
        return filename;
    }
 
    public void setFilename(String filename) { 
        this.filename = filename;
    }
 
    public Integer getFilesize() { 
        return filesize;
    }
 
    public void setFilesize(Integer filesize) { 
        this.filesize = filesize;
    }
 
    public String getUploaddate() { 
        return uploaddate;
    }
 
    public void setUploaddate(String uploaddate) { 
        this.uploaddate = uploaddate;
    }
 
    public Integer getUid() { 
        return uid;
    }
 
    public void setUid(Integer uid) { 
        this.uid = uid;
    }
 

 }
