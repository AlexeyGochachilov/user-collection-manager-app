package ru.aston.finalproject.entity.stock;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import ru.aston.finalproject.entity.validator.StockValidator;
import ru.aston.finalproject.entity.validator.Validate;

import java.math.BigDecimal;

import static ru.aston.finalproject.entity.stock.CalculateFieldsStock.nowPercent;
import static ru.aston.finalproject.entity.stock.CalculateFieldsStock.setGrahamPrice;

@Getter
@EqualsAndHashCode
public class Stock implements Comparable<Stock> {

    private final String name;
    private final BigDecimal nowValue;
    private final BigDecimal maxValue;
    private final BigDecimal minValue;
    private final BigDecimal PE;
    private final BigDecimal EPS;
    private final BigDecimal epsFrom5Years;
    private final BigDecimal nowValueInPercent;
    private final BigDecimal grahamPrice;
    private final boolean dividends;
    private final boolean buyInThisPeriod;

    private Stock(Builder builder) {
        this.name = builder.name;
        this.nowValue = builder.nowValue;
        this.maxValue = builder.maxValue;
        this.minValue = builder.minValue;
        this.PE = builder.PE;
        this.EPS = builder.EPS;
        this.epsFrom5Years = builder.epsFrom5Years;
        this.nowValueInPercent = nowPercent(this);
        this.grahamPrice = setGrahamPrice(this);
        this.dividends = builder.dividends;
        this.buyInThisPeriod = builder.buyInThisPeriod;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return name + "\n"
                + "PE" + PE + "\n"
                + "52 w High = " + maxValue + "\n"
                + "--> Nov   = " + nowValue + " (" + nowValueInPercent + "%)" + "\n"
                + "grahamPrice = " + grahamPrice + "\n"
                + "minValue = " + minValue + "\n";

    }

    @Override
    public int compareTo(Stock o) {
        return this.nowValueInPercent.compareTo(o.nowValueInPercent);
    }

    public static class Builder {

        private String name;
        private BigDecimal nowValue;
        private BigDecimal maxValue;
        private BigDecimal minValue;
        private BigDecimal PE;
        private BigDecimal EPS;
        private BigDecimal epsFrom5Years;
        private boolean dividends;
        private boolean buyInThisPeriod;

        private Builder() {
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setNowValue(String nowValue) {
            this.nowValue = new BigDecimal(nowValue);
            return this;
        }

        public Builder setMaxValue(String maxValue) {
            this.maxValue = new BigDecimal(maxValue);
            return this;
        }

        public Builder setMinValue(String minValue) {
            this.minValue = new BigDecimal(minValue);
            return this;
        }

        public Builder setPE(String PE) {
            this.PE = new BigDecimal(PE);
            return this;
        }

        public Builder setEPS(String EPS) {
            this.EPS = new BigDecimal(EPS);
            return this;
        }

        public Builder setEpsFrom5Years(String epsFrom5Years) {
            this.epsFrom5Years = new BigDecimal(epsFrom5Years);
            return this;
        }

        public Builder setDividends(String dividends) {
            this.dividends = Boolean.parseBoolean(dividends);
            return this;
        }

        public Builder setBuyInThisPeriod(String buyInThisPeriod) {
            this.buyInThisPeriod = Boolean.parseBoolean(buyInThisPeriod);
            return this;
        }

        public Stock build() {
            Validate<Stock> validator = new StockValidator();
            validator.validate(name, nowValue, maxValue, minValue);
            return new Stock(this);
        }
    }
}
