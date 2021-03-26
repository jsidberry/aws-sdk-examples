//snippet-sourcedescription:[ListMultipartUploads.java demonstrates how to retrieve a list of in-progress multipart uploads.]
//snippet-keyword:[AWS SDK for Java v2]
//snippet-keyword:[Code Sample]
//snippet-service:[Amazon S3]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[01/07/2021]
//snippet-sourceauthor:[scmacdon-aws]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.s3;

// snippet-start:[s3.java2.list_multi_uploads.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListMultipartUploadsRequest;
import software.amazon.awssdk.services.s3.model.ListMultipartUploadsResponse;
import software.amazon.awssdk.services.s3.model.MultipartUpload;
import software.amazon.awssdk.services.s3.model.S3Exception;
import java.util.List;
// snippet-end:[s3.java2.list_multi_uploads.import]

public class ListMultipartUploads {

    public static void main(String[] args) {
        final String USAGE = "\n" +
                "Usage:\n" +
                "    ListMultipartUploads <bucketName> \n\n" +
                "Where:\n" +
                "    bucketName - the name of the Amazon S3 bucket where an in-progress multipart upload is occurring.\n\n" ;

        if (args.length != 1) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String bucketName = args[0];
        Region region = Region.US_WEST_2;
        S3Client s3 = S3Client.builder()
                .region(region)
                .build();

        listUploads(s3, bucketName);
        s3.close();
    }

    // snippet-start:[s3.java2.list_multi_uploads.main]
    public static void listUploads( S3Client s3, String bucketName) {

        try {
            ListMultipartUploadsRequest listMultipartUploadsRequest = ListMultipartUploadsRequest.builder()
                .bucket(bucketName)
                .build();

            ListMultipartUploadsResponse response = s3.listMultipartUploads(listMultipartUploadsRequest);
            List<MultipartUpload> uploads = response.uploads();

            for (MultipartUpload upload: uploads) {
                System.out.println("Upload in progress: Key = \"" + upload.key() + "\", id = " + upload.uploadId());
            }

        } catch (S3Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
    // snippet-end:[s3.java2.list_multi_uploads.main]
 }
