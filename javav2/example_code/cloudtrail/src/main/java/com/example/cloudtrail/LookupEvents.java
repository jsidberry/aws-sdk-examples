// snippet-comment:[These are tags for the AWS doc team's sample catalog. Do not remove.]
// snippet-sourcedescription:[LookupEvents.java demonstrates how to look up Cloud Trail events.]
//snippet-keyword:[AWS SDK for Java v2]
// snippet-service:[AWS CloudTrail]
// snippet-keyword:[Code Sample]
// snippet-sourcetype:[full-example]
// snippet-sourcedate:[11/03/2020]
// snippet-sourceauthor:[AWS - scmacdon]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.cloudtrail;

//snippet-start:[cloudtrail.java2.events.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudtrail.CloudTrailClient;
import software.amazon.awssdk.services.cloudtrail.model.CloudTrailException;
import software.amazon.awssdk.services.cloudtrail.model.Event;
import software.amazon.awssdk.services.cloudtrail.model.LookupEventsRequest;
import software.amazon.awssdk.services.cloudtrail.model.LookupEventsResponse;
import java.util.List;
//snippet-end:[cloudtrail.java2.events.import]

public class LookupEvents {

    public static void main(String[] args) {

        Region region = Region.US_EAST_1;
        CloudTrailClient cloudTrailClient = CloudTrailClient.builder()
                .region(region)
                .build();

        lookupAllEvents(cloudTrailClient);
        cloudTrailClient.close();
    }

    //snippet-start:[cloudtrail.java2.events.main]
    public static void lookupAllEvents(CloudTrailClient cloudTrailClientClient) {

        try {
            LookupEventsRequest eventsRequest = LookupEventsRequest.builder()
                .maxResults(20)
                .build();

            LookupEventsResponse response = cloudTrailClientClient.lookupEvents(eventsRequest);
            List<Event> events = response.events();

            for (Event event: events) {
                System.out.println("Event name is : "+event.eventName());
                System.out.println("The event source is : "+event.eventSource());
            }

        } catch (CloudTrailException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
    //snippet-end:[cloudtrail.java2.events.main]
}
