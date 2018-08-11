package cn.itcast.core.service.ServiceImpl;

import cn.itcast.core.Mapper.UserMapper;
import cn.itcast.core.pojo.User;
import cn.itcast.core.service.UserService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.solr.common.SolrInputDocument;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SolrServer solrServer;
    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }
    @Override
    public void addIndex(){
        List<User> userList = userMapper.findAll();
        List<SolrInputDocument> Listdocumets = new ArrayList<>();
        for(User user : userList){
            SolrInputDocument solrDocument = new  SolrInputDocument();
            solrDocument.setField("id",user.getId());
            solrDocument.setField("name",user.getName());
            solrDocument.setField("birthday",user.getBirthday());
            Listdocumets.add(solrDocument);
        }
        try {
            solrServer.add(Listdocumets);
            solrServer.commit();
        } catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("*:*");
        try {
            QueryResponse response = solrServer.query(solrQuery);
            SolrDocumentList results = response.getResults();

            for(SolrDocument solrDocument : results){
                User user = new User();
                user.setId(Integer.parseInt((String)solrDocument.get("id")));
                user.setName((String) solrDocument.get("name"));
                user.setBirthday((String) solrDocument.get("birthday"));
                users.add(user);
            }
        } catch(Exception ex) { ex.printStackTrace(); }
        return users;
    }
}
