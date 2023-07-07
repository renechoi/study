package org.designpattern.iterator;

public interface Aggregate {
    Iterator iterator(int type);
    int getLength();
}
