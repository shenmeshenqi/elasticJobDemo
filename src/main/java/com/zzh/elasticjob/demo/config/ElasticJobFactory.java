package com.zzh.elasticjob.demo.config;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("elasticJobFactory")
@Slf4j(topic = "ICP_BIZ_CONFIG")
public class ElasticJobFactory {

    @Autowired
    private ZookeeperRegistryCenter regCenter;
    @Autowired
    private JobEventConfiguration jobEventConfiguration;
    @Autowired
    private ElasticJobListener elasticJobListener;

    public void addJob(final String jobName,
                       final SimpleJob elasticJob,
                       final String cron,
                       final int shardingTotalCount,
                       final String id) {

        LiteJobConfiguration jobConfig = getLiteJobConfiguration(jobName, elasticJob.getClass(), cron,
                shardingTotalCount, id);

        new SpringJobScheduler(elasticJob, regCenter,
                jobConfig,
                jobEventConfiguration, elasticJobListener).init();
    }

    private LiteJobConfiguration getLiteJobConfiguration(final String jobName,
                                                         final Class<? extends SimpleJob> jobClass,
                                                         final String cron,
                                                         final int shardingTotalCount,
                                                         final String id) {

        log.info("liteJobConfiguration:{},{},{},{}", jobName, cron, shardingTotalCount,
                id);

        return LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(
                JobCoreConfiguration.newBuilder(jobName, cron, shardingTotalCount)
                        .jobParameter(id).build(),
                jobClass.getCanonicalName())
        ).overwrite(true).build();

    }

}

