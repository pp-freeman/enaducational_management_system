<template>
  <!--引入布局-->
  <el-container class="home-container">
    <el-header>
      <h2 style="color: white">Education System Manage</h2>
      <el-button type="info" @click="logout">安全退出</el-button>
    </el-header>
    <el-container>
      <!--侧边栏-->
      <el-aside :width="isCollapse?'64px':'200px'">
        <div class="toggle_button" @click="toggleCollapase">|||</div>
        <el-menu
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
          unique-opened
          :collapse="isCollapse"
          :collapse-transition="false"
          :router="true"
          :default-active="activePath"
        >
          <el-submenu :index="item.id+''" v-for="item in menuList" :key="item.id">
            <template slot="title">
              <i :class="iconsObject[item.title]"></i>
              <span>{{item.title}}</span>
            </template>
            <el-menu-item :index="it.path+''" v-for="it in item.menuList" :key="it.id" @click="saveNavState(it.path)">
              <template slot="title">
                <i :class="iconsObject[it.title]"></i>
                <span>{{it.title}}</span>
              </template>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>
<script>
export default {
  //onload事件
  created(){
    this.getMenuList();
    this.activePath = window.sessionStorage.getItem('activePath');
  },
  data(){
      return {
          //菜单列表
          menuList:[],
          isCollapse:false,
          activePath:'/home', //默认路径
          iconsObject:{
            '信息查看':'iconfont icon-ERP_xinxichakan',
            '开课':'iconfont icon-kaikeqingkuang',
            '行课':'iconfont icon-tongxueshangke-',
            '选课':'iconfont icon-xuanke',
            '成绩':'iconfont icon-chengji',
            '权限管理':'iconfont icon-quanxianguanli',
            '用户信息':'iconfont icon-chakan2',
            '我的信息':'iconfont icon-gerenzhongxinxinxichakantiyanbiaoxinxi',
            '课程':'iconfont icon-chakan',
            '教师学期课表':'iconfont icon-kebiaoxinxi',
            '学生学期课表':'iconfont icon-kebiaoxinxi',
            '我的学期课表':'iconfont icon-kebiaoxinxi',
            '班级学期课表':'iconfont icon-kebiaoxinxi',
            '可选课程':'iconfont icon-weiwancheng',
            '已选课程':'iconfont icon-yijingwanchengdexiangmu',
            '查看成绩':'iconfont icon-chakan1',
            '录入成绩':'iconfont icon-luru-xianxing',
            '用户管理':'iconfont icon-yonghuguanli',
            '角色管理':'iconfont icon-jiaoseguanli',
            '资源管理':'iconfont icon-ziyuanguanli',
          },
      }
  },
  methods: {
    logout() {
      //清理ssession
      window.sessionStorage.clear(); //清除
      this.$router.push({ path: "/login" });
    },
    //获取导航菜单方法
    async getMenuList(){
        const {data:res} = await this.$http.get("menus");
        if(res.flag!=200) return this.$message.error("获取列表失败");
        this.menuList = res.menus;
    },
    //控制伸缩
    toggleCollapase(){
      this.isCollapse = !this.isCollapse;
    },
    //保存路径
    saveNavState(activePath){
      window.sessionStorage.setItem('activePath',activePath);
      this.activePath = activePath;
    },
  },
};
</script>
<style scoped>
.el-header {
  background-color: #252425;
  display: flex;
  justify-content: space-between;
  padding-left: 2%;
  align-items: center;
}
.el-aside {
  background-color: #252425;
}
.el-main {
  background-color: #eaedf1;
}
.home-container {
  height: 100%;
}
.toggle_button{
  background: #4A5064;
  font-size: 10px;
  line-height: 24px;
  color: #fff;
  text-align: center;
  letter-spacing: 0.2em;
  cursor: pointer;
}
</style>