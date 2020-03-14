package cc.mrbird.febs.test.service.impl;

import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.utils.SortUtil;
import cc.mrbird.febs.test.entity.Paper;
import cc.mrbird.febs.test.mapper.PaperMapper;
import cc.mrbird.febs.test.service.PaperService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class PaperServiceImpl extends ServiceImpl<PaperMapper, Paper> implements PaperService {

    @Override
    public IPage<Paper> findPaperList(Paper paper, QueryRequest request) {
        Page<Paper> page = new Page<>(request.getPageNum(), request.getPageSize());
        SortUtil.handlePageSort(request, page, "createTime", FebsConstant.ORDER_ASC, false);
        return this.baseMapper.findPaperList(page, paper);
    }

    @Override
    public void addPaper(Paper paper) {
        paper.setCreateTime(new Date());
        paper.setPaperState(Paper.STATE_PROGRESS);
        save(paper);
    }

    @Override
    public Paper findById(String paperId) {
        return this.baseMapper.findById(paperId);
    }

    @Override
    public void updatePaper(Paper paper) {
        updateById(paper);
    }

    @Override
    public void deletePapers(String[] ids) {
        List<String> list = Arrays.asList(ids);
        this.removeByIds(list);
    }
}
