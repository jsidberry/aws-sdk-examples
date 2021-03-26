// snippet-comment:[These are tags for the AWS doc team's sample catalog. Do not remove.]
// snippet-sourcedescription:[DescribeStacks.java demonstrates how to obtain information about stacks.]
//snippet-keyword:[AWS SDK for Java v2]
// snippet-service:[AWS CloudFormation]
// snippet-keyword:[Code Sample]
// snippet-sourcetype:[full-example]
// snippet-sourcedate:[11/03/2020]
// snippet-sourceauthor:[AWS-scmacdon]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.cloudformation;

// snippet-start:[cf.java2.get_stacks.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudformation.CloudFormationClient;
import software.amazon.awssdk.services.cloudformation.model.CloudFormationException;
import software.amazon.awssdk.services.cloudformation.model.DescribeStacksResponse;
import software.amazon.awssdk.services.cloudformation.model.Stack;
import java.util.List;
// snippet-end:[cf.java2.get_stacks.import]

public class DescribeStacks {

    public static void main(String[] args) {

        Region region = Region.US_EAST_1;
        CloudFormationClient cfClient = CloudFormationClient.builder()
                .region(region)
                .build();

        describeAllStacks(cfClient);
        cfClient.close();
    }

    // snippet-start:[cf.java2.get_stacks.main]
    public static void describeAllStacks(CloudFormationClient cfClient) {

        try {
            DescribeStacksResponse stacksResponse = cfClient.describeStacks();
            List<Stack> stacks = stacksResponse.stacks();

            for (Stack stack : stacks) {
                System.out.println("The stack description is " + stack.description());
                System.out.println("The stack Id is " + stack.stackId());
                }

        } catch (CloudFormationException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
    // snippet-end:[cf.java2.get_stacks.main]
}
