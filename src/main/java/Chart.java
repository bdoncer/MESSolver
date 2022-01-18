import javafx.scene.chart.*;

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
        chart.setPrefHeight(200);
        chart.setPrefWidth(200);
        chart.setMinHeight(1);
        chart.setMinWidth(1);
        chart.setLegendVisible(false);
        chart.setStyle("-fx-font-size: " + 10 + "px;");
        chart.setCreateSymbols(false);
    }
    public void addData(String x,Number y){
        series.getData().add(new XYChart.Data<>(x,y));
    }
    public LineChart getChart(){
        return chart;
    }

}