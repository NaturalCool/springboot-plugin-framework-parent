package com.basic.example.plugin1.job;

import java.util.HashMap;
import java.util.Map;

import com.basic.example.main.quartz.QuartzJob;
import com.basic.example.plugin1.config.PluginConfig1;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 插件1 job
 * </p>
 *
 * @author isaac 2020/6/16 17:53
 * @since 1.0
 */
@Component
public class Plugin1QuartzJob implements QuartzJob {


    private static final String JOB_NAME = "plugin-job";
    private static final String TRIGGER_NAME = "plugin-trigger";

    private final PluginConfig1 pluginConfig1;

    public Plugin1QuartzJob(PluginConfig1 pluginConfig1) {
        this.pluginConfig1 = pluginConfig1;
    }


    @Override
    public boolean enable() {
        return pluginConfig1.getJobEnable();
    }

    @Override
    public String jobName() {
        return JOB_NAME;
    }

    @Override
    public String triggerName() {
        return TRIGGER_NAME;
    }

    @Override
    public String cron() {
        return pluginConfig1.getJobCron();
    }

    @Override
    public int delaySeconds() {
        return 0;
    }

    @Override
    public Class<? extends Job> jobClass() {
        return JobImpl.class;
    }

    @Override
    public Map<String, Object> jobData() {
        Map<String, Object> jobData = new HashMap<>();
        jobData.put(JOB_NAME, JOB_NAME);
        return jobData;
    }


    public static class JobImpl implements Job {

        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
            String string = jobDataMap.getString(JOB_NAME);
            System.out.println("plugin1 job start = " + string);
        }
    }

}
