package cc.mrbird.febs.announcement.service.impl;

import cc.mrbird.febs.announcement.entity.Announcement;
import cc.mrbird.febs.announcement.mapper.AnnouncementMapper;
import cc.mrbird.febs.announcement.service.AnnouncementService;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.utils.SortUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementMapper, Announcement> implements AnnouncementService {

    @Override
    public IPage<Announcement> findAnnouncementList(Announcement announcement, QueryRequest request) {
        Page<Announcement> page = new Page<>(request.getPageNum(), request.getPageSize());
        SortUtil.handlePageSort(request, page, "announcementId", FebsConstant.ORDER_ASC, false);
        return this.baseMapper.findAnnouncementList(page, announcement);
    }

    @Override
    public void addAnnouncement(Announcement announcement) {
        announcement.setCreateTime(new Date());
        save(announcement);
    }

    @Override
    public Announcement findById(String announcementId) {
        return this.baseMapper.findById(announcementId);
    }

    @Override
    public void updateAnnouncement(Announcement announcement) {
        updateById(announcement);
    }

    @Override
    public void deleteAnnouncements(String[] ids) {
        List<String> list = Arrays.asList(ids);
        this.removeByIds(list);
    }

    @Override
    public List<Announcement> latestAnnouncements() {
        return baseMapper.latestAnnouncements();
    }
}
