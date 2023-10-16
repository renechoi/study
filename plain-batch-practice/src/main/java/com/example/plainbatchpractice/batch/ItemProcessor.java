package com.example.plainbatchpractice.batch;

public interface ItemProcessor<I, O> {

    O process(I item);

}
