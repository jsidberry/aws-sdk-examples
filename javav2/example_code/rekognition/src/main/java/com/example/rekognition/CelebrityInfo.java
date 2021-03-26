// snippet-sourcedescription:[CelebrityInfo.java demonstrates how to get information about a detected celebrity.]
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
// snippet-start:[rekognition.java2.celebrityInfo.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.GetCelebrityInfoRequest;
import software.amazon.awssdk.services.rekognition.model.GetCelebrityInfoResponse;
import software.amazon.awssdk.services.rekognition.model.RekognitionException;
// snippet-end:[rekognition.java2.celebrityInfo.import]


public class CelebrityInfo {

    public static void main(String[] args) {

        final String USAGE = "\n" +
                "Usage: " +
                "CelebrityInfo <id>\n\n" +
                "Where:\n" +
                "id - the id value of the celebrity. You can use the RecognizeCelebrities example to get the ID value. \n\n";

        if (args.length != 1) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String id = args[0];
        Region region = Region.US_EAST_1;
        RekognitionClient rekClient = RekognitionClient.builder()
                .region(region)
                .build();

        getCelebrityInfo(rekClient, id);
        rekClient.close();
    }

    // snippet-start:[rekognition.java2.celebrityInfo.main]
    public static void getCelebrityInfo(RekognitionClient rekClient, String id ) {

       try {
        GetCelebrityInfoRequest info = GetCelebrityInfoRequest.builder()
                .id(id)
                .build();

        GetCelebrityInfoResponse response = rekClient.getCelebrityInfo(info);

        // Display celebrity information
        System.out.println("celebrity name: " + response.name());
        System.out.println("Further information (if available):");
        for (String url: response.urls()){
            System.out.println(url);
        }

       } catch (RekognitionException e) {
           System.out.println(e.getMessage());
           System.exit(1);
       }
        // snippet-end:[rekognition.java2.celebrityInfo.main]
    }
}
