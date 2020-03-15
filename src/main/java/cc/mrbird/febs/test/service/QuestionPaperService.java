package cc.mrbird.febs.test.service;

import cc.mrbird.febs.test.entity.QuestionPaper;

public interface QuestionPaperService {

    void addQuestionPaper(QuestionPaper questionPaper);

    void deleteQuestionPaper(QuestionPaper questionPaper);

    void removeByPaperId(String paperId);
}
