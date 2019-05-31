package nl.bank.levensverzekeringservice;


import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.http.HttpHeaders;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

            Double expected_premie = 13.88888888888889d;

            ResponseEntity<Double> response = controller.getPremie(verzekerdKapitaal, geboortedatum, looptijd);

            Assertions.assertThat(response.getStatusCode()).isEqualTo(org.springframework.http.HttpStatus.OK);
            Assertions.assertThat(response.getBody()).isEqualTo(expected_premie);

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

            Double expected_premie = null;

            ResponseEntity<Double> response = controller.getPremie(verzekerdKapitaal, geboortedatum, looptijd);

            Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
            Assertions.assertThat(response.getBody()).isEqualTo(expected_premie);

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

            Double expected_premie = null;

            ResponseEntity<Double> response = controller.getPremie(verzekerdKapitaal, geboortedatum, looptijd);

            Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
            Assertions.assertThat(response.getBody()).isEqualTo(expected_premie);

    }

    @Test
    public void testGetMaximumLening_Looptijd_null_Error() {

        try {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date geboortedatum = format.parse("1999-09-06");
            Double verzekerdKapitaal = 25000d;
            Integer looptijd = null;

            Double expected_premie = null;

            ResponseEntity<Double> response = controller.getPremie(verzekerdKapitaal, geboortedatum, looptijd);

            Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
            Assertions.assertThat(response.getBody()).isEqualTo(expected_premie);

        } catch (ParseException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
