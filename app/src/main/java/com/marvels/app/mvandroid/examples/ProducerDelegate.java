package com.marvels.app.mvandroid.examples;

/**
 * Created by yasirmahmood on 24/09/2017.
 */

interface ProducerDelegate {
    String getMessage() throws InterruptedException;
    void addMessage() throws InterruptedException;
}
