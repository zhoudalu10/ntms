package cc.mrbird.febs.announcement.service;

import cc.mrbird.febs.announcement.entity.Announcement;
import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface AnnouncementService {

    IPage<Announcement> findAnnouncementList(Announcement announcement, QueryRequest request);

    void addAnnouncement(Announcement announcement);

    Announcement findById(String announcementId);

    void updateAnnouncement(Announcement announcement);

    void deleteAnnouncements(String[] ids);

    List<Announcement> latestAnnouncements();
}
