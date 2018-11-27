package com.android.supay.test.jobscheduller;

import android.annotation.SuppressLint;
import android.app.job.JobParameters;
import android.app.job.JobService;

import com.android.supay.test.util.Util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressLint("NewApi")
public class ExampleJobService extends JobService {

    private static String TAG = ExampleJobService.class.getSimpleName();

    private boolean jobHasCancel = false;

    @Override
    public boolean onStartJob(JobParameters params) {
        Util.showLog(TAG, "Entra a on start job.");
        backGroundWork(params);
        //Nosotros lo matamos
        return true;
        //Sistema lo mata
        //return false;
    }

    private void backGroundWork(JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 15; i++){
                    DateFormat df = new SimpleDateFormat("HH:mm:ss");
                    String date = df.format(Calendar.getInstance().getTime());
                    Date currentTime = Calendar.getInstance().getTime();
                    Util.showLog( TAG,"Vallor hell yeah " + i + "Date " + date + "  current time " + currentTime);
                    if(jobHasCancel){
                        return;
                    }
                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                Util.showLog(TAG, "Job Finished");
                jobFinished( params, false);
            }
        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Util.showLog(TAG,  "On job cancelled");
        jobHasCancel = true;
        return true;
    }
}
