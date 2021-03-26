// snippet-comment:[These are tags for the AWS doc team's sample catalog. Do not remove.]
// snippet-sourcedescription:[CreateTrail.java demonstrates how to create a trail.]
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

//snippet-start:[cloudtrail.java2.create_trail.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudtrail.CloudTrailClient;
import software.amazon.awssdk.services.cloudtrail.model.CloudTrailException;
import software.amazon.awssdk.services.cloudtrail.model.CreateTrailRequest;
import software.amazon.awssdk.services.cloudtrail.model.CreateTrailResponse;
//snippet-end:[cloudtrail.java2.create_trail.import]

public class CreateTrail {

    public static void main(String[] args) {

         final String USAGE = "\n" +
                "Usage:\n" +
                "    CreateTrail <trailName> <s3BucketName> \n\n" +
                "Where:\n" +
                "    trailName - the name of the trail. \n" +
                "    s3BucketName - the name of the Amazon S3 bucket designated for publishing log files. \n" ;

         if (args.length != 2) {
             System.out.println(USAGE);
             System.exit(1);
         }

        String trailName = args[0] ;
        String s3BucketName = args[1] ;

        Region region = Region.US_EAST_1;
        CloudTrailClient cloudTrailClient = CloudTrailClient.builder()
                .region(region)
                .build();

        createNewTrail(cloudTrailClient, trailName, s3BucketName);
        cloudTrailClient.close();
    }

    //snippet-start:[cloudtrail.java2.create_trail.main]
    public static void createNewTrail(CloudTrailClient cloudTrailClient, String trailName, String s3BucketName) {

        try {
            CreateTrailRequest trailRequest = CreateTrailRequest.builder()
                .name(trailName)
                .s3BucketName(s3BucketName)
                .isMultiRegionTrail(true)
                .build();

            CreateTrailResponse trailResponse = cloudTrailClient.createTrail(trailRequest);
            System.out.println("The Trail ARN is "+trailResponse.trailARN());

        } catch (CloudTrailException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
    //snippet-end:[cloudtrail.java2.create_trail.main]
}
