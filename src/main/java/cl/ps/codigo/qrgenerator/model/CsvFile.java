package cl.ps.codigo.qrgenerator.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CsvFile {
    
    public CsvFile() {
        super();
    }
    
    private List<Register> registers;
}
