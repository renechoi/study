package org.designpattern.proxy;

public interface Printable {
    void setPrinterName(String name);   // 이름의 설정
    String getPrinterName();            // 이름의 취득
    void print(String string);          // 문자열 표시(프린트아웃)
}
