// snippet-comment:[These are tags for the AWS doc team's sample catalog. Do not remove.]
// snippet-sourcedescription:[GetObjectTags.java demonstrates how to read tags that belong to an object located in an Amazon Simple Storage Service (Amazon S3) bucket.]
///snippet-keyword:[AWS SDK for Java v2]
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

// snippet-start:[s3.java2.getobjecttags.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectTaggingRequest;
import software.amazon.awssdk.services.s3.model.GetObjectTaggingResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.model.Tag;
import java.util.Iterator;
import java.util.List;
// snippet-end:[s3.java2.getobjecttags.import]

public class GetObjectTags {

    public static void main(String[] args) {

        final String USAGE = "\n" +
                "Usage:\n" +
                "    GetObjectTags <bucketName> <keyName> \n\n" +
                "Where:\n" +
                "    bucketName - the Amazon S3 bucket name. \n\n"+
                "    keyName - a key name that represents the object. \n\n";

        if (args.length != 2) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String bucketName = args[0];
        String keyName = args[1];
        Region region = Region.US_WEST_2;
        S3Client s3 = S3Client.builder()
                .region(region)
                .build();

        listTags(s3,bucketName,keyName);
        s3.close();
    }

    // snippet-start:[s3.java2.getobjecttags.main]
    public static void listTags(S3Client s3, String bucketName, String keyName ) {

         try {
             GetObjectTaggingRequest getTaggingRequest = GetObjectTaggingRequest
                    .builder()
                    .key(keyName)
                    .bucket(bucketName)
                    .build();

            GetObjectTaggingResponse tags = s3.getObjectTagging(getTaggingRequest);
            List<Tag> tagSet= tags.tagSet();

            // Write out the tags
            Iterator<Tag> tagIterator = tagSet.iterator();
            while(tagIterator.hasNext()) {

                Tag tag = (Tag)tagIterator.next();
                System.out.println(tag.key());
                System.out.println(tag.value());
            }
        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
        // snippet-end:[s3.java2.getobjecttags.main]
    }
}
