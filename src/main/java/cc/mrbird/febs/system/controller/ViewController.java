package cc.mrbird.febs.system.controller;

import cc.mrbird.febs.announcement.entity.Announcement;
import cc.mrbird.febs.announcement.service.AnnouncementService;
import cc.mrbird.febs.classroom.entity.Classroom;
import cc.mrbird.febs.classroom.service.ClassroomService;
import cc.mrbird.febs.common.authentication.ShiroHelper;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.utils.DateUtil;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.course.entity.Course;
import cc.mrbird.febs.course.service.CourseService;
import cc.mrbird.febs.system.entity.User;
import cc.mrbird.febs.system.service.IUserService;
import cc.mrbird.febs.test.entity.Paper;
import cc.mrbird.febs.test.entity.Question;
import cc.mrbird.febs.test.service.PaperService;
import cc.mrbird.febs.test.service.QuestionService;
import cc.mrbird.febs.timetable.entity.Timetable;
import cc.mrbird.febs.timetable.service.TimetableService;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.ExpiredSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller("systemView")
public class ViewController extends BaseController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ShiroHelper shiroHelper;

    @Autowired
    private AnnouncementService announcementService;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private TimetableService timetableService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private PaperService paperService;

    @GetMapping("login")
    @ResponseBody
    public Object login(HttpServletRequest request) {
        if (FebsUtil.isAjaxRequest(request)) {
            throw new ExpiredSessionException();
        } else {
            ModelAndView mav = new ModelAndView();
            mav.setViewName(FebsUtil.view("login"));
            return mav;
        }
    }

    @GetMapping("unauthorized")
    public String unauthorized() {
        return FebsUtil.view("error/403");
    }


    @GetMapping("/")
    public String redirectIndex() {
        return "redirect:/index";
    }

    @GetMapping("index")
    public String index(Model model) {
        AuthorizationInfo authorizationInfo = shiroHelper.getCurrentuserAuthorizationInfo();
        User user = super.getCurrentUser();
        User currentUserDetail = userService.findByName(user.getUsername());
        currentUserDetail.setPassword("It's a secret");
        model.addAttribute("user", currentUserDetail);
        model.addAttribute("permissions", authorizationInfo.getStringPermissions());
        model.addAttribute("roles", authorizationInfo.getRoles());
        return "index";
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "layout")
    public String layout() {
        return FebsUtil.view("layout");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "password/update")
    public String passwordUpdate() {
        return FebsUtil.view("system/user/passwordUpdate");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "user/profile")
    public String userProfile() {
        return FebsUtil.view("system/user/userProfile");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "user/avatar")
    public String userAvatar() {
        return FebsUtil.view("system/user/avatar");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "user/profile/update")
    public String profileUpdate() {
        return FebsUtil.view("system/user/profileUpdate");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/user")
    @RequiresPermissions("user:view")
    public String systemUser() {
        return FebsUtil.view("system/user/user");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/user/add")
    @RequiresPermissions("user:add")
    public String systemUserAdd() {
        return FebsUtil.view("system/user/userAdd");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/user/detail/{username}")
    @RequiresPermissions("user:view")
    public String systemUserDetail(@PathVariable String username, Model model) {
        resolveUserModel(username, model, true);
        return FebsUtil.view("system/user/userDetail");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/user/update/{username}")
    @RequiresPermissions("user:update")
    public String systemUserUpdate(@PathVariable String username, Model model) {
        resolveUserModel(username, model, false);
        return FebsUtil.view("system/user/userUpdate");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/role")
    @RequiresPermissions("role:view")
    public String systemRole() {
        return FebsUtil.view("system/role/role");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/menu")
    @RequiresPermissions("menu:view")
    public String systemMenu() {
        return FebsUtil.view("system/menu/menu");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/dept")
    @RequiresPermissions("dept:view")
    public String systemDept() {
        return FebsUtil.view("system/dept/dept");
    }

    @RequestMapping(FebsConstant.VIEW_PREFIX + "index")
    public String pageIndex() {
        return FebsUtil.view("index");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "404")
    public String error404() {
        return FebsUtil.view("error/404");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "403")
    public String error403() {
        return FebsUtil.view("error/403");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "500")
    public String error500() {
        return FebsUtil.view("error/500");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "announcement")
    @RequiresPermissions("announcement:view")
    public String announcement() {
        return FebsUtil.view("announcement/announcement");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "announcement/add")
    @RequiresPermissions("announcement:add")
    public String announcementAdd() {
        return FebsUtil.view("announcement/announcementAdd");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "announcement/update/{announcementId}")
    @RequiresPermissions("announcement:update")
    public String announcementUpdate(@PathVariable String announcementId, Model model) {
        Announcement announcement = announcementService.findById(announcementId);
        model.addAttribute("announcement", announcement);
        return FebsUtil.view("announcement/announcementUpdate");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "announcement/detail/{announcementId}")
    @RequiresPermissions("announcement:view")
    public String announcementDetail(@PathVariable String announcementId, Model model) {
        Announcement announcement = announcementService.findById(announcementId);
        model.addAttribute("announcement", announcement);
        model.addAttribute("createTime", DateUtil.getDateFormat(announcement.getCreateTime(), DateUtil.FULL_TIME_SPLIT_PATTERN));
        return FebsUtil.view("announcement/announcementDetail");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "classroom")
    @RequiresPermissions("classroom:view")
    public String classroom() {
        return FebsUtil.view("classroom/classroom");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "classroom/add")
    @RequiresPermissions("classroom:add")
    public String classroomAdd() {
        return FebsUtil.view("classroom/classroomAdd");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "classroom/update/{classroomId}")
    @RequiresPermissions("classroom:update")
    public String classroomUpdate(@PathVariable String classroomId, Model model) {
        Classroom classroom = classroomService.findById(classroomId);
        model.addAttribute("classroom", classroom);
        return FebsUtil.view("classroom/classroomUpdate");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "classroom/detail/{classroomId}")
    @RequiresPermissions("classroom:view")
    public String classroomDetail(@PathVariable String classroomId, Model model) {
        Classroom classroom = classroomService.findById(classroomId);
        model.addAttribute("classroom", classroom);
        return FebsUtil.view("classroom/classroomDetail");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "course")
    @RequiresPermissions("course:view")
    public String course() {
        return FebsUtil.view("course/course");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "course/add")
    @RequiresPermissions("course:add")
    public String courseAdd() {
        return FebsUtil.view("course/courseAdd");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "course/update/{courseId}")
    @RequiresPermissions("course:update")
    public String courseUpdate(@PathVariable String courseId, Model model) {
        Course course = courseService.findById(courseId);
        model.addAttribute("course", course);
        return FebsUtil.view("course/courseUpdate");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "course/detail/{courseId}")
    @RequiresPermissions("course:view")
    public String courseDetail(@PathVariable String courseId, Model model) {
        Course course = courseService.findById(courseId);
        model.addAttribute("course", course);
        return FebsUtil.view("course/courseDetail");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "timetable")
    @RequiresPermissions("timetable:view")
    public String timetable() {
        return FebsUtil.view("timetable/timetable");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "timetable/add")
    @RequiresPermissions("timetable:add")
    public String timetableAdd() {
        return FebsUtil.view("timetable/timetableAdd");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "timetable/detail/{timetableId}")
    @RequiresPermissions("timetable:view")
    public String timetableDetail(@PathVariable String timetableId, Model model) {
        Timetable timetable = timetableService.findById(timetableId);
        model.addAttribute("timetable", timetable);
        model.addAttribute("createTime", DateUtil.getDateFormat(timetable.getCreateTime(), DateUtil.FULL_TIME_SPLIT_PATTERN));
        return FebsUtil.view("timetable/timetableDetail");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "myTimetable")
    @RequiresPermissions("myTimetable:view")
    public String myTimetable() {
        return FebsUtil.view("timetable/myTimetable");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "test/question")
    @RequiresPermissions("question:view")
    public String question() {
        return FebsUtil.view("test/question/question");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "test/question/add")
    @RequiresPermissions("question:add")
    public String questionAdd() {
        return FebsUtil.view("test/question/questionAdd");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "test/question/update/{questionId}")
    @RequiresPermissions("question:update")
    public String questionUpdate(@PathVariable String questionId, Model model) {
        Question question = questionService.findById(questionId);
        model.addAttribute("question", question);
        return FebsUtil.view("test/question/questionUpdate");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "test/question/detail/{questionId}")
    @RequiresPermissions("question:view")
    public String questionDetail(@PathVariable String questionId, Model model) {
        Question question = questionService.findById(questionId);
        model.addAttribute("question", question);
        model.addAttribute("createTime", DateUtil.getDateFormat(question.getCreateTime(), DateUtil.FULL_TIME_SPLIT_PATTERN));
        return FebsUtil.view("test/question/questionDetail");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "test/paper")
    @RequiresPermissions("paper:view")
    public String paper() {
        return FebsUtil.view("test/paper/paper");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "test/paper/add")
    @RequiresPermissions("paper:add")
    public String paperAdd() {
        return FebsUtil.view("test/paper/paperAdd");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "test/paper/update/{paperId}")
    @RequiresPermissions("paper:update")
    public String paperUpdate(@PathVariable String paperId, Model model) {
        Paper paper = paperService.findById(paperId);
        model.addAttribute("paper", paper);
        return FebsUtil.view("test/paper/paperUpdate");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "test/paper/detail/{paperId}")
    @RequiresPermissions("paper:view")
    public String paperDetail(@PathVariable String paperId, Model model) {
        Paper paper = paperService.findById(paperId);
        model.addAttribute("paper", paper);
        model.addAttribute("createTime", DateUtil.getDateFormat(paper.getCreateTime(), DateUtil.FULL_TIME_SPLIT_PATTERN));
        return FebsUtil.view("test/paper/paperDetail");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "test/question/addToPaper")
    @RequiresPermissions("question:addToPaper")
    public String questionAddToPaper() {
        return FebsUtil.view("test/question/questionAddToPaper");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "test/question/removeFromPaper/{questionId}")
    @RequiresPermissions("question:removeFromPaper")
    public String questionRemoveFromPaper(@PathVariable String questionId, Model model) {
        Question question = questionService.findByIdRemovePaperList(questionId);
        model.addAttribute("question", question);
        return FebsUtil.view("test/question/questionRemoveFromPaper");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "test/testStart")
    @RequiresPermissions("test:testStart")
    public String testStart() {
        return FebsUtil.view("test/test/testStart");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "test/testPage/{paperId}")
    @RequiresPermissions("test:testStart")
    public String testPage(@PathVariable String paperId, Model model) {
        Paper paper = paperService.findCompletePaperById(paperId);
        model.addAttribute("paper", paper);
        return FebsUtil.view("test/test/testPage");
    }

    private void resolveUserModel(String username, Model model, Boolean transform) {
        User user = userService.findByName(username);
        model.addAttribute("user", user);
        if (transform) {
            String ssex = user.getSex();
            if (User.SEX_MALE.equals(ssex)) user.setSex("男");
            else if (User.SEX_FEMALE.equals(ssex)) user.setSex("女");
            else user.setSex("保密");
        }
        if (user.getLastLoginTime() != null)
            model.addAttribute("lastLoginTime", DateUtil.getDateFormat(user.getLastLoginTime(), DateUtil.FULL_TIME_SPLIT_PATTERN));
    }
}
