/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
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
import model.Room;

/**
 *
 * @author phangiabao
 */
public class RoomDAO {

    public ArrayList<Room> getListRoom() {
        ArrayList<Room> list = new ArrayList<>();
        String query = "select * from Room";
        try {

            Connection conn = new JDBCUtil().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                list.add(new Room(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4)));
            }

        } catch (Exception e) {
        }
        return list;
    }

    public Room getListRoomById(Room t) {
        Room ketQua = null;
        try {
            final Connection con = JDBCUtil.getConnection();
            final String sql = "SELECT * FROM Room WHERE roomId=?";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getRoomId());
            System.out.println(sql);
            final ResultSet rs = st.executeQuery();
            if (rs.next()) {
                ketQua = new Room(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4));
            }

        } catch (Exception e) {
        }
        return ketQua;
    }


    public int delete(String t) {
        int ketQua = 0;
        try {
            final Connection con = JDBCUtil.getConnection();
            final String sql = "DELETE from Room  WHERE roomId=?";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t);
            System.out.println(sql);
            ketQua = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public int insert(Room t) {
        int ketQua = 0;
        try {
            final Connection con = JDBCUtil.getConnection();
            final String sql = "INSERT INTO room (roomId, name, type, price)  VALUES (?,?,?,?)";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getRoomId());
            st.setString(2, t.getName());
            st.setString(3, t.getType());
            st.setDouble(4, t.getPrice());

            ketQua = st.executeUpdate();
            System.out.println("B\u1ea1n \u0111\u00e3 th\u1ef1c thi: " + sql);
            System.out.println("C\u00f3 " + ketQua + " d\u00f2ng b\u1ecb thay \u0111\u1ed5i!");
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public int update(Room t) {
        int ketQua = 0;
        try {
            final Connection con = JDBCUtil.getConnection();
            final String sql = "UPDATE room  SET  roomId=?, name=?, type=?,role=?,price=? WHERE roomId=?";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getRoomId());
            st.setString(2, t.getName());
            st.setString(3, t.getType());
            st.setDouble(4, t.getPrice());

            System.out.println(sql);
            ketQua = st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public boolean kiemTraNameRoom(final String name) {
        boolean ketQua = false;
        try {
            final Connection con = JDBCUtil.getConnection();
            final String sql = "SELECT * FROM Room WHERE name=?";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, name);
            System.out.println(sql);
            final ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ketQua = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

  
}
