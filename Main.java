import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.File;

public class Main extends Application {

    private static final String folderPath = "test"; // תיקייה ידועה מראש
    private static final String PASSWORD = "1234";   // סיסמה קבועה

    @Override
    public void start(Stage stage) {
        TextArea log = new TextArea();
        log.setEditable(false);

        Button encryptBtn = new Button("Encrypt all files in 'test'");
        Button decryptBtn = new Button("Decrypt all files in 'test'");

        encryptBtn.setOnAction(e -> {
            File folder = new File(folderPath);
            if (!folder.exists()) {
                log.appendText("Folder 'test' does not exist!\n");
                return;
            }
            for (File file : folder.listFiles()) {
                if (file.isFile()) {
                    try {
                        EncryptFile.encrypt(file.toPath(), file.toPath());
                        log.appendText("Encrypted: " + file.getName() + "\n");
                    } catch (Exception ex) {
                        log.appendText("Error encrypting " + file.getName() + ": " + ex.getMessage() + "\n");
                    }
                }
            }
        });

        decryptBtn.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("Enter password to decrypt:");
            dialog.showAndWait().ifPresent(password -> {
                if (!PASSWORD.equals(password)) {
                    log.appendText("Wrong password!\n");
                    return;
                }
                File folder = new File(folderPath);
                if (!folder.exists()) {
                    log.appendText("Folder 'test' does not exist!\n");
                    return;
                }
                for (File file : folder.listFiles()) {
                    if (file.isFile()) {
                        try {
                            DecryptFile.decrypt(file.toPath(), file.toPath());
                            log.appendText("Decrypted: " + file.getName() + "\n");
                        } catch (Exception ex) {
                            log.appendText("Error decrypting " + file.getName() + ": " + ex.getMessage() + "\n");
                        }
                    }
                }
            });
        });

        VBox root = new VBox(10, encryptBtn, decryptBtn, log);
        Scene scene = new Scene(root, 500, 300);
        stage.setScene(scene);
        stage.setTitle("Simple File Encryptor");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
