package com.stock.stock.entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "article", schema = "public", catalog = "stockfinal")
public class ArticleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_article", nullable = false)
    private int idArticle;
    @Basic
    @Column(name = "nom", nullable = false, length = 50)
    private String nom;
    @OneToMany(mappedBy = "articleByIdArticle")
    private Collection<MagasinArticleEntity> magasinArticlesByIdArticle;
    @Basic
    @Column(name = "identifiant", nullable = false, length = 20)
    private String identifiant;

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleEntity that = (ArticleEntity) o;
        return idArticle == that.idArticle && Objects.equals(nom, that.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idArticle, nom);
    }

    public Collection<MagasinArticleEntity> getMagasinArticlesByIdArticle() {
        return magasinArticlesByIdArticle;
    }

    public void setMagasinArticlesByIdArticle(Collection<MagasinArticleEntity> magasinArticlesByIdArticle) {
        this.magasinArticlesByIdArticle = magasinArticlesByIdArticle;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }
}
