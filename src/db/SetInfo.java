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
            stmt.execute("UPDATE user set balance = " + balance + " WHERE email='" + GetInfo.userEmail + "';");
            System.out.println(balance + " " + GetInfo.userEmail);
            return true;

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public static void updateOwned(String coinName, float money) {
        try ( Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/just_crypto", "root", "K@r11k003"); // here sonoo is database name, root is username and password
                  Statement stmt = con.createStatement();) {
            String abbr = GetInfo.getAbbr(coinName);
            int id = GetInfo.getUserId(GetInfo.userEmail);
            float coinPrice = GetInfo.getCoinPrice(coinName);
            float quantity = money / coinPrice;

            ResultSet rs = stmt.executeQuery("SELECT * FROM owned_cryptos WHERE abbr = '" + abbr + "' AND user_id=" + id);
            if (rs.next() == false) {
                stmt.execute("INSERT INTO owned_cryptos (abbr, user_id, bought_price, bought_quantity) VALUES ('" + abbr + "'," + id
                        + "," + ((int) coinPrice) + "," + quantity + ");");
            } else {
                updateQuantity(coinName, (rs.getFloat("bought_quantity") + quantity));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void updateQuantity(String coinName, float quantity) {
        try ( Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/just_crypto", "root", "K@r11k003"); // here sonoo is database name, root is username and password
                  Statement stmt = con.createStatement();) {

            int id = GetInfo.getUserId(GetInfo.userEmail);
            String abbr = GetInfo.getAbbr(coinName);

            stmt.execute("UPDATE owned_cryptos set bought_quantity=" + quantity + "WHERE abbr = '" + abbr + "' AND user_id=" + id);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
