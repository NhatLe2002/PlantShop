/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import sample.dto.Order;
import sample.dto.OrderDetail;
import sample.utils.DBUtils;

/**
 *
 * @author HNC
 */
public class OrderDAO {

    public static ArrayList<Order> getOrders(String email) {
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = null;
        String sql = "select OrderID, OrdDate, shipdate, o.status, o.AccID  from Accounts a join Orders o on a.accID=o.AccID where email=?";
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {

                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    Order ord = new Order();
                    ord.setOrderID(rs.getInt("OrderID"));
                    ord.setOrderDate(rs.getString("OrdDate"));
                    ord.setShipDate(rs.getString("shipdate"));
                    ord.setStatus(rs.getInt("status"));
                    ord.setAccID(rs.getInt("accID"));
                    list.add(ord);
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

    public static ArrayList<Order> getOrders(int status) {
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = null;
        String sql = "select * from Orders where status=?";
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {

                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, status);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    Order ord = new Order();
                    ord.setOrderID(rs.getInt("OrderID"));
                    ord.setOrderDate(rs.getString("OrdDate"));
                    ord.setShipDate(rs.getString("shipdate"));
                    ord.setStatus(rs.getInt("status"));
                    ord.setAccID(rs.getInt("accID"));
                    list.add(ord);
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

    public static ArrayList<Order> getAllOrders() {
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = null;
        String sql = "select * from Orders ";
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {

                PreparedStatement pst = cn.prepareStatement(sql);

                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    Order ord = new Order();
                    ord.setOrderID(rs.getInt("OrderID"));
                    ord.setOrderDate(rs.getString("OrdDate"));
                    ord.setShipDate(rs.getString("shipdate"));
                    ord.setStatus(rs.getInt("status"));
                    ord.setAccID(rs.getInt("accID"));
                    list.add(ord);
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

    public static Order getOrder(int orderID) {
        Connection cn = null;
        Order ord = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select *from dbo.Orders where OrderID = ? ";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, orderID);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {

                    ord = new Order(rs.getInt("OrderID"), rs.getString("OrdDate"), rs.getString("shipdate"), rs.getInt("status"), rs.getInt("accID"));

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
        return ord;
    }

    public static boolean updateOrderStatus(int orderID, int status) {
        String sql = "update Orders set status=? where OrderID=? ";
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                ArrayList<Order> list = getAllOrders();
                for (Order ord : list) {
                    if (ord.getOrderID() == orderID) {
                        PreparedStatement st = cn.prepareStatement(sql);
                        st.setInt(1, status);
                        st.setInt(2, orderID);
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

    public static ArrayList<OrderDetail> getOrderDetail(int orderID) {
        Connection cn = null;
        ArrayList<OrderDetail> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select* from dbo.OrderDetail o join dbo.Plants p on o.FID=p.PID where o.OrderID=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, orderID);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int detailID = rs.getInt("DetailId");
                        int plantID = rs.getInt("PID");
                        String plantName = rs.getString("Pname");
                        int price = rs.getInt("price");
                        String imgPath = rs.getString("imgPath");
                        int quantity = rs.getInt("quantity");
                        OrderDetail orderdetail = new OrderDetail(detailID, orderID, plantID, plantName, price, imgPath, quantity);
                        list.add(orderdetail);

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static boolean insertOrder(String email, HashMap<String, Integer> cart) {
        boolean result = false;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                int accid = 0, orderid = 0;
                cn.setAutoCommit(false);
                String sql = "select accID from Accounts where Accounts.email=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    accid = rs.getInt("accID");
                }
                System.out.println("accountid:" + accid);
                Date d = new Date(System.currentTimeMillis());
                System.out.println("order date:" + d);
                sql = "insert Orders(OrdDate,status,AccID) values(?,?,?)";
                pst = cn.prepareStatement(sql);
                pst.setDate(1, d);
                pst.setInt(2, 1);
                pst.setInt(3, accid);
                pst.executeUpdate();
                sql = "select top 1 orderID from Orders order by orderId desc";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    orderid = rs.getInt("orderID");
                }
                System.out.println("orderid:" + orderid);
                Set<String> pids = cart.keySet();
                for (String pid : pids) {
                    sql = "insert OrderDetail values(?,?,?)";
                    pst = cn.prepareStatement(sql);
                    pst.setInt(1, orderid);
                    pst.setInt(2, Integer.parseInt(pid.trim()));
                    pst.setInt(3, cart.get(pid));
                    pst.executeUpdate();
                    cn.commit();
                    cn.setAutoCommit(true);
                }
                return true;
            } else {
                System.out.println("Cannot insert order");
            }
        } catch (Exception e) {
            try {
                if (cn != null) {
                    cn.rollback();
                }
            } catch (Exception a) {
                e.printStackTrace();

            }
            e.printStackTrace();
            result = false;
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                };
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static boolean deleteOrder(int orderid) {
        boolean result = false;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                cn.setAutoCommit(false);
                String sql = "delete from dbo.OrderDetail where OrderID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, orderid);
                ResultSet rs = pst.executeQuery();
                sql = "delete from dbo.Orders where OrderID = ?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, orderid);
                pst.executeUpdate();
                rs = pst.executeQuery();
                cn.setAutoCommit(true);

                return true;
            } else {
                System.out.println("Cannot delete order");
            }
        } catch (Exception e) {
            try {
                if (cn != null) {
                    cn.rollback();
                }
            } catch (Exception a) {
                e.printStackTrace();

            }
            e.printStackTrace();
            result = false;
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                };
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    public static int getCateId(String catename) {
        Connection cn = null;
        int cateid = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select CateID from dbo.Categories where CateName = ? ";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, catename);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {

                    cateid = rs.getInt(1);

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
        return cateid;
    }
}
