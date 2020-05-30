package com.cqszw.demo.Service;

import com.cqszw.demo.Bean.Paper;
import com.cqszw.demo.Bean.Upload_Record;
import com.cqszw.demo.Bean.User_Upload;
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
    public List<User_Upload> getulbyuser(String username){return upuMapper.getulbyuser(username);}
    public List<Paper> getpaperbyuser(String username){return upuMapper.getpaperbyuser(username);}
    public int insertUPU(String username,int paper_id,String uploadtime){return upuMapper.insertUPU(username,paper_id,uploadtime);}
    public List<Upload_Record> upload_records(String username){return  upuMapper.getUploadRecords(username);}
}
