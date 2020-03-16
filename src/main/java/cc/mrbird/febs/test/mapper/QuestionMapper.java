package cc.mrbird.febs.test.mapper;

import cc.mrbird.febs.test.entity.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface QuestionMapper extends BaseMapper<Question> {

    IPage<Question> findQuestionList(Page<Question> page, @Param("question") Question question);

    Question findById(@Param("questionId") String questionId);

    List<Question> findCompleteQuestionListByPaperId(@Param("paperId") String paperId);

    Question findKeyOptionId(@Param("questionId") String questionId);

    Set<Long> getMultipleChoiceKeySet(@Param("questionId") String questionId);
}
