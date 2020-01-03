package com.zzh.elasticjob.demo.config;

import com.google.common.base.Preconditions;
import com.zzh.elasticjob.demo.job.EventTriggerElasticJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author riliang.zrl
 * @date 2019/10/30 下午12:01
 * @Description
 **/
@Slf4j(topic = "ICP_BIZ_CONFIG")
@Component
public class ConfigJobManager {


    @Autowired
    private ElasticJobFactory elasticJobFactory;

    @Resource
    private EventTriggerElasticJob eventTriggerElasticJob;

    private static final int shardingTotalCount = 3;

    private static final String SYMBOL_ZERO = "0";

    @Value("${elastic.job.cron}")
    private String cron;

    /**
     * 自然时
     */
    private static final int NATURAL_TIME = 1;

    public void updateJob(String eventConfigCode) {

        Preconditions.checkNotNull(eventConfigCode);
        String jobName = eventConfigCode;
        elasticJobFactory.addJob(jobName, eventTriggerElasticJob,  cron, shardingTotalCount, jobName);
    }




    public static void main(String[] args) {
    }

}
