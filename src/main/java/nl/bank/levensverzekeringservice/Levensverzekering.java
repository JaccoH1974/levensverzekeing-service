package nl.bank.levensverzekeringservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Levensverzekering {
    private String Risicoprofiel;
    private Double premie;
}
