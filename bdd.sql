create database stock1;
    \c stock1

CREATE TABLE Magasin(
                        Id_Magasin SERIAL,
                        nom VARCHAR(50)  NOT NULL,
                        PRIMARY KEY(Id_Magasin),
                        UNIQUE(nom)
);

CREATE TABLE Article(
                        Id_Article SERIAL,
                        nom VARCHAR(50)  NOT NULL,
                        PRIMARY KEY(Id_Article),
                        UNIQUE(nom)
);

CREATE TABLE Magasin_Article(
                                Id_Magasin INTEGER,
                                Id_Article INTEGER,
                                PRIMARY KEY(Id_Magasin, Id_Article),
                                FOREIGN KEY(Id_Magasin) REFERENCES Magasin(Id_Magasin),
                                FOREIGN KEY(Id_Article) REFERENCES Article(Id_Article)
);

