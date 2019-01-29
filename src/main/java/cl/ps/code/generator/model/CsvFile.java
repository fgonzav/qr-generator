package cl.ps.code.generator.model;

import java.util.ArrayList;
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
        registers = new ArrayList<>();
    }
    
    private List<Register> registers;
}
