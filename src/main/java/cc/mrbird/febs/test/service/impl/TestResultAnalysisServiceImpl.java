package cc.mrbird.febs.test.service.impl;

import cc.mrbird.febs.test.entity.TestResultAnalysis;
import cc.mrbird.febs.test.mapper.TestResultAnalysisMapper;
import cc.mrbird.febs.test.service.TestResultAnalysisService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TestResultAnalysisServiceImpl extends ServiceImpl<TestResultAnalysisMapper, TestResultAnalysis> implements TestResultAnalysisService {


    @Override
    public void saveTestResultAnalysis(TestResultAnalysis testResultAnalysis) {
        this.baseMapper.saveTestResultAnalysis(testResultAnalysis);
    }
}
