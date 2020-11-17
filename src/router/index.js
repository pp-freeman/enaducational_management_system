import Vue from 'vue'
import VueRouter from 'vue-router'
// 引入login组件
import Login from '../components/Login.vue'
//引入home组件
import Home from '../components/Home.vue'  
import Welcome from '../components/Welcome.vue'
import UserList from '../components/admin/UserList.vue'
import UserMan from '../components/admin/UserMan.vue'
import RoleMan from '../components/admin/Roles.vue'
import PermissionMan from '../components/admin/Permission.vue'
import Course from '../components/admin/Course.vue'



Vue.use(VueRouter)



const routes = [
  {
    path: "/",
    redirect: "/login"
  },
  {
    path: "/login",
    component: Login
  },
  {
    path:"/home",
    component: Home,
    redirect:"/welcome",
    children:
    [
      {
        path: "/welcome",
        component: Welcome,
      },
      {
        path: "/userList",
        component: UserList,
      },
      {
        path: "/userMan",
        component: UserMan,
      },
      {
        path: "/roleMan",
        component: RoleMan,
      },
      {
        path: "/permissionMan",
        component: PermissionMan,
      },
      {
        path: "/showCourse",
        component: Course,
      }
    ]
  },
]

const router = new VueRouter({
  routes
})

//挂载路由导航守卫
router.beforeEach((to,from,next)=>{
  //to 将要访问
  //from 从哪儿访问
  //next 接着干的事情 重定向，如果为空继续访问to的路径
  if(to.path == '/login') return next();
  //获取user
  const userFlag =  window.sessionStorage.getItem("user"); //取出当前用户
  if(!userFlag) return next('/login');
  next(); //符合要求
})

export default router
