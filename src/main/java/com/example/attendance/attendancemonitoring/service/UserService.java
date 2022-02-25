package com.example.attendance.attendancemonitoring.service;

import com.example.attendance.attendancemonitoring.entity.User;
import com.google.api.core.ApiFuture;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
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
            //System.out.println(document.getId() + " => " + document.toObject(User.class));
        }
        return userList;

    }


    private String uploadFile(File file, String fileName) throws IOException {
        BlobId blobId = BlobId.of("employee-attendance-monitoring.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("./serviceAccountKey.json"));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        return String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }

    private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        return tempFile;
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public Object upload(MultipartFile multipartFile) {

        try {
            String fileName = multipartFile.getOriginalFilename();                        // to get original file name
            fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));  // to generated random string values for file name.

            File file = this.convertToFile(multipartFile, fileName);                      // to convert multipartFile to File
            TEMP_URL = this.uploadFile(file, fileName);                                   // to get uploaded file link
            file.delete();                                                                // to delete the copy of uploaded file stored in the project folder
            return TEMP_URL;                    // Your customized response
        } catch (Exception e) {
            e.printStackTrace();
            return "Unsuccessful";
        }

    }



//    public ResponseEntity<String> uploadFile(MultipartFile multipartFile) throws IOException {
//        String objectName = generateFileName(multipartFile);
//
//        FileInputStream serviceAccount = new FileInputStream("./serviceAccountKey.json");
//        File file = convertMultiPartToFile(multipartFile);
//        Path filePath = file.toPath();
//
//        Storage storage = StorageOptions.newBuilder().setCredentials(GoogleCredentials.fromStream(serviceAccount)).setProjectId(FIREBASE_PROJECT_ID).build().getService();
//        BlobId blobId = BlobId.of("employee-attendance-monitoring.appspot.com", objectName);
//        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(multipartFile.getContentType()).build();
//
//        storage.create(blobInfo, Files.readAllBytes(filePath));
//
//        return ResponseEntity.status(HttpStatus.CREATED).body("file uploaded successfully");
//    }
//
//    private File convertMultiPartToFile(MultipartFile file) throws IOException {
//        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
//        FileOutputStream fos = new FileOutputStream(convertedFile);
//        fos.write(file.getBytes());
//        fos.close();
//        return convertedFile;
//    }
//
//    private String generateFileName(MultipartFile multiPart) {
//        return new Date().getTime() + "-" + Objects.requireNonNull(multiPart.getOriginalFilename()).replace(" ", "_");
//    }




}
