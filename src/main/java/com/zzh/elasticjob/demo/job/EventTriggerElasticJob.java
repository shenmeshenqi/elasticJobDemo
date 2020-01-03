package com.zzh.elasticjob.demo.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j(topic="ICP_BIZ_EVENT")
@Component
public class EventTriggerElasticJob implements SimpleJob {
    @Override
    public void execute(ShardingContext shardingContext) {
        //log.info("本次任务开始{}", shardingContext);
        System.out.printf(Thread.currentThread().getName()+": 我是个打印,分片是%d\n",shardingContext.getShardingItem());
        //log.info("本次任务结束{}",shardingContext);
       // log.info("EventTriggerElasticJob, shardingContext:{}, reuslt:{}", shardingContext);
    }
}
