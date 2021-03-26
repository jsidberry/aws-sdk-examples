//snippet-sourcedescription:[SetAcl.java demonstrates how to set a new access control list (ACL) to an Amazon Simple Storage Service (Amazon S3) bucket.]
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

// snippet-start:[s3.java2.set_acl.import]
import java.util.ArrayList;
import java.util.List;
import software.amazon.awssdk.services.s3.model.Grantee;
import software.amazon.awssdk.services.s3.model.Permission;
import software.amazon.awssdk.services.s3.model.Grant;
import software.amazon.awssdk.services.s3.model.AccessControlPolicy;
import software.amazon.awssdk.services.s3.model.Type;
import software.amazon.awssdk.services.s3.model.PutBucketAclRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
// snippet-end:[s3.java2.set_acl.import]

public class SetAcl {

    public static void main(String[] args) {
        final String USAGE = "\n" +
                "Usage:\n" +
                "  SetAcl <bucketName> <objectKey> <id> \n\n" +
                "Where:\n" +
                    " bucketName - the Amazon S3 bucket to grant permissions on. \n" +
                    " objectKey - the object to grant permissions on. \n" +
                    " id - the ID of the owner of this bucket (you can get this value from the AWS Management Console).\n"  ;

        if (args.length != 3) {
             System.out.println(USAGE);
            System.exit(1);
        }

        String bucketName = args[0];
        String objectKey = args[1];
        String id = args[2];

        System.out.format("Setting access \n");
        System.out.println("for object: " + objectKey);
        System.out.println(" in bucket: " + bucketName);

        Region region = Region.US_WEST_2;
        S3Client s3 = S3Client.builder()
                .region(region)
                .build();

        setBucketAcl(s3, bucketName, id);
        System.out.println("Done!");
        s3.close();
    }

    // snippet-start:[s3.java2.set_acl.main]
    public static void setBucketAcl(S3Client s3, String bucketName, String id) {

       try {
             Grant ownerGrant = Grant.builder()
                    .grantee(builder -> {
                        builder.id(id)
                                .type(Type.CANONICAL_USER);
                    })
                    .permission(Permission.FULL_CONTROL)
                    .build();

            List<Grant> grantList2 = new ArrayList<>();
            grantList2.add(ownerGrant);

            AccessControlPolicy acl = AccessControlPolicy.builder()
                    .owner(builder -> builder.id(id))
                    .grants(grantList2)
                    .build();

            PutBucketAclRequest putAclReq = PutBucketAclRequest.builder()
                    .bucket(bucketName)
                    .accessControlPolicy(acl)
                    .build();

            s3.putBucketAcl(putAclReq);

        } catch (S3Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
// snippet-end:[s3.java2.set_acl.main]
