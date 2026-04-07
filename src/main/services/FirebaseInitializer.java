
// ---FINAL---

package main.services;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.InputStream;

public class FirebaseInitializer {

    private static boolean initialized = false;

    public static void initialize() {

        if (initialized) return;

        try {

            InputStream serviceAccount =
                    FirebaseInitializer.class
                            .getClassLoader()
                            .getResourceAsStream("serviceAccountKey.json");

            if (serviceAccount == null) {
                throw new RuntimeException("serviceAccountKey.json not found in resources folder");
            }

            System.out.println("Service account loaded");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://sofdes-default-rtdb.asia-southeast1.firebasedatabase.app/")
                    .build();

            FirebaseApp.initializeApp(options);

            initialized = true;

            System.out.println("Firebase Connected");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}