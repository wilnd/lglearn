package com.lglearn.proxy.staticproxy;

import com.lglearn.proxy.IRentingHouse;

public class RentingHouseProxy implements IRentingHouse {

    private IRentingHouse rentingHouse;

    public RentingHouseProxy(IRentingHouse iRentingHouse){
        rentingHouse = iRentingHouse;
    }

    @Override
    public void rentHouse() {
        System.out.println("中介");
        rentingHouse.rentHouse();
        System.out.println("增强功能2");
    }
}
