package com.pp.educational_management_system.dao;

import com.pp.educational_management_system.domain.StudentInfo;
import com.pp.educational_management_system.domain.TeacherInfo;
import com.sun.jdi.event.StepEvent;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserInfoDao {

    //查询学生列表
    public List<StudentInfo> getStudentInfoList(String username,String department,String number);
    //查询老师列表
    public List<TeacherInfo> getTeacherInfoList(String username,String department,String number);
    //查询学生总数
    public int getStudentInfoCount(String username,String department,String number);
    //查询老师总数
    public int getTeacherInfoCount(String username,String department,String number);
}
