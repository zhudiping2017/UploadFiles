package com.qhit.netstorage.service;

import java.util.List;
import com.qhit.netstorage.pojo.Netstorage;
/**
* Created by GeneratorCode on 2018/12/20
*/

public interface INetstorageService {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object id);

    List findAll();

    Netstorage findById(Object id);



}