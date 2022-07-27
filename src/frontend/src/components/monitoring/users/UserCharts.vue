<template>
  <div class="charts">
    <h3 class="title">Charts</h3>
    <Line id="chart" :chart-data="chartData" :chart-options="chartOptions"/>
  </div>
</template>

<script setup lang="ts">
import { Message } from '@/components/util/types/Types';
import { computed, onMounted, reactive, ref, watch } from 'vue';
import { Line } from 'vue-chartjs';
import { Chart as ChartJS, Title, Tooltip, Legend, CategoryScale, LinearScale, PointElement, LineElement } from 'chart.js';

ChartJS.register(Title, Tooltip, Legend, CategoryScale, LinearScale, PointElement, LineElement)


const props = defineProps<{
  userId: string,
  guildId: string
}>()

let messages: Message[];

// request message from api
try {
  if(props.guildId === "-1") {
    messages = await fetch("/api/messages/user/" + props.userId)
    .then(res => res.json());
  } else {
    messages = await fetch("/api/messages/" + props.userId + "/" + props.guildId)
    .then(res => res.json());
  }
  
} catch (error) {
  console.log(error);
}


const chartData = computed(() => {
  let labels : string[] = [];
  let data : number[] = [];
  
  let label = "";
  let counter = 0;
  let curDay: string = messages[0].timestamp.substring(8, 10);
  let addLastEntry = true;

  for(let i = 0; i < messages.length; i++) {
    label = messages[i].timestamp.substring(0, 10);
    
    if(messages[i].timestamp.substring(8, 10) === curDay) {
      counter++;
    } else {
      data.push(counter);
      labels.push(label);

      curDay = messages[i].timestamp.substring(8, 10);
      counter = 1;
      addLastEntry = false;
    }
  }

  if(addLastEntry) {
    data.push(counter);
    labels.push(label);
  }

  return {
    labels,
    datasets: [
      { 
        data,
        label: "Messages",
        borderColor: "#69b0da"
      }
    ]
  };
})

const chartOptions = {
  responsive: true,
  plugins: {
    title: {
      display: true,
      text: 'Messages per Day'
    },
    legend: {
      display: false
    },
    width: {
      value: 200
    }
  }
}

</script>


<style scoped>
.charts {
    padding: 0px;
}
h3 {
    margin: 0;
}
.charts > h3 {
  white-space: nowrap;
}

.accentColor {
  color: var(--accent-color);
}
</style>