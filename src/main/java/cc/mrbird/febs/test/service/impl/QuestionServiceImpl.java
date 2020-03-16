package cc.mrbird.febs.test.service.impl;

import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.utils.SortUtil;
import cc.mrbird.febs.test.entity.Question;
import cc.mrbird.febs.test.mapper.QuestionMapper;
import cc.mrbird.febs.test.service.OptionService;
import cc.mrbird.febs.test.service.PaperService;
import cc.mrbird.febs.test.service.QuestionService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

    @Autowired
    private OptionService optionService;

    @Autowired
    private PaperService paperService;

    @Override
    public IPage<Question> findQuestionList(Question question, QueryRequest request) {
        Page<Question> page = new Page<>(request.getPageNum(), request.getPageSize());
        SortUtil.handlePageSort(request, page, "createTime", FebsConstant.ORDER_ASC, false);
        return this.baseMapper.findQuestionList(page, question);
    }

    @Override
    public void addQuestion(Question question) {
        question.setCreateTime(new Date());
        save(question);
        question.getOptionList().forEach(option -> {
            option.setOptionQuestionId(question.getQuestionId());
            optionService.addOption(option);
        });
    }

    @Override
    public Question findById(String questionId) {
        Question question = this.baseMapper.findById(questionId);
        question.setOptionList(optionService.findByQuestionId(questionId));
        question.setBindingPaperList(paperService.findByQuestionId(questionId));
        return question;
    }

    @Override
    public void updateQuestion(Question question) {
        updateById(question);
        question.getOptionList().forEach(option -> {
            optionService.updateOption(option);
        });
    }

    @Override
    public void deleteQuestions(String[] ids) {
        List<String> list = Arrays.asList(ids);
        this.removeByIds(list);
        list.forEach(questionId -> optionService.deleteOptionsByQuestionId(questionId));
    }

    @Override
    public Question findByIdRemovePaperList(String questionId) {
        Question question = this.baseMapper.findById(questionId);
        question.setOptionList(optionService.findByQuestionId(questionId));
        question.setBindingPaperList(paperService.findRemoveListByQuestionId(questionId));
        return question;
    }

    @Override
    public List<Question> findCompleteQuestionListByPaperId(String paperId) {
        List<Question> questionList = this.baseMapper.findCompleteQuestionListByPaperId(paperId);
        questionList.forEach(question -> {
            question.setOptionList(optionService.findByQuestionIdWithoutKey(question.getQuestionId()));
        });
        return questionList;
    }
}
