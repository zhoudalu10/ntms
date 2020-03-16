package cc.mrbird.febs.test.service;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.test.entity.Question;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;
import java.util.Set;

public interface QuestionService {

    IPage<Question> findQuestionList(Question question, QueryRequest request);

    void addQuestion(Question question);

    Question findById(String questionId);

    void updateQuestion(Question question);

    void deleteQuestions(String[] ids);

    Question findByIdRemovePaperList(String questionId);

    List<Question> findCompleteQuestionListByPaperId(String paperId);

    Question findKeyOptionId(String questionId);

    Set<Long> getMultipleChoiceKeySet(String key);

    void deleteByCourseId(String courseId);

    List<Question> findByCourseId(String courseId);
}
