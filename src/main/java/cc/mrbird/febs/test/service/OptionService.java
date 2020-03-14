package cc.mrbird.febs.test.service;

import cc.mrbird.febs.test.entity.Option;

import java.util.List;

public interface OptionService {
    void addOption(Option option);

    List<Option> findByQuestionId(String questionId);

    void updateOption(Option option);

    void deleteOptionsByQuestionId(String questionId);
}
