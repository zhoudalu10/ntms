package cc.mrbird.febs.test.mapper;

import cc.mrbird.febs.test.entity.Option;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OptionMapper extends BaseMapper<Option> {

    List<Option> findByQuestionId(@Param("questionId") String questionId);

    void deleteOptionsByQuestionId(@Param("questionId") String questionId);
}
