package com.example.attendance.attendancemonitoring.service;

import com.example.attendance.attendancemonitoring.entity.LeaveRequest;
import com.example.attendance.attendancemonitoring.entity.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class LeaveService {

    // Handles Retrieving all Leave Request
    public List<LeaveRequest> getAllRequest() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future =
                dbFirestore.collection("leaves").whereEqualTo("status","Pending").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<LeaveRequest> requestList = new ArrayList<>();
        for (DocumentSnapshot document : documents) {
            requestList.add(document.toObject(LeaveRequest.class));
        }
        return requestList;

    }

    // Handles Retrieving of a single Leave request of a certain employee
    public LeaveRequest getRequest(String empID) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("leaves").document(empID);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot documentSnapshot = future.get();

        LeaveRequest leaveRequest;
        if(documentSnapshot.exists()){
            leaveRequest = documentSnapshot.toObject(LeaveRequest.class);
            return leaveRequest;
        }
        return null;
    }

}
