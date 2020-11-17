<template>
  <div class="login_container">
    <img :src="imgSrc" width="100%" height="100%" />
    <!--登录块-->
    <div class="login_box">
      <!--表单区域-->
      <el-form
        ref="loginFromRef"
        :rules="loginRules"
        :model="loginFrom"
        class="login_from"
        label-width="0px"
      >
        <h3 class="headline">教学管理系统</h3>
        <!--用户名-->
        <el-form-item prop="username">
          <el-input
            v-model="loginFrom.username"
            prefix-icon="iconfont icon-denglu"
            placeholder="用户名"
          ></el-input>
        </el-form-item>
        <!--密码-->
        <el-form-item prop="password">
          <el-input
            v-model="loginFrom.password"
            prefix-icon="iconfont icon-mima"
            type="password"
            placeholder="密码"
          ></el-input>
        </el-form-item>

        <!--按钮-->
        <el-form-item class="btn">
          <el-button type="primary" @click="login">登录</el-button>
          <!-- <el-button type="info" @click="resetLoginForm">重置</el-button> -->
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      //表单数据
      loginFrom: {
        username: "admin",
        password: "123456",
      },
      //验证对象
      loginRules: {
        //校验用户名
        username: [
          { required: true, message: "请输入用户名称", trigger: "blur" }, //必填项验证
          {
            min: 5,
            max: 12,
            message: "长度在 5 到 12 个字符",
            trigger: "blur",
          }, //验证长度
        ],
        //校验密码
        password: [
          { required: true, message: "请输入用户密码", trigger: "blur" }, //必填项验证
          {
            min: 6,
            max: 10,
            message: "长度在 6 到 10 个字符",
            trigger: "blur",
          }, //验证长度
        ],
      },
      imgSrc: require("../assets/bg.png"),
    };
  },
  methods: {
    resetLoginForm() {
      //重置表单内容
      this.$refs.loginFromRef.resetFields();
    },
    login() {
      //登录请求
      this.$refs.loginFromRef.validate(async (validate) => {
        //判断是否验证成功
        if (!validate) return;
        const { data: res } = await this.$http.post(
          "home/login",
          this.loginFrom
        );
        
        if (res.status == "200") {
          this.$message.success("登陆成功");
          window.sessionStorage.setItem("user", res.username);
          window.sessionStorage.setItem("token", res.token);
          //跳转页面
          this.$router.push({ path: "/home" });
        } else {
          this.$message.error("用户名或密码错误");
        }
      });
    },
  },
};
</script>
<style lang="less" scoped>
.login_container {
  background-color: #2b4b6b;
  height: 100%;
}
.login_box {
  background: rgba(78, 102, 112, 0.1);
  width: 450px;
  height: 330px;
  border-radius: 5px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}
.btn {
  width: 100%;
}
.login_from {
  position: absolute;
  bottom: 0%;
  width: 100%;
  padding: 0 10px;
  box-sizing: border-box;
}
.el-button {
  margin-left: 10%;
  width: 80%;
}
.el-form-item {
  margin-left: 10%;
  width: 80%;
}
.headline {
  font-size: 20px;
  color: white;
  margin-left: 35%;
}
</style>