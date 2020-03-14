package cc.mrbird.febs.test.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.test.entity.Question;
import cc.mrbird.febs.test.service.QuestionService;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("question")
public class QuestionController extends BaseController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping("list")
    @RequiresPermissions("question:view")
    public FebsResponse questionList(Question question, QueryRequest request) {
        Map<String, Object> dataTable = getDataTable(questionService.findQuestionList(question, request));
        return new FebsResponse().success().data(dataTable);
    }

    @RequestMapping("add")
    @RequiresPermissions("question:add")
    @ControllerEndpoint(operation = "新增试题", exceptionMessage = "新增试题失败")
    public FebsResponse addQuestion(@RequestBody @Valid Question question) {
        questionService.addQuestion(question);
        return new FebsResponse().success();
    }

    @RequestMapping("update")
    @RequiresPermissions("question:update")
    @ControllerEndpoint(operation = "修改试题", exceptionMessage = "修改试题失败")
    public FebsResponse updateQuestion(@RequestBody @Valid Question question) {
        if (question.getQuestionId() == null)
            throw new FebsException("试题ID为空");
        questionService.updateQuestion(question);
        return new FebsResponse().success();
    }

    @RequestMapping("delete/{questionIds}")
    @RequiresPermissions("question:delete")
    @ControllerEndpoint(operation = "删除试题", exceptionMessage = "删除试题失败")
    public FebsResponse deleteQuestions(@NotBlank(message = "{required}") @PathVariable String questionIds) {
        String[] ids = questionIds.split(StringPool.COMMA);
        questionService.deleteQuestions(ids);
        return new FebsResponse().success();
    }

    @RequestMapping("excel")
    @RequiresPermissions("question:export")
    @ControllerEndpoint(exceptionMessage = "导出Excel失败")
    public void export(QueryRequest queryRequest, Question question, HttpServletResponse response) {
        List<Question> questions = questionService.findQuestionList(question, queryRequest).getRecords();
        ExcelKit.$Export(Question.class, response).downXlsx(questions, false);
    }

}
