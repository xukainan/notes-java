package com.uaian.designpatterns.singlepattern;

public class StaticClassSinglePattern {

    private StaticClassSinglePattern() {
    }

    private static class SingleFactory{
        private static StaticClassSinglePattern staticClassSinglePattern = new StaticClassSinglePattern();
    }

    public StaticClassSinglePattern getStaticClassSinglePattern(){
        return SingleFactory.staticClassSinglePattern;
    }
}
