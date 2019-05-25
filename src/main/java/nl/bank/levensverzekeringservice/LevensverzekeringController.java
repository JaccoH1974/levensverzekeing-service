package nl.bank.levensverzekeringservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/bank/verzekering/leven")
public class LevensverzekeringController {
    @Autowired
    private LevensverzekeringService service;

    @RequestMapping
    ResponseEntity<Double> getPremie(
            @QueryParam("verzekerdkapitaal") Double verzekerdkapitaal,
            @QueryParam("geboortedatum") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date geboortedatum,
            @QueryParam("looptijd") Integer looptijd) {
        if (verzekerdkapitaal == null || geboortedatum == null || looptijd == null) {
            return ResponseEntity.badRequest().build();
        } else {
            log.debug("Request parm: verzerkerdkapitaal: {}, geboortedatum: {}, looptijd: {}", verzekerdkapitaal, geboortedatum, looptijd);

            Levensverzekering levensverzekering = new Levensverzekering();
            levensverzekering.setGeboortedatum(geboortedatum);
            levensverzekering.setVerzekerdkapitaal(verzekerdkapitaal);
            Double premie = service.calculatePremie(verzekerdkapitaal, geboortedatum, looptijd);
            return ResponseEntity.ok(premie);
        }

    }
}
