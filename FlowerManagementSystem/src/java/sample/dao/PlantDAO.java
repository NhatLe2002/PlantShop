/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import sample.utils.DBUtils;
import java.util.ArrayList;
import sample.dto.Plant;

/**
 *
 * @author PC
 */
public class PlantDAO {

    public static ArrayList<Plant> getPlants(String keyword, String searchby) {
        ArrayList<Plant> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null && searchby != null) {
                String sql = "select PID, PName, price,imgPath, description, status, Plants.CateID as 'CateID', CateName\n"
                        + "from Plants join Categories on Plants.CateID=Categories.CateID\n";
                if (searchby.equalsIgnoreCase("byname")) {
                    sql += "where PName like ?";
                } else {
                    sql += "where CateName like ?";
                }
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + keyword + "%");
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int id = rs.getInt("PID");
                        String name = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgpath = rs.getString("imgpath");
                        String description = rs.getString("description");
                        int status = rs.getInt("status");
                        int cateid = rs.getInt("CateID");
                        String catename = rs.getString("CateName");
                        Plant plant = new Plant(id, name, price, imgpath, description, status, cateid, catename);
                        list.add(plant);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public static ArrayList<Plant> getPlants(String byname) {
        ArrayList<Plant> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null && byname != null) {
                String sql = "select PID, PName, price,imgPath, description, status, Plants.CateID as 'CateID', CateName\n"
                        + "from Plants join Categories on Plants.CateID=Categories.CateID\n"
                        + "where PName like ?";

                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, byname);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int id = rs.getInt("PID");
                        String name = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgpath = rs.getString("imgpath");
                        String description = rs.getString("description");
                        int status = rs.getInt("status");
                        int cateid = rs.getInt("CateID");
                        String catename = rs.getString("CateName");
                        Plant plant = new Plant(id, name, price, imgpath, description, status, cateid, catename);
                        list.add(plant);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    public static  ArrayList<Plant> getAllPlants() {
        ArrayList<Plant> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select p.PID,p.PName,p.price,p.imgPath,p.description,p.status,p.CateID,c.CateName from Plants p join Categories c on p.CateID=c.CateID";

                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int id = rs.getInt("PID");
                        String name = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgpath = rs.getString("imgpath");
                        String description = rs.getString("description");
                        int status = rs.getInt("status");
                        int cateid = rs.getInt("CateID");
                        String catename = rs.getString("CateName");
                        Plant plant = new Plant(id, name, price, imgpath, description, status, cateid, catename);
                        list.add(plant);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public static Plant getPlants(int pid) {

        Connection cn = null;
        Plant p = null;
        try {
            cn = DBUtils.makeConnection();

            if (cn != null) {
                String sql = "select *from Plants join Categories on Plants.CateID=Categories.CateID where PID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, pid);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {

                    int id = rs.getInt("PID");
                    String name = rs.getString("PName");
                    int price = rs.getInt("price");
                    String imgpath = rs.getString("imgpath");
                    String description = rs.getString("description");
                    int status = rs.getInt("status");
                    int cateid = rs.getInt("CateID");
                    String catename = rs.getString("CateName");
                    p = new Plant(id, name, price, imgpath, description, status, cateid, catename);

                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return p;
    }
    public static boolean insertPlant( Plant plant){
        String sql = "insert into Plants values(?,?,?,?,?,?)";
        Connection cn = null;
        boolean check = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, plant.getName());
                st.setInt(2, plant.getPrice());
                st.setString(3, plant.getImgpath());
                st.setString(4, plant.getDescription());
                st.setInt(5, plant.getStatus());
                st.setInt(6, plant.getCateid());
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
    public static boolean updatePlant(Plant plant) {
        String sql = "UPDATE Plants SET PName = ?, price = ?, imgpath = ?, description = ?, status = ?, cateid = ? where PID = ?";
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                
                        PreparedStatement st = cn.prepareStatement(sql);
                        st.setString(1, plant.getName());
                        st.setInt(2, plant.getPrice());
                        st.setString(3, plant.getImgpath());
                        st.setString(4, plant.getDescription());
                        st.setInt(5, plant.getStatus());
                        st.setInt(6, plant.getCateid());
                        st.setInt(7, plant.getId());
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
