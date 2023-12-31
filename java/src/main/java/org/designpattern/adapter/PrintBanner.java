package org.designpattern.adapter;

public class PrintBanner implements Print {
    private Banner banner;  //Composition
    public PrintBanner(String string) {
        this.banner = new Banner(string);
    }
    public void printWeak() {
        banner.showWithParenthesis();
    }
    public void printStrong() {
        banner.showWithAster();
    }
}
