package com.example.attendance.attendancemonitoring;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.IOException;


@SpringBootApplication
public class AttendanceMonitoringApplication {

	public static void main(String[] args) throws IOException {

		FileInputStream serviceAccount =
				new FileInputStream("./serviceAccountKey.json");

		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.setDatabaseUrl("https://employee-attendance-monitoring-default-rtdb.firebaseio.com")
				.build();

		FirebaseApp.initializeApp(options);

		SpringApplication.run(AttendanceMonitoringApplication.class, args);
	}

}
