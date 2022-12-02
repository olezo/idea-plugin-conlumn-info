package com.example.columninfo;

public class ColumnInformation {
    private String name;
    private String description;
    private String symbol;
    private String units;

    public ColumnInformation(String name, String description, String symbol, String units) {
        this.name = name;
        this.description = description;
        this.symbol = symbol;
        this.units = units;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getUnits() {
        return units;
    }

    @Override
    public String toString() {
        return "ColumnInformation{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", symbol='" + symbol + '\'' +
                ", units='" + units + '\'' +
                '}';
    }
}
