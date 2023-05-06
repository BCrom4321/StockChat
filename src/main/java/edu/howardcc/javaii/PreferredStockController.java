package edu.howardcc.javaii;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

public class PreferredStockController {

    @FXML
    private Label stockName1, stockName2, stockName3;
    @FXML
    private Label stockSymbol1, stockSymbol2, stockSymbol3;
    @FXML
    private Label currentPrice1, currentPrice2, currentPrice3;
    @FXML
    private Label percentageChange1, percentageChange2, percentageChange3;
    @FXML
    private Button addRemoveButton1, addRemoveButton2, addRemoveButton3;

    private StockService stockService = new StockService();
    private Stock currentStock1, currentStock2, currentStock3;

    @FXML
    public void initialize() throws Exception {
        // Fetch stock data and update UI
        updateUI();
    }

    private void updateUI() throws Exception {
        // Fetch the stock data and update the UI for each stock
        // Assuming you have a method to get the stock data for a specific symbol
        currentStock1 = stockService.getStock("AAPL");
        updateStockInfo(currentStock1, stockName1, stockSymbol1, currentPrice1, percentageChange1, addRemoveButton1);

        currentStock2 = stockService.getStock("GOOGL");
        updateStockInfo(currentStock2, stockName2, stockSymbol2, currentPrice2, percentageChange2, addRemoveButton2);

        currentStock3 = stockService.getStock("AMZN");
        updateStockInfo(currentStock3, stockName3, stockSymbol3, currentPrice3, percentageChange3, addRemoveButton3);
    }

    private void updateStockInfo(Stock stock, Label stockName, Label stockSymbol, Label currentPrice, Label percentageChange, Button addRemoveButton) {
        if (stock != null) {
            stockName.setText(stock.getName());
            stockSymbol.setText(stock.getSymbol());
            currentPrice.setText(String.format("%.2f", stock.getCurrentPrice()));
            percentageChange.setText(String.format("%.2f", stock.getPercentageChange()));
            if (stockService.isPreferredStock(stock)) {
                addRemoveButton.setText("Remove");
            } else {
                addRemoveButton.setText("Add");
            }
        }
    }

    @FXML
    private void handleAddRemove1() {
        handleAddRemove(stockName1, stockSymbol1, currentPrice1, percentageChange1, addRemoveButton1, currentStock1);
    }

    @FXML
    private void handleAddRemove2() {
        handleAddRemove(stockName2, stockSymbol2, currentPrice2, percentageChange2, addRemoveButton2, currentStock2);
    }

    @FXML
    private void handleAddRemove3() {
        handleAddRemove(stockName3, stockSymbol3, currentPrice3, percentageChange3, addRemoveButton3, currentStock3);
    }

    private void handleAddRemove(Label stockName, Label stockSymbol, Label currentPrice, Label percentageChange, Button addRemoveButton, Stock currentStock) {
        if (currentStock != null) {
            if (stockService.isPreferredStock(currentStock)) {
                stockService.removePreferredStock(currentStock);
                addRemoveButton.setText("Add");
            } else {
                stockService.addPreferredStock(currentStock);
                addRemoveButton.setText("Remove");
            }}}}