package com.example.attendance.attendancemonitoring.service;

import com.example.attendance.attendancemonitoring.entity.Attendance;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class AttendanceService {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDateTime currentDate = LocalDateTime.now();
    String formattedDate = formatter.format(currentDate);


    public List<Attendance> getAttendance() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future =
                dbFirestore.collection(formattedDate).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<Attendance> attendances = new ArrayList<>();
        for (DocumentSnapshot document : documents) {
            attendances.add(document.toObject(Attendance.class));
            //System.out.println(document.getId() + " => " + document.toObject(User.class));
        }
        return attendances;

    }
}
