package ch.epfl.sweng.qeeqbii;

// https://stackoverflow.com/questions/24152192/android-espresso-running-multiple-tests-sequentially

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.support.test.espresso.core.deps.guava.collect.Collections2;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitor;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;
import android.util.Log;

import org.junit.AfterClass;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.CountDownLatch;


public final class ActivityFinisher implements Runnable {

    private final Integer DELAY_MS = 200;

    static CountDownLatch latch = null;

    public static void finishOpenActivities() {
        latch = new CountDownLatch(1);
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new ActivityFinisher());
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private final ActivityLifecycleMonitor activityLifecycleMonitor;

    private ActivityFinisher() {
        this.activityLifecycleMonitor = ActivityLifecycleMonitorRegistry.getInstance();
    }

    public List<Activity> getActivities() {
        final List<Activity> activities = new ArrayList<Activity>();



        for (final Stage stage : EnumSet.range(Stage.CREATED, Stage.STOPPED)/*Stage.values()*/) {

            final Collection<Activity> activities_in_stage = activityLifecycleMonitor.getActivitiesInStage(stage);
            for (final Activity activity : activities_in_stage) {
                Log.d("STATE", "EXTERMINATE: Found " + activity.getClass().getName() + " in state " + stage);
            }

            activities.addAll(activities_in_stage);
        }

        return activities;
    }

    @Override
    public void run() {

        Boolean need_another_loop = true;

        while(need_another_loop) {

            need_another_loop = false;

            List<Activity> activities = getActivities();

            for (final Activity activity : activities) {
                if (!activity.isFinishing()) {
                    Log.d("STATE", "EXTERMINATE: Finishing " + activity.getClass().getName() + " isFinishing = " + activity.isFinishing());

                    activity.finish();

                    need_another_loop = true;
                }
            }

            try {
                Thread.sleep(DELAY_MS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        latch.countDown();
    }

    @AfterClass
    public static void finish_all_activities() {
        ActivityFinisher.finishOpenActivities();
    }
}
