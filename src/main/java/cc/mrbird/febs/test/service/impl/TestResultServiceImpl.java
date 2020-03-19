package cc.mrbird.febs.test.service.impl;

import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.entity.Regexp;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.utils.SortUtil;
import cc.mrbird.febs.test.entity.Question;
import cc.mrbird.febs.test.entity.TestResult;
import cc.mrbird.febs.test.entity.TestResultAnalysis;
import cc.mrbird.febs.test.mapper.TestResultMapper;
import cc.mrbird.febs.test.service.PaperService;
import cc.mrbird.febs.test.service.QuestionService;
import cc.mrbird.febs.test.service.TestResultAnalysisService;
import cc.mrbird.febs.test.service.TestResultService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections.SetUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TestResultServiceImpl extends ServiceImpl<TestResultMapper, TestResult> implements TestResultService {

    @Autowired
    private PaperService paperService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private TestResultAnalysisService testResultAnalysisService;

    @Override
    @Transactional
    public void calculationResults(Map<String, Object> map) {
        TestResult testResult = new TestResult();
        testResult.setResultUserId(MapUtils.getLong(map, "resultUserId"));
        testResult.setResultPaperId(MapUtils.getLong(map, "resultPaperId"));
        testResult = this.baseMapper.findByUserAndPaperId(testResult);
        TestResultAnalysis testResultAnalysis = new TestResultAnalysis();
        testResultAnalysis.setResultUserId(testResult.getResultUserId());
        testResultAnalysis.setPaperId(testResult.getResultPaperId());
        testResultAnalysis.setResultId(testResult.getResultId());
        map.remove("resultUserId");
        map.remove("resultPaperId");
        Float fullMarks = paperService.findPaperFullMarks(testResult.getResultPaperId());
        Float tempMarks = 0f;
        Map<String, Set<Long>> multipleChoiceKey = new HashMap<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String optionId = entry.getValue().toString();
            //答案有两种形式
            //单选判断为 题号=选项号
            //多选为 题号[选项号]=选项号
            //分开处理
            //判断是否为单选或判断题
            if (FebsUtil.match(Regexp.NUMBER_REG, entry.getKey())) {
                //是单选或判断
                //根据题号得到带有正确答案选项id的question对象
                testResultAnalysis.setQuestionId(Long.parseLong(entry.getKey()));
                Question question = questionService.findKeyOptionId(entry.getKey());
                if (StringUtils.equals(optionId, question.getKeyOptionId().toString())) {
                    //如果question对象的正确选项id和选项号相同 则该题正确
                    tempMarks += Float.parseFloat(question.getQuestionScore());
                    testResultAnalysis.setResultIsCorrect(TestResultAnalysis.RESULT_IS_CORRECT);
                } else {
                    testResultAnalysis.setResultIsCorrect(TestResultAnalysis.RESULT_IS_WRONG);
                }
                testResultAnalysisService.saveTestResultAnalysis(testResultAnalysis);
            } else {
                //多选则将题号-选项好以<String, Set>的形式存入map
                String questionId = entry.getKey().split("\\[")[0];
                //如果map中已有该题 则对应set加入选项
                if (multipleChoiceKey.containsKey(questionId)) {
                    multipleChoiceKey.get(questionId).add(Long.parseLong(optionId));
                } else {
                    //没有则新建set对象
                    Set<Long> set = new HashSet<>();
                    set.add(Long.parseLong(optionId));
                    multipleChoiceKey.put(questionId, set);
                }
            }
        }
        //迭代map 多选判分
        for (Map.Entry<String, Set<Long>> entry : multipleChoiceKey.entrySet()) {
            //根据题号拿到多选答案的set集合
            Set<Long> keySet = questionService.getMultipleChoiceKeySet(entry.getKey());
            testResultAnalysis.setQuestionId(Long.parseLong(entry.getKey()));
            //如果完全相等 则该题正确
            if (SetUtils.isEqualSet(keySet, entry.getValue())) {
                tempMarks += Float.parseFloat(questionService.findById(entry.getKey()).getQuestionScore());
                testResultAnalysis.setResultIsCorrect(TestResultAnalysis.RESULT_IS_CORRECT);
            } else {
                testResultAnalysis.setResultIsCorrect(TestResultAnalysis.RESULT_IS_WRONG);
            }
            testResultAnalysisService.saveTestResultAnalysis(testResultAnalysis);
        }
        testResult.setResultScore((int) (tempMarks / fullMarks * 100));
        this.baseMapper.updateById(testResult);
    }

    @Override
    public Integer judgePaper(TestResult testResult) {
        return this.baseMapper.judgePaper(testResult);
    }

    @Override
    @Transactional
    public void insertZeroMarks(TestResult testResult) {
        testResult.setCreateTime(new Date());
        testResult.setResultScore(0);
        save(testResult);
    }

    @Override
    public IPage<TestResult> findTestResultList(TestResult testResult, QueryRequest request) {
        Page<TestResult> page = new Page<>(request.getPageNum(), request.getPageSize());
        SortUtil.handlePageSort(request, page, "createTime", FebsConstant.ORDER_ASC, false);
        return this.baseMapper.findTestResultList(page, testResult);
    }

    @Override
    @Transactional
    public void deleteTestResults(String[] ids) {
        List<String> list = Arrays.asList(ids);
        this.removeByIds(list);
    }
}
