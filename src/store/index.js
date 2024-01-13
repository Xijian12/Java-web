// 引入 createApp 和 createStore 函数
import { createApp } from 'vue';
import { createStore } from 'vuex';

// 创建 store 实例
const store = createStore({
  state: {
    personalID:[{
      email:'524535336@qq.com',
      expire:'',
      role:'',
      token:'',
      username:"admin",
      userPic:0,
    }],
  },
  mutations: {
    addID(state, value){
      state.personalID=[];
      state.personalID.push(value);
    },
    addUserPic(state, value){
      state.personalID[0].userPic=value;
    }
  },
  actions: {
    addToCart(context, value) {
      context.commit("addToCart", value);
    },
    addID(context, value) {
      context.commit("addID", value);
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
