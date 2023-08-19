package org.api.binance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class FindLowHighPoints {
    public static void main(String[] args) {
        String csvFile = "/Users/Rene/Documents/rene/study/rsi_1m.csv";
        String line;
        String cvsSplitBy = ",";

        List<Date> times = new ArrayList<>();
        List<Double> prices = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                Date date = dateFormat.parse(data[0]);
                times.add(date);
                prices.add(Double.parseDouble(data[1]));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        // 데이터 읽기 후에 리스트 뒤집기
        // Collections.reverse(times);
        // Collections.reverse(prices);



        TimeSeries series = new TimeSeries("가격");
        TimeSeries lowHighSeries = new TimeSeries("저점 및 고점");

        for (int i = 0; i < prices.size(); i++) {
            series.add(new Minute(times.get(i)), prices.get(i));
        }

        int windowSize = 40; // 윈도우 크기
        double cumulativeReturn = 1.0;
        boolean inLongPosition = false;
        double lastBuyPrice = 0.0;

        for (int i = windowSize; i < prices.size() - windowSize; i++) {
            List<Double> windowBefore = prices.subList(i - windowSize, i);
            List<Double> windowAfter = prices.subList(i + 1, i + windowSize + 1);

            double currentPrice = prices.get(i);

            double maxBefore = windowBefore.stream().max(Double::compareTo).orElse(Double.MAX_VALUE);
            double minBefore = windowBefore.stream().min(Double::compareTo).orElse(Double.MIN_VALUE);
            double maxAfter = windowAfter.stream().max(Double::compareTo).orElse(Double.MAX_VALUE);
            double minAfter = windowAfter.stream().min(Double::compareTo).orElse(Double.MIN_VALUE);

            // 현재 가격이 창 크기 내의 주변 포인트보다 높은 경우: 로컬 고점
            if (currentPrice > maxBefore && currentPrice > maxAfter) {
                System.out.println("고점 부근: " + times.get(i) + " 가격: " + currentPrice);
                if (inLongPosition) {
                    double localReturn = currentPrice / lastBuyPrice;
                    cumulativeReturn *= localReturn;
                    System.out.println("로컬 수익률: " + (localReturn - 1) * 100 + "%");
                    inLongPosition = false;
                }
            }

            // 현재 가격이 창 크기 내의 주변 포인트보다 낮은 경우: 로컬 저점
            if (currentPrice < minBefore && currentPrice < minAfter) {
                System.out.println("저점 부근: " + times.get(i) + " 가격: " + currentPrice);
                inLongPosition = true;
                lastBuyPrice = currentPrice;
            }

            // 기존 for 루프 내에서 고점 및 저점을 추가
            if (currentPrice > maxBefore && currentPrice > maxAfter || currentPrice < minBefore && currentPrice < minAfter) {
                lowHighSeries.add(new Minute(times.get(i)), currentPrice);
            }
        }


        System.out.println("최종 누적 수익률: " + (cumulativeReturn - 1) * 100 + "%");




        // 차트 생성 및 설정
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series);
        dataset.addSeries(lowHighSeries);
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "가격 차트",
            "시간",
            "가격",
            dataset
        );

        double maxY = Collections.max(prices);
        double minY = Collections.min(prices);
        XYPlot plot = chart.getXYPlot();
        plot.getRangeAxis().setRange(minY, maxY);
        DateAxis xAxis = new DateAxis("시간");
        plot.setDomainAxis(xAxis);



        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(1, false);
        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesShapesVisible(1, true);
        plot.setRenderer(renderer);




        // 차트 패널 및 프레임 생성
        ChartPanel panel = new ChartPanel(chart);
        JFrame frame = new JFrame();
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}