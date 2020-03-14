package cc.mrbird.febs.test.service;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.test.entity.Question;
import com.baomidou.mybatisplus.core.metadata.IPage;

public interface QuestionService {

    IPage<Question> findQuestionList(Question question, QueryRequest request);

    void addQuestion(Question question);

    Question findById(String questionId);

    void updateQuestion(Question question);

    void deleteQuestions(String[] ids);
}
