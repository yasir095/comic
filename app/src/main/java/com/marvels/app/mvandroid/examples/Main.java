package com.marvels.app.mvandroid.examples;

/**
 * Created by yasirmahmood on 24/09/2017.
 */

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("=======================================");

        Producer producer = new Producer(10);
        producer.start();

        Consumer consumer = new Consumer(producer);
        consumer.start();
    }
}
