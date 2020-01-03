package com.zzh.elasticjob.demo.config;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.AbstractDistributeOnceElasticJobListener;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "ICP_BIZ_EVENT")
public class EventTriggerElasticJobListener extends AbstractDistributeOnceElasticJobListener {

    public EventTriggerElasticJobListener(long startedTimeoutMilliseconds, long completedTimeoutMilliseconds) {
        super(startedTimeoutMilliseconds, completedTimeoutMilliseconds);
    }

    @Override
    public void doBeforeJobExecutedAtLastStarted(ShardingContexts shardingContexts) {
       // log.info("doBeforeJobExecutedAtLastStarted, shardingContexts: {}", shardingContexts);
    }

    @Override
    public void doAfterJobExecutedAtLastCompleted(ShardingContexts shardingContexts) {
       // log.info("doAfterJobExecutedAtLastCompleted, shardingContexts:{}", shardingContexts);
    }
}
