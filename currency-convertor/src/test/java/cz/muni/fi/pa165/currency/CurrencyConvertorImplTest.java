package cz.muni.fi.pa165.currency;

import java.math.BigDecimal;
import java.util.Currency;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

public class CurrencyConvertorImplTest {

        private static final Currency EUR = Currency.getInstance("EUR");
        private static final Currency CZK = Currency.getInstance("CZK");
        
    @Test
    public void testConvert() throws ExternalServiceFailureException {
        ExchangeRateTable exchangeRateTable = mock(ExchangeRateTable.class);
        CurrencyConvertor cc = new CurrencyConvertorImpl(exchangeRateTable);
        when(exchangeRateTable.getExchangeRate(EUR, CZK)).thenReturn(new BigDecimal("25.54"));

        assertThat(cc.convert(EUR, CZK, new BigDecimal("9.97"))).isEqualTo((new BigDecimal("254.63")));
    }

    @Test
    public void testConvertWithNullSourceCurrency() {
        CurrencyConvertor cc = new CurrencyConvertorImpl(mock(ExchangeRateTable.class));

        assertThatThrownBy(() -> 
            cc.convert(null, CZK, new BigDecimal("10.50"))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testConvertWithNullTargetCurrency() {
        CurrencyConvertor cc = new CurrencyConvertorImpl(mock(ExchangeRateTable.class));

        assertThatThrownBy(() -> {
            BigDecimal convert = cc.convert(EUR, null, new BigDecimal("10.50"));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testConvertWithNullSourceAmount() {
        CurrencyConvertor cc = new CurrencyConvertorImpl(mock(ExchangeRateTable.class));

        assertThatThrownBy(() -> {
            BigDecimal convert = cc.convert(EUR, CZK, null);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testConvertWithUnknownCurrency() throws ExternalServiceFailureException {
        ExchangeRateTable exchangeRateTable = mock(ExchangeRateTable.class);
        CurrencyConvertor cc = new CurrencyConvertorImpl(exchangeRateTable);
        when(exchangeRateTable.getExchangeRate(EUR, CZK)).thenReturn(null);
        
        
        assertThatThrownBy(() -> {
            BigDecimal convert = cc.convert(EUR, CZK, new BigDecimal("10.50"));
        }).isInstanceOf(UnknownExchangeRateException.class);
    }

    @Test
    public void testConvertWithExternalServiceFailure() throws ExternalServiceFailureException {
        ExchangeRateTable ert = mock(ExchangeRateTable.class);
        CurrencyConvertor cc = new CurrencyConvertorImpl(ert);
        when(ert.getExchangeRate(EUR, CZK)).thenThrow(new ExternalServiceFailureException("test"));
        
        assertThatThrownBy(() -> {
            BigDecimal convert = cc.convert(EUR, CZK, new BigDecimal("10.50"));
        }).isInstanceOf(UnknownExchangeRateException.class);
    }

}
