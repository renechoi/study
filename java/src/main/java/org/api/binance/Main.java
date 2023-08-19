package org.api.binance;

import com.binance.connector.client.impl.spot.Market;
import org.json.JSONArray;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String baseUrl = "https://api.binance.com";
        String apiKey = "";
        boolean showLimitUsage = false;

        Market market = new Market(baseUrl, apiKey, showLimitUsage, null);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("symbol", "BTCUSDT");
        parameters.put("interval", "1m");
        parameters.put("startTime", System.currentTimeMillis() - (12 * 60 * 60 * 1000L));
        parameters.put("limit", 720);

        String klineData = market.klines(parameters);
        JSONArray klines = new JSONArray(klineData);

        System.out.println("Kline Data for BTCUSDT (1-minute chart, last 12 hours): ");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        LinkedList<BigDecimal> closePrices = new LinkedList<>();

        // CSV 파일에 쓰기
        try (FileWriter writer = new FileWriter("rsi_1m.csv")) {
            writer.append("Date,Close,RSI\n");

            for (int i = 0; i < klines.length(); i++) {
                JSONArray kline = klines.getJSONArray(i);
                long openTime = kline.getLong(0);
                Date date = new Date(openTime);
                String formattedDate = sdf.format(date);

                BigDecimal closePrice = new BigDecimal(kline.getString(4));
                closePrice = closePrice.setScale(2, BigDecimal.ROUND_HALF_UP);

                closePrices.add(closePrice);
                if (closePrices.size() > 14) closePrices.removeFirst();

                if (i >= 13) {
                    double rsi = calculateRSI(closePrices);
                    writer.append(formattedDate).append(',').append(closePrice.toString()).append(',').append(String.valueOf(rsi)).append('\n');
                    System.out.println(formattedDate + " | Close: " + closePrice + " | RSI: " + rsi);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static double calculateRSI(LinkedList<BigDecimal> closePrices) {
        BigDecimal gains = BigDecimal.ZERO;
        BigDecimal losses = BigDecimal.ZERO;

        for (int i = 1; i < closePrices.size(); i++) {
            BigDecimal change = closePrices.get(i).subtract(closePrices.get(i - 1));
            if (change.compareTo(BigDecimal.ZERO) > 0) {
                gains = gains.add(change);
            } else {
                losses = losses.subtract(change);
            }
        }

        BigDecimal avgGain = gains.divide(BigDecimal.valueOf(14), BigDecimal.ROUND_HALF_UP);
        BigDecimal avgLoss = losses.divide(BigDecimal.valueOf(14), BigDecimal.ROUND_HALF_UP);

        BigDecimal rs = avgLoss.compareTo(BigDecimal.ZERO) > 0 ? avgGain.divide(avgLoss, BigDecimal.ROUND_HALF_UP) : BigDecimal.ZERO;
        double rsi = 100 - (100 / (1 + rs.doubleValue()));

        // 소수점 두 번째 자리까지 반올림
        return new BigDecimal(rsi).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
