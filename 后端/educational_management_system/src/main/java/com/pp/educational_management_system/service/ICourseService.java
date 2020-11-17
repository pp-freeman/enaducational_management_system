package com.pp.educational_management_system.service;

import com.pp.educational_management_system.domain.Course;
import com.pp.educational_management_system.domain.Semester;
import com.pp.educational_management_system.domain.TeacherInfo;

import java.util.List;

public interface ICourseService {
    //得到所有学生的年级
    public List<String> getAllGradeYear();
    //得到所有学生的学院
    public List<String> getAllAcademy();
    //得到所有的学期
    public List<Semester> getAllSemester();
    //得到所有班级
    public List<String> getAllClass();
    public List<TeacherInfo> getAllTeacher();
    //添加课程
    public int addCourse(Course course);
    //通过班级得到人数
    public int getClassCount(List<String> idList);
    //得到课程列表
    public List<Course> getCourseList(int pageStart,int pageSize,String coursetype,String beginacademy,String begingrade,String coursename);

    public int getCourseCount(String coursetype,String beginacademy,String begingrade,String coursename);
}
