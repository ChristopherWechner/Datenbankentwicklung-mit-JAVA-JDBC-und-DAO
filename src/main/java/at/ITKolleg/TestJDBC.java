package at.ITKolleg;

import java.sql.*;

public class TestJDBC {

    public static void main(String[] args) {
        System.out.println("JDBC DEMO!");

        //INSERT INTO `student` (`id`, `name`, `email`) VALUES (NULL, 'Test Test', 'test@test.test'), (NULL, 'Maria', 'maria@test.at');

        selectAllDemo();
        insertTestDemo("b", 4);
        selectAllDemo();
        updateStudentDemo("b", 2, 3);
        selectAllDemo();
        deleteStudentDeom(1);
        selectAllDemo();
    }


    public static void deleteStudentDeom(int testid){

        System.out.println("DeELETE DEMO mit JDBC");
        String sqlSelectAllPersono = "SELECT * FROM `testDemo`";
        String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String pwd = "";

        try (Connection conn = DriverManager.getConnection(connectionUrl, user,pwd)){
            System.out.println("Verbindung zur DB hergestellt!");

            PreparedStatement preparedStatement = conn.prepareStatement(
                    "DELETE FROM `testdemo` WHERE `testdemo`.`id` = ?"
            );

            try {
                preparedStatement.setInt(1,testid);
                int rowAffected = preparedStatement.executeUpdate();
                System.out.println("anzahl der gelöschten Datensätze: " + rowAffected);
            }catch (SQLException ex){
                System.out.println("Fehler im SQL-DELETE Statement: " + ex.getMessage());
            }

        }catch (SQLException e){
            System.out.println("Fehler bei Aufbau der Verbindung zur DB: " + e.getMessage());
        }

    }

    public static  void updateStudentDemo(String neuesFach, int neueNote, int id){

        System.out.println("Update DEMO mit JDBC");
        String sqlSelectAllPersono = "SELECT * FROM `testdemo`";
        String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String pwd = "";

        try (Connection conn = DriverManager.getConnection(connectionUrl, user,pwd)){
            System.out.println("Verbindung zur DB hergestellt!");

            PreparedStatement preparedStatement = conn.prepareStatement(
                    "UPDATE `testdemo` SET `fach` = ?, `note` = ?  WHERE  `testdemo`.`id` = ?"
            );

            try {
                preparedStatement.setString(1,neuesFach);
                preparedStatement.setInt(2,neueNote);
                preparedStatement.setInt(3,id);
                int affectedRows = preparedStatement.executeUpdate();
                System.out.println("Anzahl der aktualisierten Datensätze: " + affectedRows);
            }catch (SQLException ex){
                System.out.println("Fehler im SQL-Update Statement: " + ex.getMessage());
            }

        }catch (SQLException e){
            System.out.println("Fehler bei Aufbau der Verbindung zur DB: " + e.getMessage());
        }

    }

    public static void insertTestDemo(String fach, int note ){

        System.out.println("INSERT DEMO mit JDBC");
        String sqlSelectAllPersono = "SELECT * FROM `testdemo`";
        String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String pwd = "";

        try (Connection conn = DriverManager.getConnection(connectionUrl, user,pwd)){
            System.out.println("Verbindung zur DB hergestellt!");

            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO `testdemo` (`id`, `fach`, `note`) VALUES (NULL, ?, ?)"
            );

            try {
                preparedStatement.setString(1,fach);
                preparedStatement.setInt(2,note);
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
        String sqlSelectAllPersono = "SELECT * FROM `testdemo`";
        String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String pwd = "";

        try (Connection conn = DriverManager.getConnection(connectionUrl, user,pwd)){
            System.out.println("Verbindung zur DB hergestellt!");

            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM `testdemo`");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                String fach = rs.getString("Fach");
                int note = rs.getInt("Note");
                System.out.println("Student aus der DB: [ID] " + id + "[Fach]" + fach + " [Note]" + note);

            }



        }catch (SQLException e){
            System.out.println("Fehler bei Aufbau der Verbindung zur DB: " + e.getMessage());
        }

    }


}
