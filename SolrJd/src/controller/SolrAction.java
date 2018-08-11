package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.Solrservice;

@Controller
public class SolrAction {
    @Autowired
    private Solrservice solrservice;
    @RequestMapping("addDocument")
    public void addDocument(){
        solrservice.addDocument();
    }
    @RequestMapping("seeList")
    public void seeList(){
        solrservice.seeList();
    }
    @RequestMapping("find")
    public void findList(){
        solrservice.find();
    }
}
