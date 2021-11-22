//package com.example.demo2.template.schedule.sample1;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Select;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.SchedulingConfigurer;
//import org.springframework.scheduling.config.ScheduledTaskRegistrar;
//import org.springframework.scheduling.support.CronTrigger;
//
//import java.time.LocalDateTime;
//
///**
// * 动态配置定时任务
// * 此实现缺点在于修改了数据库的执行周期后不能立即触发,需要执行一次原有的周期后才能按新周期执行
// *
// * @author zhanghao
// * @date 2021-11-22
// */
//@Configuration
//@EnableScheduling
//public class CompleteScheduleConfig implements SchedulingConfigurer {
//    @Mapper
//    public interface CronMapper {
//        @Select("select cron from cron limit 1")
//        String getCron();
//    }
//
//    @Autowired
//    @SuppressWarnings("all")
//    CronMapper cronMapper;
//
//    /**
//     * 执行定时任务.
//     */
//    @Override
//    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//        taskRegistrar.addTriggerTask(
//                //1.添加任务内容(Runnable)
//                () -> System.out.println("执行定时任务: " + LocalDateTime.now().toLocalTime()),
//                //2.设置执行周期(Trigger)
//                triggerContext -> {
//                    //2.1 从数据库获取执行周期
//                    String cron = cronMapper.getCron();
//                    //2.2 合法性校验.
//                    if (StringUtils.isEmpty(cron)) {
//                        // Omitted Code ..
//                        System.out.println("cron表达式不能为空");
//                    }
//                    //2.3 返回执行周期(Date)
//                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
//                }
//        );
//    }
//}
