package test.Quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class HelloJob implements Job {
    public void execute(JobExecutionContext context) throws JobExecutionException {

        System.out.println(System.currentTimeMillis() + "Hello world");
    }

    public static void main(String[] args) throws SchedulerException {

        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("job1", "group1").build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group").startNow()
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInSeconds(2)
                        .repeatForever()).build();

        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger2", "group").startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ? *")).build();

        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler scheduler = sf.getScheduler();
        scheduler.scheduleJob(jobDetail,cronTrigger);
        scheduler.start();


    }
    class Test{

    }
}
