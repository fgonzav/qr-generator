package cl.ps.codigo.qrgenerator.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Register {

    public Register(){
        super();
    }
    
    private String name;
    private String code;
}
