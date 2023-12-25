<template>
  <el-card>
    <el-row>
      <el-col :span="12">
        <div id="div1" style="height: 75vh;width:40vw;"></div>
      </el-col>
      <el-col :span="12">
        <div id="div2" style="height: 75vh;width:40vw;"></div>
      </el-col>
    </el-row>
  </el-card>
</template>

<script>
export default {
  name: "UserAnalyse",
  mounted() {
    this.init();
  },
  methods: {
    init(){
      let option1 = {
        title: {
          text: '性别分析',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          left: 'left'
        },
        series: [
          {
            name: '性别',
            type: 'pie',
            radius: '50%',
            data: [
              { value: 1048, name: '男读者' },
              { value: 735, name: '女读者' },
            ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      };
      let option2 = {
        title: {
          text: '年龄分析',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          left: 'left'
        },
        series: [
          {
            name: '年龄',
            type: 'pie',
            radius: '50%',
            data: [
              { value: 1048, name: '15岁以下' },
              { value: 735, name: '15-20岁' },
              { value: 635, name: '20-30岁' },
              { value: 535, name: '30-40岁' },
              { value: 435, name: '40-50岁' },
              { value: 335, name: '50-60岁' },
              { value: 135, name: '60岁以上' }
            ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      };
      let myChart = this.$echarts.init(document.getElementById('div1'));
      window.onresize = myChart.resize;
      myChart.setOption(option1);
      let myChart2 = this.$echarts.init(document.getElementById('div2'));
      window.onresize = myChart2.resize;
      myChart2.setOption(option2);
    }
  }
}
</script>

<style scoped>

</style>
