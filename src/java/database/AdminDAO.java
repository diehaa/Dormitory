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
import model.Nganh;
import model.TaiKhoanAdmin;
import model.ThiSinh;
import model.Truong;

/**
 *
 * @author phangiabao
 */
public class AdminDAO {

    

    public ArrayList<TaiKhoanAdmin> getListTaiKhoanAdmin() {
            ArrayList<TaiKhoanAdmin> list = new ArrayList<>();
            String query = "select * from taikhoanadmin";
        try {
            
            Connection conn = new JDBCUtil().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                
                list.add(new TaiKhoanAdmin(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
            }

        } catch (Exception e) {
        }
        return list;
    }

    public TaiKhoanAdmin getListTaiKhoanAdminById(TaiKhoanAdmin t) {
        TaiKhoanAdmin ketQua = null;
        try {
            final Connection con = JDBCUtil.getConnection();
            final String sql = "SELECT * FROM taikhoanadmin WHERE maadmin=?";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getMaAdmin());
            System.out.println(sql);
            final ResultSet rs = st.executeQuery();
            if (rs.next()) {
                ketQua = new TaiKhoanAdmin(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
            }

        } catch (Exception e) {
        }
        return ketQua;
    }
    public TaiKhoanAdmin getListTaiKhoanAdminByIdString(String t) {
        TaiKhoanAdmin ketQua = null;
        try {
            final Connection con = JDBCUtil.getConnection();
            final String sql = "SELECT * FROM taikhoanadmin WHERE maadmin=?";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t);
            System.out.println(sql);
            final ResultSet rs = st.executeQuery();
            if (rs.next()) {
                ketQua = new TaiKhoanAdmin(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
            }

        } catch (Exception e) {
        }
        return ketQua;
    }
    
    public int delete(String maAdmin){
        int ketQua = 0;
        try {
            final Connection con = JDBCUtil.getConnection();
            final String sql = "DELETE from taikhoanadmin  WHERE maadmin=?";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, maAdmin);
            System.out.println(sql);
            ketQua = st.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }
    
     public int insert(final TaiKhoanAdmin t) {
        int ketQua = 0;
        try {
            final Connection con = JDBCUtil.getConnection();
            final String sql = "INSERT INTO taikhoanadmin (maadmin, tendangnhap, matkhau, hovaten, emailadmin, chucvu)  VALUES (?,?,?,?,?,?)";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getMaAdmin());
            st.setString(2, t.getTenDangNhap());
            st.setString(3, t.getMatKhau());
            st.setString(4, t.getHoVaTen());
            st.setString(5, t.getEmailAdmin());
            st.setString(6, t.getChucVu());
            ketQua = st.executeUpdate();
            System.out.println("B\u1ea1n \u0111\u00e3 th\u1ef1c thi: " + sql);
            System.out.println("C\u00f3 " + ketQua + " d\u00f2ng b\u1ecb thay \u0111\u1ed5i!");
            JDBCUtil.closeConnection(con);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }
     
     public int insertAll( ArrayList<TaiKhoanAdmin> arr) {
        int dem = 0;
        for (final TaiKhoanAdmin taiKhoanAdmin : arr) {
            dem += this.insert(taiKhoanAdmin);
        }
        return dem;
    }
     
     public int update( TaiKhoanAdmin t) {
        int ketQua = 0;
        try {
            final Connection con = JDBCUtil.getConnection();
            final String sql = "UPDATE taikhoanadmin  SET  hovaten=?, emailadmin=?, chucvu=? WHERE maadmin=?";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setString(4, t.getMaAdmin());
            st.setString(1, t.getHoVaTen());
            st.setString(2, t.getEmailAdmin());
            st.setString(3, t.getChucVu());
            System.out.println(sql);
            ketQua = st.executeUpdate();
          
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }
     
     public TaiKhoanAdmin selectByUserAndPassword(TaiKhoanAdmin t) {
        TaiKhoanAdmin ketQua = null;
        try {
            final Connection con = JDBCUtil.getConnection();
            final String sql = "SELECT * FROM taikhoanadmin WHERE tendangnhap=? and matkhau=?";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getTenDangNhap());
            st.setString(2, t.getMatKhau());
            System.out.println(sql);
            final ResultSet rs = st.executeQuery();
            if (rs.next()) {
                ketQua = new TaiKhoanAdmin(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
            }

        } catch (Exception e) {
        }
        return ketQua;
    }
     public boolean kiemTraTenDangNhap(final String tenDangNhap) {
        boolean ketQua = false;
        try {
            final Connection con = JDBCUtil.getConnection();
            final String sql = "SELECT * FROM taikhoanadmin WHERE tendangnhap=?";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, tenDangNhap);
            System.out.println(sql);
            final ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ketQua = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }
     public int countCB() {
        int ketQua = 0;
        try {
            final Connection con = JDBCUtil.getConnection();
            final String sql = "SELECT COUNT(*) FROM  taikhoanadmin";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ketQua = rs.getInt(1);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

}
