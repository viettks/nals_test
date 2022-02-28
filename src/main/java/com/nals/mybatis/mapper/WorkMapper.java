package com.nals.mybatis.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WorkMapper {

    /**
    * create work
    * @param param work info
    * @return void
    */
    public void createWork(Map<String, Object> param) throws SQLException;

    /**
    * update work
    * @param param work info
    * @return void
    */
    public void updateWork(Map<String, Object> param) throws SQLException;

    /**
    * delete work
    * @param param work id
    * @return void
    */
    public void removeWork(String workId) throws SQLException;

    /**
    * get list work
    * @param param search info
    * @return list of works
    */
    public <T> List<T> getListWork(Map<String, Object> param) throws SQLException;

    /**
    * count work by work Id
    * @param workId work id
    * @return sum of work id
    */
    public int countWorkId(String workId) throws SQLException;
}
