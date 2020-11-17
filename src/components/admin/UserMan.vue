<template>
    <div>
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>权限管理</el-breadcrumb-item>
            <el-breadcrumb-item>用户管理</el-breadcrumb-item>
        </el-breadcrumb>
        <!--用户列表主体部分-->
        <el-card>
            <el-row :gutter="25">
                <el-col :span="10">
                    <!--搜索区域-->
                    <el-input placeholder="请输入用户名" v-model="queryInfo.username" clearable @clear="getUserList">
                        <el-button slot="append" icon='el-icon-search' @click="getUserList"></el-button>
                    </el-input>
                </el-col>
                <el-col :span="4">
                    <el-select placeholder="请选择角色" v-model="queryInfo.roleName"  @change="getUserList" clearable @clear="getUserList">
                        <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                        </el-option>
                    </el-select>
                </el-col>
                <el-col :span="4">
                    <el-button type="primary" @click="addDialogVisible = true">添加用户</el-button>
                </el-col>
            </el-row>
            <!--用户列表-->
            <el-table :data="userList" border stripe>
                <el-table-column type="index"></el-table-column>
                <el-table-column label="用户名" prop="username"></el-table-column>
                <el-table-column label="密码" prop="password"></el-table-column>
                <el-table-column label="角色" prop="role"></el-table-column>
                <el-table-column label="状态" prop="state">
                    <template scope="scope">
                        <el-switch  :active-value="1" :inactive-value="0" v-model="scope.row.state" @change="userStateChange(scope.row)"></el-switch>
                    </template>
                </el-table-column>
                <el-table-column label="操作" >
                    <template scope="scope">
                        
                        <el-tooltip effect="dark" content="修改信息" placement="top-start" :enterable="false">
                            <el-button type="primary" icon="el-icon-edit" size="mini" @click="showEditDialog(scope.row.id)"></el-button>
                        </el-tooltip>
                        <!-- 删除 -->
                        <el-tooltip effect="dark" content="删除信息" placement="top-start" :enterable="false">
                        <el-button type="danger" icon="el-icon-delete" size="mini" @click="deleteUser(scope.row.id)"></el-button>
                        </el-tooltip>
                        <!-- 权限 -->
                        <el-tooltip effect="dark" content="分配角色" placement="top-start" :enterable="false">
                        <el-button type="warning" icon="el-icon-setting" size="mini" @click="setRole(scope.row)"></el-button>
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

                <!--新增用户-->
                <el-dialog title="添加用户" :visible.sync="addDialogVisible" width="50%" @close="addDialogClosed">
                    <el-form :model="addFrom" :rules="addFromRules" ref="addFromRef" label-width="70px">
                        <el-form-item label="用户名" prop="username">
                            <el-input v-model="addFrom.username">
                            </el-input>
                        </el-form-item>
                        <el-form-item label="密码" prop="password">
                            <el-input v-model="addFrom.password">
                            </el-input>
                        </el-form-item>
                    </el-form>
                    <span slot="footer" class="dialog-footer">
                            <el-button type="primary" @click="addUser">确 定</el-button>
                            <el-button @click="addDialogVisible = false">取 消</el-button>
                    </span>
                </el-dialog>

                <!--修改对话框-->
                <el-dialog title="修改用户信息" :visible.sync="editDialogVisible" width="50%" @close="editDialogClosed">
                    <el-form :model="editForm" :rules="editFormRules" ref="editFormRef" label-width="70px">
                        <!-- 用户名 -->
                        <el-form-item label="用户名" prop="username">
                        <el-input v-model="editForm.username" disabled></el-input>
                        </el-form-item>
                        <!-- 密码 -->
                        <el-form-item label="密码" prop="password">
                        <el-input v-model="editForm.password"></el-input>
                        </el-form-item>
                    </el-form>
                    <span slot="footer" class="dialog-footer">
                        <el-button @click="editDialogVisible = false">取 消</el-button>
                        <el-button type="primary" @click="editUserInfo">确 定</el-button>
                    </span>
                </el-dialog>
                <!--分配角色-->
                <!--修改对话框-->
                <el-dialog title="分配角色" :visible.sync="setRoleDialogVisible" width="50%" >
                    <div>
                        <p>当前用户：{{userInfo.username}}</p>
                        <p>当前的角色：{{userInfo.role}}</p>
                        <p>
                            分配新角色：
                            <el-select placeholder="请选择" v-model="selectedRoleId">
                                <el-option
                                v-for="item in rolesList"
                                :key="item.id"
                                :label="item.roleName"
                                :value="item.id">
                                </el-option>
                            </el-select>
                        </p>
                    </div>
                    <span slot="footer" class="dialog-footer">
                        <el-button @click="setRoleDialogVisible = false">取 消</el-button>
                        <el-button type="primary" @click="saveRoleInfo">确 定</el-button>
                    </span>
                </el-dialog>
            </div>
        </el-card>
    </div>
</template>
<script>
export default {
    created(){
        this.getUserList();
    },
    data(){
        return {
            //查询信息
            queryInfo:{
                username:"",
                roleName:"",
                pageNum:1,
                pagesize:8,
            },
            rolesList:[],//所有角色数据
            userInfo:{},//需要被分配角色的用户信息
            userList:[],//用户列表
            total:0,//总记录数
            addDialogVisible:false,//对话框状态
            addFrom:{
                username:'',
                password:'',
            },
            //修改用户信息
            editForm:{},
            editDialogVisible:false,//对话框状态
            addFromRules: {
                username: [
                    { required: true, message: '请输入用户名', trigger: 'blur' },
                    { min: 5, max: 8, message: '长度在 5 到 8 个字符', trigger: 'blur' }
                ],
                password: [
                    { required: true, message: '请输入密码', trigger: 'blur' },
                    { min: 6, max: 8, message: '长度在 6 到 8 个字符', trigger: 'blur' }
                ],
            },  
            editFormRules:{
                password: [
                    { required: true, message: '请输入密码', trigger: 'blur' },
                    { min: 6, max: 8, message: '长度在 6 到 8 个字符', trigger: 'blur' }
                ],
            },  

            options: [{
            value: '系统管理员',
            label: '系统管理员'
            }, {
            value: '学生',
            label: '学生'
            }, {
            value: '老师',
            label: '老师'
            }],
            setRoleDialogVisible:false,
            //选中的角色id
            selectedRoleId:'',
        }

    },
    methods:{
        //获取所有用户
        async getUserList(){
            const {data:res} = await this.$http.get("user/userList",{params:{
                username:this.queryInfo.username,
                roleName:this.queryInfo.roleName,
                pageNum:this.queryInfo.pageNum,
                pageSize:this.queryInfo.pagesize,
            }});
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
        async userStateChange(userInfo){
            const {data:res} =await this.$http.get("user/updateState",{params:{
                id:userInfo.id,
                state:userInfo.state,
            }});
            console.log(res);
            if(res!="success"){
                userInfo.id = !userInfo.id
                return this.$message.error("操作失败！！！");
            }else{
                this.$message.success("操作成功！！！");
            }
        },
        addDialogClosed(){
            this.$refs.addFromRef.resetFields();// 重置表单项
        },
        editDialogClosed(){
            this.$refs.addFromRef.resetFields();// 重置表单项
        },
        addUser(){
             this.$refs.addFromRef.validate(async valid=>{
                 if(!valid){
                     return;
                 }else{
                     const {data:res} = await this.$http.post("user/addUser",this.addFrom);
                     if(res!="success"){
                         return this.$message.error("操作失败！！！");
                     }else{
                        this.$message.success("操作成功！！！");
                     }
                     this.addDialogVisible = false;
                     this.getUserList();
                 }
             });
        },
        async deleteUser(id){
            const confirmResult = await this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).catch(err => err)
            // 成功删除为confirm 取消为 cancel
            if(confirmResult!='confirm'){
                return this.$message.info("已取消删除");
            }
            const {data:res} = await this.$http.delete("user/deleteUser?id="+id);
            if (res != "success") {
                return this.$message.error("操作失败！！！");
            }
            this.$message.success("操作成功！！！");
            this.getUserList();
        },
        async showEditDialog(id){
            const {data:res} = await this.$http.get("user/getUpdateUser?id="+id);
            this.editForm = res;
            this.editDialogVisible = true;
        },
        editUserInfo(){
            this.$refs.editFormRef.validate(async valid=>{
                if(!valid){
                    return;
                }else{
                    const {data:res} = await this.$http.post("user/editUser",this.editForm);
                    if(res!="success"){
                         return this.$message.error("操作失败！！！");
                     }else{
                        this.$message.success("操作成功！！！");
                     }
                     this.editDialogVisible = false;
                     this.getUserList();
                }
            });
        },
        //分配角色
        async setRole(userInfo){
            this.userInfo = userInfo;

            const {data:res} = await this.$http.get("role/getAllRole");
            if(res.state!=200){
                return this.$message.error("获取角色数据失败！");
            }
            this.rolesList = res.data;
            this.setRoleDialogVisible=true;
        },
        async saveRoleInfo(){
            if(!this.selectedRoleId){
                 return this.$message.error("请选择要分配的角色！");
            }
            const {data:res} = await this.$http.get("role/updateRole",{params:{
                userId:this.userInfo.id,
                roleId:this.selectedRoleId,
            }});
            if(res.state!=200){
                return this.$message.error("更新角色失败！");
            }
            this.$message.success("更新角色成功！");
            this.getUserList();
            this.setRoleDialogVisible=false;
        }

    },
}
</script>
<style scoped>
/*
表格样式
*/
::v-deep .el-table th,
::v-deep .el-table td {
  text-align: center !important;
}

.el-table{
    margin-top: 15px;
    font-size:13px;
    min-width: 350px;
}
</style>