package com.uaian.designpatterns.singlepattern;

public class LazySinglePattern {

    private static LazySinglePattern lazySinglePattern;

    private LazySinglePattern() {
    }

    public static LazySinglePattern getLazySinglePattern(){
        if(lazySinglePattern == null) {
            lazySinglePattern = new LazySinglePattern();
        }
        return lazySinglePattern;
    }
}
