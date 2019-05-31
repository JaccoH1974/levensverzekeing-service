package nl.bank.levensverzekeringservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Levensverzekering {
    private Double verzekerdkapitaal;
    private Date geboortedatum ;
    private Integer looptijdInMaanden;
    private Double premie;
}
