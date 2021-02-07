package com.lglearn;

import com.lglearn.factory.factorymethod.INoodlesFactory;
import com.lglearn.factory.factorymethod.NoodlesFactory.LZNoodlesFactory;
import com.lglearn.factory.simplefactory.SimpleNoodlesFactory;
import com.lglearn.factory.simplefactory.noodles.INoodles;
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

}
