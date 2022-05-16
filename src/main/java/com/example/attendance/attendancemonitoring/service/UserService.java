package com.example.attendance.attendancemonitoring.service;

import com.example.attendance.attendancemonitoring.entity.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    private static final String FIREBASE_PROJECT_ID ="employee-attendance-monitoring" ;
    private static final String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/employee-attendance-monitoring.appspot.com/o/%s?alt=media";
   private static String TEMP_URL ="" ;


    public String createUser(User user) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture  = dbFirestore.collection("users").document(user.getEmpId()).set(user);
        return "Success";
    }

    public String updateUser(User user) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("users").document(user.getEmpId()).set(user);
        return "Success";
    }

    public String getTotalEmployees()throws ExecutionException, InterruptedException {

        int count=0;
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future =
                dbFirestore.collection("users").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<User> userList = new ArrayList<>();
        for (DocumentSnapshot document : documents) {
            count++;
        }
        return String.valueOf(count);
    }

    public String deleteUser(String empID){
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection("users").document(empID).delete();
        return "Success";
    }

    public User getUser(String empID) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("users").document(empID);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot documentSnapshot = future.get();

        User user;
        if(documentSnapshot.exists()){
            user = documentSnapshot.toObject(User.class);
            return user;
        }
        return null;
    }


    public List<User> getAllEmployeeList() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future =
                dbFirestore.collection("users").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<User> userList = new ArrayList<>();
        for (DocumentSnapshot document : documents) {
            userList.add(document.toObject(User.class));
        }
        return userList;

    }





}
