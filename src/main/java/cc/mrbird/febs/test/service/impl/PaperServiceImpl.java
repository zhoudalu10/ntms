package cc.mrbird.febs.test.service.impl;

import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.utils.SortUtil;
import cc.mrbird.febs.test.entity.Paper;
import cc.mrbird.febs.test.mapper.PaperMapper;
import cc.mrbird.febs.test.service.PaperService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class PaperServiceImpl extends ServiceImpl<PaperMapper, Paper> implements PaperService {

    @Autowired
    private QuestionPaperServiceImpl questionPaperService;

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
}
