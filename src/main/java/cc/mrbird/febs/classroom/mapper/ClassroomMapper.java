package cc.mrbird.febs.classroom.mapper;

import cc.mrbird.febs.classroom.entity.Classroom;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassroomMapper extends BaseMapper<Classroom> {

    IPage<Classroom> findClassroomList(Page<Classroom> page, @Param("classroom") Classroom classroom);

    List<Classroom> findClassroomListAll(@Param("classroom") Classroom classroom);
}
