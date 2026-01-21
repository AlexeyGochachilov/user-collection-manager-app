package ru.aston.finalproject.environment.appdata;

import ru.aston.finalproject.entity.stock.Stock;
import ru.aston.finalproject.parser.StockParser;
import ru.aston.finalproject.service.loader.ConsoleDataLoader;
import ru.aston.finalproject.service.loader.ReadFromFileDataLoader;
import ru.aston.finalproject.service.loader.StockLoaderService;

public class StockAppData extends AppData<Stock> {

    private static final StockParser stockParser = new StockParser();
    private static final ReadFromFileDataLoader<Stock> reader = new ReadFromFileDataLoader<>(stockParser);
    private static final ConsoleDataLoader<Stock> consoleDataLoader = new ConsoleDataLoader<>(stockParser);
    private static final StockLoaderService loaderService = new StockLoaderService(reader, consoleDataLoader);

    public StockAppData() {
        super(stockParser, loaderService, reader);
    }
}
