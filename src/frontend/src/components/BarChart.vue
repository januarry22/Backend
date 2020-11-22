<script>
  import { Bar } from 'vue-chartjs'
  export default {
    extends: Bar,
    props: {
      chartData: {
        type: Array | Object,
        required: false
      },
      chartLabels: {
        type: Array,
        required: true
      }
    },
    data () {
      return {
        options: {
          scales: {

          },
          legend: {
            display: false
          },
          responsive: true,
          maintainAspectRatio: false
        }
      }
    },
    mounted () {
      this.renderChart({
        labels: this.chartLabels,
        datasets: [
          {
            label: 'downloads',
            borderColor:['white','red','blue'],
            backgroundColor:['white','red','blue'],
            pointBackgroundColor: ['white','red','blue'],
            pointBorderColor:['white','red','blue'],
            data: this.chartData
          }
        ]
      }, this.options)
    }
  }
</script>