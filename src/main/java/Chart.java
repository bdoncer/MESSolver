import javafx.scene.chart.*;

import java.awt.*;

public class Chart {
    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    final LineChart<String, Number> chart = new LineChart<>(xAxis, yAxis);
    XYChart.Series<String, Number> series = new XYChart.Series<>();
    public Chart(String xLabel, String yLabel){
        xAxis.setLabel(xLabel);
        xAxis.setAnimated(false);
        yAxis.setLabel(yLabel);
        yAxis.setAnimated(false);
        chart.setAnimated(false);
        chart.getData().add(series);
        chart.setPrefHeight(700);
        chart.setPrefWidth(200);
        chart.setMinHeight(1);
        chart.setMinWidth(1);
        chart.setLegendVisible(false);
        chart.setStyle("-fx-font-size: " + 13 + "px;");
        chart.setCreateSymbols(false);
        series.getNode().lookup(".chart-series-line").setStyle("-fx-stroke: #ea88dc;");
    }
    public void addData(String x,Number y){
        series.getData().add(new XYChart.Data<>(x,y));
    }
    public LineChart getChart(){
        return chart;
    }

}