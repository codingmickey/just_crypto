/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Meet
 */
public class SetInfo {

    public static Boolean updateBalance(float balance) {
        try ( Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/just_crypto", "root", "K@r11k003"); // here sonoo is database name, root is username and password
                  Statement stmt = con.createStatement();) {
            ResultSet rs = stmt.executeQuery("UPDATE user set balance = "+balance+" WHERE email='" + GetInfo.userEmail + "';");
            if (rs.next()) {
//                return rs.getString("username");
//            } else {
//                return "NOT FOUND";
                    return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
}