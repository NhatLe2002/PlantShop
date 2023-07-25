/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import sample.dto.Categories;
import sample.utils.DBUtils;

/**
 *
 * @author HNC
 */
public class CategoriesDAO {
    
    public static  ArrayList<Categories> getAllCate() {
        ArrayList<Categories> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select*from Categories";

                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int cateID = rs.getInt("CateID");
                        String cateName = rs.getString("CateName");
                        Categories cate = new Categories(cateID, cateName);
                        list.add(cate);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public static boolean insertCategories( String newCateName){
        String sql = "insert into Categories values(?)";
        Connection cn = null;
        boolean check = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, newCateName);
                st.executeUpdate();
                check = true;
            }
        } catch (Exception e) {

        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return check;
    }
    public static boolean updateCategories(String newCateNameUpdate, int cateID) {
        String sql = "UPDATE Categories SET CateName=? where CateID=?";
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                
                        PreparedStatement st = cn.prepareStatement(sql);
                        st.setString(1, newCateNameUpdate);
                        st.setInt(2, cateID);
                        st.executeUpdate();
                        return true;
                 
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
