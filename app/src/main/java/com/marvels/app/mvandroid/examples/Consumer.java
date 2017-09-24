package com.marvels.app.mvandroid.examples;

/**
 * Created by yasirmahmood on 24/09/2017.
 */

class Consumer extends Thread
{
    private Producer producer;

    public Consumer(Producer producer) {
        this.producer = producer;
    }

    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                String message = producer.getMessage()+"";
                System.out.println("Message | " + message);
                sleep(1500);
            }
        }
        catch (Exception exception)
        {
            System.out.println(exception.getMessage());
        }
    }
}
