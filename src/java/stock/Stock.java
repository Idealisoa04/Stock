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
import java.util.ArrayList;

/**
 *
 * @author Idealisoa
 */
public class Stock {
    int id;
    Article article;
    Timestamp date;
    double quantite;
    double montant;
    String etat;
    
    Magasin magasin;

    public Stock(int id, Article article, Timestamp date, double quantite, double montant, String etat) {
        this.id = id;
        this.article = article;
        this.date = date;
        this.quantite = quantite;
        this.montant = montant;
        this.etat = etat;
    }

    public Stock(int id, Article article, Timestamp date, double quantite, double montant, String etat, Magasin magasin) {
        this.id = id;
        this.article = article;
        this.date = date;
        this.quantite = quantite;
        this.montant = montant;
        this.etat = etat;
        this.magasin = magasin;
    }

    public Stock() {
    }
    
    
    public double take(double quantite,Timestamp date,Magasin magasin,Connection co) throws Exception{
        boolean estouvert = false;
        double qtt = quantite ; 
        double base = this.getResteHisto(co);
        
        double reste = base - qtt;
        
        double total = 0;
        Historique histo = new Historique();
            histo.setDate(date);
            histo.setId_article(this.getArticle().getId());
            histo.setId_magasin(magasin.getId());
            histo.setId_stock(this.getId());
        try{
            if(co == null){
                estouvert = true;
                Connect con = new Connect();
                co = con.dbConnect();
            }
                if(reste < 0 ){
                    
                    System.out.println("eto");
                    total = base * this.getMontant();

                    histo.setQuantite(base);
                    histo.setPu(this.getMontant());
                    histo.setMontant(total);

                    histo.insert(co);

                    this.update(co,"0");
                    
                    qtt = Math.abs(reste);
                } else if(reste > 0 ){
                    
                    System.out.println("eto hoa");
                    total = qtt * this.getMontant();
                    
                    histo.setQuantite(qtt);
                    histo.setPu(this.getMontant());
                    histo.setMontant(total);

                    histo.insert(co);
                    
                    qtt = 0;
                }else{
                    
                    System.out.println("eto ftsn");
                    total = qtt * this.getMontant();
                    
                    histo.setQuantite(qtt);
                    histo.setPu(this.getMontant());
                    histo.setMontant(total);

                    histo.insert(co);
                    this.update(co,"0");
                    qtt = 0;
                }
        }catch(Exception e){
            co.rollback();
            throw e;
        }finally{
            if(estouvert == true){
                co.close();
            }
        }
        return qtt;
    }
    
    public void update(Connection co, String etat) throws Exception{
        boolean estouvert = false;
        try{
            if(co == null){
                estouvert = true;
                Connect con = new Connect();
                co = con.dbConnect();
            }
                String sql = "update stock set etat = ? where id = ?";
                PreparedStatement stmt = co.prepareStatement(sql);
               
                stmt.setString(1, etat);
                stmt.setInt(2, this.getId());
                
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
    
    public Historique[] getAllHisto(Connection co) throws Exception{
        Historique[] rep = null;
        boolean estouvert = false;
        try{
            if(co == null){
                estouvert = true;
                Connect con = new Connect();
                co = con.dbConnect();
            }
                ArrayList<Historique> histos = new ArrayList<Historique>();
            
                String sql = "select * from historique where id_stock = ?";
                PreparedStatement stmt = co.prepareStatement(sql);
               
                stmt.setInt(1, this.getId());
               
                ResultSet res = stmt.executeQuery();
                while(res.next()){
                    Historique temp = new Historique(res.getInt("id_stock"),res.getString("id_article"),res.getString("id_magasin"),res.getDouble("quantite"),res.getDouble("pu"), res.getDouble("montant"),res.getTimestamp("date"));
                    histos.add(temp);
                }
                
                rep = new Historique[histos.size()];
                for(int i=0; i<histos.size() ; i++){
                    rep[i] = histos.get(i);
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
    
    public double getResteHisto(Connection co) throws Exception{
        double reste = 0;
        try{
            Historique[] all = this.getAllHisto(co);
            double sum = 0;
            if(all != null){
                for(int i = 0 ; i<all.length ; i++){
                    sum = sum + all[i].getQuantite() ; 
                }
            }
            reste = this.getQuantite() - sum;
        }catch(Exception e){
            throw e;
        }
        return reste;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Magasin getMagasin() {
        return magasin;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }
    
    
}
