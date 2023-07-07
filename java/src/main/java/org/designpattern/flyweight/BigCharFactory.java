package org.designpattern.flyweight;

import java.util.Hashtable;

public class BigCharFactory {
   
    private Hashtable<String,BigChar> pool = new Hashtable<>();
    
    private static BigCharFactory singleton = new BigCharFactory();
    
    private BigCharFactory() {
    }
    
    public static BigCharFactory getInstance() {
        return singleton;
    }
    
    public synchronized BigChar getBigChar(char charname) {
        BigChar bc = pool.get("" + charname);
        if (bc == null) {
            bc = new BigChar(charname); // 여기에서 BigChar의 인스턴스를 생성
            pool.put("" + charname, bc);
        }
        return bc;
    }
}
