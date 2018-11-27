package com.android.supay.test.jobscheduller;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import com.android.supay.test.R;
import com.android.supay.test.util.Util;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class JobSchedullerActivity extends AppCompatActivity {

    private static String TAG = JobSchedullerActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_scheduller);
        ButterKnife.bind(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.buttonStart) public void scheduleJob(View view){
        ComponentName componentName = new ComponentName( this, ExampleJobService.class);

        JobInfo jobInfo = new JobInfo.Builder( 123, componentName)
                .setRequiresCharging(false)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)
                .setPeriodic(15*60*1000)
                .build();

        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = scheduler.schedule(jobInfo);

        if(resultCode == JobScheduler.RESULT_SUCCESS){
            Util.showLog(TAG, "Job scheduling success.");

        }else{
            Util.showLog(TAG, "Job scheduling failed.");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.buttonStop) public void scheduleJobStop(View view){
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.cancel(123);
        Util.showLog(TAG, "job cancelled");
    }
}
