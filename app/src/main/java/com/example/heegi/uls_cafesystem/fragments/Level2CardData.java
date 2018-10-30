package com.example.heegi.uls_cafesystem.fragments;

public class Level2CardData {
    private String orderNo;
    private String menu;

    public Level2CardData(String orderNo, String menu) {
        this.orderNo = orderNo;
        this.menu = menu;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public String getMenu() {
        return menu;
    }
}
