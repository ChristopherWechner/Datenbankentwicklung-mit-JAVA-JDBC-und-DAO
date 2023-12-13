package at.ITKolleg;

import java.sql.*;

public class JdbcDemo {

    public static void main(String[] args) {
        System.out.println("JDBC DEMO!");

        //INSERT INTO `student` (`id`, `name`, `email`) VALUES (NULL, 'Test Test', 'test@test.test'), (NULL, 'Maria', 'maria@test.at');

        selectAllDemo();
        insertStudentDemo();
        selectAllDemo();
    }

    public static void insertStudentDemo(){

        System.out.println("INSERT DEMO mit JDBC");
        String sqlSelectAllPersono = "SELECT * FROM `student`";
        String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String pwd = "1234";

        try (Connection conn = DriverManager.getConnection(connectionUrl, user,pwd)){
            System.out.println("Verbindung zur DB hergestellt!");

            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO `student` (`id`, `name`, `email`) VALUES (NULL, ?, ?)"
            );

            try {
                preparedStatement.setString(1,"Peter Zeck");
                preparedStatement.setString(2,"p.zeck@hotmail.com");
                int rowAffected = preparedStatement.executeUpdate();
                System.out.println(rowAffected + "Datensetze eingefügt");
            }catch (SQLException ex){
                System.out.println("Fehler im SQL-INSERT Statement: " + ex.getMessage());
            }

        }catch (SQLException e){
            System.out.println("Fehler bei Aufbau der Verbindung zur DB: " + e.getMessage());
        }
    }

    public static void selectAllDemo(){

        System.out.println("Select DEMO mit JDBC");
        String sqlSelectAllPersono = "SELECT * FROM `student`";
        String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String pwd = "1234";
        try (Connection conn = DriverManager.getConnection(connectionUrl, user,pwd)){
            System.out.println("Verbindung zur DB hergestellt!");

            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM `student`");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                System.out.println("Student aus der DB: [ID] " + id + "[NAME]" + name + " [Email]" + email);

            }



        }catch (SQLException e){
            System.out.println("Fehler bei Aufbau der Verbindung zur DB: " + e.getMessage());
        }

    }




}
