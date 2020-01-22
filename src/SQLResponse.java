class SQLResponse {
    static String createTable() {
        return "CREATE TABLE "+ Data.tableName +
                " ("+Data.td_num + " INTEGER PRIMARY KEY, " +
                Data.description + " TEXT NOT NULL, " +
                Data.state+" TINYINT NOT NULL) ";
    }

    static String insertIntoTable(String description, String td_num) {
        return "INSERT INTO " + Data.tableName + " (td_num, description, state)" +
                " VALUES ("+td_num+", '" + description + "', " + 0 + ") ";
    }

    static String updateDescription(String td_num, String description) {
        return "UPDATE "+Data.tableName+
                " SET "+Data.description + " = '"+description+"'"+
                "WHERE "+Data.td_num+" = "+td_num;
    }

    static String getOne(String td_num) {
        return "SELECT * FROM "+Data.tableName+" WHERE "+Data.td_num+" = "+td_num;
    }

    public static String getAll() {
        return "SELECT * FROM "+Data.tableName;
    }

    static String getAllOrdered(int state) {
        return getAll()+" WHERE "+Data.state+" = "+state;
    }

    static String deleteOne(String td_num) {
        return "DELETE FROM "+Data.tableName + " WHERE "+Data.td_num+" = "+td_num;
    }

    static String clear() {
        return "DELETE FROM "+Data.tableName;
    }

    static String clearOrdered(int state) {
        return clear()+" WHERE "+Data.state+" = "+ state;
    }

    static String setState(String td_num, int state) {
        return "UPDATE "+Data.tableName+
                " SET "+Data.state + " = '"+state+"'"+
                "WHERE "+Data.td_num+" = "+td_num;
    }
}
