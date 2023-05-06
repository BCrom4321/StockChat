package edu.howardcc.javaii;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

public class Stock {
    private String name;
    private String symbol;
    private double currentPrice;
    private double percentageChange;

    public Stock(String name, String symbol, double currentPrice, double percentageChange) {
        this.name = name;
        this.symbol = symbol;
        this.currentPrice = currentPrice;
        this.percentageChange = percentageChange;
    }
    public Stock() {
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public double getPercentageChange() {
        return percentageChange;
    }

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

    public void initialize() throws Exception {
        currentStock1 = updateCurrentStock(stockName1, stockSymbol1, currentPrice1, percentageChange1, addRemoveButton1, currentStock1);
        currentStock2 = updateCurrentStock(stockName2, stockSymbol2, currentPrice2, percentageChange2, addRemoveButton2, currentStock2);
        currentStock3 = updateCurrentStock(stockName3, stockSymbol3, currentPrice3, percentageChange3, addRemoveButton3, currentStock3);
    }


    private void handleAddRemove(Label stockName, Label stockSymbol, Label currentPrice, Label percentageChange, Button addRemoveButton, Stock currentStock) throws Exception {
        if (currentStock != null) {
            if (stockService.isPreferredStock(currentStock)) {
                stockService.removePreferredStock(currentStock);
                addRemoveButton.setText("Add");
            } else {
                stockService.addPreferredStock(currentStock);
                addRemoveButton.setText("Remove");
            }
            updateCurrentStock(stockName, stockSymbol, currentPrice, percentageChange, addRemoveButton, currentStock);
        }
    }

    private Stock updateCurrentStock(Label stockName, Label stockSymbol, Label currentPrice, Label percentageChange, Button addRemoveButton, Stock currentStock) throws Exception {
        currentStock = stockService.getStock(stockSymbol.getText());
        if (currentStock != null) {
            stockName.setText(currentStock.getName());
            stockSymbol.setText(currentStock.getSymbol());
            currentPrice.setText(String.format("%.2f", currentStock.getCurrentPrice()));
            percentageChange.setText(String.format("%.2f", currentStock.getPercentageChange()));
            if (stockService.isPreferredStock(currentStock)) {
                addRemoveButton.setText("Remove");
            } else {
                addRemoveButton.setText("Add");
            }
        }
        return currentStock;
    }
}
