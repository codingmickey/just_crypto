/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Kartik
 */
public class DBConnection {

    public static void saveUser(String username, String email, String password) {
        try ( Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/just_crypto", "root", "K@r11k003"); // here sonoo is database name, root is username and password
                  Statement stmt = con.createStatement();) {

            stmt.execute("INSERT INTO user (username, email, password) VALUES ('" + username + "','" + email
                    + "','" + password + "');");
        } catch (SQLException e) {
            System.out.println(e);
        }

    }
}
