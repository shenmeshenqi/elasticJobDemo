package com.zzh.elasticjob.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author riliang.zrl
 * @date 2019/11/2 下午3:16
 * @Description
 **/
@Slf4j(topic = "ICP_BIZ_MSG")
@Component
public class ConfigJobInit {

    @Resource
    private ConfigJobManager configJobManager;


    //@PostConstruct
    public void initJobs() {
        try {
            configJobManager.updateJob("啦啦啦");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
