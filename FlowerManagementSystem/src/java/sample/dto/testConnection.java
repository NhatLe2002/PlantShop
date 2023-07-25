package sample.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import sample.dao.AccountDAO;
import sample.dao.OrderDAO;
import sample.dao.PlantDAO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author PC
 */
public class testConnection {

    public static void main(String[] args) {
        Account acc = AccountDAO.getAccount("test@gmail.com", "test");
        if (acc != null) {
            if (acc.getRole() == 1) {
                System.out.println("I am an admin");
            } else {
                System.out.println("I am an user");
            }
        } else {
            System.out.println("login fail");
        }

        //check plantDAO.getPlant
        System.out.println("plantDAO.getPlant");
        ArrayList<Plant> list = new ArrayList<>();
        if (PlantDAO.getPlants("", "") != null) {
            System.out.println(PlantDAO.getPlants("", "").get(1).getName());
        }

        System.out.println("test getPlant(pid)");
        System.out.println(PlantDAO.getPlants(2).getImgpath());

//        System.out.println("check updateToken");
//        if (AccountDAO.updateToken("456", "vu@gmail.com")) {
//            System.out.println("check updateToken oke nhe");
//        } else {
//            System.out.println("check sai roi");
//        }
//        System.out.println("check getAccount(token)");
//        Account acc1 = AccountDAO.getAccount("123");
//        if (acc1 != null) {
//            System.out.println(acc1.getEmail());
//        } else
//            System.out.println("check fail");
//        
//        if (AccountDAO.insertAccount("ngjdsg@gmail.com", "1", "thien cao", "01516512313", 1, 0)) {
//            System.out.println("oke");
//        } else
//            System.out.println("nhu lz");
//        
        //Test the first challenge
//        System.out.println("---Challenge 1---");
//        ArrayList<Account> list = AccountDAO.getAccount();
//        for (Account account : list) {
//            System.out.println(account.getAccID() +","+ account.getEmail()+","+ 
//                    account.getPassword() +","+ account.getFullname()  +","+  
//                    account.getPhone() +","+ account.getStatus() +","+ 
//                    account.getRole());
//                                
//                               
//        }
//        
//        //Test the second challenge
//        System.out.println("---Challenge 2---");
//        if(AccountDAO.updateAccountStatus("test@gmail.com", 0))
//            System.out.println("update status successfully");
//        else System.out.println("can not update status!");
//        ////Test the third challenge
        System.out.println("---Challenge 3---");
        if(AccountDAO.updateAccountStatus("test@gmail.com", "ThiNo2002", "15123123"))
            System.out.println("updated profile successfully");
        else System.out.println("update profile fail!");
//        
//        
//        check getOrder
//        LocalDate localDate = LocalDate.now();
//        System.out.println(localDate);
//        LocalDate localDate1 = LocalDate.parse("2024-02-26", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        System.out.println(localDate1);
//        System.out.println(localDate.isAfter(localDate1));
//        
//        
//        
//        System.out.println("getAccount");
//        System.out.println(AccountDAO.getAccount("admin@gmail.com", "admin").getFullname());
//        
        ArrayList<Account> listOrder = AccountDAO.getAccountByPhone("thien");
        for (Account o : listOrder) {
            System.out.println(o.getAccID());
        }
//            System.out.println("test get order(int)");
//            ArrayList<Order> listOrd = OrderDAO.getOrders(2);
//            for(Order o : listOrd){
//                System.out.println(o.getStatus());
//            }
//            
//             System.out.println("getOrder");
//        ArrayList<Order> listOrder = OrderDAO.getOrders("test@gmail.com");
//        for (Order o : listOrder) {
//            System.out.println( o.getOrderID() +" "+ o.getStatus() +" "+  o.getAccID() );
//            System.out.println(o.getAccID());
//            System.out.println(o.getStatus());
//            if (o.getStatus()==3) {
//                System.out.println(o.getStatus());
//            }
//        }
//        System.out.println(OrderDAO.getOrder(4).getAccID());
//        System.out.println("test update order");
//        if (OrderDAO.updateOrderStatus(5, 3)) {
//            System.out.println("update oke");
//        }
//
//        ArrayList<OrderDetail> listOrderDetail = OrderDAO.getOrderDetail(8);
//        for (OrderDetail o : listOrderDetail) {
//            System.out.println(o.getOrderID() + " " + o.getPlantID() + " " + o.getQuantity());
//
////        }
//        }
        
        
        
//        if (OrderDAO.deleteOrder(10)) {
//            System.out.println("delete oke nek");
//        }


//           ArrayList<Plant> listplat = PlantDAO.getAllPlants();
//           for (Plant plant : listplat) {
//               System.out.println(plant.getCatename());
//        }



    Plant plant = new Plant(1, "Hoa", 150, "images/img9.jpg", "Hoa Lan rung nek", 1, 3, "others");
        System.out.println(plant.getDescription());
           if (PlantDAO.insertPlant(plant)) {
               System.out.println("oke roi nek");
        } else {
               System.out.println("sai roi nek");
        }

    }
}
