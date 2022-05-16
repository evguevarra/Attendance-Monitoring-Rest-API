package com.example.attendance.attendancemonitoring.service;

import com.example.attendance.attendancemonitoring.entity.QrCode;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;
import java.util.concurrent.ExecutionException;

@Service
public class QrService {


    public String updateQr(QrCode qrCode) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("qr").document(qrCode.getQrId()).set(qrCode);
        return "Success";
    }

    public QrCode getQr(String qrId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("qr").document(qrId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot documentSnapshot = future.get();

        QrCode qrCode;
        if(documentSnapshot.exists()){
            qrCode = documentSnapshot.toObject(QrCode.class);
            return qrCode;
        }
        return null;
    }

}
