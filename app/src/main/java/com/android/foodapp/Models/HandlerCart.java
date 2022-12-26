package com.android.foodapp.Models;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class HandlerCart {

    public HandlerCart(){}

    public List<Cart> getCart(){
        List<Cart> listCart = Paper.book().read("cart");
        if(listCart == null || listCart.size() == 0){
            listCart = new ArrayList<Cart>();
            Paper.book().write("cart", listCart);
        }
        return listCart;
    }

    public void AddCart(Cart cart){
        List<Cart> listCart = getCart();
        boolean flag = false;
        for(int i=0;i<listCart.size(); i++){
            if(listCart.get(i).getIdFood() == cart.getIdFood()){
                flag = true;
                int quantity = listCart.get(i).getQuantity() + cart.getQuantity();
                listCart.get(i).setQuantity(quantity);
                listCart.get(i).setSubTotal(quantity * listCart.get(i).getPrice());
            }
        }

        if(flag == false){
            cart.setQuantity(cart.getQuantity());
            cart.setSubTotal(cart.getQuantity() * cart.getPrice());
            listCart.add(cart);
        }

        Paper.book().write("cart", listCart);
    }

    public void UpdateCart(int id, String type){
        List<Cart> listCart = getCart();
        int quantity = 0;
        for(int i=0; i<listCart.size();i++){
            if(listCart.get(i).getIdFood() == id){
                switch (type){
                    case "plus":
                        quantity = listCart.get(i).getQuantity() + 1;
                        listCart.get(i).setQuantity(quantity);
                        listCart.get(i).setSubTotal(listCart.get(i).getPrice() * quantity);
                        break;
                    case "minus":
                        if(listCart.get(i).getQuantity() != 1){
                            quantity = listCart.get(i).getQuantity() -1;
                            listCart.get(i).setQuantity(quantity);
                            listCart.get(i).setSubTotal(listCart.get(i).getPrice() * quantity);
                        }
                        break;
                }
            }
        }
        Paper.book().write("cart", listCart);
    }

    public double getTotal(){
        List<Cart> cartList = getCart();
        double total = 0;
        for(int i =0;i<cartList.size();i++){
            total += cartList.get(i).getSubTotal();
        }
        return total;
    }

    public void DeleteItemCart(int idCard){
        List<Cart> listCart = getCart();
        for (int i =0; i < listCart.size(); i++){
            if(listCart.get(i).getIdFood() == idCard){
                listCart.remove(i);
                break;
            }
        }
        Paper.book().write("cart", listCart);
    }

    public void DeleteAllCart(){
        Paper.book().delete("cart");
    }
}
