<template>
    <div>
       <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>权限管理</el-breadcrumb-item>
            <el-breadcrumb-item>角色管理</el-breadcrumb-item>
        </el-breadcrumb>
        <!--卡片视图-->
        <el-card>
            <el-row>
                <el-col>
                    <el-button type="primary" @click="addRoleDialogVisible=true">添加角色</el-button>
                </el-col>
            </el-row>
            <!--角色列表区域-->
            <el-table :data="roleList" border stripe>
                <el-table-column type="expand">
                    <template slot-scope="scope">
                        <el-row :class="['dbbottom',i1===0?'bdtop':'','vcenter']" v-for="(item1,i1) in scope.row.children" :key="item1.id">
                            <el-col :span="5">
                                <el-tag>{{item1.permissionName}}</el-tag>
                                <i class='el-icon-caret-right'></i>
                            </el-col>
                            <el-col :span="19">
                                <el-row :class="['bdtop',i2===0?'':'bdtop','vcenter']" v-for="(item2,i2) in item1.children" :key="item2.id">
                                    <el-col :span="6">
                                        <el-tag type="success">{{item2.permissionName}}</el-tag>
                                        <i class='el-icon-caret-right'></i>
                                    </el-col>
                                    <el-col :span="18">
                                        <el-tag type="warning" v-for="(item3) in item2.children" :key="item3.id">
                                            {{item3.permissionName}}
                                        </el-tag>
                                    </el-col>
                                </el-row>
                            </el-col>
                        </el-row>
                    </template>
                </el-table-column>
                <el-table-column type="index"></el-table-column>
                <el-table-column label="角色名称" prop="roleName"></el-table-column>
                <el-table-column label="角色描述" prop="roleDesc"></el-table-column>
                <el-table-column label="操作" >
                    <template scope="scope">
                        <el-tooltip effect="dark" content="修改" placement="top-start" :enterable="false">
                            <el-button type="primary" icon="el-icon-edit" size="mini" @click="showEditRoleDialog(scope.row.id)"></el-button>
                        </el-tooltip>
                        <!-- 删除 -->
                        <el-tooltip effect="dark" content="删除" placement="top-start" :enterable="false">
                            <el-button type="danger" icon="el-icon-delete" size="mini" @click="deleteRole(scope.row.id)"></el-button>
                        </el-tooltip>
                        <!-- 权限 -->
                        <el-tooltip effect="dark" content="分配权限" placement="top-start" :enterable="false">
                        <el-button type="warning" icon="el-icon-setting" size="mini" @click="showSetRightDialog(scope.row)"></el-button>
                        </el-tooltip>
                    </template>
                </el-table-column>
            </el-table>
        </el-card>
        <!--添加角色对话框-->
        <el-dialog title="添加角色" :visible.sync="addRoleDialogVisible" width="50%" @close="addRoleDialogClosed">
                    <el-form :model="addFrom" :rules="addRoleFromRules" ref="addRoleFromRef" label-width="70px">
                        <el-form-item label="角色名" prop="roleName">
                            <el-input v-model="addFrom.roleName">
                            </el-input>
                        </el-form-item>
                        <el-form-item label="描述" prop="roleDesc">
                            <el-input v-model="addFrom.roleDesc">
                            </el-input>
                        </el-form-item>
                    </el-form>
                    <span slot="footer" class="dialog-footer">
                            <el-button type="primary" @click="addRole">确 定</el-button>
                            <el-button @click="addRoleDialogVisible = false">取 消</el-button>
                    </span>
        </el-dialog>
        <!--修改对话框-->
                <el-dialog title="修改角色信息" :visible.sync="editRoleDialogVisible" width="50%" @close="editRoleDialogClosed">
                    <el-form :model="editForm" :rules="editRoleFormRules" ref="editRoleFormRef" label-width="70px">
                        <!-- 用户名 -->
                        <el-form-item label="角色名" prop="roleName">
                        <el-input v-model="editForm.roleName"></el-input>
                        </el-form-item>
                        <!-- 密码 -->
                        <el-form-item label="描述" prop="roleDesc">
                        <el-input v-model="editForm.roleDesc"></el-input>
                        </el-form-item>
                    </el-form>
                    <span slot="footer" class="dialog-footer">
                        <el-button @click="editRoleDialogVisible = false">取 消</el-button>
                        <el-button type="primary" @click="editRoleInfo">确 定</el-button>
                    </span>
                </el-dialog>
        <!--分配权限的对话框-->
        <el-dialog title="分配权限" :visible.sync="setRightDialogVisible" width="50%"  @close="setRightDialogClosed">
                    <!--树形结构-->
                    <el-tree 
                    :data="permissionList" 
                    :props="treeProps"
                    show-checkbox
                    node-key="permissionName"
                    default-expand-all
                    :default-checked-keys="defKeys"
                    ref="treeRef"
                    >
                    </el-tree>
                    <span slot="footer" class="dialog-footer">
                            <el-button type="primary" @click="allotPermission">确 定</el-button>
                            <el-button @click="setRightDialogVisible= false">取 消</el-button>
                    </span>
        </el-dialog>

    </div>
</template>
<script>

export default {
    created(){
        this.getRoleList();
    },
    data(){
        return{
            //所有角色列表数据
            roleList:[],
            setRightDialogVisible:false,
            //所有权限的数据
            permissionList:[],
            //树形控件的属性绑定对象
            treeProps:{
                label:'permissionName',
                children:'children',
            },
            //默认选中的权限数组
            defKeys:[],
            roleId:'',
            addRoleDialogVisible:false,
            editRoleDialogVisible:false,
            addFrom:{
                roleName:'',
                roleDesc:'',
            },
            editForm:{},
            addRoleFromRules: {
                roleName: [
                    { required: true, message: '请输入角色名', trigger: 'blur' },
                    { min: 1, max: 6, message: '长度在 1 到 6 个字符', trigger: 'blur' }
                ],
                roleDesc: [
                    { required: true, message: '请输入角色描述', trigger: 'blur' },
                    { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
                ],
            },
            editRoleFormRules: {
                roleName: [
                    { required: true, message: '请输入角色名', trigger: 'blur' },
                    { min: 1, max: 6, message: '长度在 1 到 6 个字符', trigger: 'blur' }
                ],
                roleDesc: [
                    { required: true, message: '请输入角色描述', trigger: 'blur' },
                    { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
                ],
            },  
        }
    },
    methods:{
        //获取所有角色列表
        async getRoleList(){
            const {data:res} = await this.$http.get("role/getRoleList");
            if(res.state!=200){
                return this.$message.error("获取角色列表失败！");
            }
            this.roleList = res.data;
        },
        async showSetRightDialog(role){
            const {data:res} = await this.$http.get("role/getAllRoleList");
            this.roleId = role.id;
            if(res.state!=200){
                return this.$message.error("获取权限数据失败！");
            }
            this.permissionList = res.data;
            //递归获取三级节点
            this.getDefKeys(role,this.defKeys);
            this.setRightDialogVisible=true;
        },
        setRightDialogClosed(){
            this.defKeys = [];
        },
        getDefKeys(node,arr){
            if(!node.children){
                return arr.push(node.permissionName);
            }
            node.children.forEach(item=>
            this.getDefKeys(item,arr))
        },
        async allotPermission(){
            const keys =[
                ...this.$refs.treeRef.getCheckedKeys(),
                ...this.$refs.treeRef.getHalfCheckedKeys(),
            ]
            const idStr = keys.join(',');
            const {data:res} = await this.$http.get("role/updatePermission",{params:{
                roleId:this.roleId,
                idStr:idStr,
            }});
            if(res.state!=200){
                return this.$message.error("分配权限数据失败！");
            }
            this.$message.success("分配权限成功！");
            this.getRoleList();
            this.setRightDialogVisible=false;
            this.setRightDialogClosed();
        },
        addRoleDialogClosed(){
            this.$refs.addRoleFromRef.resetFields();// 重置表单项
        },
        editRoleDialogClosed(){
            this.$refs.editRoleFormRef.resetFields();
        },
        addRole(){
            this.$refs.addRoleFromRef.validate(async valid=>{
                if(!valid){
                    return;
                }else{
                    const {data:res} =  await this.$http.post("role/addRole",this.addFrom);
                    if(res.state!=200){
                        return this.$message.error("添加角色失败！");
                    }
                    this.$message.success("添加角色成功！");
                    this.addRoleDialogVisible=false;
                    this.addRoleDialogClosed();
                    this.getRoleList();
                }
            });
        },
        async showEditRoleDialog(id){
            const {data:res} = await this.$http.get("role/getUpdateRole?id="+id);
            this.editForm = res;
            this.editRoleDialogVisible = true;
        },
        editRoleInfo(){
            this.$refs.editRoleFormRef.validate(async valid=>{
                if(!valid){
                    return;
                }else{
                    const {data:res} = await this.$http.post("role/editRole",this.editForm);
                    if(res.state!=200){
                        return this.$message.error("修改角色失败！");
                    }
                    this.$message.success("修改角色成功！");
                     this.editRoleDialogVisible = false;
                     this.getRoleList();
                }
            });
        },
        async deleteRole(id){
            const confirmResult = await this.$confirm('此操作将永久删除该角色, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).catch(err => err)
            // 成功删除为confirm 取消为 cancel
            if(confirmResult!='confirm'){
                return this.$message.info("已取消删除");
            }
            const {data:res} = await this.$http.delete("role/deleteRole?id="+id);
            if (res.state != 200) {
                return this.$message.error("操作失败！！！");
            }
            this.$message.success("操作成功！！！");
            this.getRoleList();
        },
    }
}
</script>
<style lang="less" scoped>

/*
表格样式
*/
::v-deep .el-table th,
::v-deep .el-table td {
  text-align: center !important;
}

.el-tag{
    margin: 7px;
}

.bdtop{
    border-top: 1px solid #eee;
}

.dbbottom{
    border-bottom: 1px solid #eee;
}
.vcenter{
    display: flex;
    align-items: center;
}
</style>