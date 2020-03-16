package cc.mrbird.febs.job.mapper;


import org.apache.ibatis.annotations.Mapper;

public interface LogTaskMapper extends Mapper {

    void deleteLoginLog();

    void deleteJobLog();
}
