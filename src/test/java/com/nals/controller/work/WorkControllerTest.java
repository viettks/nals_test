package com.nals.controller.work;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.nals.mybatis.service.WorkService;

@WebMvcTest(WorkController.class)
public class WorkControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    WorkService workService;

    @Autowired
    WorkService realWorkService;

    @Test
    public void testCreateWork() throws Exception 
    {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.put("name", Collections.singletonList("Work name test"));
        parameters.put("start_date", Collections.singletonList("2022-02-28"));
        parameters.put("end_date", Collections.singletonList("2022-02-28"));
        parameters.put("status", Collections.singletonList("1"));

        Mockito.when(workService.createWork(Mockito.any())).thenReturn("1");

        mvc.perform( 
                 post("/api/work")
                .params(parameters)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Work has been created!"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.work_id").value("1"));

        MultiValueMap<String, String> paramFail = new LinkedMultiValueMap<>();
        paramFail.putAll(parameters);
        paramFail.remove("name");

        mvc.perform( 
                post("/api/work")
               .params(paramFail)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest())
               .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Work name is required!"));

        paramFail.putAll(parameters);
        paramFail.remove("start_date");

        mvc.perform( 
                post("/api/work")
               .params(paramFail)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest())
               .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Start Date is required!"));

        paramFail.putAll(parameters);
        paramFail.remove("end_date");

        mvc.perform( 
                post("/api/work")
               .params(paramFail)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest())
               .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("End Date is required!"));

        paramFail.putAll(parameters);
        paramFail.remove("status");

        mvc.perform( 
                post("/api/work")
               .params(paramFail)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest())
               .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Status is required!"));

        paramFail.putAll(parameters);
        paramFail.put("start_date", Collections.singletonList("2022/02/27"));

        mvc.perform( 
                post("/api/work")
               .params(paramFail)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest())
               .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Start Date, End date must be yyyy-MM-dd."));

        paramFail.putAll(parameters);
        paramFail.put("end_date", Collections.singletonList("2022/02/27"));

        mvc.perform( 
                post("/api/work")
               .params(paramFail)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest())
               .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Start Date, End date must be yyyy-MM-dd."));


        paramFail.putAll(parameters);
        paramFail.put("status", Collections.singletonList("abc"));

        mvc.perform( 
                post("/api/work")
               .params(paramFail)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest())
               .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Status must be number."));

        paramFail.putAll(parameters);
        paramFail.put("end_date", Collections.singletonList("2022-02-27"));

        mvc.perform( 
                post("/api/work")
               .params(paramFail)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest())
               .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Start date must not be greater than end date."));

        paramFail.putAll(parameters);
        paramFail.put("status", Collections.singletonList("4"));

        mvc.perform( 
                post("/api/work")
               .params(paramFail)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest())
               .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Status must be in 1,2,3."));
    }

    @Test
    public void testUpdateWork() throws Exception 
    {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.put("work_id", Collections.singletonList("1"));
        parameters.put("name", Collections.singletonList("Work name test"));
        parameters.put("start_date", Collections.singletonList("2022-02-28"));
        parameters.put("end_date", Collections.singletonList("2022-02-28"));
        parameters.put("status", Collections.singletonList("1"));

        Mockito.doNothing().when(workService).updateWork(Mockito.any());
        // workId 1 is exist in DB
        // workId 1 is not exist in DB
        Mockito.when(workService.countWorkId("1")).thenReturn(1);
        Mockito.when(workService.countWorkId("0")).thenReturn(0);

        mvc.perform( 
                 put("/api/work")
                .params(parameters)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Work has been updated!"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.work_id").value("1"));

        MultiValueMap<String, String> paramFail = new LinkedMultiValueMap<>();
        paramFail.putAll(parameters);
        paramFail.remove("work_id");

        mvc.perform( 
                put("/api/work")
               .params(paramFail)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest())
               .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Work id is required!"));

        paramFail.putAll(parameters);
        paramFail.remove("name");

        mvc.perform( 
                put("/api/work")
               .params(paramFail)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest())
               .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Work name is required!"));

        paramFail.putAll(parameters);
        paramFail.remove("start_date");

        mvc.perform( 
                put("/api/work")
               .params(paramFail)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest())
               .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Start Date is required!"));

        paramFail.putAll(parameters);
        paramFail.remove("end_date");

        mvc.perform( 
                put("/api/work")
               .params(paramFail)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest())
               .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("End Date is required!"));

        paramFail.putAll(parameters);
        paramFail.remove("status");

        mvc.perform( 
                put("/api/work")
               .params(paramFail)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest())
               .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Status is required!"));

        paramFail.putAll(parameters);
        paramFail.put("start_date", Collections.singletonList("2022/02/27"));

        mvc.perform( 
                put("/api/work")
               .params(paramFail)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest())
               .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Start Date, End date must be yyyy-MM-dd."));

        paramFail.putAll(parameters);
        paramFail.put("end_date", Collections.singletonList("2022/02/27"));

        mvc.perform( 
                put("/api/work")
               .params(paramFail)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest())
               .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Start Date, End date must be yyyy-MM-dd."));


        paramFail.putAll(parameters);
        paramFail.put("status", Collections.singletonList("abc"));

        mvc.perform( 
                put("/api/work")
               .params(paramFail)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest())
               .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Status must be number."));

        paramFail.putAll(parameters);
        paramFail.put("end_date", Collections.singletonList("2022-02-27"));

        mvc.perform( 
                put("/api/work")
               .params(paramFail)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest())
               .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Start date must not be greater than end date."));

        paramFail.putAll(parameters);
        paramFail.put("status", Collections.singletonList("4"));

        mvc.perform( 
                put("/api/work")
               .params(paramFail)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest())
               .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Status must be in 1,2,3."));

        paramFail.putAll(parameters);
        paramFail.put("work_id", Collections.singletonList("0"));

        mvc.perform( 
                put("/api/work")
               .params(paramFail)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest())
               .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Work has not been created!"));
    }

    @Test
    public void testDeleteWork() throws Exception 
    {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.put("work_id", Collections.singletonList("1"));

        Mockito.doNothing().when(workService).removeWork(Mockito.any());
        // workId 1 is exist in DB
        // workId 1 is not exist in DB
        Mockito.when(workService.countWorkId("1")).thenReturn(1);
        Mockito.when(workService.countWorkId("0")).thenReturn(0);

        mvc.perform( 
                 delete("/api/work")
                .params(parameters)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Work has been deleted!"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.work_id").value("1"));

        MultiValueMap<String, String> paramFail = new LinkedMultiValueMap<>();
        mvc.perform( 
                delete("/api/work")
               .params(paramFail)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest())
               .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Work id is required!"));

        paramFail.put("work_id", Collections.singletonList("0"));
        mvc.perform( 
                delete("/api/work")
               .params(paramFail)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest())
               .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Work has not been created!"));
    }
}
