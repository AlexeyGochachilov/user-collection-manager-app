package ru.aston.finalproject.parser;

import ru.aston.finalproject.entity.stock.Stock;
import ru.aston.finalproject.entity.validator.StockValidator;
import ru.aston.finalproject.entity.validator.Validate;
import ru.aston.finalproject.environment.AppException;

import java.math.BigDecimal;

import static ru.aston.finalproject.util.ConstantMethods.checkedStringOnEmpty;
import static ru.aston.finalproject.util.Message.X_CANNOT_BE_EMPTY;

public class StockParser extends AbstractParser<Stock> {

    private static final String DELIMITER = "\\s+";
    private static final int LENGTH_PARAMETER = 9;
    public static final String STOCK_FORMAT =
            "name nowValue maxValue minValue dividends pe(withPE) eps(withEPS) epsFrom5years buyInThisPeriod";
    private static final int NAME_PARAMETER = 0;
    private static final int NOW_VALUE_PARAMETER = 1;
    private static final int MAX_VALUE_PARAMETER = 2;
    private static final int MIN_VALUE_PARAMETER = 3;
    private static final int DIVIDENDS_PARAMETER = 4;
    private static final int PE_PARAMETER = 5;
    private static final int EPS_PARAMETER = 6;
    private static final int EPS_FROM_5_YEARS_PARAMETER = 7;
    private static final int BUY_IN_THIS_PERIOD_PARAMETER = 8;
    private final Validate<Stock> stockValidator;

    public StockParser(Validate<Stock> stockValidator) {
        this.stockValidator = stockValidator;
    }

    @Override
    public String parseToString(Stock stock) {
        if (stock == null) {
            throw new AppException(String.format(X_CANNOT_BE_EMPTY, "Stock"));
        }
        return exampleStock(
                stock.getName(), stock.getNowValue(),
                stock.getMaxValue(), stock.getMinValue(),
                stock.isDividends(), stock.getPe(), stock.getEps(),
                stock.getEpsFrom5Years(), stock.isBuyInThisPeriod()
        );
    }

    private String exampleStock(String name, BigDecimal nowValue,
                                BigDecimal maxValue, BigDecimal minValue,
                                boolean dividends, BigDecimal pe, BigDecimal eps,
                                BigDecimal epsFrom5Years, boolean buyInThisPeriod) {

        return name + " " + nowValue + " " + maxValue
                + " " + minValue + " " + dividends + " " + "pe" + pe + " "
                + "eps" + eps + " " + epsFrom5Years + " " + buyInThisPeriod;
    }

    @Override
    public Stock parse(String data) {
        return parse(data, DELIMITER);
    }

    @Override
    public Stock parse(String data, String delimiter) {

        checkedStringOnEmpty(data, "data in parser");
        String[] dataArray = preparingForParsing(data, delimiter, LENGTH_PARAMETER);
        dataArray = replaceToDot(dataArray);
        String name = dataArray[NAME_PARAMETER];
        String nowValue = createdDigitFromFirstInteger(dataArray[NOW_VALUE_PARAMETER]);
        String maxValue = createdDigitFromFirstInteger(dataArray[MAX_VALUE_PARAMETER]);
        String minValue = createdDigitFromFirstInteger(dataArray[MIN_VALUE_PARAMETER]);
        String dividends = dataArray[DIVIDENDS_PARAMETER];
        String pe = createdDigitFromFirstInteger(dataArray[PE_PARAMETER].substring(2));
        String eps = createdDigitFromFirstInteger(dataArray[EPS_PARAMETER].substring(3));
        String epsFrom5Years = createdDigitFromFirstInteger(dataArray[EPS_FROM_5_YEARS_PARAMETER]);
        String buyInThisPeriod = dataArray[BUY_IN_THIS_PERIOD_PARAMETER];

        return Stock.builder().setName(name).setNowValue(nowValue).setMaxValue(maxValue).setMinValue(minValue)
                .setDividends(dividends).setPe(pe).setEps(eps).setEpsFrom5Years(epsFrom5Years)
                .setBuyInThisPeriod(buyInThisPeriod).build(stockValidator);
    }
}
