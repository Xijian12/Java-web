<template>
  <el-card>
    <el-row>
      <el-col :span="11">
        <div id="div1" style="height: 75vh;width:40vw;"></div>
      </el-col>
      <el-col :span="13">
        <div id="div2" style="height: 75vh;width:40vw;"></div>
      </el-col>
    </el-row>
  </el-card>
</template>

<script>
export default {
  name: "BorrowAnalyse",
  mounted() {
    this.init();
  },
  methods: {
    init(){
      let option1 = {
        title: {
          text: '不同类型借阅量分析',
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
            name: '借书量',
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
          text: '本年度每月借阅量分析',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        xAxis: {
          type: 'category',
          data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月','8月', '9月', '10月', '11月', '12月']
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: [150, 230, 224, 218, 135, 147, 260,85,456,562,125,412],
            type: 'line'
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
