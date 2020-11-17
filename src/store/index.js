// 导入 vue
import Vue from 'vue'
// 导入 vuex
import Vuex from 'vuex'
// 使用vuex
Vue.use(Vuex);
 
//创建vue实例
const store = new Vuex.Store({
	state:{
		permission:[]
	},
	mutations:{
		SET_PERMISSION(state,permission){//permission 为传入的权限标识集合
		    // 传入的权限集合 赋值给状态中的 permission
			state.permission = permission;
		}
	},
	actions:{// 官方不推荐直接修改mutation， 可使用actions来提交 mutation
		
		SET_PERMISSION(context,permission){
			// 提交到 mutation 中的 SET_PERMISSION 函数
			context.commit("SET_PERMISSION",permission);
		}
	}
	
})
 
//导出store
export default store