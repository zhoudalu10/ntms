package cc.mrbird.febs.test.controller;

import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.utils.DateUtil;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.test.entity.Paper;
import cc.mrbird.febs.test.entity.Question;
import cc.mrbird.febs.test.entity.TestResult;
import cc.mrbird.febs.test.service.PaperService;
import cc.mrbird.febs.test.service.QuestionService;
import cc.mrbird.febs.test.service.TestResultService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("testView")
@RequestMapping(FebsConstant.VIEW_PREFIX + "test")
public class ViewController extends BaseController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private PaperService paperService;

    @Autowired
    private TestResultService testResultService;

    @GetMapping("question")
    @RequiresPermissions("question:view")
    public String question() {
        return FebsUtil.view("test/question/question");
    }

    @GetMapping("question/add")
    @RequiresPermissions("question:add")
    public String questionAdd() {
        return FebsUtil.view("test/question/questionAdd");
    }

    @GetMapping("question/update/{questionId}")
    @RequiresPermissions("question:update")
    public String questionUpdate(@PathVariable String questionId, Model model) {
        Question question = questionService.findById(questionId);
        model.addAttribute("question", question);
        return FebsUtil.view("test/question/questionUpdate");
    }

    @GetMapping("question/detail/{questionId}")
    @RequiresPermissions("question:view")
    public String questionDetail(@PathVariable String questionId, Model model) {
        Question question = questionService.findById(questionId);
        model.addAttribute("question", question);
        model.addAttribute("createTime", DateUtil.getDateFormat(question.getCreateTime(), DateUtil.FULL_TIME_SPLIT_PATTERN));
        return FebsUtil.view("test/question/questionDetail");
    }

    @GetMapping("paper")
    @RequiresPermissions("paper:view")
    public String paper() {
        return FebsUtil.view("test/paper/paper");
    }

    @GetMapping("paper/add")
    @RequiresPermissions("paper:add")
    public String paperAdd() {
        return FebsUtil.view("test/paper/paperAdd");
    }

    @GetMapping("paper/update/{paperId}")
    @RequiresPermissions("paper:update")
    public String paperUpdate(@PathVariable String paperId, Model model) {
        Paper paper = paperService.findById(paperId);
        model.addAttribute("paper", paper);
        return FebsUtil.view("test/paper/paperUpdate");
    }

    @GetMapping("paper/detail/{paperId}")
    @RequiresPermissions("paper:view")
    public String paperDetail(@PathVariable String paperId, Model model) {
        Paper paper = paperService.findById(paperId);
        model.addAttribute("paper", paper);
        model.addAttribute("createTime", DateUtil.getDateFormat(paper.getCreateTime(), DateUtil.FULL_TIME_SPLIT_PATTERN));
        return FebsUtil.view("test/paper/paperDetail");
    }

    @GetMapping("paper/analysis/{paperId}")
    @RequiresPermissions("paper:analysis")
    public String paperAnalysis(@PathVariable String paperId, Model model) {
        Paper paper = paperService.findAnalysisById(paperId);
        model.addAttribute("paper", paper);
        model.addAttribute("createTime", DateUtil.getDateFormat(paper.getCreateTime(), DateUtil.FULL_TIME_SPLIT_PATTERN));
        return FebsUtil.view("test/paper/paperAnalysis");
    }

    @GetMapping("question/addToPaper")
    @RequiresPermissions("question:addToPaper")
    public String questionAddToPaper() {
        return FebsUtil.view("test/question/questionAddToPaper");
    }

    @GetMapping("question/removeFromPaper/{questionId}")
    @RequiresPermissions("question:removeFromPaper")
    public String questionRemoveFromPaper(@PathVariable String questionId, Model model) {
        Question question = questionService.findByIdRemovePaperList(questionId);
        model.addAttribute("question", question);
        return FebsUtil.view("test/question/questionRemoveFromPaper");
    }

    @GetMapping("testStart")
    @RequiresPermissions("test:testStart")
    public String testStart() {
        return FebsUtil.view("test/test/testStart");
    }

    @GetMapping("testPage/{paperId}")
    @RequiresPermissions("test:testStart")
    public String testPage(@PathVariable String paperId, Model model) {
        TestResult testResult = new TestResult();
        testResult.setResultUserId(FebsUtil.getCurrentUser().getUserId());
        testResult.setResultPaperId(Long.parseLong(paperId));
        if (testResultService.judgePaper(testResult) > 0) {
            return FebsUtil.view("error/403");
        }
        testResultService.insertZeroMarks(testResult);
        Paper paper = paperService.findCompletePaperById(paperId);
        model.addAttribute("paper", paper);
        model.addAttribute("createTime", DateUtil.getDateFormat(paper.getCreateTime(), DateUtil.FULL_TIME_SPLIT_PATTERN));
        return FebsUtil.view("test/test/testPage");
    }

    @GetMapping("testResult")
    @RequiresPermissions("testResult:view")
    public String testResult() {
        return FebsUtil.view("test/testResult/testResult");
    }

    @GetMapping("testResult/analysis/{resultId}")
    @RequiresPermissions("testResult:analysis")
    public String testResultAnalysis(@PathVariable String resultId, Model model) {
        TestResult testResult = testResultService.findAnalysisById(resultId);
        model.addAttribute("testResult", testResult);
        return FebsUtil.view("test/testResult/testResultAnalysis");
    }
}
