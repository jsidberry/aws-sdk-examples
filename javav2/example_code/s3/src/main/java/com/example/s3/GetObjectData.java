// snippet-comment:[These are tags for the AWS doc team's sample catalog. Do not remove.]
// snippet-sourcedescription:[GetObjectData.java demonstrates how to read data from an Amazon Simple Storage Service (Amazon S3) object.]
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

// snippet-start:[s3.java2.getobjectdata.import]
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
// snippet-end:[s3.java2.getobjectdata.import]

public class GetObjectData {

    public static void main(String[] args) {

     final String USAGE = "\n" +
                "Usage:\n" +
                "    GetObjectData <bucketName> <keyName> <path>\n\n" +
                "Where:\n" +
                "    bucketName - the Amazon S3 bucket name. \n\n"+
                "    keyName - the key name. \n\n"+
                "    path - the path where the file is written to. \n\n";

        if (args.length != 3) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String bucketName = args[0];
        String keyName = args[1];
        String path = args[2];

        Region region = Region.US_WEST_2;
        S3Client s3 = S3Client.builder()
                .region(region)
                .build();

        getObjectBytes(s3,bucketName,keyName, path);
        s3.close();
    }

    // snippet-start:[s3.java2.getobjectdata.main]
    public static void getObjectBytes (S3Client s3, String bucketName, String keyName, String path ) {

        try {
            GetObjectRequest objectRequest = GetObjectRequest
                    .builder()
                    .key(keyName)
                    .bucket(bucketName)
                    .build();

            ResponseBytes<GetObjectResponse> objectBytes = s3.getObjectAsBytes(objectRequest);
            byte[] data = objectBytes.asByteArray();

            // Write the data to a local file
            File myFile = new File(path );
            OutputStream os = new FileOutputStream(myFile);
            os.write(data);
            System.out.println("Successfully obtained bytes from an S3 object");
            os.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (S3Exception e) {
          System.err.println(e.awsErrorDetails().errorMessage());
           System.exit(1);
        }
        // snippet-end:[s3.java2.getobjectdata.main]
    }
}
