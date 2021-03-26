// snippet-comment:[These are tags for the AWS doc team's sample catalog. Do not remove.]
// snippet-sourcedescription:[StartLogging.java demonstrates how to start and stop logging.]
//snippet-keyword:[AWS SDK for Java v2]
// snippet-service:[AWS CloudTrail]
// snippet-keyword:[Code Sample]
// snippet-sourcetype:[full-example]
// snippet-sourcedate:[11/03/2020]
// snippet-sourceauthor:[AWS - scmacdon]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.cloudtrail;

//snippet-start:[cloudtrail.java2.logging.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudtrail.CloudTrailClient;
import software.amazon.awssdk.services.cloudtrail.model.CloudTrailException;
import software.amazon.awssdk.services.cloudtrail.model.StartLoggingRequest;
import software.amazon.awssdk.services.cloudtrail.model.StopLoggingRequest;
//snippet-end:[cloudtrail.java2.logging.import]

public class StartLogging {

    public static void main(String[] args) {

       final String USAGE = "\n" +
                "Usage:\n" +
                "    StartLogging <trailName> \n\n" +
                "Where:\n" +
                "    trailName - the name of the trail. \n" ;

        if (args.length != 1) {
             System.out.println(USAGE);
             System.exit(1);
         }

        String trailName = args[0] ;
        Region region = Region.US_EAST_1;
        CloudTrailClient cloudTrailClient = CloudTrailClient.builder()
                .region(region)
                .build();

        startLog(cloudTrailClient, trailName);
        stopLog(cloudTrailClient, trailName);
        cloudTrailClient.close();
    }

    //snippet-start:[cloudtrail.java2.logging.main]
    public static void startLog( CloudTrailClient cloudTrailClientClient, String trailName) {

        try {
            StopLoggingRequest loggingRequest = StopLoggingRequest.builder()
                .name(trailName)
                .build() ;

            cloudTrailClientClient.stopLogging(loggingRequest);
            System.out.println(trailName +" has stopped logging");

        } catch (CloudTrailException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public static void stopLog( CloudTrailClient cloudTrailClientClient, String trailName) {

        try {
            StartLoggingRequest loggingRequest = StartLoggingRequest.builder()
                    .name(trailName)
                    .build() ;

            cloudTrailClientClient.startLogging(loggingRequest);
            System.out.println(trailName +" has started logging");

        } catch (CloudTrailException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
    //snippet-end:[cloudtrail.java2.logging.main]
 }
