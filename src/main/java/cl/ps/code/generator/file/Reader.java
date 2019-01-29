package cl.ps.code.generator.file;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import cl.ps.code.generator.exception.QrGeneratorRuntimeException;
import cl.ps.code.generator.model.CsvFile;
import cl.ps.code.generator.model.Register;

public class Reader {
    
    private static final String DEFAULT_IN_DIR = "./in";
    
    private Reader() {
        super();
    }
    
    public static List<CsvFile> findFiles(){
        return findFiles(DEFAULT_IN_DIR);
    }
    
    public static List<CsvFile> findFiles(String path){
        
        Collection<File> files = FileUtils.listFiles(new File(path), new String[] { "csv" }, false);
        List<CsvFile> csvFiles = new ArrayList<>();
        
        files.stream().forEach(f -> csvFiles.add(read(f)));
        
        return csvFiles;
    }

    private static CsvFile read(File file) {
        try {
            List<String> lines = FileUtils.readLines(file, StandardCharsets.UTF_8);
            
            final CsvFile csvfile = new CsvFile();
            lines.stream().forEach(l -> {
                String[] register = StringUtils.split(l, ",");
                if(register == null || register.length < 2)
                    throw new QrGeneratorRuntimeException("El formato de archivo de entrada es incorrecto");
                csvfile.getRegisters().add(new Register(register[0], register[1]));
            });
            return csvfile;
        }
        catch(IOException e) {
            throw new QrGeneratorRuntimeException("Error al leer archivo", e);
        }
    }
}
