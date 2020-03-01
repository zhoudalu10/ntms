package cc.mrbird.febs.timetable.service.impl;

import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.utils.SortUtil;
import cc.mrbird.febs.timetable.entity.Timetable;
import cc.mrbird.febs.timetable.entity.TimetableAddState;
import cc.mrbird.febs.timetable.mapper.TimetableMapper;
import cc.mrbird.febs.timetable.service.TimetableService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class TimetableServiceImpl extends ServiceImpl<TimetableMapper, Timetable> implements TimetableService {

    @Override
    public IPage<Timetable> findTimetableList(Timetable timetable, QueryRequest request) {
        Page<Timetable> page = new Page<>(request.getPageNum(), request.getPageSize());
        SortUtil.handlePageSort(request, page, "timetableId", FebsConstant.ORDER_ASC, false);
        if (timetable.getTimetableIndex() != null && !timetable.getTimetableIndex().equals("")) {
            timetable.setTimetableIndexList(new ArrayList<>(Arrays.asList(timetable.getTimetableIndex().split(StringPool.COMMA))));
        }
        return this.baseMapper.findTimetableList(page, timetable);
    }

    @Override
    public TimetableAddState addTimetable(Timetable timetable) {
        timetable.setTimetableIndexList(new ArrayList<>(Arrays.asList(timetable.getTimetableIndex().split(StringPool.COMMA))));
        if (judgeSameClassSameTimeState(timetable)) {
            if (judgeSameClassSameTeacherState(timetable)) {
                if (judgeClassroomState(timetable)) {
                    if (judgeSameClassroomSameTimeState(timetable)) {
                        if (judgeCoursePeriod(timetable)) {
                            List<String> timetableIndexList = timetable.getTimetableIndexList();
                            for (String timetableIndex : timetableIndexList) {
                                timetable.setTimetableIndex(timetableIndex);
                                timetable.setCreateTime(new Date());
                                save(timetable);
                            }
                            return TimetableAddState.SUCCESS;
                        } else {
                            return TimetableAddState.COURSEPERIODCLASH;
                        }
                    } else {
                        return TimetableAddState.SAMECLASSROOMSAMETIMECLASH;
                    }
                } else {
                    return TimetableAddState.CLASSROOMPEOPLEAMOUNTCLASH;
                }
            } else {
                return TimetableAddState.SAMETEACHERSAMETIMECLASH;
            }
        } else {
            return TimetableAddState.SAMECLASSSAMETIMECLASH;
        }
    }

    @Override
    public void deleteTimetables(String[] ids) {
        List<String> list = Arrays.asList(ids);
        removeByIds(list);
    }

    @Override
    public Timetable findById(String timetableId) {
        return this.baseMapper.findById(timetableId);
    }

    @Override
    public List<Timetable> findMyTimetableList(Timetable timetable) {
        return this.baseMapper.findMyTimetableList(timetable);
    }

    private boolean judgeSameClassSameTimeState(Timetable timetable) {
        List<Timetable> timetables = this.baseMapper.judgeSameClassSameTimeState(timetable);
        return timetables.size() == 0;
    }

    private boolean judgeSameClassSameTeacherState(Timetable timetable) {
        List<Timetable> timetables = this.baseMapper.judgeSameClassSameTeacherState(timetable);
        return timetables.size() == 0;
    }

    private boolean judgeClassroomState(Timetable timetable) {
        int classroomPeopleAmount = this.baseMapper.selectClassroomPeopleAmount(timetable);
        List<String> timetableIndexList = timetable.getTimetableIndexList();
        for (String timetableIndex : timetableIndexList) {
            timetable.setTimetableIndex(timetableIndex);
            int classroomPeopleAmountNow = this.baseMapper.selectClassroomPeopleAmountNow(timetable);
            if (classroomPeopleAmountNow > classroomPeopleAmount) {
                return false;
            }
        }
        return true;
    }

    private boolean judgeSameClassroomSameTimeState(Timetable timetable) {
        List<String> timetableIndexList = timetable.getTimetableIndexList();
        for (String timetableIndex : timetableIndexList) {
            timetable.setTimetableIndex(timetableIndex);
            List<Timetable> timetables = this.baseMapper.judgeSameClassroomSameTimeState(timetable);
            if (timetables.size() > 0) {
                return false;
            }
        }
        return true;
    }

    private boolean judgeCoursePeriod(Timetable timetable) {
        int addPeriod = timetable.getTimetableIndexList().size();
        int coursePeriodNow = this.baseMapper.selectCoursePeriodNow(timetable);
        int coursePeriod = this.baseMapper.selectCoursePeriod(timetable);
        return coursePeriodNow + addPeriod <= coursePeriod;
    }
}
