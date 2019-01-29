package cl.ps.code.generator.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class Register {

    @NonNull private String name;
    @NonNull private String code;
}
