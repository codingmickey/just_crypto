/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author Kartik
 */
public class DBConnection {

    public static boolean saveUser(String username, String email, String password) {
        try ( Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/just_crypto", "root", "K@r11k003"); // here sonoo is database name, root is username and password
                  Statement stmt = con.createStatement();) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE email='" + email + "';");

            if (rs.next() == false) {
                stmt.execute("INSERT INTO user (username, email, password) VALUES ('" + username + "','" + email
                        + "','" + password + "');");
                return true;
            } else {
                System.out.println("WORKING");

                return false;
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public static int checkUser(String email, String password) {
        try ( Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/just_crypto", "root", "K@r11k003"); // here sonoo is database name, root is username and password
                  Statement stmt = con.createStatement();) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE email='" + email + "';");
            if (rs.next()) {
                System.err.println("HI THRE");
                if (rs.getString("password").equals(password)) {
                    System.out.println(rs.getString("password"));
                    return 0; // Account found and authenticated
                } else {
                    return 1; // Incorrect Password
                }
            } else {
                System.out.println("NOT WORKING");
                return 2; // Email not present
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 4;
    }

//
//    public static Float getBalance(String email) {
//        try ( Connection con = DriverManager.getConnection(
//                "jdbc:mysql://localhost:3306/just_crypto", "root", "K@r11k003"); // here sonoo is database name, root is username and password
//                  Statement stmt = con.createStatement();) {
//            ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE email='" + email + "';");
//            if (rs.next()) {
//                return rs.getFloat("balance");
//            } else {
//                return 0.0f;
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//        return 0.0f;
//    }
}
