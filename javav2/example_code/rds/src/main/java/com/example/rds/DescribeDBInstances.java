//snippet-sourcedescription:[DescribeDBInstances.java demonstrates how to describe Amazon Relational Database Service (RDS) instances.]
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

// snippet-start:[rds.java2.describe_instances.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rds.RdsClient;
import software.amazon.awssdk.services.rds.model.DescribeDbInstancesResponse;
import software.amazon.awssdk.services.rds.model.DBInstance;
import software.amazon.awssdk.services.rds.model.RdsException;
import java.util.List;
// snippet-end:[rds.java2.describe_instances.import]

public class DescribeDBInstances {

    public static void main(String[] args) {

        Region region = Region.US_WEST_2;
        RdsClient rdsClient = RdsClient.builder()
                .region(region)
                .build();

        describeInstances(rdsClient) ;
        rdsClient.close();
    }

    // snippet-start:[rds.java2.describe_instances.main]
    public static void describeInstances(RdsClient rdsClient) {

        try {

            DescribeDbInstancesResponse response = rdsClient.describeDBInstances();

            List<DBInstance> instanceList = response.dbInstances();

            for (DBInstance instance: instanceList) {
                System.out.println("Instance Identifier is: "+instance.dbInstanceIdentifier());
                System.out.println("The Engine is " +instance.engine());
                System.out.println("Connection endpoint is" +instance.endpoint().address());
            }

        } catch (RdsException e) {
            System.out.println(e.getLocalizedMessage());
            System.exit(1);
        }
        // snippet-end:[rds.java2.describe_instances.main]
    }
}

