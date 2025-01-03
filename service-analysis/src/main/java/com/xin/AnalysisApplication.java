package com.xin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;

/**
 * 分析入口
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
@RefreshScope
public class AnalysisApplication {
    /*
     * @Description全文检索入口
     * @Param  [args]
     * @Return      void
     * @Exception
     *
     */
    public static void main(String[] args) {
        SpringApplication.run(AnalysisApplication.class, args);
    }


}
