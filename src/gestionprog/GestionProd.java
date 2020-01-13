/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionprog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bani
 */
public class GestionProd {
           public void insertUpdateDeletProduit(char operation,Integer id,String name,String ref,String  qlttotale,String qltrestant,String prix)
            {
                Connection con=MyConnection.getConnection();
                PreparedStatement ps;
                //i for insert 
                if(operation=='i')
                {
                    try {
                        ps=con.prepareStatement("INSERT INTO produits(nom,ref,qltotal,qlrest,prix) VALUES (?,?,?,?,?)");
                        ps.setString(1, name);
                        ps.setString(2, ref);
                        ps.setString(3, qlttotale);
                        ps.setString(4, qltrestant);
                        ps.setString(5, prix);

                        if(ps.executeUpdate()>0)
                        { JOptionPane.showMessageDialog(null, "Produit Insert");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionProd.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                 if(operation=='u')
                {
                    try {
                        ps=con.prepareStatement("UPDATE produits SET nom = ?,ref = ?, qltotal = ?, qlrest = ?, prix= ? WHERE num = ?");
                        ps.setString(1, ref);
                        ps.setString(2, name);
                        ps.setString(3, qlttotale);
                        ps.setString(4, qltrestant);
                        ps.setString(5, prix);
                        ps.setInt(6, id);

                        if(ps.executeUpdate()>0)
                        { JOptionPane.showMessageDialog(null, "Produit updated ");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionProd.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(operation=='d')
                {
                    try {
                        ps=con.prepareStatement("DELETE FROM `produits` WHERE num = ?");
                       
                        ps.setInt(1, id);

                        if(ps.executeUpdate()>0)
                        { JOptionPane.showMessageDialog(null, "Produit Deleted ");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionProd.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                 
            }
     public void fillProduitJtable(JTable table ,String valueToString)  
        {
           Connection con= MyConnection.getConnection();
           PreparedStatement ps;
        try {
            ps=con.prepareStatement("SELECT * FROM produits WHERE CONCAT(`nom`,`ref`,`qltotal`,`qlrest`,`prix`)LIKE ?");
            ps.setString(1, "%"+valueToString+"%");
            ResultSet rs=ps.executeQuery();
            ResultSet rss;
            DefaultTableModel model=(DefaultTableModel) table.getModel();
            Object[] row;
            while(rs.next() )
                 { 
                    
                     row= new Object[6];
                     row[0]=rs.getString(1);
                     row[1]=rs.getString(2);
                     row[2]=rs.getString(3);
                     row[3]=rs.getString(4);
                     row[4]=rs.getString(5);
                     row[5]=rs.getString(6);
                     model.addRow(row);
                 }
            }
         catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
   
        }
     
      
    
}
