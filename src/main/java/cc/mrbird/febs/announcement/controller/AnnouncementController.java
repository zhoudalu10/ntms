package cc.mrbird.febs.announcement.controller;

import cc.mrbird.febs.announcement.entity.Announcement;
import cc.mrbird.febs.announcement.service.AnnouncementService;
import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

@Slf4j
@Validated
@RestController
@RequestMapping("announcement")
public class AnnouncementController extends BaseController {

    @Autowired
    private AnnouncementService announcementService;

    @RequestMapping("list")
    @RequiresPermissions("announcement:view")
    public FebsResponse announcementList(Announcement announcement, QueryRequest request) {
        Map<String, Object> dataTable = getDataTable(announcementService.findAnnouncementList(announcement, request));
        return new FebsResponse().success().data(dataTable);
    }

    @RequestMapping("add")
    @RequiresPermissions("announcement:add")
    @ControllerEndpoint(operation = "新增公告", exceptionMessage = "新增公告失败")
    public FebsResponse addAnnouncement(@Valid Announcement announcement) {
        announcementService.addAnnouncement(announcement);
        return new FebsResponse().success();
    }

    @RequestMapping("update")
    @RequiresPermissions("announcement:update")
    @ControllerEndpoint(operation = "修改公告", exceptionMessage = "修改公告失败")
    public FebsResponse updateAnnouncement(@Valid Announcement announcement) {
        if (announcement.getAnnouncementId() == null)
            throw new FebsException("公告ID为空");
        announcementService.updateAnnouncement(announcement);
        return new FebsResponse().success();
    }

    @RequestMapping("delete/{announcementIds}")
    @RequiresPermissions("announcement:delete")
    @ControllerEndpoint(operation = "删除公告", exceptionMessage = "删除公告失败")
    public FebsResponse deleteAnnouncements(@NotBlank(message = "{required}") @PathVariable String announcementIds) {
        String[] ids = announcementIds.split(StringPool.COMMA);
        announcementService.deleteAnnouncements(ids);
        return new FebsResponse().success();
    }

    @RequestMapping("excel")
    @RequiresPermissions("announcement:export")
    @ControllerEndpoint(exceptionMessage = "导出Excel失败")
    public void export(QueryRequest queryRequest, Announcement announcement, HttpServletResponse response) {
        List<Announcement> announcements = announcementService.findAnnouncementList(announcement, queryRequest).getRecords();
        ExcelKit.$Export(Announcement.class, response).downXlsx(announcements, false);
    }

    @RequestMapping("latest")
    public FebsResponse latestAnnouncements() {
        return new FebsResponse().success().data(announcementService.latestAnnouncements());
    }


}
