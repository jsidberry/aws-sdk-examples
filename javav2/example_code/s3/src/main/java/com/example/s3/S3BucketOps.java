//snippet-sourcedescription:[S3BucketOps.java demonstrates how to create, list and delete an Amazon Simple Storage Service (Amazon S3) bucket.]
//snippet-keyword:[AWS SDK for Java v2]
//snippet-keyword:[Code Sample]
//snippet-service:[Amazon S3]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[10/28/2020]
//snippet-sourceauthor:[scmacdon-aws]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.s3;

// snippet-start:[s3.java2.s3_bucket_ops.import]
import software.amazon.awssdk.core.waiters.WaiterResponse;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.waiters.S3Waiter;

// snippet-end:[s3.java2.s3_bucket_ops.import]
// snippet-start:[s3.java2.s3_bucket_ops.main]
public class S3BucketOps {

    public static void main(String[] args) {


        // snippet-start:[s3.java2.s3_bucket_ops.region]
        Region region = Region.US_WEST_2;
        S3Client s3 = S3Client.builder()
                .region(region)
                .build();

        // snippet-end:[s3.java2.s3_bucket_ops.region]
        String bucket = "bucket" + System.currentTimeMillis();
        System.out.println(bucket);
        createBucket(s3, bucket, region);
        performOperations(s3, bucket,region ) ;
        }


    // snippet-start:[s3.java2.s3_bucket_ops.create_bucket]
    // Create a bucket by using a S3Waiter object
    public static void createBucket( S3Client s3Client, String bucketName, Region region) {

        try {
            S3Waiter s3Waiter = s3Client.waiter();
            CreateBucketRequest bucketRequest = CreateBucketRequest.builder()
                    .bucket(bucketName)
                    .createBucketConfiguration(
                            CreateBucketConfiguration.builder()
                                    .locationConstraint(region.id())
                                    .build())
                    .build();

            s3Client.createBucket(bucketRequest);
            HeadBucketRequest bucketRequestWait = HeadBucketRequest.builder()
                    .bucket(bucketName)
                    .build();

            // Wait until the bucket is created and print out the response
            WaiterResponse<HeadBucketResponse> waiterResponse = s3Waiter.waitUntilBucketExists(bucketRequestWait);
            waiterResponse.matched().response().ifPresent(System.out::println);
            System.out.println(bucketName +" is ready");

        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }
    // snippet-end:[s3.java2.s3_bucket_ops.create_bucket]

    public static void performOperations(S3Client s3, String bucket, Region region) {

        // snippet-start:[s3.java2.s3_bucket_ops.list_bucket]
        // List buckets
        ListBucketsRequest listBucketsRequest = ListBucketsRequest.builder().build();
        ListBucketsResponse listBucketsResponse = s3.listBuckets(listBucketsRequest);
        listBucketsResponse.buckets().stream().forEach(x -> System.out.println(x.name()));
        // snippet-end:[s3.java2.s3_bucket_ops.list_bucket]

        // Delete empty bucket
        // snippet-start:[s3.java2.s3_bucket_ops.delete_bucket]      
        DeleteBucketRequest deleteBucketRequest = DeleteBucketRequest.builder().bucket(bucket).build();
        s3.deleteBucket(deleteBucketRequest);
        s3.close();
        // snippet-end:[s3.java2.s3_bucket_ops.delete_bucket] 
    }
}
// snippet-end:[s3.java2.s3_bucket_ops.main]
