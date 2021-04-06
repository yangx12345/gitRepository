<template>
  <div class="dashboard-container">
    <el-row>
      <el-col :span="12"> <div ref="workStateChart" style="width:400px;height:400px" /></el-col>
      <el-col :span="12"> <div ref="sexChart" style="width:400px;height:400px" /></el-col>
    </el-row>
    <el-row>
      <el-col :span="12"> <div ref="tiptopDegreeChart" style="width:400px;height:400px" /></el-col>
      <el-col :span="12"> <div ref="jobLevelChart" style="width:400px;height:400px" /></el-col>
    </el-row>
    <el-row>

      <el-col :span="12"> <div ref="posChart" style="width:400px;height:400px" /></el-col>
    </el-row>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { getWorkStateChartData, getSexChartData, getTiptopDegreeChartData, getJobLevelChartData, getPosChartData } from '@/api/employee'
export default {
  data() {
    return {
      workStateChartOption: {
        title: {
          text: '公司员工在职人数',
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
            name: '访问来源',
            type: 'pie',
            radius: '50%',
            data: [],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      },
      sexOption: {
        title: {
          text: '公司员工性别分布',
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
            name: '访问来源',
            type: 'pie',
            radius: '50%',
            data: [
              { value: 1048, name: '搜索引擎' },
              { value: 735, name: '直接访问' }
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
      },
      tiptopDegreeChartOption: {
        title: { text: '公司学历分布', left: 'center' },
        xAxis: {
          type: 'category',
          data: ['博士', '硕士', '本科', '大专', '高中', '初中', '小学', '其他']
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          data: [],
          type: 'bar'
        }]
      },
      jobLevelChartOption: {
        title: { text: '职称分布', left: 'center' },
        legend: {
          top: 'bottom'
        },
        toolbox: {
          show: true,
          feature: {
            mark: { show: true },
            dataView: { show: true, readOnly: false },
            restore: { show: true },
            saveAsImage: { show: true }
          }
        },
        series: [
          {
            name: '职称分布',
            type: 'pie',
            radius: [50, 120],
            center: ['50%', '50%'],
            roseType: 'area',
            itemStyle: {
              borderRadius: 8
            },
            data: [
              { value: 40, name: 'rose 1' },
              { value: 38, name: 'rose 2' },
              { value: 32, name: 'rose 3' },
              { value: 30, name: 'rose 4' },
              { value: 28, name: 'rose 5' },
              { value: 26, name: 'rose 6' },
              { value: 22, name: 'rose 7' },
              { value: 18, name: 'rose 8' }
            ]
          }
        ]
      },
      posChartOption: {
        title: { text: '职位分布', left: 'center' },
        legend: {
          top: 'bottom'
        },
        toolbox: {
          show: true,
          feature: {
            mark: { show: true },
            dataView: { show: true, readOnly: false },
            restore: { show: false },
            saveAsImage: { show: true }
          }
        },
        series: [
          {
            name: '职位分布',
            type: 'pie',
            radius: [50, 120],
            center: ['50%', '50%'],
            roseType: 'area',
            itemStyle: {
              borderRadius: 8
            },
            data: [
              { value: 40, name: 'rose 1' },
              { value: 38, name: 'rose 2' },
              { value: 32, name: 'rose 3' },
              { value: 30, name: 'rose 4' },
              { value: 28, name: 'rose 5' },
              { value: 26, name: 'rose 6' },
              { value: 22, name: 'rose 7' },
              { value: 18, name: 'rose 8' }
            ]
          }
        ]
      }
    }
  },
  computed: {
    ...mapGetters([
      'name'
    ])
  },
  mounted() {
    this.initCharts()
  },
  methods: {
    getWorkStateChartData() {
      getWorkStateChartData().then((result) => {
        if (result.code === 1) {
          this.workStateChartOption.series[0].data = result.data
          const workStateChart = this.$echarts.init(this.$refs.workStateChart)
          workStateChart.setOption(this.workStateChartOption)
        }
      })
    },
    getSexChartData() {
      getSexChartData().then((result) => {
        if (result.code === 1) {
          this.sexOption.series[0].data = result.data
          const sexChart = this.$echarts.init(this.$refs.sexChart)
          sexChart.setOption(this.sexOption)
        }
      })
    },
    getTiptopDegreeChartData() {
      getTiptopDegreeChartData().then((result) => {
        if (result.code === 1) {
          this.tiptopDegreeChartOption.series[0].data = result.data
          const tiptopDegreeChart = this.$echarts.init(this.$refs.tiptopDegreeChart)
          tiptopDegreeChart.setOption(this.tiptopDegreeChartOption)
        }
      })
    },
    getJobLevelChartData() {
      getJobLevelChartData().then((result) => {
        if (result.code === 1) {
          this.jobLevelChartOption.series[0].data = result.data
          const jobLevelChart = this.$echarts.init(this.$refs.jobLevelChart)
          jobLevelChart.setOption(this.jobLevelChartOption)
        }
      })
    },
    getPosChartData() {
      getPosChartData().then((result) => {
        if (result.code === 1) {
          this.posChartOption.series[0].data = result.data
          const posChart = this.$echarts.init(this.$refs.posChart)
          posChart.setOption(this.posChartOption)
        }
      })
    },
    initCharts() {
      this.getWorkStateChartData()
      this.getSexChartData()
      this.getTiptopDegreeChartData()
      this.getJobLevelChartData()
      this.getPosChartData()
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
