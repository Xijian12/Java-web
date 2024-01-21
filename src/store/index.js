// 引入 createApp 和 createStore 函数
import { createApp } from 'vue';
import { createStore } from 'vuex';
import {useStorage} from '@vueuse/core';
const sessionStorageDate = useStorage("user", "session")
const sessionStorageDateName = useStorage("username", "游客")
// 创建 store 实例
const store = createStore({
  state: {
    personalID:[{
      email:sessionStorageDate.value,
      expire:'',
      role:'',
      token:'',
      username:sessionStorageDateName.value,
      points:0,
    }],
  },
  mutations: {
    addID(state, value){
      state.personalID=[];
      state.personalID.push(value);
    },
    addUserPic(state, value){
      state.personalID[0].points=value;
    },
    addUserName(state, value){
      state.personalID[0].username=value;
    }
  },
  actions: {
    addID(context, value) {
      context.commit("addID", value);
    },
    addUserPic(context, value) {
      context.commit("addUserPic", value);
    },
    addUserName(context, value) {
      context.commit("addUserName", value);
    }
  },
  getters: {
    sumPriceEnd(state) {
      return state.sumPrice * 10000;
    }
  }
});

// 将 store 实例挂载到 app 上
createApp().use(store);

// 导出 store
export default store;
