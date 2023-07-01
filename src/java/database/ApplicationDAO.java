/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Admin;
import model.Application;
import model.Room;
import model.Users;

/**
 *
 * @author phangiabao
 */
public class ApplicationDAO {

    public ArrayList<Application> getApplication() {
        ArrayList<Application> list = new ArrayList<>();
        String query = "select * from Application";
        try {

            Connection conn = new JDBCUtil().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Users r1 = new Users();
                r1.setUsersId(rs.getInt(11));
                Users r2 = new database.UserDAO().getListTaiKhoanUsersById(r1);
                list.add(new Application());
            }

        } catch (Exception e) {
        }
        return list;
    }

    public Application getApplicationId(Application t) {
        Application ketQua = null;
        try {
            final Connection con = JDBCUtil.getConnection();
            final String sql = "SELECT * FROM Application WHERE ApplicationId=?";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getApplicationId());
            System.out.println(sql);
            final ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Users r1 = new Users();
                r1.setUsersId(rs.getInt(2));
                Users r2 = new database.UserDAO().getListTaiKhoanUsersById(r1);
                ketQua = new Application(rs.getInt(1), r2, rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
            }

        } catch (Exception e) {
        }
        return ketQua;
    }


    public int delete(String t) {
        int ketQua = 0;
        try {
            final Connection con = JDBCUtil.getConnection();
            final String sql = "DELETE from application  WHERE applicationId=?";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t);
            System.out.println(sql);
            ketQua = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public int insert(Application t) {
        int ketQua = 0;
        try {
            final Connection con = JDBCUtil.getConnection();
            final String sql = "INSERT INTO application (applicationId, title, reason, file, status, comment)  VALUES (?,?,?,?,?,?)";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getApplicationId());
            st.setString(2, t.getTitle());
            st.setString(3, t.getReason());
            st.setString(4, t.getFile());
            st.setString(5, t.getStatus());
            st.setString(6, t.getComment());

            ketQua = st.executeUpdate();
            System.out.println("B\u1ea1n \u0111\u00e3 th\u1ef1c thi: " + sql);
            System.out.println("C\u00f3 " + ketQua + " d\u00f2ng b\u1ecb thay \u0111\u1ed5i!");
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public int update(Application t) {
        int ketQua = 0;
        try {
            final Connection con = JDBCUtil.getConnection();
            final String sql = "UPDATE application  SET  applicationId=?, title=?, reason=?,files=?,statuses=?,comment=? WHERE applicationId=?";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getApplicationId());
            st.setString(2, t.getTitle());
            st.setString(3, t.getReason());
            st.setString(4, t.getFile());
            st.setString(5, t.getStatus());
            st.setString(6, t.getComment());

            System.out.println(sql);
            ketQua = st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

}