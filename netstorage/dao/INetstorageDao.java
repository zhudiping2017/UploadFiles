package com.qhit.netstorage.dao;

import org.springframework.stereotype.Repository;
import java.util.List;


@Repository  
public interface INetstorageDao {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object object);

    List freeFind(String sql);

    List findAll();

    List findById(Object id);

    boolean freeUpdate(String sql);

    List findByFilename(Object filename);

    List findByFilesize(Object filesize);

    List findByUploaddate(Object uploaddate);

    List findByUid(Object uid);

}
