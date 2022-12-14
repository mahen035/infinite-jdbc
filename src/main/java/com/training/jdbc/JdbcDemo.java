package com.training.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDemo {

    public static void main(String... args) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/infinite";
        String user = "root";
        String password = "pass123";
        Connection con = null;

        try{
             con = DriverManager.getConnection(url, user, password);

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from product");
            List<Product> productList = new ArrayList<Product>();
            while(rs.next()){
                Product product = new Product();
                product.setProdId(rs.getInt(1));
                product.setProdName(rs.getString(2));
                product.setProdDesc(rs.getString(3));
                product.setPrice(rs.getDouble(4));
                productList.add(product);

                //System.out.println(rs.getInt(1)+":"+rs.getString(2)+":"+rs.getString(3));
            }

            for(Product pr : productList){
                System.out.println(pr.getProdDesc()+"::"+pr.getPrice());
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
       finally{
            try{
                con.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }

    }
}

// Create a user table in database with username and password fields. Add at least 3 records in user table
// From java code ask user to input username and password. If they match with any one record in db
// then show a message: "Logged in Successfully" else "Incorrect credentials"
