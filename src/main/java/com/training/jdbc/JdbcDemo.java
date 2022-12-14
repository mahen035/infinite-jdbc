package com.training.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class JdbcDemo {

    public static void main(String... args) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Scanner sc = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/infinite";
        String user = "root";
        String password = "pass123";
        Connection con = null;

        try{
             con = DriverManager.getConnection(url, user, password);
             System.out.println("Enter product id you want to search:");
             int id = sc.nextInt();

             String sql = "{ call get_products(?) }";
            //Statement stmt = con.createStatement();  // Statement is used for static queries

           // PreparedStatement stmt = con.prepareStatement("select * from product where prod_id = ?"); // Prepared statement is used for dynamic queries

            CallableStatement stmt = con.prepareCall(sql);

            stmt.setDouble(1, 100);

            ResultSet rs = stmt.executeQuery();
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

// Use PreparedStatement to insert 2 records in your table. Get the values from user
