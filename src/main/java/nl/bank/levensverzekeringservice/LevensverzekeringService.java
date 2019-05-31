package nl.bank.levensverzekeringservice;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
@NoArgsConstructor
public class LevensverzekeringService {

    public Double calculatePremie(Double verzekerdkapitaal, Date geboortedatum, Integer looptijd) {
        if (verzekerdkapitaal == null || geboortedatum == null || looptijd == null) {
            return null;
        } else {
            Integer leeftijd = new Date().getYear() - geboortedatum.getYear();
            Double factor = new Double(leeftijd) / new Double(100);
            Double premie = (verzekerdkapitaal * factor) / looptijd;
            return premie;
        }
    }
}
