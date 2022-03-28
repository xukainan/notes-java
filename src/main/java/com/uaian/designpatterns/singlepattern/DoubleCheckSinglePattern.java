package com.uaian.designpatterns.singlepattern;

public class DoubleCheckSinglePattern {

    private static volatile DoubleCheckSinglePattern doubleCheckSinglePattern;

    private DoubleCheckSinglePattern() {
    }

    public static DoubleCheckSinglePattern getDoubleCheckSinglePattern(){
        if(doubleCheckSinglePattern == null) {
            synchronized (DoubleCheckSinglePattern.class) {
                if(doubleCheckSinglePattern == null) {
                    doubleCheckSinglePattern = new DoubleCheckSinglePattern();
                }
            }
        }

        return doubleCheckSinglePattern;
    }
}
