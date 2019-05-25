package nl.bank.levensverzekeringservice;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LevensverzekeringServiceTest {

    private LevensverzekeringService service = new LevensverzekeringService();

    @Test
    public void calculatePremieTest()  {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = format.format(new Date());
            Date geboortedatum = format.parse("1999-09-06");
            Double verzekerdkapitaal = 50000d;
            Integer looptijd = 360;
            Double premie =  service.calculatePremie(verzekerdkapitaal, geboortedatum, looptijd);
            Assert.assertEquals(new Double(27.77777777777778), premie);
        } catch (ParseException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
