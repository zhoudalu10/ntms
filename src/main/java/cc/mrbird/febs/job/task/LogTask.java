package cc.mrbird.febs.job.task;

import cc.mrbird.febs.job.mapper.LogTaskMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LogTask {

    @Autowired
    private LogTaskMapper logTaskMapper;

    public void deleteLoginLog() {
        log.info("删除登陆日志任务正在被执行...");
        logTaskMapper.deleteLoginLog();
    }

    public void deleteJobLog() {
        log.info("删除Job日志任务正在被执行...");
        logTaskMapper.deleteJobLog();
    }
}
