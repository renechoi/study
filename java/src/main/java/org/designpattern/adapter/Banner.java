package org.designpattern.adapter;

public class Banner {
    private String string;
    public Banner(String string) {
        this.string = string;
    }
    public void showWithParenthesis() {
        System.out.println("(" + string + ")");
    }
    public void showWithAster() {
        System.out.println("*" + string + "*");
    }
}
