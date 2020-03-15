package cc.mrbird.febs.test.mapper;

import cc.mrbird.febs.test.entity.QuestionPaper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionPaperMapper extends BaseMapper<QuestionPaper> {

    List<QuestionPaper> findQuestionPaper(@Param("questionPaper") QuestionPaper questionPaper);

    void deleteQuestionPaper(@Param("questionPaper") QuestionPaper questionPaperEach);

    void removeByPaperId(@Param("paperId") String paperId);
}
