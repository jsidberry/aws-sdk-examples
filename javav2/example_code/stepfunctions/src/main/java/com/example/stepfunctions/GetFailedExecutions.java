//snippet-sourcedescription:[GetFailedExecutions.java demonstrates how to obtain a list of failed executions for the specified AWS Step Functions state machine.]
//snippet-keyword:[AWS SDK for Java v2]
//snippet-keyword:[Code Sample]
//snippet-service:[AWS Step Functions]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[01/28/2021]
//snippet-sourceauthor:[scmacdon-AWS]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/


package com.example.stepfunctions;

// snippet-start:[stepfunctions.java2.get_failed_exes.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sfn.SfnClient;
import software.amazon.awssdk.services.sfn.model.ListExecutionsRequest;
import software.amazon.awssdk.services.sfn.model.ExecutionStatus;
import software.amazon.awssdk.services.sfn.model.ListExecutionsResponse;
import software.amazon.awssdk.services.sfn.model.ExecutionListItem;
import software.amazon.awssdk.services.sfn.model.SfnException;
import java.util.List;
// snippet-end:[stepfunctions.java2.get_failed_exes.import]

public class GetFailedExecutions {

    public static void main(String[] args) {

        final String USAGE = "\n" +
                "Usage:\n" +
                "    GetFailedExecutions <stateMachineARN>\n\n" +
                "Where:\n" +
                "    stateMachineARN - The ARN of the state machine.\n";

        if (args.length != 1) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String stateMachineARN = args[0];
        Region region = Region.US_WEST_2;
        SfnClient sfnClient = SfnClient.builder()
                .region(region)
                .build();

        getFailedExes(sfnClient, stateMachineARN);
        sfnClient.close();
    }

    // snippet-start:[stepfunctions.java2.get_failed_exes.main]
    public static void getFailedExes(SfnClient sfnClient, String stateMachineARN) {

        try {
            ListExecutionsRequest executionsRequest = ListExecutionsRequest.builder()
                    .maxResults(10)
                    .stateMachineArn(stateMachineARN)
                    .statusFilter(ExecutionStatus.FAILED)
                    .build();

            ListExecutionsResponse response = sfnClient.listExecutions(executionsRequest);
            List<ExecutionListItem> items = response.executions();
            for (ExecutionListItem item: items) {
                System.out.println("The Amazon Resource Name (ARN) of the failed execution is "+item.executionArn());
            }

        } catch (SfnException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }
    // snippet-end:[stepfunctions.java2.get_failed_exes.main]
}
