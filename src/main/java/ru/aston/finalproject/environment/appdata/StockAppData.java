package ru.aston.finalproject.environment.appdata;

import ru.aston.finalproject.entity.stock.Stock;
import ru.aston.finalproject.entity.validator.StockValidator;
import ru.aston.finalproject.entity.validator.Validate;
import ru.aston.finalproject.parser.StockParser;
import ru.aston.finalproject.service.loader.ConsoleDataLoader;
import ru.aston.finalproject.service.loader.ReadFromFileDataLoader;
import ru.aston.finalproject.service.loader.StockLoaderService;

public class StockAppData extends AppData<Stock> {

    private static final Validate<Stock> stockValidator = new StockValidator();
    private static final StockParser stockParser = new StockParser(stockValidator);
    private static final ReadFromFileDataLoader<Stock> reader = new ReadFromFileDataLoader<>(stockParser);
    private static final ConsoleDataLoader<Stock> consoleDataLoader = new ConsoleDataLoader<>(stockParser);
    private static final StockLoaderService loaderService = new StockLoaderService(reader, consoleDataLoader);

    public StockAppData() {
        super(stockParser, loaderService, reader);
    }
}
