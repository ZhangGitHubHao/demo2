package com.example.demo2.template.schedule.sample2;

import java.util.concurrent.ScheduledFuture;

/**
 * @author zhanghao
 * @date 2021-11-22
 */
public class ScheduledTask {
    public volatile ScheduledFuture<?> future;
    /**
     * 取消定时任务
     */
    public void cancel() {
        ScheduledFuture<?> future = this.future;
        if (future != null) {
            future.cancel(true);
        }
    }
}
