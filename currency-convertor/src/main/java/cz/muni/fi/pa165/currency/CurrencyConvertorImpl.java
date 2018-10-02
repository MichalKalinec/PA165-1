package cz.muni.fi.pa165.currency;

//import ch.qos.logback.classic.Logger;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is base implementation of {@link CurrencyConvertor}.
 *
 * @author petr.adamek@embedit.cz
 */
public class CurrencyConvertorImpl implements CurrencyConvertor {

    private final ExchangeRateTable exchangeRateTable;
    private final Logger logger = LoggerFactory.getLogger(CurrencyConvertorImpl.class);

    public CurrencyConvertorImpl(ExchangeRateTable exchangeRateTable) {
        this.exchangeRateTable = exchangeRateTable;
    }

    @Override
    public BigDecimal convert(Currency sourceCurrency, Currency targetCurrency, BigDecimal sourceAmount) {
        logger.trace("convert");
        if (sourceAmount == null || targetCurrency == null || sourceCurrency == null) {
            throw new IllegalArgumentException("null parameter");
        }
        BigDecimal rate;
        try {
            rate = exchangeRateTable.getExchangeRate(sourceCurrency, targetCurrency);
            if (rate == null) {
                String msg = "exchange rate for given pair is not known";
                UnknownExchangeRateException ex = new UnknownExchangeRateException(msg);
                logger.warn(msg, ex);
                throw ex;
            }
        } catch (ExternalServiceFailureException ex) {
            logger.error("lookup failed", ex);
            throw new UnknownExchangeRateException("lookup failed", ex);
        }
        return sourceAmount.multiply(rate).setScale(2, RoundingMode.HALF_EVEN);
    }

}
