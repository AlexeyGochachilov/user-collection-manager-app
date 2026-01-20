package ru.aston.finalproject.parser;

import ru.aston.finalproject.entity.stock.Stock;
import ru.aston.finalproject.environment.AppException;

import java.math.BigDecimal;

import static ru.aston.finalproject.util.ConstantMethods.checkedStringOnEmpty;
import static ru.aston.finalproject.util.Message.X_CANNOT_BE_EMPTY;

public class StockParser extends AbstractParser<Stock>{

    private static final String DELIMITER = "\\s+";
    private static final int LENGTH_PARAMETER = 9;

    @Override
    public String parseToString(Stock stock) {
        if (stock == null) {
            throw new AppException(String.format(X_CANNOT_BE_EMPTY, "Stock"));
        }
        return exampleStock(
                stock.getName(), stock.getNowValue(),
                stock.getMaxValue(), stock.getMinValue(),
                stock.isDividends(), stock.getPE(), stock.getEPS(),
                stock.getEpsFrom5Years(), stock.isBuyInThisPeriod()
                );
    }

    private String exampleStock(String name, BigDecimal nowValue,
                                BigDecimal maxValue, BigDecimal minValue,
                                boolean dividends, BigDecimal pe, BigDecimal EPS,
                                BigDecimal epsFrom5Years, boolean buyInThisPeriod) {

        return name + " " + nowValue + " " + maxValue +
                " " + minValue + " " + dividends + " " + "pe" + pe + " " +
                "eps" + EPS + " " + epsFrom5Years + " " + buyInThisPeriod;
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
        String name = dataArray[0];
        String nowValue = createdDigitFromFirstInteger(dataArray[1]);
        String maxValue = createdDigitFromFirstInteger(dataArray[2]);
        String minValue = createdDigitFromFirstInteger(dataArray[3]);
        String dividends = dataArray[4];
        String pe = createdDigitFromFirstInteger(dataArray[5].substring(2));
        String EPS = createdDigitFromFirstInteger(dataArray[6].substring(3));
        String epsFrom5Years = createdDigitFromFirstInteger(dataArray[7]);
        String buyInThisPeriod = dataArray[8];

        return Stock.builder().setName(name).setNowValue(nowValue).setMaxValue(maxValue).setMinValue(minValue)
                .setDividends(dividends).setPE(pe).setEPS(EPS).setEpsFrom5Years(epsFrom5Years)
                .setBuyInThisPeriod(buyInThisPeriod).build();
    }
}
