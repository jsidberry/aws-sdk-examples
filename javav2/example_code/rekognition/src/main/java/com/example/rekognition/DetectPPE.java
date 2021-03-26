// snippet-sourcedescription:[DetectPPE.java demonstrates how to detect Personal Protective Equipment (PPE) worn by people detected in an image.]
//snippet-keyword:[AWS SDK for Java v2]
// snippet-service:[Amazon Rekognition]
// snippet-keyword:[Code Sample]
// snippet-sourcetype:[full-example]
// snippet-sourcedate:[11-03-2020]
// snippet-sourceauthor:[scmacdon - AWS]
/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.rekognition;

// snippet-start:[rekognition.java2.detect_ppe.import]
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.*;
import software.amazon.awssdk.services.rekognition.model.Image;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.rekognition.model.ProtectiveEquipmentPerson;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
// snippet-end:[rekognition.java2.detect_ppe.import]

public class DetectPPE {

    public static void main(String[] args) {

        final String USAGE = "\n" +
            "Usage: " +
            "DetectPPE <sourceImage> <bucketName>\n\n" +
            "Where:\n" +
            "sourceImage - the name of the image in an Amazon S3 bucket (for example, people.png). \n\n" +
            "bucketName - the name of the Amazon S3 bucket (for example, myBucket). \n\n";

        if (args.length != 2) {
             System.out.println(USAGE);
             System.exit(1);
        }

        String sourceImage = args[0];
        String bucketName = args[1];
        Region region = Region.US_EAST_1;
        S3Client s3 = S3Client.builder()
            .region(region)
            .build();

        RekognitionClient rekClient = RekognitionClient.builder()
            .region(region)
            .build();

        displayGear(s3, rekClient, sourceImage, bucketName) ;
        s3.close();
        rekClient.close();
        System.out.println("This example is done!");
    }

    // snippet-start:[rekognition.java2.detect_ppe.main]
    public static void displayGear(S3Client s3,
                                   RekognitionClient rekClient,
                                   String sourceImage,
                                   String bucketName) {

        byte[] data = getObjectBytes (s3, bucketName, sourceImage);
        InputStream is = new ByteArrayInputStream(data);

        try {
            ProtectiveEquipmentSummarizationAttributes summarizationAttributes = ProtectiveEquipmentSummarizationAttributes.builder()
                    .minConfidence(80F)
                    .requiredEquipmentTypesWithStrings("FACE_COVER", "HAND_COVER", "HEAD_COVER")
                    .build();

            SdkBytes sourceBytes = SdkBytes.fromInputStream(is);
            software.amazon.awssdk.services.rekognition.model.Image souImage = Image.builder()
                    .bytes(sourceBytes)
                    .build();

            DetectProtectiveEquipmentRequest request = DetectProtectiveEquipmentRequest.builder()
                    .image(souImage)
                    .summarizationAttributes(summarizationAttributes)
                    .build();

            DetectProtectiveEquipmentResponse result = rekClient.detectProtectiveEquipment(request);
            List<ProtectiveEquipmentPerson> persons = result.persons();

            for (ProtectiveEquipmentPerson person: persons) {
                System.out.println("ID: " + person.id());
                List<ProtectiveEquipmentBodyPart> bodyParts=person.bodyParts();
                if (bodyParts.isEmpty()){
                    System.out.println("\tNo body parts detected");
                } else
                    for (ProtectiveEquipmentBodyPart bodyPart: bodyParts) {
                        System.out.println("\t" + bodyPart.name() + ". Confidence: " + bodyPart.confidence().toString());
                        List<EquipmentDetection> equipmentDetections=bodyPart.equipmentDetections();

                        if (equipmentDetections.isEmpty()){
                            System.out.println("\t\tNo PPE Detected on " + bodyPart.name());
                        } else {
                            for (EquipmentDetection item: equipmentDetections) {
                                System.out.println("\t\tItem: " + item.type() + ". Confidence: " + item.confidence().toString());
                                System.out.println("\t\tCovers body part: "
                                        + item.coversBodyPart().value().toString() + ". Confidence: " + item.coversBodyPart().confidence().toString());

                                System.out.println("\t\tBounding Box");
                                BoundingBox box =item.boundingBox();

                                System.out.println("\t\tLeft: " +box.left().toString());
                                System.out.println("\t\tTop: " + box.top().toString());
                                System.out.println("\t\tWidth: " + box.width().toString());
                                System.out.println("\t\tHeight: " + box.height().toString());
                                System.out.println("\t\tConfidence: " + item.confidence().toString());
                                System.out.println();
                            }
                        }
                    }
            }
            System.out.println("Person ID Summary\n-----------------");

            DisplaySummary("With required equipment", result.summary().personsWithRequiredEquipment());
            DisplaySummary("Without required equipment", result.summary().personsWithoutRequiredEquipment());
            DisplaySummary("Indeterminate", result.summary().personsIndeterminate());

        } catch (RekognitionException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] getObjectBytes (S3Client s3, String bucketName, String keyName) {

        try {
            GetObjectRequest objectRequest = GetObjectRequest
                    .builder()
                    .key(keyName)
                    .bucket(bucketName)
                    .build();

            ResponseBytes<GetObjectResponse> objectBytes = s3.getObjectAsBytes(objectRequest);
            byte[] data = objectBytes.asByteArray();
            return data;

        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
        return null;
    }

    static void DisplaySummary(String summaryType,List<Integer> idList)
    {
        System.out.print(summaryType + "\n\tIDs  ");
        if (idList.size()==0) {
            System.out.println("None");
        }
        else {
            int count=0;
            for (Integer id: idList ) {
                if (count++ == idList.size()-1) {
                    System.out.println(id.toString());
                }
                else {
                    System.out.print(id.toString() + ", ");
                }
            }
        }
        System.out.println();
    }
    // snippet-end:[rekognition.java2.detect_ppe.main]
}
