package com.uaian.designpatterns.singlepattern;

public class HungrySinglePattern {

    private static HungrySinglePattern hungrySinglePattern = new HungrySinglePattern();

    private HungrySinglePattern() {
    }

    public static HungrySinglePattern getHungrySinglePattern(){
        return hungrySinglePattern;
    }

}
