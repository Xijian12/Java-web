<script setup>
import { useDark, useToggle } from '@vueuse/core'
import { defineComponent, onBeforeUnmount } from "vue";

const debounce = (callback, delay) => {
  let tid;
  return function (...args) {
    tid && clearTimeout(tid);
    tid = setTimeout(() => {
      callback(...args);
    }, delay);
  };
};

const _ResizeObserver = window.ResizeObserver;
window.ResizeObserver = class ResizeObserver extends _ResizeObserver {
  constructor(callback) {
    callback = debounce(callback, 20);
    super(callback);
  }
};

const darkModeObserver = useDark({
  selector: 'html',
  attribute: 'class',
  valueDark: 'dark',
  valueLight: 'light'
});

useDark({
  onChanged(dark) {
    useToggle(dark);
  }
});

onBeforeUnmount(() => {
  darkModeObserver.stop();
  // 如果需要，添加其他清理逻辑
});
</script>

<template>
  <header>
    <div class="wrapper">
      <router-view/>
    </div>
  </header>
</template>

<style scoped>
header {
  line-height: 1.5;
}
html,
body,
#app {
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100%;
}

.el-container,
.el-main {
  height: 100%;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
</style>
