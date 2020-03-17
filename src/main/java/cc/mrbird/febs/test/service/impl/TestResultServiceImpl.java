package cc.mrbird.febs.test.service.impl;

import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.entity.Regexp;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.utils.SortUtil;
import cc.mrbird.febs.test.entity.Question;
import cc.mrbird.febs.test.entity.TestResult;
import cc.mrbird.febs.test.mapper.TestResultMapper;
import cc.mrbird.febs.test.service.PaperService;
import cc.mrbird.febs.test.service.QuestionService;
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

    @Override
    @Transactional
    public void calculationResults(Map<String, Object> map) {
        TestResult testResult = new TestResult();
        testResult.setResultUserId(MapUtils.getLong(map, "resultUserId"));
        testResult.setResultPaperId(MapUtils.getLong(map, "resultPaperId"));
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
                Question question = questionService.findKeyOptionId(entry.getKey());
                if (StringUtils.equals(optionId, question.getKeyOptionId().toString())) {
                    tempMarks += Float.parseFloat(question.getQuestionScore());
                }
            } else {
                String questionId = entry.getKey().split("\\[")[0];
                if (multipleChoiceKey.containsKey(questionId)) {
                    multipleChoiceKey.get(questionId).add(Long.parseLong(optionId));
                } else {
                    Set<Long> set = new HashSet<>();
                    set.add(Long.parseLong(optionId));
                    multipleChoiceKey.put(questionId, set);
                }
            }
        }

        for (Map.Entry<String, Set<Long>> entry : multipleChoiceKey.entrySet()) {
            Set<Long> keySet = questionService.getMultipleChoiceKeySet(entry.getKey());
            if (SetUtils.isEqualSet(keySet, entry.getValue())) {
                tempMarks += Float.parseFloat(questionService.findById(entry.getKey()).getQuestionScore());
            }
        }
        testResult.setResultScore((int) (tempMarks / fullMarks * 100));
        this.baseMapper.updateResult(testResult);
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
