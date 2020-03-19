package cc.mrbird.febs.test.service;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.test.entity.Paper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface PaperService {
    IPage<Paper> findPaperList(Paper paper, QueryRequest request);

    void addPaper(Paper paper);

    Paper findById(String paperId);

    void updatePaper(Paper paper);

    void deletePapers(String[] ids);

    List<Paper> findPaperListAll(Paper paper);

    List<Paper> findByQuestionId(String questionId);

    List<Paper> findRemoveListByQuestionId(String questionId);

    void startTest(String[] ids);

    void endTest(String[] ids);

    List<Paper> findUserPaper(String userId);

    Paper findCompletePaperById(String paperId);

    Float findPaperFullMarks(Long paperId);

    void deleteByCourseId(String courseId);

    Paper findAnalysisById(String paperId);
}
