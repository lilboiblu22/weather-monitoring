package edu.iu.habahram.weathermonitoring.model;

import org.springframework.stereotype.Component;

@Component
public class StatisticsDisplay implements Observer, DisplayElement{

    private float avgTemperature;
    private float maxTemperature;
    private float minTemperature;

    private Subject weatherData;

    public StatisticsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
    }


    @Override
    public String display() {
        String html = "";
        html += String.format("<div style=\"background-image: " +
                "url(/images/sky.webp); " +
                "height: 400px; " +
                "width: 647.2px;" +
                "display:flex;flex-wrap:wrap;justify-content:center;align-content:center;" +
                "\">");
        html += "<section>";
        html += String.format("<label>Average Temperature: %s</label><br />",avgTemperature);
        html += String.format("<label>Max Temperature: %s</label><br />", maxTemperature);
        html += String.format("<label>Min Temperature: %s</label>", minTemperature);
        html += "</section>";
        html += "</div>";
        return html;
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        float temperature2 = (float) Math.random();
        float temperature3 = (float) Math.random();
        this.avgTemperature = averageTemp(temperature,temperature2,temperature3);
        this.maxTemperature = maxTemp(temperature,temperature2,temperature3);
        this.minTemperature = minTemp(temperature,temperature2,temperature3);
    }

    @Override
    public String name() {
        return "Weather Statistics Display";
    }

    @Override
    public String id() {
        return "statistics-display";
    }

    public void subscribe() {
        weatherData.registerObserver(this);
    }

    public void unsubscribe() {
        weatherData.removeObserver(this);
    }

    public float averageTemp(float temp1, float temp2, float temp3) {
        return (temp1 + temp2 + temp3) / 3;
    }
    public float minTemp(float temp1, float temp2, float temp3) {
        return Math.min(temp1, Math.min(temp2, temp3));
    }
    public float maxTemp(float temp1, float temp2, float temp3) {
        return Math.max(temp1, Math.max(temp2, temp3));
    }
}
