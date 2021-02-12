package com.hsf.learn.demo.nio;

import java.nio.IntBuffer;

public class BufferDemo {
    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(5);
        intBuffer.put(1);
        intBuffer.put(2);
        intBuffer.put(3);
        intBuffer.flip();

        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
    }
}
