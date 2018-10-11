/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.currency;

import java.math.BigDecimal;
import java.util.Currency;
import javax.inject.Named;

/**
 *
 * @author x444505
 */
@Named
public class ExchangeRateTableImpl implements ExchangeRateTable {

    @Override
    public BigDecimal getExchangeRate(Currency sourceCurrency, Currency targetCurrency) throws ExternalServiceFailureException {
        if(sourceCurrency == null || targetCurrency == null) {
            throw new IllegalArgumentException("null currency");
        }
        if(sourceCurrency.equals(Currency.getInstance("EUR")) && targetCurrency.equals(Currency.getInstance("CZK"))) {
            return new BigDecimal(27);
        } else {
            return null;
        }
    }
    
}
