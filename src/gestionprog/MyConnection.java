/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionprog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author Bani
 */
public  class MyConnection {
  public static Connection getConnection() 
  {       
    Connection con =null;
    try{
    Class.forName("com.mysql.jdbc.Driver");

    con=  DriverManager.getConnection("jdbc:mysql://localhost:3308/gestion","root",""); 
    
       }
    catch(Exception ex)
    {System.out.println(ex.getMessage());}
    
      return  con;
      
  }
}

