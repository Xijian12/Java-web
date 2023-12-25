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
  name: "BookAnalyse",
  mounted() {
    this.init();
  },
  methods: {
    init(){
      let option1 = {
        title: {
          text: '不同类型藏书量分析',
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
            name: '藏书量',
            type: 'pie',
            radius: '50%',
            data: [
              { value: 1048, name: '小说' },
              { value: 735, name: '教育' },
              { value: 580, name: '文学' },
              { value: 484, name: '程序设计' },
              { value: 300, name: '数据库' }
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
          text: '各个书架藏书量分析',
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
            name: '藏书量',
            type: 'pie',
            radius: '50%',
            data: [
              { value: 500, name: '01号书架' },
              { value: 735, name: '02号书架' },
              { value: 300, name: '03号书架' },
              { value: 200, name: '04号书架' },
              { value: 600, name: '05号书架' }
            ],
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
