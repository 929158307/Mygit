package Mapper;

import org.springframework.stereotype.Repository;
import pojo.Product;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class SolrDao {
    public ArrayList<Product> findProduct(){
        ArrayList<Product> list = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/solr?characterEncoding=utf-8", "root", "root");
            String sql = "select p.pid,p.name,p.price from products p LIMIT 100 ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Product product = new Product();
                product.setId(Integer.parseInt(resultSet.getString("pid")));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getString("price"));
                list.add(product);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
