//snippet-sourcedescription:[DeleteSubscriptionFilter.java demonstrates how to delete Amazon CloudWatch log subscription filters.]
//snippet-keyword:[AWS SDK for Java v2]
//snippet-keyword:[Code Sample]
//snippet-service:[Amazon CloudWatch]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[11/02/2020]
//snippet-sourceauthor:[scmacdon - aws]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/
package com.example.cloudwatch;

// snippet-start:[cloudwatch.java2.delete_subscription_filter.import]
import software.amazon.awssdk.services.cloudwatch.model.CloudWatchException;
import software.amazon.awssdk.services.cloudwatchlogs.CloudWatchLogsClient;
import software.amazon.awssdk.services.cloudwatchlogs.model.DeleteSubscriptionFilterRequest;
// snippet-end:[cloudwatch.java2.delete_subscription_filter.import]

/**
 * Deletes a CloudWatch Logs subscription filter.
 */

public class DeleteSubscriptionFilter {
    public static void main(String[] args) {

       final String USAGE = "\n" +
                "Usage:\n" +
                "  DeleteSubscriptionFilter <filter> <logGroup>\n\n" +
                "Where:\n" +
                "  filter - the name of the subscription filter (for example, MyFilter).\n" +
                "  logGroup - the name of the log group. (for example,testgroup).\n" ;

        if (args.length != 2) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String filter = args[0];
        String logGroup = args[1];

        CloudWatchLogsClient logs = CloudWatchLogsClient.builder()
                .build();

        deleteSubFilter(logs, filter, logGroup );
        logs.close();

    }
    // snippet-start:[cloudwatch.java2.delete_subscription_filter.main]
    public static void deleteSubFilter(CloudWatchLogsClient logs, String filter, String logGroup) {

       try {
           DeleteSubscriptionFilterRequest request =
                DeleteSubscriptionFilterRequest.builder()
                        .filterName(filter)
                        .logGroupName(logGroup)
                        .build();

           logs.deleteSubscriptionFilter(request);
           System.out.printf(
                   "Successfully deleted CloudWatch logs subscription filter %s",
                   filter);

       } catch (CloudWatchException e) {
           System.err.println(e.awsErrorDetails().errorMessage());
           System.exit(1);
       }
   }
}
// snippet-end:[cloudwatch.java2.delete_subscription_filter.main]
