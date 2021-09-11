package com.hsf.my.netty.netty03nio;

import java.nio.IntBuffer;

public class SelectedTest {
    public static void main(String[] args) {

        IntBuffer intBuffer = IntBuffer.allocate(10);
        for(int i = 1; i < 6; i++){
            intBuffer.put(i);
        }

        System.out.println(intBuffer.capacity());
        System.out.println(intBuffer.limit());
        System.out.println(intBuffer.position());
        intBuffer.flip();
        System.out.println(intBuffer.capacity());
        System.out.println(intBuffer.limit());
        System.out.println(intBuffer.position());
        System.out.println("+++++");
        System.out.println(intBuffer.get());
        System.out.println(intBuffer.capacity());
        System.out.println(intBuffer.limit());
        System.out.println(intBuffer.position());
        intBuffer.clear();
        System.out.println("+++++");
        System.out.println(intBuffer.capacity());
        System.out.println(intBuffer.limit());
        System.out.println(intBuffer.position());
        for(int i = 1; i < 6; i++){
            intBuffer.put(i*2);
        }
        System.out.println("+++++");
        System.out.println(intBuffer.capacity());
        System.out.println(intBuffer.limit());
        System.out.println(intBuffer.position());

        intBuffer.flip();
        System.out.println("+++++");
        System.out.println( intBuffer.get());
        System.out.println(intBuffer.capacity());
        System.out.println(intBuffer.limit());
        System.out.println(intBuffer.position());

        intBuffer.mark();
        System.out.println( intBuffer.get());
        System.out.println(intBuffer.position());
        intBuffer.reset();
        System.out.println(intBuffer.position());




    }
}
