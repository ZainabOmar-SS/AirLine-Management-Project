package DAO;

import util.ColumnWhitelist;

import java.sql.*;
import java.util.List;

public class JDBC {
    private final ColumnWhitelist columnWhitelist;
    Connection conn = null;
    public JDBC() throws SQLException {
        columnWhitelist = new ColumnWhitelist();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/utopia-EER", "root", "ZainabSQL@93");

        } catch (Exception e){
            e.printStackTrace();

        }
    }
    public String whiteListedColumnName(String columnName) {
        List<String> list = columnWhitelist.getWhitelist();
        if(list.contains(columnName)) return columnName;
        else return "";
    }

    protected void update(String sql, Object... objs) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement(sql);

        int count = 1;

        for(Object obj : objs) {
            statement.setObject(count, obj);
            count++;
        }

        statement.executeUpdate();
    }
    protected ResultSet query(String sql, Object... objs) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement(sql);

        int count = 1;

        for(Object obj : objs) {
            statement.setObject(count, obj);
            count++;
        }

        return statement.executeQuery();
    }

    public void commit() throws SQLException {
        getConnection().commit();
    }

    public void rollback() throws SQLException {
        getConnection().rollback();
    }

    public Connection getConnection() {
        return conn;
    }

}

//public class JDBC {
//    public static void main(String[] args) {
//        Connection conn = null;
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/utopia-EER", "root", "ZainabSQL@93");
//            Statement statement = conn.createStatement();
//            ResultSet resultSet = statement.executeQuery("select * from user");
//            while(resultSet.next()){
//                System.out.println(resultSet.getString("username"));
//            }
//
//
//        } catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//}
