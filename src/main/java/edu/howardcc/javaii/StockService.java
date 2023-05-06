package edu.howardcc.javaii;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class StockService {
    private List<Stock> preferredStocks = new ArrayList<>();
    private List<Stock> allStocks = new ArrayList<>();
    private static final String API_KEY = "R66CZ0MFHRT21L0C";
    private static final String STOCK_API_URL = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&apikey=" + API_KEY + "&symbol=";

    public Stock getStock(String symbol) throws Exception {
        String url = STOCK_API_URL + symbol;
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        String response;
        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        } else {
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            response = in.readLine();

            in.close();
        }
        // Parse the response and create a Stock object.
        String[] parts = response.split(",");
        if (parts.length >= 4) {
            String name = parts[0];
            symbol = parts[1];
            double currentPrice = Double.parseDouble(parts[2]);
            double percentageChange = Double.parseDouble(parts[3]);

            Stock stock = new Stock(name, symbol, currentPrice, percentageChange);
            return stock;
        } else {
            // Handle the case where the response does not have the expected number of parts
            return new Stock("Error fetching data for " + symbol, symbol, 0, 0);
        }
    }

    public Stock getallStock(String symbol) {
        return allStocks.stream()
                .filter(stock -> stock.getSymbol().equals(symbol))
                .findFirst()
                .orElse(null);
    }

    public void addPreferredStock(Stock stock) {
        if (stock != null && !isPreferredStock(stock)) {
            preferredStocks.add(stock);
        }
    }

    public void removePreferredStock(Stock stock) {
        preferredStocks.remove(stock);
    }

    public boolean isPreferredStock(Stock stock) {
        return preferredStocks.contains(stock);
    }

    public List<Stock> getPreferredStocks() {
        return preferredStocks;
    }

    public List<Stock> getGeneralStocks() {
        List<Stock> generalStocks = new ArrayList<>();

        // Fetch stock data for some stock symbols
        List<String> stockSymbols = List.of("AAPL", "MSFT", "AMZN");

        for (String symbol : stockSymbols) {
            try {
                Stock stock = getStock(symbol);
                generalStocks.add(stock);
            } catch (Exception e) {
                // Log or handle the exception, e.g., add a default Stock object with an error message
                generalStocks.add(new Stock("Error fetching data for " + symbol, symbol, 0, 0));
            }
        }

        return generalStocks;
    }
}

