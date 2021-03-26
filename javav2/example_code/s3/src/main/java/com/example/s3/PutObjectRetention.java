//snippet-sourcedescription:[PutObjectRetention demonstrates how to place an object retention configuration on an Amazon Simple Storage Service (Amazon S3) object.]
//snippet-keyword:[AWS SDK for Java v2]
//snippet-keyword:[Code Sample]
//snippet-service:[Amazon S3]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[10/20/2020]
//snippet-sourceauthor:[scmacdon-aws]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.s3;

// snippet-start:[s3.java2.retention_object.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRetentionRequest;
import software.amazon.awssdk.services.s3.model.ObjectLockRetention ;
import software.amazon.awssdk.services.s3.model.S3Exception;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
// snippet-end:[s3.java2.retention_object.import]

public class PutObjectRetention {

    public static void main(String[] args) {

        final String USAGE = "\n" +
                "Usage:\n" +
                "    PutObjectRetention <key> <bucketName> \n\n" +
                "Where:\n" +
                "    key - the name of the object (for example, book.pdf). \n\n" +
                "    bucketName - the Amazon S3 bucket name that contains the object (for example, bucket1). \n" ;

        if (args.length != 2) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String key = args[0];
        String bucketName = args[1];

        Region region = Region.US_WEST_2;
        S3Client s3 = S3Client.builder()
                .region(region)
                .build();

        setRentionPeriod(s3, key, bucketName) ;
        s3.close();
    }

    // snippet-start:[s3.java2.retention_object.main]
    public static void setRentionPeriod(S3Client s3, String key, String bucket) {

        try{
            LocalDate localDate = LocalDate.parse("2020-07-17");
            LocalDateTime localDateTime = localDate.atStartOfDay();
            Instant instant = localDateTime.toInstant(ZoneOffset.UTC);

            ObjectLockRetention lockRetention = ObjectLockRetention.builder()
                .mode("COMPLIANCE")
                .retainUntilDate(instant)
                .build();

            PutObjectRetentionRequest retentionRequest = PutObjectRetentionRequest.builder()
                .bucket(bucket)
                .key(key)
                .bypassGovernanceRetention(true)
                .retention(lockRetention)
                .build();

            /**
             * To set Retention on an object, the Amazon S3 bucket must support object locking, otherwise an exception is thrown.
             */
            s3.putObjectRetention(retentionRequest);
            System.out.print("An object retention configuration was successfully placed on the object");

        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
        // snippet-end:[s3.java2.retention_object.main]
    }
}


