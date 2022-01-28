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
 * @author Kartik
 */
public class GetInfo {

    public static String userEmail;

    public static String getUserName(String email) {
        try ( Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/just_crypto", "root", "K@r11k003"); // here sonoo is database name, root is username and password
                  Statement stmt = con.createStatement();) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE email='" + email + "';");
            if (rs.next()) {
                return rs.getString("username");
            } else {
                return "NOT FOUND";
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return "NULL";
    }

    public static int getUserId(String email) {
        try ( Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/just_crypto", "root", "K@r11k003"); // here sonoo is database name, root is username and password
                  Statement stmt = con.createStatement();) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE email='" + email + "';");
            if (rs.next()) {
                return rs.getInt("user_id");
            } else {
                return 0;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public static float getBalance(String email) {
        try ( Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/just_crypto", "root", "K@r11k003"); // here sonoo is database name, root is username and password
                  Statement stmt = con.createStatement();) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE email='" + email + "';");
            if (rs.next()) {
                return rs.getFloat("balance");
            } else {
                return 0.0f;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0.0f;
    }

    public static float getCoinPrice(String coinName) {
        try ( Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/just_crypto", "root", "K@r11k003"); // here sonoo is database name, root is username and password
                  Statement stmt = con.createStatement();) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM crypto WHERE name='" + coinName + "';");
            if (rs.next()) {
                return (float) rs.getInt("current_price");
            } else {
                return 0.0f;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0.0f;
    }

    public static String getAbbr(String coinName) {
        try ( Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/just_crypto", "root", "K@r11k003"); // here sonoo is database name, root is username and password
                  Statement stmt = con.createStatement();) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM crypto WHERE name='" + coinName + "';");
            if (rs.next()) {
                return rs.getString("abbr");
            } else {
                return "";
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return "";

    }

    public static float getUserQuantity(String coinName) {
        try ( Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/just_crypto", "root", "K@r11k003"); // here sonoo is database name, root is username and password
                  Statement stmt = con.createStatement();) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM owned_cryptos WHERE user_id=" + getUserId(userEmail) + " AND abbr='" + getAbbr(coinName) + "';");
            if (rs.next()) {
                return rs.getFloat("bought_quantity");
            } else {
                return 0.0f;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0.0f;
    }

}
