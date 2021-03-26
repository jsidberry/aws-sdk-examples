//snippet-sourcedescription:[ListActivities.java demonstrates how to List existing activities for AWS Step Functions.]
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

// snippet-start:[stepfunctions.java2.list_activities.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sfn.SfnClient;
import software.amazon.awssdk.services.sfn.model.ListActivitiesRequest;
import software.amazon.awssdk.services.sfn.model.ListActivitiesResponse;
import software.amazon.awssdk.services.sfn.model.SfnException;
import software.amazon.awssdk.services.sfn.model.ActivityListItem;
import java.util.List;
// snippet-end:[stepfunctions.java2.list_activities.import]


public class ListActivities {

    public static void main(String[] args) {

        Region region = Region.US_EAST_1;
        SfnClient sfnClient = SfnClient.builder()
                .region(region)
                .build();

        listAllActivites(sfnClient);
        sfnClient.close();
    }

    // snippet-start:[stepfunctions.java2.list_activities.main]
    public static void listAllActivites(SfnClient sfnClient) {

        try {
            ListActivitiesRequest activitiesRequest = ListActivitiesRequest.builder()
                    .maxResults(10)
                    .build();

            ListActivitiesResponse response = sfnClient.listActivities(activitiesRequest);
            List<ActivityListItem> items = response.activities();
            for (ActivityListItem item: items) {
                System.out.println("The activity ARN is "+item.activityArn());
                System.out.println("The activity name is "+item.name());
            }

        } catch (SfnException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }
    // snippet-end:[stepfunctions.java2.list_activities.main]
}


