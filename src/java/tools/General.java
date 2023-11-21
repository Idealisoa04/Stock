/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import Connection.Connect;
import java.sql.Connection;

/**
 *
 * @author Idealisoa
 */
public class General {
    String table;
    String[] attributs;
    Object[] valeur;

    public General(String table, String[] attributs, Object[] valeur) {
        this.table = table;
        this.attributs = attributs;
        this.valeur = valeur;
    }

    public General() {
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String[] getAttributs() {
        return attributs;
    }

    public void setAttributs(String[] attributs) {
        this.attributs = attributs;
    }

    public Object[] getValeur() {
        return valeur;
    }

    public void setValeur(Object[] valeur) {
        this.valeur = valeur;
    }
    
    public void insert(Connection co) throws Exception{
        boolean estouvert = false;
        try{
            if(co == null){
                estouvert = true;
                Connect con = new Connect();
                co = con.dbConnect();
            }
            
        }
        catch(Exception e){
            
        }
        finally{
            if(estouvert == true){
                co.close();
            }
        }
    }
    
    public String setCommande(){
        String commande = "";
        
        return commande;
    }
}
