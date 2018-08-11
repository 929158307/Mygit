package service;

import Mapper.SolrDao;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
;
import pojo.Product;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

@Service
public class Solrservice {
    @Autowired
    private SolrDao solrDao;
    @Autowired
    private SolrServer solrServer;
    public void addDocument() {
        ArrayList<Product> productList = solrDao.findProduct();
        List<SolrInputDocument> Listdocumets = new ArrayList<>();
        for (Product product : productList) {
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id", product.getId());
            document.addField("name", product.getName());
            document.addField("price", product.getPrice());
            Listdocumets.add(document);
        }
        try {
            solrServer.add(Listdocumets);
            solrServer.commit();
        } catch(Exception ex) { ex.printStackTrace(); }


    }
        public void seeList(){
            ArrayList<Product> product = solrDao.findProduct();
             System.out.println(product);
        }
        public void find()  {
            SolrQuery query = new SolrQuery();
            query.setQuery("*:*");
            query.addFilterQuery("name:美");
            try {
                QueryResponse response = solrServer.query(query);
                SolrDocumentList solrDocuments = response.getResults();
                List<Product> productList = new ArrayList<>();
                for(SolrDocument solrDocument : solrDocuments){
                    Product product = new Product();
                    product.setId(Integer.parseInt((String) solrDocument.get("id")));
                    product.setName((String)solrDocument.get("name"));
                    product.setPrice((String)solrDocument.get("price"));
                    productList.add(product);
                }
                System.out.println(productList);
            } catch(Exception ex) { ex.printStackTrace(); }
        }
}
