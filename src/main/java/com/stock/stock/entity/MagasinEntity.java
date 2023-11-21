package com.stock.stock.entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "magasin", schema = "public", catalog = "stockfinal")
public class MagasinEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_magasin", nullable = false)
    private int idMagasin;
    @Basic
    @Column(name = "nom", nullable = false, length = 50)
    private String nom;
    @OneToMany(mappedBy = "magasinByIdMagasin")
    private Collection<MagasinArticleEntity> magasinArticlesByIdMagasin;

    public int getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(int idMagasin) {
        this.idMagasin = idMagasin;
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
        MagasinEntity that = (MagasinEntity) o;
        return idMagasin == that.idMagasin && Objects.equals(nom, that.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMagasin, nom);
    }

    public Collection<MagasinArticleEntity> getMagasinArticlesByIdMagasin() {
        return magasinArticlesByIdMagasin;
    }

    public void setMagasinArticlesByIdMagasin(Collection<MagasinArticleEntity> magasinArticlesByIdMagasin) {
        this.magasinArticlesByIdMagasin = magasinArticlesByIdMagasin;
    }
}
