package cc.mrbird.febs.test.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.test.entity.TestResult;
import cc.mrbird.febs.test.service.TestResultService;
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
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

@Slf4j
@Validated
@RestController
@RequestMapping("testResult")
public class TestResultController extends BaseController {

    @Autowired
    private TestResultService testResultService;

    @RequestMapping("list")
    @RequiresPermissions("testResult:view")
    public FebsResponse testResultList(TestResult testResult, QueryRequest request) {
        Map<String, Object> dataTable = getDataTable(testResultService.findTestResultList(testResult, request));
        return new FebsResponse().success().data(dataTable);
    }

    @RequestMapping("delete/{testResultIds}")
    @RequiresPermissions("testResult:delete")
    @ControllerEndpoint(operation = "删除测验记录", exceptionMessage = "删除测验记录失败")
    public FebsResponse deleteTestResults(@NotBlank(message = "{required}") @PathVariable String testResultIds) {
        String[] ids = testResultIds.split(StringPool.COMMA);
        testResultService.deleteTestResults(ids);
        return new FebsResponse().success();
    }

    @RequestMapping("excel")
    @RequiresPermissions("testResult:export")
    @ControllerEndpoint(exceptionMessage = "导出Excel失败")
    public void export(QueryRequest queryRequest, TestResult testResult, HttpServletResponse response) {
        List<TestResult> testResults = testResultService.findTestResultList(testResult, queryRequest).getRecords();
        ExcelKit.$Export(TestResult.class, response).downXlsx(testResults, false);
    }

}
