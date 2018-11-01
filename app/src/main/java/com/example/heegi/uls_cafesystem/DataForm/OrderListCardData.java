package com.example.heegi.uls_cafesystem.DataForm;

public class OrderListCardData {
    private String orderTime;
    private String menu;

    public OrderListCardData(String orderTime, String menu) {
        this.orderTime = orderTime;
        this.menu = menu;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public String getMenu() {
        return menu;
    }
}
