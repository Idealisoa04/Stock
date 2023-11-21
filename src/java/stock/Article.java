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
public class Article {
    String id;
    String nom;
    int idtype_stock;
    int idunite;
    
    TypeStock type_stock;
    Unite unite;
    boolean estdisponible;
    
    Stock[] stock_dispo;

    
    public Article(String id, String nom, int idtype_stock, int id_unite,Connection co) throws Exception {
        try{
            this.setId(id);
            this.setNom(nom);
            this.setIdtype_stock(idtype_stock,co);
            this.setIdunite(id_unite,co);
        }catch(Exception e){
            throw e;
        }
    }

    public Article(String id, String nom, int idtype_stock, int id_unite) throws Exception {
        try{
            this.setId(id);
            this.setNom(nom);
            this.setIdtype_stock(idtype_stock);
            this.setIdunite(id_unite);
        }catch(Exception e){
            throw e;
        }
    }
    public Article() {
        
    }
    
    public Article(String id,Connection co) throws Exception{
        try{
            this.setId(id);
            this.getDet(co);
        }catch(Exception e){
            throw e;
        }
    }

    public void GetMyTypeStock(Connection co) throws Exception{
        boolean estouvert = false;
        try{
            if(co == null){
                estouvert = true;
                Connect con = new Connect();
                co = con.dbConnect();
            }
            String sql = "select * from type_stock where id=?";
            
            PreparedStatement stmt = co.prepareStatement(sql);
            stmt.setInt(1,this.getIdtype_stock());
            ResultSet res = stmt.executeQuery();
            TypeStock temp = null;
            if (res.next()) {
                temp = new TypeStock(res.getInt("id"),res.getString("nom"));
            } 
            this.setType_stock(temp);
            
        }
        catch(Exception e){
            throw e;
        }
        finally{
            if(estouvert == true){
                co.close();
            }
        }
    }
    
    public void GetMyUnite(Connection co) throws Exception{
        boolean estouvert = false;
        try{
            if(co == null){
                estouvert = true;
                Connect con = new Connect();
                co = con.dbConnect();
            }
            
            String sql = "select * from unite where id=?";
            
            PreparedStatement stmt = co.prepareStatement(sql);
            stmt.setInt(1,this.getIdunite());
            ResultSet res = stmt.executeQuery();
            Unite temp = null;
            if (res.next()) {
                temp = new Unite(res.getInt("id"),res.getString("nom"));
            } 
            this.setUnite(temp);
            
        }
        catch(Exception e){
            throw e;
        }
        finally{
            if(estouvert == true){
                co.close();
            }
        }
    }
    
    public void getDet(Connection co) throws Exception{
        boolean estouvert = false;
        try{
            if(co == null){
                estouvert = true;
                Connect con = new Connect();
                co = con.dbConnect();
            }
            String sql = "select * from article where id=?";
            
            PreparedStatement stmt = co.prepareStatement(sql);
            stmt.setString(1,this.getId());
            
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                this.setIdtype_stock(res.getInt("idtype_stock"),co);
                this.setIdunite(res.getInt("id_unite"));
                this.setNom(res.getString("nom"));
            } 
        }
        catch(Exception e){
            throw e;
        }
        finally{
            if(estouvert == true){
                co.close();
            }
        }
    }
    
    public int countStock(Connection co, String etat, Magasin mag, Timestamp limit) throws Exception{
        int rep = 0;
        boolean estouvert = false;
         try{
            if(co == null){
                estouvert = true;
                Connect con = new Connect();
                co = con.dbConnect();
            }
            String order = this.getType_stock().getCnf();
            String sql = "";PreparedStatement stmt = null;
            if(mag == null){
                sql = "SELECT count(date) FROM stock WHERE id_article = ? and etat = ? ";
                stmt = co.prepareStatement(sql);
                stmt.setString(1,this.getId());
                stmt.setString(2,etat);
            }else{
                sql = "SELECT count(date) FROM stock WHERE id_article = ? and etat = ? and id_magasin = ? and date < ? ";
                stmt = co.prepareStatement(sql);
                stmt.setString(1,this.getId());
                stmt.setString(2,etat);
                stmt.setString(3, mag.getId());
                stmt.setTimestamp(4, limit);
                System.out.println(stmt.toString());
            }
            
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                rep = res.getInt(1);
            }
        }
        catch(Exception e){
            throw e;
        }
        finally{
            if(estouvert == true){
                co.close();
            }
        }
        return rep;
    }
    
    public Stock[] getStock(Connection co, String etat,Magasin magasin, Timestamp limit) throws Exception{
        Stock[] rep = null;
        boolean estouvert = false;
        try{
            if(co == null){
                estouvert = true;
                Connect con = new Connect();
                co = con.dbConnect();
            }
            int count = this.countStock(co,etat,magasin,limit);
            if(count > 0 ){
                String order = this.getType_stock().getCnf();
                String sql = "";PreparedStatement stmt = null;
                if(magasin == null){
                    sql = "SELECT * FROM stock WHERE id_article = ? and etat = ? ORDER BY date " + order;
                    stmt = co.prepareStatement(sql);
                    stmt.setString(1,this.getId());
                    stmt.setString(2,etat);
                }else{
                    sql = "SELECT * FROM stock WHERE id_article = ? and etat = ? and id_magasin= ? and date < ? ORDER BY date " + order;
                    stmt = co.prepareStatement(sql);
                    stmt.setString(1,this.getId());
                    stmt.setString(2,etat);
                    stmt.setString(3,magasin.getId());
                    stmt.setTimestamp(4, limit);
                    System.out.println(stmt.toString());
                }
                ResultSet res = stmt.executeQuery();

                rep = new Stock[count];
                int i = 0 ;
                while (res.next()) {
                    rep[i] = new Stock(res.getInt("id"),this,res.getTimestamp("date"),res.getDouble("quantite"),res.getDouble("montant"),res.getString("etat"));
                    i++;
                }    
            }
        }
        catch(Exception e){
            throw e;
        }
        finally{
            if(estouvert == true){
                co.close();
            }
        }
        return rep;
    }
   
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getIdtype_stock() {
        return idtype_stock;
    }

    public void setIdtype_stock(int idtype_stock,Connection co) throws Exception{
        this.idtype_stock = idtype_stock;
        try{
            this.GetMyTypeStock(co);
            this.setEstdisponible(co);
        }catch(Exception e){
            throw e;
        }
    }

    public TypeStock getType_stock() {
        return type_stock;
    }

    public void setType_stock(TypeStock type_stock){
        this.type_stock = type_stock;
    }

    public int getIdunite() {
        return idunite;
    }

     public void setIdunite(int idunite){
         this.idunite = idunite;
     }
    
    public void setIdunite(int idunite, Connection co) throws Exception{
        this.idunite = idunite;
        try{
            this.GetMyUnite(co);
        }catch(Exception e){
            throw e;
        }
    }

    public Unite getUnite() {
        return unite;
    }

    public void setUnite(Unite unite) {
        this.unite = unite;
    }

    public boolean isEstdisponible() {
        return estdisponible;
    }

    public void Estdisponible(boolean estdisponible)  {
        this.estdisponible = estdisponible;
    }
    
    public void setEstdisponible(Connection co) throws Exception {
        boolean rep = true;
        try{
            Stock[] stock_dispo = this.getStock(co,"1",null,null);
            this.setStock_dispo(stock_dispo);
            if(stock_dispo == null){
                rep = false;
            }
        }catch(Exception e){
            throw e;
        }
        this.Estdisponible(rep);
    }

    public void setIdtype_stock(int idtype_stock) {
        this.idtype_stock = idtype_stock;
    }

    public Stock[] getStock_dispo() {
        return stock_dispo;
    }

    public void setStock_dispo(Stock[] stock_dispo) {
        this.stock_dispo = stock_dispo;
    }
}
