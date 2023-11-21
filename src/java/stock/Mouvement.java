/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock;

import java.sql.Connection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author Idealisoa
 */
public class Mouvement {
    Timestamp sortie;
    Article article;
    double quantite;
    Magasin magasin;
    
    Stock[] stock_mag;
    
    public Mouvement(String sortie, String id_article, String quantite, String id_magasin, Connection co) throws Exception{
        try{
            this.setArticle(new Article(id_article,co));
            this.setMagasin(id_magasin);
            this.setSortie(sortie);
            this.setQuantite(quantite);
        }catch(Exception e){
            throw e;
        }
    }

    public Mouvement(Timestamp sortie, Article article, double quantite, Magasin magasin) {
        this.sortie = sortie;
        this.article = article;
        this.quantite = quantite;
        this.magasin = magasin;
    }
    
    public void execute_sortie(Connection co) throws Exception{
        try{
            Stock[] stock_mag = this.getStock_mag();
            if(stock_mag != null){
               double qtt = stock_mag[0].take(this.getQuantite(), this.getSortie(),this.getMagasin(), co);
               int ind = 1;
               while(qtt != 0){
                   qtt = stock_mag[ind].take(qtt, this.getSortie(),this.getMagasin(), co);
                   ind++;
               }
            }
        }catch(Exception e){
            throw e;
        }
    }

    public Timestamp getSortie() {
        return sortie;
    }

    public void setSortie(Timestamp sortie) throws Exception {
        LocalDateTime time = sortie.toLocalDateTime();
        LocalDateTime now = LocalDateTime.now();
        if(time.isAfter(now)){
           throw new Exception("La date est invalide, date dans le futur"); 
        }else{
            this.sortie = sortie;
        }
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public double getQuantite() {
        return quantite;
    }   

    public void setQuantite(double quantite) throws Exception {
        if(quantite<0){
            throw new Exception("valeur negative pour la quantité");
        }else{
            Stock[] stock_mag = this.getArticle().getStock(null,"1",this.getMagasin(),this.getSortie());
            if(stock_mag == null){
                throw new Exception("Stock insuffisant pour cette article dans ce magasin , le stock n'est pas encore disponible à cette date");
            }
            double qtt_total = 0 ;
            if(stock_mag != null){
                this.setStock_mag(stock_mag);
                for(int i=0 ; i<stock_mag.length ; i ++){
                    qtt_total = qtt_total + stock_mag[i].getResteHisto(null);
                }
                if(qtt_total<quantite){
                    throw new Exception("Stock insuffisant pour cette article");
                }else{
                    this.quantite = quantite;
                }
            }
        }
    }

    public Magasin getMagasin() {
        return magasin;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }

    private void setMagasin(String id_magasin) {
        Magasin mag = new Magasin();
        mag.setId(id_magasin);
        this.setMagasin(mag);
    }

    private void setQuantite(String quantite) throws Exception {
        double qtt = Double.parseDouble(quantite);
        try{
            this.setQuantite(qtt);
        }catch(Exception e){
            throw e;
        }
    }

    private void setSortie(String datetime) throws Exception{
       try {
           LocalDateTime localDateTime = LocalDateTime.parse(datetime);
           Timestamp time = Timestamp.valueOf(localDateTime);
           this.setSortie(time);
       }catch(Exception e){
           throw e;
       }
     }

    public Stock[] getStock_mag() {
        return stock_mag;
    }

    public void setStock_mag(Stock[] stock_mag) {
        this.stock_mag = stock_mag;
    }
    
    
}
