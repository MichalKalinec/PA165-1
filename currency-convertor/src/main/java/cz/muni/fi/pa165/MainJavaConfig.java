package cz.muni.fi.pa165;

import cz.muni.fi.pa165.currency.CurrencyConvertor;
import java.math.BigDecimal;
import java.util.Currency;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author x444505
 */
public class MainJavaConfig {
    
    public static void main(String[] args) {
        ApplicationContext springCtx = new AnnotationConfigApplicationContext(JavaConfig.class);
        CurrencyConvertor cc = springCtx.getBean("CurrencyConvertorJAVA", CurrencyConvertor.class);
        
        System.err.println(cc.convert(Currency.getInstance("EUR"), Currency.getInstance("CZK"), BigDecimal.ONE));
}
}
