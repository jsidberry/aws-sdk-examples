/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import java.io.*;
import java.util.*;
import com.example.s3.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3control.S3ControlClient;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AmazonS3Test {

    private static S3Client s3;
    private static Region region;
    private static S3Presigner presigner;
    private static S3ControlClient s3ControlClient;

    // Define the data members required for the tests
    private static String bucketName = "";
    private static String objectKey = "";
    private static String objectPath = "";
    private static String toBucket = "";
    private static String policyText = "";
    private static String id = "";
    private static String presignKey="";
    private static String presignBucket="";
    private static String path="";
    private static String bucketNamePolicy="";
    private static String accountId="";
    private static String accessPointName="";

    //Used for the Encryption test
    private static String encryptObjectName="";
    private static String encryptObjectPath="";
    private static String encryptOutPath="";
    private static String keyId="";

    @BeforeAll
    public static void setUp() throws IOException {

        // Run tests on Real AWS Resources
        region = Region.US_WEST_2;
        s3 = S3Client.builder()
                .region(region)
                .build();

        presigner = S3Presigner.builder()
                .region(region)
                .build();

        s3ControlClient = S3ControlClient.builder()
                .region(region)
                .build();

        try (InputStream input = AmazonS3Test.class.getClassLoader().getResourceAsStream("config.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }

            //load a properties file from class path, inside static method
            prop.load(input);

            // Populate the data members required for all tests
            bucketName = prop.getProperty("bucketName");
            objectKey = prop.getProperty("objectKey");
            objectPath= prop.getProperty("objectPath");
            toBucket = prop.getProperty("toBucket");
            policyText = prop.getProperty("policyText");
            id  = prop.getProperty("id");
            presignKey = prop.getProperty("presignKey");
            presignBucket= prop.getProperty("presignBucket");
            path = prop.getProperty("path");
            bucketNamePolicy = prop.getProperty("bucketNamePolicy");
            accountId = prop.getProperty("accountId");
            accessPointName = prop.getProperty("accessPointName");
            encryptObjectName = prop.getProperty("encryptObjectName");
            encryptObjectPath = prop.getProperty("encryptObjectPath");
            encryptOutPath = prop.getProperty("encryptOutPath");
            keyId = prop.getProperty("keyId");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    @Order(1)
    public void whenInitializingAWSS3Service_thenNotNull() {
        assertNotNull(s3);
        System.out.println("Test 1 passed");
    }

    @Test
    @Order(2)
   public void createBucket() {

        S3ObjectOperations.createBucket(s3,bucketName, region);
      System.out.println("Test 2 passed");
   }

    @Test
    @Order(3)
   public void putObject() {

       String result = PutObject.putS3Object(s3, bucketName, objectKey, objectPath);
       assertTrue(!result.isEmpty());
       System.out.println("Test 3 passed");
   }

    @Test
    @Order(4)
   public void copyBucketObject() {

      String result = CopyObject.copyBucketObject(s3,bucketName,objectKey,toBucket);
      assertTrue(!result.isEmpty());
      System.out.println("Test 4 passed");
    }

    @Test
    @Order(5)
    public void setBucketPolicy() {

     String polText = SetBucketPolicy.getBucketPolicyFromFile(policyText);
     assertTrue(!polText.isEmpty());
     SetBucketPolicy.setPolicy(s3, bucketName, polText);
     System.out.println("Test 5 passed");
    }

    @Test
    @Order(6)
    public void getBucketPolicy() {

    String polText = GetBucketPolicy.getPolicy(s3, bucketName);
    assertTrue(!polText.isEmpty());
    System.out.println("Test 6 passed");
    }

    @Test
    @Order(7)
    public void deleteBucketPolicy() {

   DeleteBucketPolicy.deleteS3BucketPolicy(s3,bucketName );
   System.out.println("Test 7 passed");
    }

    @Test
    @Order(8)
    public void setBucketACL()
    {
        System.out.format("Running Amazon S3 Test 8");
        System.out.println("for object: " + objectKey);
        System.out.println(" in bucket: " + bucketName);
        SetAcl.setBucketAcl(s3, bucketName, id);
        System.out.println("Test 8 passed");
    }

    @Test
    @Order(9)
    public void getACL(){

        String result = GetAcl.getBucketACL(s3,objectKey,bucketName);
        assertTrue(!result.isEmpty());
        System.out.println("Test 9 passed");
    }


    @Test
    @Order(10)
    public void GeneratePresignedUrlAndUploadObject() {
        GeneratePresignedUrlAndUploadObject.signBucket(presigner, presignBucket, presignKey);
        System.out.println("Test 10 passed");
    }

    @Test
    @Order(11)
    public void GetObjectPresignedUrl() {

        GetObjectPresignedUrl.getPresignedUrl(presigner, presignBucket, presignKey);
        System.out.println("Test 11 passed");
    }

    @Test
    @Order(12)
    public void GetObjectData() {
        GetObjectData.getObjectBytes(s3,bucketName,objectKey, path);
        System.out.println("Test 12 passed");
    }

    @Test
    @Order(13)
    public void ListObjects() {
        ListObjects.listBucketObjects(s3,bucketName);
        System.out.println("Test 13 passed");
    }

    @Test
    @Order(14)
    public void CreateAccessPoint() {
        CreateAccessPoint.createSpecificAccessPoint(s3ControlClient, accountId, bucketName, accessPointName);
        CreateAccessPoint.deleteSpecificAccessPoint(s3ControlClient, accountId, accessPointName);
        System.out.println("Test 14 passed");
    }

    @Test
    @Order(15)
    public void KMSEncryptionExample() {
        KMSEncryptionExample.putEncryptData(s3, encryptObjectName, bucketName, encryptObjectPath, keyId );
        KMSEncryptionExample.getEncryptedData(s3, bucketName, encryptObjectName, encryptOutPath, keyId);
        System.out.println("Test 15 passed");
    }

    @Test
    @Order(16)
    public void  LifecycleConfiguration() {

        LifecycleConfiguration.setLifecycleConfig(s3, bucketName, accountId);
        LifecycleConfiguration.getLifecycleConfig(s3, bucketName, accountId);
        LifecycleConfiguration.deleteLifecycleConfig(s3, bucketName, accountId);
        System.out.println("Test 16 passed");
    }

    @Test
    @Order(17)
    public void S3Cors() {
        S3Cors.setCorsInformation(s3, bucketName, accountId);
        S3Cors.getBucketCorsInformation(s3, bucketName, accountId);
        S3Cors.deleteBucketCorsInformation(s3, bucketName, accountId);
        System.out.println("Test 17 passed");
    }

    @Test
    @Order(18)
    public void DeleteMultiObjects() {
        DeleteMultiObjects.deleteBucketObjects(s3, bucketName);
        System.out.println("Test 18 passed");
    }

    @Test
    @Order(19)
    public void deleteObjects() {
        DeleteObjects.deleteBucketObjects(s3,bucketName,objectKey);
        DeleteObjects.deleteBucketObjects(s3,bucketName,encryptObjectName);
        System.out.println("Test 19 passed");
    }

    @Test
    @Order(20)
    public void deleteBucket() {
    S3ObjectOperations.deleteBucket(s3,bucketName);
    System.out.println("Test 20 passed");
    }
}
