package viktor.tarasov;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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
        testQuery(Arrays.asList("Brazil", "Germany"), firstQuery);
    }

    private static void testSecondQuery() throws ClassNotFoundException, SQLException, IOException {
        String firstQuery = "SELECT Country.Name FROM Country " +
                "INNER JOIN City ON City.CountryID = Country.CountryID " +
                "LEFT JOIN Buildnig ON Buildnig.CityID = City.CityID " +
                "GROUP BY Country.CountryID " +
                "HAVING COUNT(Buildnig.BuildnigID) = 0";
        testQuery(Arrays.asList("Ukraine", "Brazil"), firstQuery);
    }

    private static void testQuery(List<String> expectedResult, String query) throws ClassNotFoundException, SQLException, IOException {
        ResultSet resultSet = connection.createStatement().executeQuery(query);
        List<String> actualResult = new ArrayList<>();
        while (resultSet.next()) {
            actualResult.add(resultSet.getString(1));
        }
        printResult(expectedResult, actualResult, query);
    }

    private static void printResult(List<String> expectedResult, List<String> actualResult, String query) {
        Collections.sort(expectedResult);
        Collections.sort(actualResult);
        if (expectedResult.equals(actualResult)) {
            System.out.println(query);
            System.out.println("Result: " + actualResult);
        } else {
            System.err.println(query);
            System.err.println("Result was [" + actualResult + "] when expected [" + expectedResult + "]");
        }
    }
}
