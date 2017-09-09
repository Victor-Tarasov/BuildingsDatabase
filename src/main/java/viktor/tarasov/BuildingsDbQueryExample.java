package viktor.tarasov;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class BuildingsDbQueryExample {
    private static Connection connection;

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        try {
            BuildingsDbQueryExample.connection = DbInitializer.startUp();
            testFirstQuery();
            testSecondQuery();
        } finally {
            DbInitializer.close();
        }
    }

    private static void testFirstQuery() throws ClassNotFoundException, SQLException, IOException {
        String firstQuery = "SELECT Country.Name FROM Country " +
                "INNER JOIN City ON City.CountryID = Country.CountryID GROUP BY Country.CountryID " +
                "HAVING SUM(City.Population) > 400";
        Set<String> expectedResult = new HashSet<>();
        expectedResult.add("Brazil");
        expectedResult.add("Germany");
        testQuery(expectedResult, firstQuery);
    }

    private static void testSecondQuery() throws ClassNotFoundException, SQLException, IOException {
        String firstQuery = "SELECT Country.Name FROM Country " +
                "INNER JOIN City ON City.CountryID = Country.CountryID " +
                "LEFT JOIN Buildnig ON Buildnig.CityID = City.CityID " +
                "GROUP BY Country.CountryID " +
                "HAVING COUNT(Buildnig.BuildnigID) = 0";
        Set<String> expectedResult = new HashSet<>();
        expectedResult.add("Ukraine");
        expectedResult.add("Brazil");
        testQuery(expectedResult, firstQuery);
    }

    private static void testQuery(Set<String> expectedResult, String query) throws ClassNotFoundException, SQLException, IOException {
        ResultSet resultSet = connection.createStatement().executeQuery(query);
        Set<String> actualResult = new HashSet<>();
        while (resultSet.next()) {
            actualResult.add(resultSet.getString(1));
        }
        printResult(expectedResult, actualResult, query);
    }

    private static void printResult(Set<String> expectedResult, Set<String> actualResult, String query) {
        if (expectedResult.equals(actualResult)) {
            System.out.println(query);
            System.out.println("Result: " + actualResult);
        } else {
            System.err.println(query);
            System.err.println("Result was [" + actualResult + "] when expected [" + expectedResult + "]");
        }
    }
}
