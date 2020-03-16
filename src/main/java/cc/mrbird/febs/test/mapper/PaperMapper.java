package cc.mrbird.febs.test.mapper;

import cc.mrbird.febs.test.entity.Paper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PaperMapper extends BaseMapper<Paper> {

    IPage<Paper> findPaperList(Page<Paper> page, @Param("paper") Paper paper);

    Paper findById(@Param("paperId") String paperId);

    List<Paper> findPaperListAll(@Param("paper") Paper paper);

    List<Paper> findByQuestionId(@Param("questionId") String questionId);

    List<Paper> findRemoveListByQuestionId(@Param("questionId") String questionId);

    void startTest(@Param("paperId") String paperId);

    void endTest(@Param("paperId") String paperId);

    List<Paper> findUserPaper(@Param("userId") String userId);

    Float findPaperFullMarks(@Param("paperId") Long paperId);

    void deleteByCourseId(@Param("courseId") String courseId);
}
