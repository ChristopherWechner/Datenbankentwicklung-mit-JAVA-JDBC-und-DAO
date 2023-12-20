package at.ITKolleg;

import java.sql.*;
//Vid5
public class JdbcDemo {

    public static void main(String[] args) {
        System.out.println("JDBC DEMO!");

        //INSERT INTO `student` (`id`, `name`, `email`) VALUES (NULL, 'Test Test', 'test@test.test'), (NULL, 'Maria', 'maria@test.at');

        selectAllDemo();
        insertStudentDemo("Name des Studenten", "Email@prov.at");
        selectAllDemo();
        updateStudentDemo("Neuer Name", "neueemail@provider.at", 4);
        selectAllDemo();
        deleteStudentDeom(5);
        selectAllDemo();
    }

    public static void deleteStudentDeom(int studentID){

        System.out.println("DeELETE DEMO mit JDBC");
        String sqlSelectAllPersono = "SELECT * FROM `student`";
        String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String pwd = "";

        try (Connection conn = DriverManager.getConnection(connectionUrl, user,pwd)){
            System.out.println("Verbindung zur DB hergestellt!");

            PreparedStatement preparedStatement = conn.prepareStatement(
                    "DELETE FROM `student` WHERE `student`.`id` = ?"
            );

            try {
                preparedStatement.setInt(1,studentID);
                int rowAffected = preparedStatement.executeUpdate();
                System.out.println("anzahl der gelöschten Datensätze: " + rowAffected);
            }catch (SQLException ex){
                System.out.println("Fehler im SQL-DELETE Statement: " + ex.getMessage());
            }

        }catch (SQLException e){
            System.out.println("Fehler bei Aufbau der Verbindung zur DB: " + e.getMessage());
        }

    }

    public static  void updateStudentDemo(String neuerName, String neueEmail, int id){

        System.out.println("Update DEMO mit JDBC");
        String sqlSelectAllPersono = "SELECT * FROM `student`";
        String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String pwd = "";

        try (Connection conn = DriverManager.getConnection(connectionUrl, user,pwd)){
            System.out.println("Verbindung zur DB hergestellt!");

            PreparedStatement preparedStatement = conn.prepareStatement(
                    "UPDATE `student` SET `name` = ?, `email` = ?  WHERE  `student`.`id` = ?"
            );

            try {
                preparedStatement.setString(1,neuerName);
                preparedStatement.setString(2,neueEmail);
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

    public static void insertStudentDemo(String name, String email){

        System.out.println("INSERT DEMO mit JDBC");
        String sqlSelectAllPersono = "SELECT * FROM `student`";
        String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String pwd = "";

        try (Connection conn = DriverManager.getConnection(connectionUrl, user,pwd)){
            System.out.println("Verbindung zur DB hergestellt!");

            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO `student` (`id`, `name`, `email`) VALUES (NULL, ?, ?)"
            );

            try {
                preparedStatement.setString(1,name);
                preparedStatement.setString(2,email);
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
        String pwd = "";
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
