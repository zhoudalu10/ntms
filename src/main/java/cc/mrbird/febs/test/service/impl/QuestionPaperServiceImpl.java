package cc.mrbird.febs.test.service.impl;

import cc.mrbird.febs.test.entity.QuestionPaper;
import cc.mrbird.febs.test.mapper.QuestionPaperMapper;
import cc.mrbird.febs.test.service.QuestionPaperService;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class QuestionPaperServiceImpl extends ServiceImpl<QuestionPaperMapper, QuestionPaper> implements QuestionPaperService {

    @Override
    public void addQuestionPaper(QuestionPaper questionPaper) {
        List<String> questionIds = Arrays.asList(questionPaper.getQuestionId().split(StringPool.COMMA));
        List<String> paperIds = Arrays.asList(questionPaper.getPaperId().split(StringPool.COMMA));
        List<QuestionPaper> questionPaperList = new ArrayList<>();
        questionIds.forEach(questionId -> paperIds.forEach(paperId -> {
            QuestionPaper questionPaperEach = new QuestionPaper();
            questionPaperEach.setQuestionId(questionId);
            questionPaperEach.setPaperId(paperId);
            questionPaperList.add(questionPaperEach);
        }));
        questionPaperList.stream().filter(questionPaperEach -> this.baseMapper.findQuestionPaper(questionPaperEach).size() == 0).forEach(this::save);
    }

    @Override
    public void deleteQuestionPaper(QuestionPaper questionPaper) {
        List<String> paperIds = Arrays.asList(questionPaper.getPaperId().split(StringPool.COMMA));
        List<QuestionPaper> questionPaperList = new ArrayList<>();
        paperIds.forEach(paperId -> {
            QuestionPaper questionPaperEach = new QuestionPaper();
            questionPaperEach.setQuestionId(questionPaper.getQuestionId());
            questionPaperEach.setPaperId(paperId);
            questionPaperList.add(questionPaperEach);
        });
        questionPaperList.forEach(this.baseMapper::deleteQuestionPaper);
    }

    @Override
    public void removeByPaperId(String paperId) {
        this.baseMapper.removeByPaperId(paperId);
    }
}