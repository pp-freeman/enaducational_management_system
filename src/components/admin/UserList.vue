<template>
    <div>
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>信息查看</el-breadcrumb-item>
            <el-breadcrumb-item>用户信息</el-breadcrumb-item>
        </el-breadcrumb>
        <!--用户列表主体部分-->
        <el-card>
            <el-row :gutter="25">
                <el-col :span="8">
                    <!--搜索区域-->
                    <el-input placeholder="请输入学号" v-model="queryInfo.number" clearable @clear="getUserList">
                        <el-button slot="append" icon='el-icon-search' @click="getUserList"></el-button>
                    </el-input>
                </el-col>
                <el-col :span="8">
                    <!--搜索区域-->
                    <el-input placeholder="请输入姓名" v-model="queryInfo.username" clearable @clear="getUserList">
                        <el-button slot="append" icon='el-icon-search' @click="getUserList"></el-button>
                    </el-input>
                </el-col>
                <el-col :span="4">
                    <el-select placeholder="请选择学院" v-model="queryInfo.department"  @change="getUserList" clearable @clear="getUserList">
                        <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                        </el-option>
                    </el-select>
                </el-col>
            </el-row>
            <!--用户列表-->
            <el-table :data="userList" border stripe>
                <el-table-column type="index"></el-table-column>
                <el-table-column label="学号/职工号" prop="num"></el-table-column>
                <el-table-column label="姓名" prop="name"></el-table-column>
                <el-table-column label="学院" prop="academy"></el-table-column>
                <el-table-column label="联系电话" prop="phone"></el-table-column>
                <el-table-column label="身份证号" prop="id"></el-table-column>
                <el-table-column label="政治面貌" prop="polotics"></el-table-column>
                <el-table-column label="操作" >
                    <template>
                        <el-tooltip effect="dark" content="查看信息" placement="top-start" :enterable="false">
                            <KtButton size="mini" icon="el-icon-view"   perms='show' type="primary"/>
                        </el-tooltip>
                        
                        <el-tooltip effect="dark" content="修改信息" placement="top-start" :enterable="false">
                            <KtButton size="mini" icon="el-icon-edit" perms='edit' type="danger"/>
                        </el-tooltip>
                    </template>
                </el-table-column>
            </el-table>
            <!--分页组件-->
            <div>
                <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="queryInfo.pageNum"
                :page-sizes="[3, 5, 8, 100]"
                :page-size="queryInfo.pagesize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
                </el-pagination>
            </div>
        </el-card>
    </div>
</template>
<script>
export default {
    created(){
        this.getUserList();
        this.getPermission();
    },
    data(){
        return {
            //查询信息
            queryInfo:{
                number:"",
                department:"",
                username:"",
                pageNum:1,
                pagesize:8,
            },
            userList:[],//用户列表
            total:0,//总记录数
            options: [{
            value: '计算机科学学院',
            label: '计算机科学学院'
            }, {
            value: '数学学院',
            label: '数学学院'
            }, {
            value: '英语学院',
            label: '英语学院'
            }],
            }

    },
    methods:{
        //获取所有用户
        async getUserList(){
            const {data:res} = await this.$http.post("user/userInfoList",this.queryInfo);
            // await this.$http.get("user/userInfoList",{params:this.queryInfo});
            this.userList=res.userInfos;
            this.total=res.number;
        },
        handleSizeChange(newSize){
            this.queryInfo.pagesize = newSize;
            this.getUserList();
        },
        handleCurrentChange(newNum){
            this.queryInfo.pageNum = newNum;
            this.getUserList();
        },
        hasPerms: function (perms) {
        // 根据权限标识和外部指示状态进行权限判断
            return hasPermission(perms) 
        },
        getPermission(){ // 模拟接口 获取 权限数据集合
            let perms = ['edit','delete','show'];
            this.$store.dispatch("SET_PERMISSION",perms);
        }
    },
}
</script>
<style scoped>
.el-breadcrumb{
    margin-bottom:15px;
    font-size:17px;
}
/*
表格样式
*/
::v-deep .el-table th,
::v-deep .el-table td {
  text-align: center !important;
}

</style>