package cc.mrbird.febs.test.mapper;

import cc.mrbird.febs.test.entity.TestResultAnalysis;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface TestResultAnalysisMapper extends BaseMapper<TestResultAnalysis> {

    void saveTestResultAnalysis(@Param("testResultAnalysis") TestResultAnalysis testResultAnalysis);
}
