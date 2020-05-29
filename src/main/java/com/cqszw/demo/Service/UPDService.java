package com.cqszw.demo.Service;

import com.cqszw.demo.Bean.Paper;
import com.cqszw.demo.Bean.User_Download;
import com.cqszw.demo.Mapper.UPDMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cqs
 * @date 2020/5/27
 */
@Service
public class UPDService {
    @Autowired
    private UPDMapper updMapper;
    public List<User_Download> getdlbyuser(String username){return updMapper.getdlbyuser(username);}
    public List<Paper> getpaperbyuser(String username){return updMapper.getpaperbyuser(username);}
    public int insertUPD(String username,int paper_id,String downloadtime){return updMapper.insertUPD(username,paper_id,downloadtime);}

}
