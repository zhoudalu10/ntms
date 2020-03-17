package cc.mrbird.febs.test.service.impl;

import cc.mrbird.febs.test.entity.Option;
import cc.mrbird.febs.test.mapper.OptionMapper;
import cc.mrbird.febs.test.service.OptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class OptionServiceImpl extends ServiceImpl<OptionMapper, Option> implements OptionService {

    @Override
    @Transactional
    public void addOption(Option option) {
        save(option);
    }

    @Override
    public List<Option> findByQuestionId(String questionId) {
        return this.baseMapper.findByQuestionId(questionId);
    }

    @Override
    @Transactional
    public void updateOption(Option option) {
        updateById(option);
    }

    @Override
    @Transactional
    public void deleteOptionsByQuestionId(String questionId) {
        this.baseMapper.deleteOptionsByQuestionId(questionId);
    }

    @Override
    public List<Option> findByQuestionIdWithoutKey(Long questionId) {
        return this.baseMapper.findByQuestionIdWithoutKey(questionId);
    }
}
