package com.lglearn.proxy;

/**
 * 委托方
 */
public class RentingHouseImpl implements IRentingHouse {
    @Override
    public void rentHouse() {
        System.out.println("租房");
    }
}
