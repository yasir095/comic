package com.marvels.app.mvandroid.examples;

import java.util.Vector;

/**
 * Created by yasirmahmood on 24/09/2017.
 */

class Producer extends Thread implements ProducerDelegate
{
    private int queSize;
    private Vector messages = new Vector();

    public Producer(int queSize) {
        this.queSize = queSize;
    }

    @Override
    public synchronized String getMessage() throws InterruptedException {
        notify();
        while(messages.size() == 0)
        {
            wait();
        }

        Object message = messages.firstElement();
        messages.removeElement(message);

        return String.valueOf(message);
    }

    @Override
    public synchronized void addMessage() throws InterruptedException
    {
        while(this.messages.size() == queSize)
        {
            wait();
        }

        messages.addElement(System.currentTimeMillis());
        notify();
    }

    @Override
    public void run()
    {
        //catch on interrupt exception
        try
        {
            while (true)
            {
                addMessage();
                sleep(500);
            }
        }
        catch (InterruptedException e)
        {

        }
    }
}
