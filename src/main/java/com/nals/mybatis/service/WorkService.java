package com.nals.mybatis.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nals.mybatis.mapper.WorkMapper;

@Service
public class WorkService {

    //sort type
    protected static final String SORT_ASC = "ASC";
    protected static final String SORT_DESC = "DESC";

    //pagging
    protected static final int NUMBER_OF_PAGE = 5;
    @Autowired
    private WorkMapper workMapper;

    /**
    * create work
    * @param param : work info
    * @return work Id
    * @throws SQLException 
    */
    public String createWork(Map<String, Object> param) throws SQLException {
        workMapper.createWork(param);
        String workId = (String) param.get("workId");
        return workId;
    }

    /**
    * upadate work
    * @return void
    * @throws SQLException 
    */
    public void updateWork(Map<String, Object> param) throws SQLException {
        workMapper.updateWork(param);
    }

    /**
    * remove work
    * @return void
    * @throws SQLException 
    */
    public void removeWork(String workId) throws SQLException {
        workMapper.removeWork(workId);
    }

    /**
    * get list work
    * @return work list
    * @throws SQLException
    */
    public List<Map<String, Object>> getListWork(Map<String, Object> param) throws SQLException {
        String sortType = (String) param.get("sort_type");
        if(!(StringUtils.equals(SORT_ASC, sortType) || StringUtils.equals(SORT_DESC, sortType))) {
            //default is ASC
            param.put("sort_type",SORT_ASC);
        }
        int pageOffset = 0;
        if(!ObjectUtils.isEmpty(param.get("page"))) {
            try {
                int page = Integer.parseInt(param.get("page").toString());
                int offset = (NUMBER_OF_PAGE * page) - NUMBER_OF_PAGE;
                pageOffset = offset > 0 ? offset : 0;
            } catch (NumberFormatException e) {
                // do notthing, using default
            }
        }
        param.put("number_of_page", NUMBER_OF_PAGE);
        param.put("page_offset", pageOffset);
        return workMapper.getListWork(param);
    }

    /**
    * count work by Id
    * @return count workId
    * @throws SQLException 
    */
    public int countWorkId(String workId) throws SQLException {
        return workMapper.countWorkId(workId);
    }
}
