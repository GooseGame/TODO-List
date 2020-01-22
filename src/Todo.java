import java.sql.*;

class Todo {
    static boolean makeNewItem(String description, String td_num) {
        Statement statement;
        try {
            Connection c = DriverManager.getConnection(Data.url);
            statement = c.createStatement();
            statement.executeUpdate(SQLResponse.insertIntoTable(description, td_num));
            statement.close();
            c.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    static boolean showItem(String td_num) {
        Statement statement;
        try {
            Connection c = DriverManager.getConnection(Data.url);
            statement = c.createStatement();
            ResultSet rs = statement.executeQuery(SQLResponse.getOne(td_num));
                if (rs.next()) {
                    System.out.println("---------------------");
                    System.out.println("Id: "+rs.getInt("td_num"));
                    System.out.println("text: \n"+rs.getString("description"));
                    int state = rs.getInt("state");
                    switch (state) {
                        case 0: System.out.println("Status: in progress"); break;
                        case 1: System.out.println("Status: done"); break;
                        case 2: System.out.println("Status: canceled"); break;
                    }
                    System.out.println("---------------------");
                }
                else {
                    System.out.println("Looks like you don't have any items with this id.\nYou can create it.");
                    rs.close();
                    statement.close();
                    c.close();
                    throw new SQLException();
                }
            rs.close();
            statement.close();
            c.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    static boolean showItems(String option) {
        Statement statement;
        try {
            Connection c = DriverManager.getConnection(Data.url);
            statement = c.createStatement();
            ResultSet rs = null;
            switch (option) {
                case "unfinished": {
                    rs = statement.executeQuery(SQLResponse.getAllOrdered(0));
                    break;
                }
                case "done": {
                    rs = statement.executeQuery(SQLResponse.getAllOrdered(1));
                    break;
                }
                case "canceled": {
                    rs = statement.executeQuery(SQLResponse.getAllOrdered(2));
                    break;
                }
                case "default": {
                    rs = statement.executeQuery(SQLResponse.getAll());
                    break;
                }
                default: {
                    System.out.println("Wrong order type.\nUse help command to check documentation");
                    throw new SQLException();
                }
            }
            int count = 0;
            if (rs != null) {
                while (rs.next()) {
                    count++;
                    System.out.println("---------------------");
                    System.out.println("Id: "+rs.getInt("td_num"));
                    System.out.println("text: \n"+rs.getString("description"));
                    int state = rs.getInt("state");
                    switch (state) {
                        case 0: System.out.println("Status: in progress"); break;
                        case 1: System.out.println("Status: done"); break;
                        case 2: System.out.println("Status: cancelled"); break;
                    }
                    System.out.println("---------------------");
                }
                if (count==0) {
                    System.out.println("there are no items on your order");
                    rs.close();
                    statement.close();
                    c.close();
                    throw new SQLException();
                }
                rs.close();
            }

            statement.close();
            c.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    static boolean clear(String option) {
        Statement statement;
        try {
            Connection c = DriverManager.getConnection(Data.url);
            statement = c.createStatement();
            switch (option) {
                case "unfinished": {
                    statement.executeUpdate(SQLResponse.clearOrdered(0));
                    System.out.println("unfinished items deleted successfully");
                    break;
                }
                case "done": {
                    statement.executeUpdate(SQLResponse.clearOrdered(1));
                    System.out.println("done items deleted successfully");
                    break;
                }
                case "canceled": {
                    statement.executeUpdate(SQLResponse.clearOrdered(2));
                    System.out.println("canceled items deleted successfully");
                    break;
                }
                case "default": {
                    statement.executeUpdate(SQLResponse.clear());
                    System.out.println("all items deleted successfully");
                    break;
                }
                default: {
                    System.out.println("Wrong order type.\nUse help command to check documentation");
                    statement.close();
                    c.close();
                    throw new SQLException();
                }
            }
            statement.close();
            c.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    static boolean delete(String td_num) {
        Statement statement;
        try {
            Connection c = DriverManager.getConnection(Data.url);
            statement = c.createStatement();
            statement.executeUpdate(SQLResponse.deleteOne(td_num));
            statement.close();
            c.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    static boolean setState(String td_num, int state) {
        Statement statement;
        try {
            if ((state < 0) || (state > 2)) {
                throw new SQLException();
            }
            Connection c = DriverManager.getConnection(Data.url);
            statement = c.createStatement();
            statement.executeUpdate(SQLResponse.setState(td_num, state));
            statement.close();
            c.close();
        } catch (SQLException e) {
            return false;
        }
        System.out.println("State updated");
        return true;
    }
    static boolean updateDescription(String option, String description, String td_num) {
        Statement statement;
        try {
            Connection c = DriverManager.getConnection(Data.url);
            statement = c.createStatement();
            switch (option) {
                case "default": {
                    ResultSet rs = statement.executeQuery(SQLResponse.getOne(td_num));
                    String oldDesc = rs.getString("description");
                    rs.close();
                    statement.executeUpdate(SQLResponse.updateDescription(td_num, oldDesc+"\n"+description));
                    System.out.println("item text has been updated");
                    break;
                }
                case "rewrite": {
                    statement.executeUpdate(SQLResponse.updateDescription(td_num, description));
                    System.out.println("item description has been rewrote");
                    break;
                }
                default: {
                    System.out.println("Wrong order type.\nUse help command to check documentation");
                    statement.close();
                    c.close();
                    throw new SQLException();
                }
            }
            statement.close();
            c.close();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
}
