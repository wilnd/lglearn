package com.lglearn;

import com.lglearn.factory.factorymethod.INoodlesFactory;
import com.lglearn.factory.factorymethod.NoodlesFactory.LZNoodlesFactory;
import com.lglearn.factory.simplefactory.SimpleNoodlesFactory;
import com.lglearn.factory.simplefactory.noodles.INoodles;
import com.lglearn.proxy.IRentingHouse;
import com.lglearn.proxy.ProxyFactory;
import com.lglearn.proxy.RentingHouseImpl;
import com.lglearn.proxy.staticproxy.RentingHouseProxy;
import org.junit.jupiter.api.Test;

public class MyTest {

    @Test
    public void test() {
        INoodles noodles = SimpleNoodlesFactory.createNoodles(1);
    }

    @Test
    public void test2(){
        INoodlesFactory  noodlesFactory = new LZNoodlesFactory();
        INoodles noodles = noodlesFactory.createNoodles();
    }

    @Test
    public void testStaticProxy(){
        IRentingHouse rentingHouse = new RentingHouseImpl();

        RentingHouseProxy rentingHouseProxy = new RentingHouseProxy(rentingHouse);
        rentingHouseProxy.rentHouse();
    }

    @Test
    public void testJDKProxy(){
        IRentingHouse rentingHouse = new RentingHouseImpl();

        IRentingHouse rentingHouseProxy = (IRentingHouse)ProxyFactory.getInstance().getCglibProxy(rentingHouse);
        rentingHouseProxy.rentHouse();
    }

}
