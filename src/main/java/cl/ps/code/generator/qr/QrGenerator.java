package cl.ps.code.generator.qr;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.EnumMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import cl.ps.code.generator.exception.QrGeneratorRuntimeException;
import cl.ps.code.generator.model.Register;

public class QrGenerator {

    private static final int DEFAULT_SIZE = 250;
    private static final String DEFAULT_FILE_TYPE = "png";
    private static final String DEFAULT_OUTPUT_DIR = "./out";
    
    public QrGenerator() {
        super();
        try {
            File outputDir = new File(DEFAULT_OUTPUT_DIR);
            if(outputDir.exists() && outputDir.isDirectory())
                FileUtils.cleanDirectory(outputDir);
            else {
                FileUtils.deleteQuietly(outputDir);
                FileUtils.forceMkdir(outputDir);
            }
        }
        catch(IOException e) {
            System.err.println("imposible limpiar directorio de salida");
        }
    }
    
    public void generate(Register reg) {
        try {
            Map<EncodeHintType, Object> hintMap = new EnumMap<>(EncodeHintType.class);
            hintMap.put(EncodeHintType.CHARACTER_SET, StandardCharsets.UTF_8);
            
            //hintMap.put(EncodeHintType.MARGIN, 1); /* default = 4 */
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix byteMatrix = qrCodeWriter.encode(reg.getCode(), BarcodeFormat.QR_CODE, DEFAULT_SIZE,
                    DEFAULT_SIZE, hintMap);
            int width = byteMatrix.getWidth();
            BufferedImage image = new BufferedImage(width, width,
                    BufferedImage.TYPE_INT_RGB);
            image.createGraphics();
    
            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, width);
            graphics.setColor(Color.BLACK);
    
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            ImageIO.write(image, DEFAULT_FILE_TYPE, new File(String.format("%s/%s.png", new File(DEFAULT_OUTPUT_DIR).getPath(), reg.getName())));
        } catch(IOException | WriterException e) {
            throw new QrGeneratorRuntimeException("Error al generar imagen qr", e);
        }
    }
}
