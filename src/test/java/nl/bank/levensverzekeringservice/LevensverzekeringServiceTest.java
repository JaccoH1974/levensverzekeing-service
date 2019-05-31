package nl.bank.levensverzekeringservice;

import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LevensverzekeringServiceTest {

    private LevensverzekeringService service = new LevensverzekeringService();

    @Test
    public void calculatePremieTest()  {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
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

    @Test
    public void calculatePremieTest_geboortedatum_null()  {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Double verzekerdkapitaal = 50000d;
            Integer looptijd = 360;
            Double premie =  service.calculatePremie(verzekerdkapitaal, null, looptijd);
            Assert.assertNull(premie);
    }

    @Test
    public void calculatePremieTest_looptijd_null()  {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date geboortedatum = format.parse("1999-09-06");
            Double verzekerdkapitaal = 50000d;
            Double premie =  service.calculatePremie(verzekerdkapitaal, geboortedatum, null);
            Assert.assertNull(premie);

        } catch (ParseException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void calculatePremieTest_verzekerdkapitaal_null()  {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date geboortedatum = format.parse("1999-09-06");
            Integer looptijd = 360;
            Double premie =  service.calculatePremie(null, geboortedatum, looptijd);
            Assert.assertNull(premie);

        } catch (ParseException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}