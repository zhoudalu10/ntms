package cc.mrbird.febs.test.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.test.entity.Paper;
import cc.mrbird.febs.test.service.PaperService;
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
@RequestMapping("paper")
public class PaperController extends BaseController {

    @Autowired
    private PaperService paperService;

    @RequestMapping("list")
    @RequiresPermissions("paper:view")
    public FebsResponse paperList(Paper paper, QueryRequest request) {
        Map<String, Object> dataTable = getDataTable(paperService.findPaperList(paper, request));
        return new FebsResponse().success().data(dataTable);
    }

    @RequestMapping("add")
    @RequiresPermissions("paper:add")
    @ControllerEndpoint(operation = "新增试卷", exceptionMessage = "新增试卷失败")
    public FebsResponse addPaper(@Valid Paper paper) {
        paperService.addPaper(paper);
        return new FebsResponse().success();
    }

    @RequestMapping("update")
    @RequiresPermissions("paper:update")
    @ControllerEndpoint(operation = "修改试卷", exceptionMessage = "修改试卷失败")
    public FebsResponse updatePaper(@Valid Paper paper) {
        if (paper.getPaperId() == null)
            throw new FebsException("试卷ID为空");
        paperService.updatePaper(paper);
        return new FebsResponse().success();
    }

    @RequestMapping("delete/{paperIds}")
    @RequiresPermissions("paper:delete")
    @ControllerEndpoint(operation = "删除试卷", exceptionMessage = "删除试卷失败")
    public FebsResponse deletePapers(@NotBlank(message = "{required}") @PathVariable String paperIds) {
        String[] ids = paperIds.split(StringPool.COMMA);
        paperService.deletePapers(ids);
        return new FebsResponse().success();
    }

    @RequestMapping("excel")
    @RequiresPermissions("paper:export")
    @ControllerEndpoint(exceptionMessage = "导出Excel失败")
    public void export(QueryRequest queryRequest, Paper paper, HttpServletResponse response) {
        List<Paper> papers = paperService.findPaperList(paper, queryRequest).getRecords();
        ExcelKit.$Export(Paper.class, response).downXlsx(papers, false);
    }

    @RequestMapping("listAll")
    public FebsResponse paperListAll(Paper paper) {
        return new FebsResponse().success().data(paperService.findPaperListAll(paper));
    }

    @RequestMapping("startTest/{paperIds}")
    @RequiresPermissions("paper:startTest")
    @ControllerEndpoint(operation = "开始测验", exceptionMessage = "开始测验失败")
    public FebsResponse startTest(@NotBlank(message = "{required}") @PathVariable String paperIds) {
        String[] ids = paperIds.split(StringPool.COMMA);
        paperService.startTest(ids);
        return new FebsResponse().success();
    }

    @RequestMapping("endTest/{paperIds}")
    @RequiresPermissions("paper:endTest")
    @ControllerEndpoint(operation = "结束测验", exceptionMessage = "结束测验失败")
    public FebsResponse endTest(@NotBlank(message = "{required}") @PathVariable String paperIds) {
        String[] ids = paperIds.split(StringPool.COMMA);
        paperService.endTest(ids);
        return new FebsResponse().success();
    }
}
