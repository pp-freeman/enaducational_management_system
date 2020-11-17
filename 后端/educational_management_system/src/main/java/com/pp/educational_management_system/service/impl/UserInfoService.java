package com.pp.educational_management_system.service.impl;

import com.pp.educational_management_system.dao.IUserInfoDao;
import com.pp.educational_management_system.domain.StudentInfo;
import com.pp.educational_management_system.domain.TeacherInfo;
import com.pp.educational_management_system.domain.User;
import com.pp.educational_management_system.domain.UserInfo;
import com.pp.educational_management_system.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserInfoService implements IUserInfoService {

    @Autowired
    IUserInfoDao userInfoDao;
    @Override
    public List<UserInfo> getUserInfoList(int pageNum,int pageSize,String username,String department,String number) {
        List<StudentInfo> studentInfos = userInfoDao.getStudentInfoList(username,department,number);
        List<TeacherInfo> teacherInfos = userInfoDao.getTeacherInfoList(username,department,number);
        List<StudentInfo> studentInfos1 = new ArrayList<>();
        List<TeacherInfo> teacherInfos1 = new ArrayList<>();
        List<UserInfo> userInfos = new ArrayList<>();
        if(studentInfos.size()>=pageNum*pageSize){
            for(int i=(pageNum-1)*pageSize;i<pageNum*pageSize;i++){
                studentInfos1.add(studentInfos.get(i));
            }
        }else{
            if(studentInfos.size()>=(pageNum-1)*pageSize){
                for(int i=(pageNum-1)*pageSize;i<studentInfos.size();i++){
                    studentInfos1.add(studentInfos.get(i));
                }
                for(int i=0;i<pageNum*pageSize-studentInfos.size()&&i<teacherInfos.size();i++){
                    teacherInfos1.add(teacherInfos.get(i));
                }
            }else{
                for(int i=(pageNum-1)*pageSize-studentInfos.size();i<pageNum*pageSize-studentInfos.size()&&i<teacherInfos.size();i++){
                    teacherInfos1.add(teacherInfos.get(i));
                }
            }
        }
        for(int i=0;i<studentInfos1.size();i++){
            UserInfo userInfo = new UserInfo(studentInfos1.get(i).getSnum(),studentInfos1.get(i).getSname(),studentInfos1.get(i).getSsex(),studentInfos1.get(i).getSacademy(),studentInfos1.get(i).getSphone(),studentInfos1.get(i).getSid(),studentInfos1.get(i).getSpolotics());
            userInfos.add(userInfo);
        }
        for(int i=0;i<teacherInfos1.size();i++){
            UserInfo userInfo = new UserInfo(teacherInfos1.get(i).getTnum(),teacherInfos1.get(i).getTname(),teacherInfos1.get(i).getTsex(),teacherInfos1.get(i).getTdepartment(),teacherInfos1.get(i).getTphone(),teacherInfos1.get(i).getTid(),teacherInfos1.get(i).getTpolitics());
            userInfos.add(userInfo);
        }
        return userInfos;
    }

    @Override
    public int getUserInfoCount(String username,String department,String number) {
        return userInfoDao.getStudentInfoCount(username,department,number)+userInfoDao.getTeacherInfoCount(username,department,number);
    }


}
