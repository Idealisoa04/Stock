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
import java.util.ArrayList;

/**
 *
 * @author Idealisoa
 */
public class List {
    Article[] articles;
    Magasin[] magasins;

    public List() throws Exception{
        try{
            this.setMagasins(this.getMagasins(null));
            this.setArticles(this.getArticlesDispo(null));
        }catch(Exception e){
            throw e;
        }
    }

    public Magasin[] getMagasins(Connection co) throws Exception{
        Magasin[] rep = null;
        boolean estouvert = false;
        try{
            if(co == null){
                estouvert = true;
                Connect con = new Connect();
                co = con.dbConnect();
            }
            ArrayList<Magasin> lists = new ArrayList<Magasin>();
            String sql = "select * from magasin ";
            
            PreparedStatement stmt = co.prepareStatement(sql);
            
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                Magasin temp = new Magasin(res.getString("id"),res.getString("nom"));
                lists.add(temp);
            } 
            
            rep = new Magasin[lists.size()];
            for(int i = 0 ; i < lists.size() ; i ++){
                rep[i] = lists.get(i);
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
    
    public Article[] getArticles(Connection co) throws Exception{
        Article[] rep = null;boolean estouvert = false;
        try{
            if(co == null){
                estouvert = true;
                Connect con = new Connect();
                co = con.dbConnect();
            }
            ArrayList<String> article_id = new ArrayList<String>();
            String sql = "select * from article";
            
            PreparedStatement stmt = co.prepareStatement(sql);
            
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                article_id.add(res.getString("id"));
            } 
            
            rep = new Article[article_id.size()];
            for(int i = 0 ; i < article_id.size() ; i ++){
                rep[i] = new Article(article_id.get(i),co);
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
    
    public Article[] getArticlesDispo(Connection co) throws Exception{
        Article[] rep = null;
        try {
            Article[] all = this.getArticles(co);
            rep = all;
            ArrayList<Article> dispo = new ArrayList<Article>();
            for(int i=0 ; i<all.length ; i++){
                if(all[i].isEstdisponible() == true){
                    dispo.add(all[i]);
                }
            }
            rep = new Article[dispo.size()];
            for(int i = 0 ; i<dispo.size() ; i++){
                rep[i] = dispo.get(i);
            }
            System.out.println("hereee");
        }catch(Exception e){
            throw e;
        }
        return rep;
    }
    
    public Article[] getArticles() {
        return articles;
    }

    public void setArticles(Article[] articles) {
        this.articles = articles;
    }

    public Magasin[] getMagasins() {
        return magasins;
    }

    public void setMagasins(Magasin[] magasins) {
        this.magasins = magasins;
    }
    
    
}
