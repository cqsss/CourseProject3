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
    public String gettopicbyid(int id){return paperMapper.gettopicbyid(id);};
    public int getnum(){return paperMapper.getnum();}
    public int getmaxid(){return paperMapper.getmaxid();};
    public Paper getpaperbyid(int id) {return paperMapper.getpaperbyid(id);};
    public List<Paper> getPaperByType(String type){return paperMapper.getPaperByType(type);};
    public List<String> getType(){return paperMapper.getType();}
    public int insertPaper(Paper paper){return paperMapper.insertPaper(paper);};
    public int deletePaper(int id){return paperMapper.deletePaper(id);};
    public List<Paper> search(String keyword){return paperMapper.search(keyword);}
    public List<Paper> searchandtype(String type,String keyword){return  paperMapper.searchandtype(type,keyword);}
}
