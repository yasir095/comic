package com.marvels.app.mvandroid.core.request;

import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by yasirmahmood on 23/09/2017.
 */

/**
 * Use this to make async Http Request
 * Should be called as a single instance
 * accepts a Runnable task or just call requestWebContents with url to make an async Request.
 */
public class TaskExecutor
{
    private TaskManager taskManager;

    int defaultPoolSize = 10;

    public TaskExecutor() {
        taskManager = new TaskManager(defaultPoolSize, defaultPoolSize, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    }

    public TaskExecutor(int poolSize)
    {
        this.defaultPoolSize = poolSize;
    }

    public void addTask(Runnable task){
        taskManager.execute(task);
    }
    /**
     * Provide delegate to get the response in that function
     * @param urlString
     * @param requestType
     * @param params
     */
    public void requestWebContents(String urlString, String requestType, Map<String, String> params, HttpResponseDelegate responseDelegate){
        HttpRequestRunnable runnable = new HttpRequestRunnable(urlString, requestType, responseDelegate);

        if(params != null && params.size()>0)
        {
            runnable.setPostParams(params);
        }

        taskManager.execute(runnable);
    }

    public TaskManager getTaskExecutor()
    {
        return taskManager;
    }

    public void shutDown()
    {
        this.taskManager.shutdown();
    }
}
