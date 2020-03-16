package cc.mrbird.febs.test.service.impl;

import cc.mrbird.febs.test.mapper.TestMapper;
import cc.mrbird.febs.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;
}
