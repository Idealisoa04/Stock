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
public class Etat {
    Article article;
    Magasin magasin;
    double qttinitial;
    double qttfinal;
    double qttsortie;
    double qttentree;
    double pump;
    double totalmp;
    Timestamp debut;
    Timestamp fin;

    public Etat(Article article,Magasin magasin ,double qttinitial, double qttfinal, double qttsortie, double qttentree, double pump, double totalmp, Timestamp debut, Timestamp fin) {
        this.article = article;
        this.magasin = magasin;
        this.qttinitial = qttinitial;
        this.qttfinal = qttfinal;
        this.qttsortie = qttsortie;
        this.qttentree = qttentree;
        this.pump = pump;
        this.totalmp = totalmp;
        this.debut = debut;
        this.fin = fin;
    }
    
    public Etat(){
        
    }
    
    public Etat(Article article,Magasin magasin,Timestamp debut,Timestamp fin, Connection co) throws Exception{
        this.setArticle(article);
        this.setMagasin(magasin);
        this.setDebut(debut);
        this.setFin(fin);
        try{
            double initial = this.getEtatInitial(this.getArticle(), this.getMagasin(), this.getDebut(), co);
            double finale = this.getEtatInitial(this.getArticle(), this.getMagasin(), this.getFin(), co);
            double pump = this.getPump(this.getArticle(), this.getMagasin(), this.getDebut(), this.getFin(), co);
            double total = this.getTotalmp(this.getArticle(), this.getMagasin(), this.getDebut(), this.getFin(), co);
            double entree = this.getTotalEntree(this.getArticle(), this.getMagasin(), this.getDebut(), this.getFin(), co);
            
            this.setQttinitial(initial);
            this.setQttfinal(finale);
            this.setPump(pump);
            this.setTotalmp(total);
            this.setQttentree(entree);
        }catch(Exception e){
            throw e;
        }
    }
    
    public double getEtatInitial(Article article,Magasin magasin,Timestamp fin,Connection co) throws Exception{
        double init = 0;
        try{
            Historique[] sortie = this.getAllSortie(article, magasin, null, fin, co);
            Stock[] entree = this.getAllEntree(article, magasin, null, fin, co);
            double totals = 0; double totale = 0;
            for(int i = 0 ; i<sortie.length ; i++){
                totals = totals + sortie[i].getQuantite() ; 
            }
            for(int i = 0 ; i<entree.length ; i++){
                totale = totale + entree[i].getQuantite() ; 
            }
            init  = totale - totals;
        }catch(Exception e){
            throw e;
        }
        return init;
    }
    
    
    public double getPump(Article article,Magasin magasin,Timestamp debut, Timestamp fin,Connection co) throws Exception{
        double pump = 0 ; 
        try{
            Historique[] sortie = this.getAllSortie(article, magasin, debut, fin, co);
            Stock[] entree = this.getAllEntree(article, magasin,null, fin, co);
            double totalpu = 0 ; double totalqtt = 0;
            for(int i=0 ; i<sortie.length ; i++){
                totalpu = totalpu + (sortie[i].getQuantite() * sortie[i].getPu());
                totalqtt = totalqtt + sortie[i].getQuantite();
            }
            this.setQttsortie(totalqtt);
            pump = totalpu / totalqtt;
        }catch (Exception e){
            throw e;
        }
        return pump;
    }
    
    public double getTotalmp(Article article,Magasin magasin,Timestamp debut, Timestamp fin,Connection co) throws Exception{
        double pump = 0 ; 
        try{
            Historique[] sortie = this.getAllSortie(article, magasin, debut, fin, co);
            Stock[] entree = this.getAllEntree(article, magasin,null, fin, co);
            double totalmt = 0 ; double totalqtt = 0;
            for(int i=0 ; i<sortie.length ; i++){
                totalmt = totalmt + sortie[i].getMontant();
                totalqtt = totalqtt + 1;
            }
            //pump = totalmt / totalqtt;
            pump = totalmt;
        }catch (Exception e){
            throw e;
        }
        return pump;
    }
    
    public double getTotalEntree(Article article,Magasin magasin,Timestamp debut, Timestamp fin,Connection co) throws Exception{
        double pump = 0 ; 
        try{
            Stock[] entree = this.getAllEntree(article, magasin,debut, fin, co);double totalqtt = 0;
            for(int i=0 ; i<entree.length ; i++){
                totalqtt = totalqtt + entree[i].getQuantite();
            }
            pump = totalqtt;
        }catch (Exception e){
            throw e;
        }
        return pump;
    }
    
    public Historique[] getAllSortie(Article article,Magasin magasin,Timestamp debut, Timestamp fin,Connection co) throws Exception{
        Historique[] rep = null;
        boolean estouvert = false;
        try{
            if(co == null){
                estouvert = true;
                Connect con = new Connect();
                co = con.dbConnect();
            }
                ArrayList<Historique> histos = new ArrayList<Historique>();
                String sql = "";
                PreparedStatement stmt = co.prepareStatement(sql);
                if(magasin == null){
                    if(debut == null){
                        sql = "select * from historique where id_article = ? and date < ? ";
                        stmt.setString(1, article.getId());
                        stmt.setTimestamp(2, fin);
                    }
                    sql = "select * from historique where id_article = ? and date > ? and date < ? ";
                    stmt.setString(1, article.getId());
                    stmt.setTimestamp(2, debut);
                    stmt.setTimestamp(3, fin);
                } else {
                    if(debut == null){
                        sql = "select * from historique where id_article = ? and id_magasin = ? and date < ?";
                        stmt.setString(1, article.getId());
                        stmt.setString(2, magasin.getId());
                        stmt.setTimestamp(3, fin);
                    }
                    sql = "select * from historique where id_article = ? and id_magasin = ? and date > ? and date < ?";
                    stmt.setString(1, article.getId());
                    stmt.setString(2, magasin.getId());
                    stmt.setTimestamp(3, debut);
                    stmt.setTimestamp(4, fin);
                }
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
    
    public Stock[] getAllEntree(Article article,Magasin magasin,Timestamp debut,Timestamp fin,Connection co) throws Exception{
        Stock[] rep = null;
        boolean estouvert = false;
        try{
            if(co == null){
                estouvert = true;
                Connect con = new Connect();
                co = con.dbConnect();
            }
            ArrayList<Stock> stocks = new ArrayList<Stock>();
            String sql = "";
            PreparedStatement stmt = null;
            if(magasin == null){
                if(debut == null){
                    sql = "SELECT * FROM stock WHERE id_article = ? and date < ? " ;
                    stmt = co.prepareStatement(sql);
                    stmt.setString(1,article.getId());
                    stmt.setTimestamp(2,fin);
                }
                    sql = "SELECT * FROM stock WHERE id_article = ? and date < ? and date > ?" ;
                    stmt = co.prepareStatement(sql);
                    stmt.setString(1,article.getId());
                    stmt.setTimestamp(2,fin);
                    stmt.setTimestamp(3,debut);
            }else{
                if(debut == null){
                    sql = "SELECT * FROM stock WHERE id_article = ? and id_magasin= ? and date < ? ";
                    stmt = co.prepareStatement(sql);
                    stmt.setString(1,article.getId());
                    stmt.setString(2,magasin.getId());
                    stmt.setTimestamp(3, fin);
                }
                    sql = "SELECT * FROM stock WHERE id_article = ? and id_magasin= ? and date < ? and date > ?";
                    stmt = co.prepareStatement(sql);
                    stmt.setString(1,article.getId());
                    stmt.setString(2,magasin.getId());
                    stmt.setTimestamp(3, fin);
                    stmt.setTimestamp(4, debut);
            }
            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                    Stock temp = new Stock(res.getInt("id"),article,res.getTimestamp("date"),res.getDouble("quantite"),res.getDouble("montant"),res.getString("etat"),magasin);
                    stocks.add(temp);
            }  
            
            rep = new Stock[stocks.size()];
            for(int i = 0 ; i<stocks.size() ; i++){
                rep[i] = stocks.get(i);
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

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Magasin getMagasin() {
        return magasin;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }

    public double getQttinitial() {
        return qttinitial;
    }

    public void setQttinitial(double qttinitial) {
        this.qttinitial = qttinitial;
    }

    public double getQttfinal() {
        return qttfinal;
    }

    public void setQttfinal(double qttfinal) {
        this.qttfinal = qttfinal;
    }

    public double getQttsortie() {
        return qttsortie;
    }

    public void setQttsortie(double qttsortie) {
        this.qttsortie = qttsortie;
    }

    public double getQttentree() {
        return qttentree;
    }

    public void setQttentree(double qttentree) {
        this.qttentree = qttentree;
    }

    public double getPump() {
        return pump;
    }

    public void setPump(double pump) {
        this.pump = pump;
    }

    public double getTotalmp() {
        return totalmp;
    }

    public void setTotalmp(double totalmp) {
        this.totalmp = totalmp;
    }

    public Timestamp getDebut() {
        return debut;
    }

    public void setDebut(Timestamp debut) {
        this.debut = debut;
    }

    public Timestamp getFin() {
        return fin;
    }

    public void setFin(Timestamp fin) {
        this.fin = fin;
    }
    
    
}
