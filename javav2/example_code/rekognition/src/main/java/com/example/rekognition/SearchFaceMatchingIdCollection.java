// snippet-sourcedescription:[SearchFaceMatchingIdCollection.java demonstrates how to display information about a face that matches a face identified by its ID value.]
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

// snippet-start:[rekognition.java2.match_faces_collection.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.SearchFacesRequest;
import software.amazon.awssdk.services.rekognition.model.SearchFacesResponse;
import software.amazon.awssdk.services.rekognition.model.FaceMatch;
import software.amazon.awssdk.services.rekognition.model.RekognitionException;
import java.util.List;
// snippet-end:[rekognition.java2.match_faces_collection.import]

public class SearchFaceMatchingIdCollection {

    public static void main(String[] args) {

        final String USAGE = "\n" +
                "Usage: " +
                "SearchFaceMatchingIdCollection <collectionId> <sourceImage>\n\n" +
                "Where:\n" +
                "  collectionId - the id of the collection.  \n" +
                "  sourceImage - the path to the image (for example, C:\\AWS\\pic1.png). \n\n";

        if (args.length != 2) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String collectionId = args[0];
        String faceId = args[1];
        Region region = Region.US_EAST_1;
        RekognitionClient rekClient = RekognitionClient.builder()
                .region(region)
                .build();

        System.out.println("Searching for a face in a collections");
        searchFacebyId(rekClient, collectionId, faceId ) ;
        rekClient.close();
    }

    // snippet-start:[rekognition.java2.match_faces_collection.main]
    public static void searchFacebyId(RekognitionClient rekClient,String collectionId, String faceId) {

        try {
            SearchFacesRequest searchFacesRequest = SearchFacesRequest.builder()
                    .collectionId(collectionId)
                    .faceId(faceId)
                    .faceMatchThreshold(70F)
                    .maxFaces(2)
                    .build();

            SearchFacesResponse imageResponse = rekClient.searchFaces(searchFacesRequest) ;

            // Display the results
            System.out.println("Faces matching in the collection");
            List<FaceMatch> faceImageMatches = imageResponse.faceMatches();
            for (FaceMatch face: faceImageMatches) {
                System.out.println("The similarity level is  "+face.similarity());
                System.out.println();
            }
        } catch (RekognitionException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        // snippet-end:[rekognition.java2.match_faces_collection.main]
    }
}
