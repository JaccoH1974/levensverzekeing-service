package nl.bank.levensverzekeringservice;


import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
@ActiveProfiles({"test"})
public class LevensverzekeringControllerTest {

    @Autowired
    private LevensverzekeringController controller;

    @Autowired
    private LevensverzekeringService service;

    @Test
    public void testGetMaximumLening() {

        try {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date geboortedatum = format.parse("1999-09-06");
            Double verzekerdKapitaal = 25000d;
            Integer looptijd = 360;

            Levensverzekering expectedLevensverzekering = new Levensverzekering();
            expectedLevensverzekering.setPremie(13.88888888888889d);
            expectedLevensverzekering.setRisicoprofiel("risico laag");

            ResponseEntity<Levensverzekering> response = controller.getVerzekeringsPremieAndRisicoprofiel(verzekerdKapitaal, geboortedatum, looptijd);

            Assertions.assertThat(response.getStatusCode()).isEqualTo(org.springframework.http.HttpStatus.OK);
            Assertions.assertThat(response.getBody()).isEqualTo(expectedLevensverzekering);

        } catch (ParseException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void testGetMaximumLening_Verzekerdkapitaak_Null_Error() {

        try {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date geboortedatum = format.parse("1999-09-06");
            Double verzekerdKapitaal = null;
            Integer looptijd = 360;

            Levensverzekering expectedVerzekering = null;

            ResponseEntity<Levensverzekering> response = controller.getVerzekeringsPremieAndRisicoprofiel(verzekerdKapitaal, geboortedatum, looptijd);

            Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
            Assertions.assertThat(response.getBody()).isEqualTo(expectedVerzekering);

        } catch (ParseException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void testGetMaximumLening_Geboortedatum_Null_Error() {

        Date geboortedatum = null;
        Double verzekerdKapitaal = 25000d;
        Integer looptijd = 360;

        Levensverzekering expectedVerzekering = null;

        ResponseEntity<Levensverzekering> response = controller.getVerzekeringsPremieAndRisicoprofiel(verzekerdKapitaal, geboortedatum, looptijd);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        Assertions.assertThat(response.getBody()).isEqualTo(expectedVerzekering);

    }

    @Test
    public void testGetMaximumLening_Looptijd_null_Error() {

        try {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date geboortedatum = format.parse("1999-09-06");
            Double verzekerdKapitaal = 25000d;
            Integer looptijd = null;

            Levensverzekering expectedVerzekering = null;

            ResponseEntity<Levensverzekering> response = controller.getVerzekeringsPremieAndRisicoprofiel(verzekerdKapitaal, geboortedatum, looptijd);

            Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
            Assertions.assertThat(response.getBody()).isEqualTo(expectedVerzekering);

        } catch (ParseException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
