package cc.mrbird.febs.common.runner;

import cc.mrbird.febs.common.properties.FebsProperties;
import cc.mrbird.febs.common.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

@Slf4j
@Component
public class FebsStartedUpRunner implements ApplicationRunner {

    @Autowired
    private ConfigurableApplicationContext context;
    @Autowired
    private FebsProperties febsProperties;
    @Autowired
    private RedisService redisService;

    @Value("${server.port:8080}")
    private String port;
    @Value("${server.servlet.context-path:}")
    private String contextPath;
    @Value("${spring.profiles.active}")
    private String active;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            // 测试 Redis连接是否正常
            redisService.hasKey("febs_test");
        } catch (Exception e) {
            log.error(" ______      _____ _        _ ");
            log.error("|  ____/\\   |_   _| |      | |");
            log.error("| |__ /  \\    | | | |      | |");
            log.error("|  __/ /\\ \\   | | | |      | |");
            log.error("| | / ____ \\ _| |_| |____  |_|");
            log.error("|_|/_/    \\_\\_____|______| (_)");
            log.error("NTMS启动失败，{}", e.getMessage());
            log.error("Redis连接异常，请检查Redis连接配置并确保Redis服务已启动");
            // 关闭 FEBS
            context.close();
        }
        if (context.isActive()) {
            InetAddress address = InetAddress.getLocalHost();
            String url = String.format("http://%s:%s", address.getHostAddress(), port);
            String loginUrl = febsProperties.getShiro().getLoginUrl();
            if (StringUtils.isNotBlank(contextPath))
                url += contextPath;
            if (StringUtils.isNotBlank(loginUrl))
                url += loginUrl;
            log.info("  _____ ____  __  __ _____  _      ______ _______ ______   _ ");
            log.info(" / ____/ __ \\|  \\/  |  __ \\| |    |  ____|__   __|  ____| | |");
            log.info("| |   | |  | | \\  / | |__) | |    | |__     | |  | |__    | |");
            log.info("| |   | |  | | |\\/| |  ___/| |    |  __|    | |  |  __|   | |");
            log.info("| |___| |__| | |  | | |    | |____| |____   | |  | |____  |_|");
            log.info(" \\_____\\____/|_|  |_|_|    |______|______|  |_|  |______| (_)");
            log.info("NTMS新型教学管理系统启动完毕，地址：{}", url);

            boolean auto = febsProperties.isAutoOpenBrowser();
            if (auto && StringUtils.equalsIgnoreCase(active, "dev")) {
                String os = System.getProperty("os.name");
                // 默认为 windows时才自动打开页面
                if (StringUtils.containsIgnoreCase(os, "windows")) {
                    //使用默认浏览器打开系统登录页
                    Runtime.getRuntime().exec("cmd  /c  start " + url);
                }
            }
        }
    }
}
