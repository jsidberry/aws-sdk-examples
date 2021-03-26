//snippet-sourcedescription:[DescribeTemplate.java demonstrates how to obtain information about a template.]
//snippet-keyword:[AWS SDK for Java v2]
//snippet-keyword:[Code Sample]
//snippet-service:[Amazon QuickSight]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[1/14/2021]
//snippet-sourceauthor:[scmacdon - aws]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.quicksight;

// snippet-start:[quicksight.java2.describe_template.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.quicksight.QuickSightClient;
import software.amazon.awssdk.services.quicksight.model.DescribeTemplateRequest;
import software.amazon.awssdk.services.quicksight.model.DescribeTemplateResponse;
import software.amazon.awssdk.services.quicksight.model.QuickSightException;
// snippet-end:[quicksight.java2.describe_template.import]

public class DescribeTemplate {

    public static void main(String[] args) {

        final String USAGE = "\n" +
                "Usage: DescribeTemplate <account> <templateId>\n\n" +
                "Where:\n" +
                "  account - the ID of the AWS account.\n\n" +
                "  templateId - the ID of the Amazon QuickSight template to describe.\n\n";

         if (args.length != 2) {
             System.out.println(USAGE);
             System.exit(1);
         }

        String account = args[0];
        String templateId = args[1];
        QuickSightClient qsClient = QuickSightClient.builder()
                .region(Region.US_EAST_1)
                .build();

        describeSpecificTemplate(qsClient, account, templateId);
        qsClient.close();
    }

    // snippet-start:[quicksight.java2.describe_template.main]
    public static void describeSpecificTemplate(QuickSightClient qsClient, String account,String templateId) {
        try {

            DescribeTemplateRequest temRequest = DescribeTemplateRequest.builder()
                    .awsAccountId(account)
                    .templateId(templateId)
                    .build();

            DescribeTemplateResponse templateResponse = qsClient.describeTemplate(temRequest);
            System.out.println("The template ARN is " +templateResponse.template().arn());

        } catch (QuickSightException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
       }
    }
    // snippet-end:[quicksight.java2.describe_template.main]
}


