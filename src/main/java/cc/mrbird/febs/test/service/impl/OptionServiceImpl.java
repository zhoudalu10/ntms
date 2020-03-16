package cc.mrbird.febs.test.service.impl;

import cc.mrbird.febs.test.entity.Option;
import cc.mrbird.febs.test.mapper.OptionMapper;
import cc.mrbird.febs.test.service.OptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionServiceImpl extends ServiceImpl<OptionMapper, Option> implements OptionService {

    @Override
    public void addOption(Option option) {
        save(option);
    }

    @Override
    public List<Option> findByQuestionId(String questionId) {
        return this.baseMapper.findByQuestionId(questionId);
    }

    @Override
    public void updateOption(Option option) {
        updateById(option);
    }

    @Override
    public void deleteOptionsByQuestionId(String questionId) {
        this.baseMapper.deleteOptionsByQuestionId(questionId);
    }

    @Override
    public List<Option> findByQuestionIdWithoutKey(Long questionId) {
        return this.baseMapper.findByQuestionIdWithoutKey(questionId);
    }
}
