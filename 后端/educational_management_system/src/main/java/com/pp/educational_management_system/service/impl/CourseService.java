package com.pp.educational_management_system.service.impl;

import com.pp.educational_management_system.dao.ICourseDao;
import com.pp.educational_management_system.dao.IUserInfoDao;
import com.pp.educational_management_system.domain.Course;
import com.pp.educational_management_system.domain.Semester;
import com.pp.educational_management_system.domain.TeacherInfo;
import com.pp.educational_management_system.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService implements ICourseService {
    @Autowired
    ICourseDao courseDao;

    @Autowired
    IUserInfoDao userInfoDao;

    //得到所有学生的年级
    public List<String> getAllGradeYear(){
        return courseDao.getAllGradeYear();
    }
    //得到所有学生的学院
    public List<String> getAllAcademy(){
        return courseDao.getAllAcademy();
    }
    //得到所有的学期
    public List<Semester> getAllSemester(){
        return courseDao.getAllSemester();
    }
    public List<String> getAllClass(){
        return courseDao.getAllClass();
    }

    public List<TeacherInfo> getAllTeacher(){
        return userInfoDao.getTeacherInfoList(null,null,null);
    }

    //添加课程
    public int addCourse(Course course){
        return courseDao.addCourse(course);
    }
    //通过班级得到人数
    public int getClassCount(List<String> idList){
        return courseDao.getClassCount(idList);
    }
    //得到课程列表
    public List<Course> getCourseList(int pageStart,int pageSize,String coursetype,String beginacademy,String begingrade,String coursename){
        return courseDao.getCourseList(pageStart,pageSize,coursetype,beginacademy,begingrade,coursename);
    }

    public int getCourseCount(String coursetype,String beginacademy,String begingrade,String coursename){
        return courseDao.getCourseCount(coursetype,beginacademy,begingrade,coursename);
    }
}
