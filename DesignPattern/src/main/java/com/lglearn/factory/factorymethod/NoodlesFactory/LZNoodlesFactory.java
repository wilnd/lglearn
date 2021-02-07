package com.lglearn.factory.factorymethod.NoodlesFactory;

import com.lglearn.factory.factorymethod.INoodlesFactory;
import com.lglearn.factory.simplefactory.noodles.INoodles;
import com.lglearn.factory.simplefactory.noodles.LZNoodles;

public class LZNoodlesFactory implements INoodlesFactory {
    @Override
    public INoodles createNoodles() {
        return new LZNoodles();
    }
}
