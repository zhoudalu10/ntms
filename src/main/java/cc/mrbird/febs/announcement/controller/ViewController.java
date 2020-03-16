package cc.mrbird.febs.announcement.controller;

import cc.mrbird.febs.announcement.entity.Announcement;
import cc.mrbird.febs.announcement.service.AnnouncementService;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.utils.DateUtil;
import cc.mrbird.febs.common.utils.FebsUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("announcementView")
@RequestMapping(FebsConstant.VIEW_PREFIX + "announcement")
public class ViewController extends BaseController {

    @Autowired
    private AnnouncementService announcementService;

    @GetMapping("announcement")
    @RequiresPermissions("announcement:view")
    public String announcement() {
        return FebsUtil.view("announcement/announcement");
    }

    @GetMapping("add")
    @RequiresPermissions("announcement:add")
    public String announcementAdd() {
        return FebsUtil.view("announcement/announcementAdd");
    }

    @GetMapping("update/{announcementId}")
    @RequiresPermissions("announcement:update")
    public String announcementUpdate(@PathVariable String announcementId, Model model) {
        Announcement announcement = announcementService.findById(announcementId);
        model.addAttribute("announcement", announcement);
        return FebsUtil.view("announcement/announcementUpdate");
    }

    @GetMapping("detail/{announcementId}")
    @RequiresPermissions("announcement:view")
    public String announcementDetail(@PathVariable String announcementId, Model model) {
        Announcement announcement = announcementService.findById(announcementId);
        model.addAttribute("announcement", announcement);
        model.addAttribute("createTime", DateUtil.getDateFormat(announcement.getCreateTime(), DateUtil.FULL_TIME_SPLIT_PATTERN));
        return FebsUtil.view("announcement/announcementDetail");
    }
}
