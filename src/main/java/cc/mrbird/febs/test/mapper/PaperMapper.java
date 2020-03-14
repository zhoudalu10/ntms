package cc.mrbird.febs.test.mapper;

import cc.mrbird.febs.test.entity.Paper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

public interface PaperMapper extends BaseMapper<Paper> {

    IPage<Paper> findPaperList(Page<Paper> page, @Param("paper") Paper paper);

    Paper findById(@Param("paperId") String paperId);
}
