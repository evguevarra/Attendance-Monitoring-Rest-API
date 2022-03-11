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

    public List<LeaveRequest> getAllRequest() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future =
                dbFirestore.collection("leaves").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<LeaveRequest> requestList = new ArrayList<>();
        for (DocumentSnapshot document : documents) {
            requestList.add(document.toObject(LeaveRequest.class));
            //System.out.println(document.getId() + " => " + document.toObject(User.class));
        }
        return requestList;

    }

}
