package cc.mrbird.febs.test.service.impl;

import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.utils.SortUtil;
import cc.mrbird.febs.test.entity.Paper;
import cc.mrbird.febs.test.entity.Question;
import cc.mrbird.febs.test.mapper.PaperMapper;
import cc.mrbird.febs.test.service.PaperService;
import cc.mrbird.febs.test.service.QuestionService;
import cc.mrbird.febs.test.service.TestResultAnalysisService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class PaperServiceImpl extends ServiceImpl<PaperMapper, Paper> implements PaperService {

    @Autowired
    private QuestionPaperServiceImpl questionPaperService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private TestResultAnalysisService testResultAnalysisService;

    @Override
    public IPage<Paper> findPaperList(Paper paper, QueryRequest request) {
        Page<Paper> page = new Page<>(request.getPageNum(), request.getPageSize());
        SortUtil.handlePageSort(request, page, "createTime", FebsConstant.ORDER_ASC, false);
        return this.baseMapper.findPaperList(page, paper);
    }

    @Override
    @Transactional
    public void addPaper(Paper paper) {
        paper.setCreateTime(new Date());
        paper.setPaperState(Paper.STATE_PROGRESS);
        save(paper);
    }

    @Override
    public Paper findById(String paperId) {
        Paper paper = this.baseMapper.findById(paperId);
        paper.setPaperAnalysis(testResultAnalysisService.findPaperAnalysisByPaperId(paper.getPaperId()));
        return paper;
    }

    @Override
    @Transactional
    public void updatePaper(Paper paper) {
        updateById(paper);
    }

    @Override
    @Transactional
    public void deletePapers(String[] ids) {
        List<String> list = Arrays.asList(ids);
        this.removeByIds(list);
        list.forEach(paperId -> questionPaperService.removeByPaperId(paperId));
    }

    @Override
    public List<Paper> findPaperListAll(Paper paper) {
        return this.baseMapper.findPaperListAll(paper);
    }

    @Override
    public List<Paper> findByQuestionId(String questionId) {
        return this.baseMapper.findByQuestionId(questionId);
    }

    @Override
    public List<Paper> findRemoveListByQuestionId(String questionId) {
        return this.baseMapper.findRemoveListByQuestionId(questionId);
    }

    @Override
    @Transactional
    public void startTest(String[] ids) {
        List<String> list = Arrays.asList(ids);
        list.forEach(paperId -> this.baseMapper.startTest(paperId));
    }

    @Override
    @Transactional
    public void endTest(String[] ids) {
        List<String> list = Arrays.asList(ids);
        list.forEach(paperId -> this.baseMapper.endTest(paperId));
    }

    @Override
    public List<Paper> findUserPaper(String userId) {
        return this.baseMapper.findUserPaper(userId);
    }

    @Override
    public Paper findCompletePaperById(String paperId) {
        Paper paper = this.findById(paperId);
        List<Question> questionList = questionService.findCompleteQuestionListByPaperId(paperId);
        Collections.shuffle(questionList);
        paper.setPaperQuestionList(questionList);
        return paper;
    }

    @Override
    public Float findPaperFullMarks(Long paperId) {
        return this.baseMapper.findPaperFullMarks(paperId);
    }

    @Override
    @Transactional
    public void deleteByCourseId(String courseId) {
        this.baseMapper.deleteByCourseId(courseId);
    }

    @Override
    public Paper findAnalysisById(String paperId) {
        Paper paper = this.baseMapper.findById(paperId);
        Map<String, Object> analysisMap = testResultAnalysisService.findPaperAnalysisByPaperId(paper.getPaperId());
        analysisMap.putAll(testResultAnalysisService.findPaperResultAnalysisByPaperId(paper.getPaperId()));
        paper.setPaperAnalysis(analysisMap);
        paper.setTestResultAnalysisList(testResultAnalysisService.findMostWrongQuestion(paper.getPaperId()));
        return paper;
    }
}
