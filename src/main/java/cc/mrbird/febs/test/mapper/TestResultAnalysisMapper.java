package cc.mrbird.febs.test.mapper;

import cc.mrbird.febs.test.entity.TestResultAnalysis;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TestResultAnalysisMapper extends BaseMapper<TestResultAnalysis> {

    void saveTestResultAnalysis(@Param("testResultAnalysis") TestResultAnalysis testResultAnalysis);

    Map<String, Object> findPaperAnalysisByPaperId(@Param("paperId") Long paperId);

    Map<String, Object> findPaperResultAnalysisByPaperId(@Param("paperId") Long paperId);

    List<TestResultAnalysis> findMostWrongQuestion(@Param("paperId") Long paperId);

    Map<String, Object> findAnalysisByResultId(@Param("resultId") String resultId);

    List<TestResultAnalysis> findAllWrongQuestionByResultId(@Param("resultId") String resultId);

    void deleteByResultId(@Param("resultId") String resultId);
}
