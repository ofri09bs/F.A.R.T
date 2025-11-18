import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Files;
import java.nio.file.Path;

public class DecryptFile {

    private static final byte[] keyBytes = "whereismymoney67".getBytes();
    private static final SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");

    public static void decrypt(Path inputFile, Path outputFile) throws Exception {
        byte[] fileData = Files.readAllBytes(inputFile);

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] decrypted = cipher.doFinal(fileData);

        Files.write(outputFile, decrypted);
    }
}
