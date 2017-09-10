package viktor.tarasov;

import org.hsqldb.Server;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DbInitializer {
    private static Connection connection;
    private static Server server;

    public static Connection startUp() throws ClassNotFoundException, SQLException, IOException {
        if (connection != null) {
            return connection;
        }

        startServer();
        Class.forName("org.hsqldb.jdbc.JDBCDriver");
        connection = DriverManager.getConnection("jdbc:hsqldb:mem:Buildings", "SA", "");
        executeFile("/init.sql", connection);
        return connection;
    }

    private static void executeFile(String fileName, Connection connection) throws SQLException, IOException {
        try (InputStream in = BuildingsDbQueryExample.class.getResourceAsStream(fileName);
             Statement statement = connection.createStatement()) {
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("(;(\r)?\n)|(--\n)");
            while (scanner.hasNext()) {
                statement.executeUpdate(scanner.next());
            }
            statement.close();
        }
    }

    private static void startServer() {
        server = new Server();
        server.setDatabaseName(0, "Buildings");
        server.setDatabasePath(0, "mem:Buildings");
        server.start();
    }

    public static void close() throws SQLException {
        try {
            connection.close();
        } finally {
            server.stop();
        }
    }
}
