package cc.mrbird.febs.test.controller;

import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.test.service.PaperService;
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

    @RequestMapping("testPaper")
    @RequiresPermissions("test:testStart")
    public FebsResponse testPaper(String userId) {
        return new FebsResponse().success().data(paperService.findUserPaper(userId));
    }

    @RequestMapping("submitPaper")
    @RequiresPermissions("test:testStart")
    public FebsResponse testSubmit(@RequestBody @Valid Map<String, Object> map) {
        System.out.println(map);
        return new FebsResponse().success();
    }
}
