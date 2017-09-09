    -- INITIALIZE DATABASE SCHEMA

CREATE SCHEMA XF AUTHORIZATION DBA
CREATE TABLE Country (
    CountryID INT NOT NULL,
    Name NVARCHAR(50) NOT NULL,
    PRIMARY KEY (CountryID)
)

CREATE TABLE City (
    CityID INT NOT NULL,
    CountryID INT NOT NULL,
    Name NVARCHAR(50) NOT NULL,
    Population INT NOT NULL,
    PRIMARY KEY (CityID),
    FOREIGN KEY (CountryID) REFERENCES  Country(CountryID)
)

CREATE TABLE Buildnig (
    BuildnigID INT NOT NULL,
    CityID INT NOT NULL,
    Name NVARCHAR(50) NOT NULL,
    Floors INT NOT NULL,
    PRIMARY KEY (BuildnigID),
    FOREIGN KEY (CityID) REFERENCES  City(CityID)
);

    -- STATEMENTS GROUP SEPARATION

SET SCHEMA XF;

    -- STATEMENTS GROUP SEPARATION

    -- COUNTRIES

INSERT INTO Country VALUES (1, 'Poland'), (2, 'Ukraine'), (3 , 'Brazil'), (4 , 'Germany'), (5 , 'Italy');

    -- CITIES

INSERT INTO City VALUES (1, 1, 'Warsaw', 70), (2, 1, 'Kraków', 50), (3, 1, 'Łódź', 40);
INSERT INTO City VALUES (4, 2, 'Kiev', 90), (5, 2, 'Kharkiv', 80), (6, 2, 'Dnipro', 50);
INSERT INTO City VALUES (7, 3, 'São Paulo', 200), (8, 3, 'Rio de Janeiro', 150), (9, 3, 'Brasília', 100);
INSERT INTO City VALUES (10, 4, 'Berlin', 150), (11, 4, 'Hamburg', 170), (12, 4, 'München', 190);
INSERT INTO City VALUES (13, 5, 'Rome', 100), (14, 5, 'Milan', 90), (15, 5, 'Naples', 70);

    -- BUILDINGS

INSERT INTO Buildnig VALUES (1, 1, 'KievB1', 5), (2, 1, 'KievB2', 9), (3, 1, 'KievB3', 4);
INSERT INTO Buildnig VALUES (4, 2, 'KharkivB1', 5), (5, 2, 'KharkivB2', 25), (6, 2, 'KharkivB3', 14);
INSERT INTO Buildnig VALUES (7, 3, 'DniproB1', 21), (8, 3, 'DniproB2', 26), (9, 3, 'DniproB3', 18);


INSERT INTO Buildnig VALUES (10, 10, 'BerlinB1', 35), (11, 10, 'BerlinB2', 48), (12, 10, 'BerlinB3', 56);
INSERT INTO Buildnig VALUES (13, 11, 'HamburgB1', 25), (14, 11, 'HamburgB2', 24), (15, 11, 'HamburgB3', 11);
INSERT INTO Buildnig VALUES (16, 12, 'MünchenB1', 17), (17, 12, 'MünchenB2', 7), (18, 12, 'MünchenB3', 2);

INSERT INTO Buildnig VALUES (19, 13, 'RomeB1', 14), (20, 13, 'RomeB2', 24), (21, 13, 'RomeB3', 34);
INSERT INTO Buildnig VALUES (22, 14, 'MilanB1', 27), (23, 14, 'MilanB2', 19), (24, 14, 'MilanB3', 35);
INSERT INTO Buildnig VALUES (25, 15, 'NaplesB1', 37), (26, 15, 'NaplesB2', 28), (27, 15, 'NaplesB3', 19);