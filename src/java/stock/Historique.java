/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock;

import Connection.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 *
 * @author Idealisoa
 */
public class Historique {
    int id_stock;
    String id_article;
    String id_magasin;
    double quantite;
    double pu;
    double montant;
    Timestamp date;

    public Historique() {
    }

    public Historique(int id_stock, String id_article, String id_magasin, double quantite, double pu, double montant, Timestamp date) {
        this.id_stock = id_stock;
        this.id_article = id_article;
        this.id_magasin = id_magasin;
        this.quantite = quantite;
        this.pu = pu;
        this.montant = montant;
        this.date = date;
    }
    
    public void insert(Connection co)throws Exception{
        boolean estouvert = false;
        try{
            if(co == null){
                estouvert = true;
                Connect con = new Connect();
                co = con.dbConnect();
            }
                String sql = "insert into historique(id_stock,id_article,id_magasin,quantite,pu,montant,date) values(?,?,?,?,?,?,?)";
                PreparedStatement stmt =  co.prepareStatement(sql);
                stmt.setInt(1, this.getId_stock());
                stmt.setString(2, this.getId_article());
                stmt.setString(3, this.getId_magasin());
                stmt.setDouble(4, this.getQuantite());
                stmt.setDouble(5, this.getPu());
                stmt.setDouble(6, this.getMontant());
                stmt.setTimestamp(7, this.getDate());
                
                System.out.println(stmt.toString());
                
                stmt.executeUpdate();
                co.commit();
    }
        catch(Exception e){
            co.rollback();
            throw e;
        }
        finally{
            if(estouvert == true){
                co.close();
            }
        }
    }

    public int getId_stock() {
        return id_stock;
    }

    public void setId_stock(int id_stock) {
        this.id_stock = id_stock;
    }
  

    public String getId_article() {
        return id_article;
    }

    public void setId_article(String id_article) {
        this.id_article = id_article;
    }

    public String getId_magasin() {
        return id_magasin;
    }

    public void setId_magasin(String id_magasin) {
        this.id_magasin = id_magasin;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public double getPu() {
        return pu;
    }

    public void setPu(double pu) {
        this.pu = pu;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
    
    
}
