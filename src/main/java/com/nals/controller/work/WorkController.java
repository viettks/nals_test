package com.nals.controller.work;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nals.mybatis.service.WorkService;

/**
 * DatabaseConfig
 *
 * @author vietnt
 * @since 2022/02/27
 * @version 1.0
 * @see
 * 
 * <pre>
 * == Work controller ==
 * 
 * date                modifier                     status
 * -----                -----            ------
 *  2022/02/27 17:57:00  vietnt           CREATE
 * 
 * </pre>
 */
@RestController
@RequestMapping("api/work")
public class WorkController {

    //work status 
    private static final int PLANING = 1;
    private static final int DOING = 2;
    private static final int COMPLETE = 3;

    @Autowired
    private WorkService workService;
    /**
    * api create work
    * @return ResponseEntity
    */
    @PostMapping
    public ResponseEntity<?> createWork(@RequestParam Map<String, Object> param){
        Map<String, Object> response = new HashMap<>();
        try {
            if(ObjectUtils.isEmpty(param.get("name"))) {
                response.put("message", "Work name is required!");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            if(ObjectUtils.isEmpty(param.get("start_date"))) {
                response.put("message", "Start Date is required!");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            if(ObjectUtils.isEmpty(param.get("end_date"))) {
                response.put("message", "End Date is required!");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            if(ObjectUtils.isEmpty(param.get("status"))) {
                response.put("message", "Status is required!");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            String startDate = (String) param.get("start_date");
            String endDate = (String) param.get("end_date");
            LocalDate stDate =  LocalDate.parse(startDate);
            LocalDate edDate =  LocalDate.parse(endDate);
            
            long p = ChronoUnit.DAYS.between(stDate, edDate);
            if(p < 0) {
                response.put("message", "Start date must not be greater than end date.");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            
            int status = Integer.parseInt(param.get("status").toString());
            if(!(status == PLANING || status == DOING || status == COMPLETE)) {
                response.put("message", "Status must be in 1,2,3.");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            response.put("work_id", workService.createWork(param));
            response.put("message", "Work has been created!");
            return ResponseEntity.ok(response);
        } catch (DateTimeParseException e) {
            response.put("message", "Start Date, End date must be yyyy-MM-dd.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (NumberFormatException e) {
            response.put("message", "Status must be number.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            e.printStackTrace();
            response.put("message", "Server Error");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
    * api update work
    * @return ResponseEntity
    */
    @PutMapping
    public ResponseEntity<?> updateWork(@RequestParam Map<String, Object> param){
        Map<String, Object> response = new HashMap<>();
        try {
            if(ObjectUtils.isEmpty(param.get("work_id"))) {
                response.put("message", "Work id is required!");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            if(ObjectUtils.isEmpty(param.get("name"))) {
                response.put("message", "Work name is required!");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            if(ObjectUtils.isEmpty(param.get("start_date"))) {
                response.put("message", "Start Date is required!");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            if(ObjectUtils.isEmpty(param.get("end_date"))) {
                response.put("message", "End Date is required!");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            if(ObjectUtils.isEmpty(param.get("status"))) {
                response.put("message", "Status is required!");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            String startDate = (String) param.get("start_date");
            String endDate = (String) param.get("end_date");
            LocalDate stDate =  LocalDate.parse(startDate);
            LocalDate edDate =  LocalDate.parse(endDate);

            long p = ChronoUnit.DAYS.between(stDate, edDate);
            if(p < 0) {
                response.put("message", "Start date must not be greater than end date.");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            int status = Integer.parseInt(param.get("status").toString());
            if(!(status == PLANING || status == DOING || status == COMPLETE)) {
                response.put("message", "Status must be in 1,2,3.");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            int workIdCnt = workService.countWorkId(param.get("work_id").toString());
            if(workIdCnt == 0) {
                response.put("message", "Work has not been created!");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            workService.updateWork(param);
            response.put("work_id", param.get("work_id"));
            response.put("message", "Work has been updated!");
            return ResponseEntity.ok(response);
        } catch (DateTimeParseException e) {
            response.put("message", "Start Date, End date must be yyyy-MM-dd.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (NumberFormatException e) {
            response.put("message", "Status must be number.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            e.printStackTrace();
            response.put("message", "Server Error");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
    * api delete work
    * @return ResponseEntity
    */
    @DeleteMapping
    public ResponseEntity<?> removeWork(@RequestParam Map<String, Object> param){
        Map<String, Object> response = new HashMap<>();
        try {
            if(ObjectUtils.isEmpty(param.get("work_id"))) {
                response.put("message", "Work id is required!");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            int workIdCnt = workService.countWorkId(param.get("work_id").toString());
            if(workIdCnt == 0) {
                response.put("message", "Work has not been created!");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            workService.removeWork(param.get("work_id").toString());
            response.put("work_id", param.get("work_id"));
            response.put("message", "Work has been deleted!");
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            e.printStackTrace();
            response.put("message", "Server Error");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
    * api fetch list of works
    * @return ResponseEntity
    */
    @GetMapping("list")
    public ResponseEntity<?> showListWork(@RequestParam Map<String, Object> param){
        Map<String, Object> response = new HashMap<>();
        try {
            return ResponseEntity.ok(workService.getListWork(param));
        }
        catch (Exception e) {
            e.printStackTrace();
            response.put("message", "Server Error");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
