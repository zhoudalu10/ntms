package cc.mrbird.febs.test.service.impl;

import cc.mrbird.febs.test.entity.TestResultAnalysis;
import cc.mrbird.febs.test.mapper.TestResultAnalysisMapper;
import cc.mrbird.febs.test.service.TestResultAnalysisService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TestResultAnalysisServiceImpl extends ServiceImpl<TestResultAnalysisMapper, TestResultAnalysis> implements TestResultAnalysisService {


    @Override
    public void saveTestResultAnalysis(TestResultAnalysis testResultAnalysis) {
        this.baseMapper.saveTestResultAnalysis(testResultAnalysis);
    }

    @Override
    public Map<String, Object> findPaperAnalysisByPaperId(Long paperId) {
        return this.baseMapper.findPaperAnalysisByPaperId(paperId);
    }

    @Override
    public Map<String, Object> findPaperResultAnalysisByPaperId(Long paperId) {
        return this.baseMapper.findPaperResultAnalysisByPaperId(paperId);
    }

    @Override
    public List<TestResultAnalysis> findMostWrongQuestion(Long paperId) {
        return this.baseMapper.findMostWrongQuestion(paperId);
    }

    @Override
    public Map<String, Object> findAnalysisByResultId(String resultId) {
        return this.baseMapper.findAnalysisByResultId(resultId);
    }

    @Override
    public List<TestResultAnalysis> findAllWrongQuestionByResultId(String resultId) {
        return this.baseMapper.findAllWrongQuestionByResultId(resultId);
    }
}
