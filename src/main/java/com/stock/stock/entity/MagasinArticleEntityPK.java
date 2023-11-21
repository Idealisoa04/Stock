package com.stock.stock.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class MagasinArticleEntityPK implements Serializable {
    @Column(name = "id_magasin", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMagasin;
    @Column(name = "id_article", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idArticle;

    public int getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(int idMagasin) {
        this.idMagasin = idMagasin;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MagasinArticleEntityPK that = (MagasinArticleEntityPK) o;
        return idMagasin == that.idMagasin && idArticle == that.idArticle;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMagasin, idArticle);
    }
}
