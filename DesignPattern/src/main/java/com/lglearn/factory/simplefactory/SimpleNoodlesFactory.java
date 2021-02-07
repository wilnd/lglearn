package com.lglearn.factory.simplefactory;

import com.lglearn.factory.simplefactory.noodles.INoodles;
import com.lglearn.factory.simplefactory.noodles.LZNoodles;
import com.lglearn.factory.simplefactory.noodles.PaoNoodles;

public class SimpleNoodlesFactory {

    public static final int LZ = 1;

    public static final int PAO = 2;

    public static INoodles createNoodles(int type) {
        switch (type) {
            case 1:
                return new LZNoodles();
            case 2:
                return new PaoNoodles();
            default:
                return new LZNoodles();
        }
    }

}
