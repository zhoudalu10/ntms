package cc.mrbird.febs.announcement.mapper;

import cc.mrbird.febs.announcement.entity.Announcement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnnouncementMapper extends BaseMapper<Announcement> {

    IPage<Announcement> findAnnouncementList(Page<Announcement> page, @Param("announcement") Announcement announcement);

    Announcement findById(@Param("announcementId") String announcementId);

    List<Announcement> latestAnnouncements();
}
