// snippet-comment:[These are tags for the AWS doc team's sample catalog. Do not remove.]
// snippet-sourcedescription:[RestoreObject.java demonstrates how to restore an archived copy of an object back into an Amazon S3 Amazon Simple Storage Service (Amazon S3) bucket.]
//snippet-keyword:[AWS SDK for Java v2]
//snippet-keyword:[Code Sample]
//snippet-service:[Amazon S3]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[01/06/2021]
//snippet-sourceauthor:[scmacdon-aws]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.s3;

// snippet-start:[s3.java2.restore_object.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.RestoreRequest;
import software.amazon.awssdk.services.s3.model.GlacierJobParameters;
import software.amazon.awssdk.services.s3.model.RestoreObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.model.Tier;
// snippet-end:[s3.java2.restore_object.import]

/*
    For more information about restoring an object, see "Restoring an archived object" at
    https://docs.aws.amazon.com/AmazonS3/latest/userguide/restoring-objects.html
*/

public class RestoreObject {

    public static void main(String[] args) {

        final String USAGE = "\n" +
                "Usage:\n" +
                "    RestoreObject <bucketName> <keyName> <expectedBucketOwner>\n\n" +
                "Where:\n" +
                "    bucketName - the Amazon S3 bucket name. \n\n" +
                "    keyName - the key name of an object. \n\n" +
                "    expectedBucketOwner - the account that owns the bucket (you can obtain this value from the AWS Management Console). \n\n";

        if (args.length != 3) {
                 System.out.println(USAGE);
                 System.exit(1);
         }

        String bucketName = args[0];
        String keyName = args[1];
        String expectedBucketOwner = args[2];

        Region region = Region.US_WEST_2;
        S3Client s3 = S3Client.builder()
                .region(region)
                .build();

        restoreS3Object(s3, bucketName, keyName, expectedBucketOwner);
        s3.close();
    }

    // snippet-start:[s3.java2.restore_object.main]
    public static void restoreS3Object(S3Client s3, String bucketName, String keyName, String expectedBucketOwner) {

        try {

            RestoreRequest restoreRequest = RestoreRequest.builder()
                    .days(10)
                    .glacierJobParameters(GlacierJobParameters.builder().tier(Tier.STANDARD).build())
                    .build();

            RestoreObjectRequest objectRequest = RestoreObjectRequest.builder()
                .expectedBucketOwner(expectedBucketOwner)
                .bucket(bucketName)
                .key(keyName)
                .restoreRequest(restoreRequest)
                .build();

        s3.restoreObject(objectRequest);

    } catch (S3Exception e) {
        System.err.println(e.awsErrorDetails().errorMessage());
        System.exit(1);
    }
        s3.close();
    }
    // snippet-end:[s3.java2.restore_object.main]
}
