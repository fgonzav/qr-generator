package cl.ps.code.generator;

import java.util.List;

import cl.ps.code.generator.file.Reader;
import cl.ps.code.generator.model.CsvFile;
import cl.ps.code.generator.qr.QrGenerator;

public class App 
{
    public static void main( String[] args ){
        
        List<CsvFile> files = Reader.findFiles();
        QrGenerator generator = new QrGenerator();
        files.stream().forEach(f-> f.getRegisters().stream().forEach(generator::generate));
    }
}
