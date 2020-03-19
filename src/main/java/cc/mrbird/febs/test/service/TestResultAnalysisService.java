package cc.mrbird.febs.test.service;

import cc.mrbird.febs.test.entity.TestResultAnalysis;

import java.util.List;
import java.util.Map;

public interface TestResultAnalysisService {

    void saveTestResultAnalysis(TestResultAnalysis testResultAnalysis);

    Map<String, Object> findPaperAnalysisByPaperId(Long paperId);

    Map<String, Object> findPaperResultAnalysisByPaperId(Long paperId);

    List<TestResultAnalysis> findMostWrongQuestion(Long paperId);

    Map<String, Object> findAnalysisByResultId(String resultId);

    List<TestResultAnalysis> findAllWrongQuestionByResultId(String resultId);
}
