import java.io.File;
import java.sql.*;

class DB {

    private static boolean createNewDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        File directory = new File(Data.PATH);
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("DB directory created successfully. PATH: "+ Data.PATH);
            } else {
                System.out.println("Failed to create directory!");
            }
        }

        String url = Data.url;

        try {
            Connection conn = DriverManager.getConnection(url);
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
                createTable(conn);
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    static void connect() {
        try {
            // db parameters
            String url = Data.url;
            // create a connection to the database
            DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");
            System.out.println("Enter 'help' to check commands.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            if (createNewDatabase()) {
                connect();
            }
        }
    }

    private static void createTable(Connection c) {
        Statement statement;
        String sql = SQLResponse.createTable();
        try {
            statement = c.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("DB table created successfully.");
    }
}
