package com.example.infs3634country;

import java.util.List;

//Currency response class to decode json [{}]
public class currencyResponse {
    public List<com.example.infs3634country.currencies> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<currencies> currencies) {
        this.currencies = currencies;
    }

    private List<currencies> currencies;

}
