package org.designpattern.iterator;

public abstract class Factory {
    public final Iterator create(Aggregate list, int type) {
		return createProduct(list, type);
    }
    protected abstract Iterator createProduct(Aggregate list, int type);
}
