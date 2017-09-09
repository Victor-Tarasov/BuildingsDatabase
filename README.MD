First query that select countries where a total number of inhabitants (population) in all cities is greater than 400.

``` sql
SELECT Country.Name FROM Country
INNER JOIN City ON City.CountryID = Country.CountryID GROUP BY Country.CountryID
HAVING SUM(City.Population) > 400
```

Second query that select names of the countries that have no buildings at all.

``` sql
SELECT Country.Name FROM Country
INNER JOIN City ON City.CountryID = Country.CountryID
LEFT JOIN Buildnig ON Buildnig.CityID = City.CityID
GROUP BY Country.CountryID
HAVING COUNT(Buildnig.BuildnigID) = 0
```

Application that tests queries against prefilled in memory database can be run with this command:
```
mvn compile exec:java
```