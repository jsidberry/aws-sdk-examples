//snippet-sourcedescription:[DescribeAccountAttributes.java demonstrates how to retrieve attributes that belongs to an Amazon Relational Database Service (RDS) account.]
//snippet-keyword:[AWS SDK for Java v2]
//snippet-keyword:[Code Sample]
//snippet-service:[Amazon Relational Database Service]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[7/6/2020]
//snippet-sourceauthor:[scmacdon - aws]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.rds;

// snippet-start:[rds.java2.describe_account.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rds.RdsClient;
import software.amazon.awssdk.services.rds.model.AccountQuota;
import software.amazon.awssdk.services.rds.model.DescribeAccountAttributesRequest;
import software.amazon.awssdk.services.rds.model.RdsException;
import software.amazon.awssdk.services.rds.model.DescribeAccountAttributesResponse;
import java.util.List;
// snippet-end:[rds.java2.describe_account.import]

public class DescribeAccountAttributes {

    public static void main(String[] args) {

        Region region = Region.US_WEST_2;
        RdsClient rdsClient = RdsClient.builder()
                .region(region)
                .build();

        getAccountAttributes(rdsClient) ;
        rdsClient.close();
    }

    // snippet-start:[rds.java2.describe_account.main]
    public static void getAccountAttributes(RdsClient rdsClient) {

    try {
        DescribeAccountAttributesRequest accountAttributesRequest = DescribeAccountAttributesRequest.builder()
            .build();

        DescribeAccountAttributesResponse response = rdsClient.describeAccountAttributes();

        List<AccountQuota> quotasList = response.accountQuotas();

        for (AccountQuota quotas: quotasList) {
            System.out.println("Name is: "+quotas.accountQuotaName());
            System.out.println("Max value is " +quotas.max());
        }

    } catch (RdsException e) {
        System.out.println(e.getLocalizedMessage());
        System.exit(1);
    }
        // snippet-end:[rds.java2.describe_account.main]
   }
 }
