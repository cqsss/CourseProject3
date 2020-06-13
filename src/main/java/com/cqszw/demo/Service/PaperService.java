package com.cqszw.demo.Service;

import com.cqszw.demo.Bean.Paper;
import com.cqszw.demo.Bean.Upload_Record;
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
    public List<Upload_Record> getAllwithUsername(){return paperMapper.getAllwithUsername();}
    public String gettopicbyid(int id){return paperMapper.gettopicbyid(id);}
    public int getidByPaper(Paper paper){return paperMapper.getidByPaper(paper.getTopic(),paper.getType(),paper.getAuthor(),paper.getKeyword());}
    public Paper getpaperbyid(int id) {return paperMapper.getpaperbyid(id);}
    public List<Paper> getPaperByType(String type){return paperMapper.getPaperByType(type);}
    public List<Upload_Record> getPaperByTypewithUsername(String type){return paperMapper.getPaperByTypewithUsername(type);}
    public List<Upload_Record> getPaperByUsername(String username){return paperMapper.getPaperByUsername(username);}
    public List<String> getType(){return paperMapper.getType();}
    public int insertPaper(Paper paper){return paperMapper.insertPaper(paper);}
    public int deletePaper(int id){return paperMapper.deletePaper(id);}
    public List<Paper> search(String keyword){return paperMapper.search(keyword);}
    public List<Paper> searchandtype(String type,String keyword){return  paperMapper.searchandtype(type,keyword);}
}
