package cc.mrbird.febs.test.service.impl;

import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.utils.SortUtil;
import cc.mrbird.febs.test.entity.Paper;
import cc.mrbird.febs.test.entity.Question;
import cc.mrbird.febs.test.mapper.PaperMapper;
import cc.mrbird.febs.test.service.PaperService;
import cc.mrbird.febs.test.service.QuestionService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class PaperServiceImpl extends ServiceImpl<PaperMapper, Paper> implements PaperService {

    @Autowired
    private QuestionPaperServiceImpl questionPaperService;

    @Autowired
    private QuestionService questionService;

    @Override
    public IPage<Paper> findPaperList(Paper paper, QueryRequest request) {
        Page<Paper> page = new Page<>(request.getPageNum(), request.getPageSize());
        SortUtil.handlePageSort(request, page, "createTime", FebsConstant.ORDER_ASC, false);
        return this.baseMapper.findPaperList(page, paper);
    }

    @Override
    public void addPaper(Paper paper) {
        paper.setCreateTime(new Date());
        paper.setPaperState(Paper.STATE_PROGRESS);
        save(paper);
    }

    @Override
    public Paper findById(String paperId) {
        return this.baseMapper.findById(paperId);
    }

    @Override
    public void updatePaper(Paper paper) {
        updateById(paper);
    }

    @Override
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
    public void startTest(String[] ids) {
        List<String> list = Arrays.asList(ids);
        list.forEach(paperId -> this.baseMapper.startTest(paperId));
    }

    @Override
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
    public void deleteByCourseId(String courseId) {
        this.baseMapper.deleteByCourseId(courseId);
    }
}
