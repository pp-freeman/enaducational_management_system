<template>
    <div>
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>权限管理</el-breadcrumb-item>
            <el-breadcrumb-item>资源管理</el-breadcrumb-item>
        </el-breadcrumb>
        <!--资源列表主体部分-->
        <el-card>
            <el-row>
                <el-col :span="4">
                    <el-select placeholder="资源层级" v-model="level"  @change="getPermisisonList" clearable @clear="getPermisisonList">
                        <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                        </el-option>
                    </el-select>
                </el-col>
            </el-row>
            <el-table :data="permissionList" border stripe>
                <el-table-column type="index"></el-table-column>
                <el-table-column label="资源名" prop="permissionName"></el-table-column>
                <el-table-column label="路径" prop="url"></el-table-column>
            </el-table>
        </el-card>
    </div>
</template>
<script>
export default {
    created(){
        this.getPermisisonList();
   },
   data(){
       return{
          permissionList:[],//资源数据 
          level:'',
          options: [{
            value: '1',
            label: '1级'
            }, {
            value: '2',
            label: '2级'
            }, {
            value: '3',
            label: '3级'
            }],
       }
   },
   methods:{
        async getPermisisonList(){
            const {data:res} = await this.$http.get("permission/getAllPermission",{params:{
                level:this.level,
            }});
            this.permissionList = res.data;
        },
   },
}
</script>
<style scoped>

</style>