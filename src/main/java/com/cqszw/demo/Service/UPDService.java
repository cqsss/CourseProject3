package com.cqszw.demo.Service;

import com.cqszw.demo.Bean.Paper;
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
    public List<Paper> getpaperbyuser(String username){return updMapper.getpaperbyuser(username);}
    public int insertUPD(String username,int paper_id,String download_time){return updMapper.insertUPD(username,paper_id,download_time);}

}
