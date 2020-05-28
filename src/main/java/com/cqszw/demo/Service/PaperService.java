package com.cqszw.demo.Service;

import com.cqszw.demo.Bean.Paper;
import com.cqszw.demo.Mapper.PaperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cqs
 * @date 2020/5/27
 */
@Service
public class PaperService {
    @Autowired
    private PaperMapper paperMapper;
    public List<Paper> getAll(){return  paperMapper.getAll();}
    public String gettopicbyid(int paper_id){return paperMapper.gettopicbyid(paper_id);};
    public int gernum(){return paperMapper.getnum();}

}
