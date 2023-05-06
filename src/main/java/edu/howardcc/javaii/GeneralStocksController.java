package edu.howardcc.javaii;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class GeneralStocksController {

    @FXML
    private TableView<Stock> stocksTableView;
    @FXML
    private TableColumn<Stock, String> nameColumn;
    @FXML
    private TableColumn<Stock, String> symbolColumn;
    @FXML
    private TableColumn<Stock, Double> priceColumn;
    @FXML
    private TableColumn<Stock, Double> changeColumn;

    private StockService stockService = new StockService();

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        symbolColumn.setCellValueFactory(new PropertyValueFactory<>("symbol"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("currentPrice"));
        changeColumn.setCellValueFactory(new PropertyValueFactory<>("percentageChange"));

        // Assuming you have a method in StockService to fetch general stocks
        ObservableList<Stock> generalStocks = FXCollections.observableArrayList(stockService.getGeneralStocks());
        stocksTableView.setItems(generalStocks);
    }
}
