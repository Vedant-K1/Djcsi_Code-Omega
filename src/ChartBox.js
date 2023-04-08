import React from "react";
import { Line } from "react-chartjs-2";
import Chart from 'chart.js/auto'

function ChartBox({ chartData }) {
  return <Line data={chartData} />;
}

export default ChartBox;