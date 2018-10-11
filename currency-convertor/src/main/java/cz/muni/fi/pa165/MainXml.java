package cz.muni.fi.pa165;

import cz.muni.fi.pa165.currency.CurrencyConvertorImpl;
import java.math.BigDecimal;
import java.util.Currency;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author x444505
 */
public class MainXml {
    
    public static void main(String args[]) {
        ApplicationContext springCtx = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        CurrencyConvertorImpl cc = springCtx.getBean("currencyConvertorImpl", CurrencyConvertorImpl.class);
        
        System.err.println(cc.convert(Currency.getInstance("EUR"), Currency.getInstance("CZK"), BigDecimal.ONE));
        
    }
}
