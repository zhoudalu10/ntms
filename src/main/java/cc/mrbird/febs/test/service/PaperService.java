package cc.mrbird.febs.test.service;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.test.entity.Paper;
import com.baomidou.mybatisplus.core.metadata.IPage;

public interface PaperService {
    IPage<Paper> findPaperList(Paper paper, QueryRequest request);

    void addPaper(Paper paper);

    Paper findById(String paperId);

    void updatePaper(Paper paper);

    void deletePapers(String[] ids);
}
