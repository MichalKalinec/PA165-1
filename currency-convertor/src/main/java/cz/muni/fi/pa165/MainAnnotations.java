package cz.muni.fi.pa165;

import cz.muni.fi.pa165.currency.CurrencyConvertorImpl;
import cz.muni.fi.pa165.currency.ExchangeRateTableImpl;
import java.math.BigDecimal;
import java.util.Currency;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author x444505
 */
public class MainAnnotations {
    
    public static void main(String args[]) {
        ApplicationContext springCtx = new AnnotationConfigApplicationContext(CurrencyConvertorImpl.class, ExchangeRateTableImpl.class);
        CurrencyConvertorImpl cc = springCtx.getBean("currencyConvertorImpl", CurrencyConvertorImpl.class);
        
        System.err.println(cc.convert(Currency.getInstance("EUR"), Currency.getInstance("CZK"), BigDecimal.ONE));
    }
}
