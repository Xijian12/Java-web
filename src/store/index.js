// 引入 createApp 和 createStore 函数
import { createApp } from 'vue';
import { createStore } from 'vuex';

// 创建 store 实例
const store = createStore({
  state: {
    Product: [],
    CartAll: [],
    sumPrice: 0,
    sumNumber: 0,
    personalID: [],
    payProduct: [],
    youMoney: 0,
    cookie: ""
  },
  mutations: {
    addToCart(state, value) {
      state.CartAll.push(value);
    },
    addPrice(state, value) {
      state.sumPrice += value;
    },
    jianPrice(state, value) {
      state.sumPrice -= value;
    },
    allChoice(state, value) {
      state.CartAll.forEach((todo) => {
        todo.car_choice = value;
        if (value === true) {
          state.sumPrice += todo.product_price * 10000 * todo.product_much;
          state.sumNumber = state.CartAll.length;
        } else {
          state.sumPrice = 0;
          state.sumNumber = 0;
        }
      });
    },
    ifChoice(state, value) {
      state.CartAll.forEach((todo) => {
        if (todo.id === value) {
          todo.car_choice = !todo.car_choice;
          if (todo.car_choice === true) {
            state.sumNumber += 1;
          } else {
            state.sumNumber -= 1;
          }
        }
      });
    },
    deleteId(state, value) {
      state.CartAll = state.CartAll.filter((todo) => {
        return todo.id !== value;
      });
      state.sumPrice = 0;
      state.sumNumber = 0;
    },
    clearAllId(state) {
      state.CartAll = state.CartAll.filter((todo) => {
        return !todo.car_choice;
      });
      state.sumPrice = 0;
      state.sumNumber = 0;
    },
    addID(state, value) {
      state.personalID.push(value);
      state.youMoney = value.youMoney;
    },
    pay1(state, value) {
      state.CartAll = value;
      state.sumPrice = 0;
      state.sumNumber = 0;
    },
    pay2(state, value) {
      state.Product = [];
      if (value.length) {
        state.payProduct.push(value);
        for (let i = 0; i < state.payProduct.length; i++) {
          if (state.payProduct[i].length >= 1) {
            for (let j = 0; j < state.payProduct[i].length; j++) {
              state.Product.push(state.payProduct[i][j]);
            }
          }
        }
      } else {
        alert("请点击商品");
      }
    },
    gaifuqianle(state, value) {
      state.youMoney -= value;
    },
    addyouMoney(state, value) {
      state.youMoney += value;
    },
    addCookie(state, value) {
      state.cookie = value;
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
