package cc.mrbird.febs.classroom.service.impl;

import cc.mrbird.febs.classroom.entity.Classroom;
import cc.mrbird.febs.classroom.mapper.ClassroomMapper;
import cc.mrbird.febs.classroom.service.ClassroomService;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.utils.SortUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ClassroomServiceImpl extends ServiceImpl<ClassroomMapper, Classroom> implements ClassroomService {

    @Override
    public IPage<Classroom> findClassroomList(Classroom classroom, QueryRequest request) {
        Page<Classroom> page = new Page<>(request.getPageNum(), request.getPageSize());
        SortUtil.handlePageSort(request, page, "classroomId", FebsConstant.ORDER_ASC, false);
        return this.baseMapper.findClassroomList(page, classroom);
    }

    @Override
    public void addClassroom(Classroom classroom) {
        save(classroom);
    }

    @Override
    public void updateClassroom(Classroom classroom) {
        updateById(classroom);
    }

    @Override
    public void deleteClassrooms(String[] ids) {
        List<String> list = Arrays.asList(ids);
        this.removeByIds(list);
    }

    @Override
    public Classroom findById(String classroomId) {
        return getById(classroomId);
    }

    @Override
    public List<Classroom> findClassroomListAll(Classroom classroom) {
        return this.baseMapper.findClassroomListAll(classroom);
    }
}
