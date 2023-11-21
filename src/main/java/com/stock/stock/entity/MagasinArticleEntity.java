package com.stock.stock.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "magasin_article", schema = "public", catalog = "stockfinal")
@IdClass(MagasinArticleEntityPK.class)
public class MagasinArticleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_magasin", nullable = false)
    private int idMagasin;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_article", nullable = false)
    private int idArticle;
    @ManyToOne
    @JoinColumn(name = "id_magasin", referencedColumnName = "id_magasin", nullable = false)
    private MagasinEntity magasinByIdMagasin;
    @ManyToOne
    @JoinColumn(name = "id_article", referencedColumnName = "id_article", nullable = false)
    private ArticleEntity articleByIdArticle;

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
        MagasinArticleEntity that = (MagasinArticleEntity) o;
        return idMagasin == that.idMagasin && idArticle == that.idArticle;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMagasin, idArticle);
    }

    public MagasinEntity getMagasinByIdMagasin() {
        return magasinByIdMagasin;
    }

    public void setMagasinByIdMagasin(MagasinEntity magasinByIdMagasin) {
        this.magasinByIdMagasin = magasinByIdMagasin;
    }

    public ArticleEntity getArticleByIdArticle() {
        return articleByIdArticle;
    }

    public void setArticleByIdArticle(ArticleEntity articleByIdArticle) {
        this.articleByIdArticle = articleByIdArticle;
    }
}
