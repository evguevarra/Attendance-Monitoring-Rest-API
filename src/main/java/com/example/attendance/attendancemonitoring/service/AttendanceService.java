package com.example.attendance.attendancemonitoring.service;

import com.example.attendance.attendancemonitoring.entity.Attendance;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
        }
        return attendances;
    }


    public Map<String, Object> getAllAttendance(String empId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("attendance").document(empId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot documentSnapshot = future.get();
        Map<String, Object> map = documentSnapshot.getData();


        return map;
    }

    public String addReportAttendance(String empId, Map report) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("attendance").document(empId);

        ApiFuture<WriteResult> collectionApiFuture  = dbFirestore.collection("attendance").document(empId).update(report);
        return "Success";
    }


    public String addAttendance(Attendance attendance) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture  = dbFirestore.collection(formattedDate).document(attendance.getEmpId()).set(attendance);
        return "Success";
    }

    public String updateAttendance(Attendance attendance) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(formattedDate).document(attendance.getEmpId()).set(attendance);
        return "Success";
    }

    public Attendance getUserAttendance(String empId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(formattedDate).document(empId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot documentSnapshot = future.get();

        Attendance attendance;
        if(documentSnapshot.exists()){
            attendance = documentSnapshot.toObject(Attendance.class);
            return attendance;
        }
        return null;
    }

    public String getTotalTimedIn()throws ExecutionException, InterruptedException {

        int count=0;
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future =
                dbFirestore.collection(formattedDate).whereEqualTo("timeOut","-").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (DocumentSnapshot document : documents) {
            count++;
        }
        return String.valueOf(count);
    }

    public String checkIfExist(String empId) throws ExecutionException, InterruptedException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(formattedDate).document(empId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot documentSnapshot = future.get();

        if(documentSnapshot.exists()){
            return "Yes";
        }else {
            return "No";
        }
    }

}
