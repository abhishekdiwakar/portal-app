package com.linneaus.portal.model;


import lombok.Data;

@Data
public class Test {
    private int type;
    private String x;
    private String y;
    private String time;
    private int button;
    private int correct;

    public Test(int type, String x, String y, String time) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.time = time;
    }

    public Test(int type, String x, String y, String time, int button, int correct) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.time = time;
        this.button = button;
        this.correct = correct;
    }
}
