package com.pp.educational_management_system.controller;

import com.alibaba.fastjson.JSON;
import com.pp.educational_management_system.domain.Course;
import com.pp.educational_management_system.domain.Semester;
import com.pp.educational_management_system.domain.TeacherInfo;
import com.pp.educational_management_system.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    ICourseService courseService;

    @RequestMapping("/getFormdata")
    public String getFormdata(){
        List<String> academy =  courseService.getAllAcademy();
        List<String> gradeYear = courseService.getAllGradeYear();
        List<Semester> semesters =  courseService.getAllSemester();
        List<String> sclass = courseService.getAllClass();
        List<TeacherInfo> teacherInfos = courseService.getAllTeacher();
        HashMap<String,Object> result = new HashMap<>();
        result.put("gradeYearList",gradeYear);
        result.put("academyList",academy);
        result.put("semesterList",semesters);
        result.put("sclass",sclass);
        result.put("teacher",teacherInfos);
        return JSON.toJSONString(result);
    }


    @RequestMapping("/addCourse")
    public String addCourse(@RequestBody Course course){
        course.setCoursecode(UUID.randomUUID().toString());
        List<String> idList = new ArrayList<>();
        String s[] = course.getExecourses().split(",");
        for(int i=0;i<s.length;i++){
            idList.add(s[i]);
        }
        if(course.getCoursetype().equals("必修课")){
            course.setAtusum(courseService.getClassCount(idList));
        }
        if(course.getCoursetype().equals("选修课")){
            course.setAtusum(0);
        }
        int i = courseService.addCourse(course);
        HashMap<String,Object> result = new HashMap<>();
        if(i==0){
            result.put("state",404);
        }else{
            result.put("state",200);
        }
        return JSON.toJSONString(result);
    }

    @RequestMapping("/getCourseList")
    public String getCourseList(int pageNum,int pageSize,String coursetype,String beginacademy,String begingrade,String coursename){
        List<Course> courses = courseService.getCourseList((pageNum-1)*pageSize,pageSize,coursetype,beginacademy,begingrade,coursename);
        int number = courseService.getCourseCount(coursetype,beginacademy,begingrade,coursename);
        HashMap<String,Object> result = new HashMap<String,Object>();
        result.put("number",number);
        result.put("courses",courses);
        return JSON.toJSONString(result);
    }
}
