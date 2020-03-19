package cc.mrbird.febs.test.service;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.test.entity.TestResult;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.Map;

public interface TestResultService {

    void calculationResults(Map<String, Object> map);

    Integer judgePaper(TestResult testResult);

    void insertZeroMarks(TestResult testResult);

    IPage<TestResult> findTestResultList(TestResult testResult, QueryRequest request);

    void deleteTestResults(String[] ids);

    TestResult findAnalysisById(String resultId);
}
