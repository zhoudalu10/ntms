package cc.mrbird.febs.test.mapper;

import cc.mrbird.febs.test.entity.TestResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

public interface TestResultMapper extends BaseMapper<TestResult> {

    Integer judgePaper(@Param("testResult") TestResult testResult);

    IPage<TestResult> findTestResultList(Page<TestResult> page, @Param("testResult") TestResult testResult);

    TestResult findByUserAndPaperId(@Param("testResult") TestResult testResult);
}
