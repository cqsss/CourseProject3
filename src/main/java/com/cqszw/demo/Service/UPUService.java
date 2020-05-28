package com.cqszw.demo.Service;

import com.cqszw.demo.Bean.Paper;
import com.cqszw.demo.Mapper.UPUMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cqs
 * @date 2020/5/28
 */
@Service
public class UPUService {
    @Autowired
    private UPUMapper upuMapper;
    public List<Paper> getpaperbyuser(String username){return upuMapper.getpaperbyuser(username);}
    public int insertUPU(String username,int paper_id,String upload_time){return upuMapper.insertUPU(username,paper_id,upload_time);}

}
