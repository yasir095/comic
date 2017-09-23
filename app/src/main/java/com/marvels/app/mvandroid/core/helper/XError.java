package com.marvels.app.mvandroid.core.helper;

import java.util.ArrayList;

/**
 * Created by yasirmahmood on 11/06/2017.
 */

public class XError extends Error {

    private int statusCode;
    private String id;
    private int status;
    private String title;
    private String message;
    private ArrayList<Error> errors;

    public XError() {

    }

    public XError(String id, int level, String title, String message) {
        this.id = id;
        this.status = level;
        this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return this.title;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return this.id;
    }

    public int getStatusCode() {
        return this.status;
    }

    public ArrayList<Error> getErrors() {
        return this.errors;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStatusCode(int level) {
        this.status = level;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setErrors(ArrayList<Error> errors) {
        this.errors = errors;
    }
}
