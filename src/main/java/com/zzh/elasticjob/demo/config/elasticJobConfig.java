package com.zzh.elasticjob.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.event.rdb.JobEventRdbConfiguration;
import com.dangdang.ddframe.job.lite.api.listener.AbstractDistributeOnceElasticJobListener;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@Slf4j(topic = "ICP_BIZ_CONFIG")
public class elasticJobConfig {
    @Autowired
    @Lazy
    private DruidDataSource dataSource;

    @Bean
    public JobEventConfiguration jobEventConfiguration(){
        return new JobEventRdbConfiguration(dataSource);
    }

    @Bean
    public ElasticJobListener elasticJobListener(){
        return new EventTriggerElasticJobListener(500,100000);
    }

    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter regCenter(
            @Value("${elastic.regCenter.serverList}") final String serverList,
            @Value("${elastic.regCenter.namespace}") final String namespace,
            @Value("${elastic.regCenter.maxRetries}") int maxRetries
    ){
        ZookeeperConfiguration zookeeperConfiguration = new ZookeeperConfiguration(serverList, namespace);
        zookeeperConfiguration.setMaxRetries(maxRetries);//设置最大重试次数
        return new ZookeeperRegistryCenter(zookeeperConfiguration);
    }

}
