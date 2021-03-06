package cc.mrbird.febs.test.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.test.service.PaperService;
import cc.mrbird.febs.test.service.TestResultService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@Slf4j
@Validated
@RestController
@RequestMapping("test")
public class TestController extends BaseController {

    @Autowired
    private PaperService paperService;

    @Autowired
    private TestResultService testResultService;

    @RequestMapping("testPaper")
    @RequiresPermissions("test:testStart")
    @ControllerEndpoint(operation = "获取试卷信息", exceptionMessage = "获取试卷信息失败")
    public FebsResponse testPaper(String userId) {
        return new FebsResponse().success().data(paperService.findUserPaper(userId));
    }

    @RequestMapping("submitPaper")
    @RequiresPermissions("test:testStart")
    @ControllerEndpoint(operation = "提交试卷", exceptionMessage = "提交试卷失败")
    public FebsResponse testSubmit(@RequestBody @Valid Map<String, Object> map) {
        testResultService.calculationResults(map);
        return new FebsResponse().success();
    }
}
