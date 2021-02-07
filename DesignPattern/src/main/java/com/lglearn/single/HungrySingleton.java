package com.lglearn.single;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HungrySingleton {

    private static HungrySingleton instance = new HungrySingleton();

    public HungrySingleton getInstance(){
        return this.instance;
    }

}
