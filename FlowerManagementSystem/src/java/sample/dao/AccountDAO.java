/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sample.dto.Account;
import sample.utils.DBUtils;

/**
 *
 * @author PC
 */
public class AccountDAO {

    public static Account getAccount(String email, String password) {
        Connection cn = null;
        Account acc = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select accID,email,password,fullname,phone,status,role\n"
                        + "from dbo.Accounts\n"
                        + "where status=1 and email =? and password =? COLLATE Latin1_General_CS_AS ";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, password);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int AccID = rs.getInt("accID");
                    String Email = rs.getString("email");
                    String Password = rs.getString("password");
                    String Fullname = rs.getString("fullname");
                    String Phone = rs.getString("phone");
                    int Status = rs.getInt("status");
                    int Role = rs.getInt("role");
                    acc = new Account(AccID, Email, Password, Fullname, Status, Phone, Role);

                }
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
        return acc;
    }
    
    public static ArrayList<Account> getAccount() {
        ArrayList<Account> list = new ArrayList<>();
        Connection cn = null;
        String sql = "select * from Accounts";
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                PreparedStatement st = cn.prepareStatement(sql);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Account acc = new Account();
                    acc.setAccID(rs.getInt("accID"));
                    acc.setEmail(rs.getString("email"));
                    acc.setPassword(rs.getString("password"));
                    acc.setFullname(rs.getString("fullname"));
                    acc.setPhone(rs.getString("phone"));
                    acc.setStatus(rs.getInt("status"));
                    acc.setRole(rs.getInt("role"));
                    list.add(acc);
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    public static ArrayList<Account> getAccountByPhone(String fullname) {
        ArrayList<Account> list = new ArrayList<>();
        Connection cn = null;
        String sql = "select * from Accounts where fullname like ?";
        
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, "%"+fullname+"%");
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Account acc = new Account();
                    acc.setAccID(rs.getInt("accID"));
                    acc.setEmail(rs.getString("email"));
                    acc.setPassword(rs.getString("password"));
                    acc.setFullname(rs.getString("fullname"));
                    acc.setPhone(rs.getString("phone"));
                    acc.setStatus(rs.getInt("status"));
                    acc.setRole(rs.getInt("role"));
                    list.add(acc);
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    public static Account getAccount(String token) {
        Connection cn = null;
        Account acc = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select * from dbo.Accounts where Accounts.token =? ";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, token);
                
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int AccID = rs.getInt("accID");
                    String Email = rs.getString("email");
                    String Password = rs.getString("password");
                    String Fullname = rs.getString("fullname");
                    String Phone = rs.getString("phone");
                    int Status = rs.getInt("status");
                    int Role = rs.getInt("role");
                    acc = new Account(AccID, Email, Password, Fullname, Status, Phone, Role);

                }
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
        return acc;
    }
    
    
    
    public static Account getAccountByName(String name) {
        Connection cn = null;
        Account acc = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select * from dbo.Accounts where Accounts.fullname like ? ";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%"+ name + "%");
                
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int AccID = rs.getInt("accID");
                    String Email = rs.getString("email");
                    String Password = rs.getString("password");
                    String Fullname = rs.getString("fullname");
                    String Phone = rs.getString("phone");
                    int Status = rs.getInt("status");
                    int Role = rs.getInt("role");
                    acc = new Account(AccID, Email, Password, Fullname, Status, Phone, Role);

                }
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
        return acc;
    }

    public static boolean updateToken(String token,String email){
        String sql = "update Accounts set token =? where email=?";
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                ArrayList<Account> list = getAccount();
                for (Account account : list) {
                    if (account.getEmail().equals(email)) {
                        PreparedStatement st = cn.prepareStatement(sql);
                        st.setString(1, token);
                        st.setString(2, email);
                        st.executeUpdate();
                        return true;
                    }
                }
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
    
    public static boolean updateAccountStatus(String email, int status) {
        String sql = "update Accounts set status=? where email=?";
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                ArrayList<Account> list = getAccount();
                for (Account account : list) {
                    if (account.getEmail().equals(email)) {
                        PreparedStatement st = cn.prepareStatement(sql);
                        st.setInt(1, status);
                        st.setString(2, email);
                        st.executeUpdate();
                        return true;
                    }
                }
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

    public static boolean updateAccountStatus(String email,  String newFullname, String newPhone) {
        String sql = "update Accounts set  fullname=?, phone=? where email=?";
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                ArrayList<Account> list = getAccount();
                for (Account account : list) {
                    if (account.getEmail().equals(email)) {
                        PreparedStatement st = cn.prepareStatement(sql);
                        
                        st.setString(1, newFullname);
                        st.setString(2, newPhone);
                        st.setString(3, email);
                        st.executeUpdate();
                        return true;
                    }
                }
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

    public static boolean insertAccount(String newEmail, String newPassword, String newFullname, String newPhone, int newStatus, int newRole) {
        String sql = "insert into Accounts values(?,?,?,?,?,?,?)";
        Connection cn = null;
        boolean check = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, newEmail);
                st.setString(2, newPassword);
                st.setString(3, newFullname);
                st.setString(4, newPhone);
                st.setInt(5, newStatus);
                st.setInt(6, newRole);
                st.setString(7, null);
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
}
