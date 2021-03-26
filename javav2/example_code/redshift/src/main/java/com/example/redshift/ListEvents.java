//snippet-sourcedescription:[ListEvents.java demonstrates how to list events for a given cluster.]
//snippet-keyword:[AWS SDK for Java v2]
//snippet-keyword:[Code Sample]
//snippet-service:[Amazon Redshift ]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[11/05/2020]
//snippet-sourceauthor:[scmacdon - aws]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.redshift;

// snippet-start:[redshift.java2._events.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.redshift.RedshiftClient;
import software.amazon.awssdk.services.redshift.model.DescribeEventsRequest;
import software.amazon.awssdk.services.redshift.model.DescribeEventsResponse;
import software.amazon.awssdk.services.redshift.model.Event;
import software.amazon.awssdk.services.redshift.model.RedshiftException;
import java.util.Date;
import java.util.List;
// snippet-end:[redshift.java2._events.import]


public class ListEvents {

    public static void main(String[] args) {

        final String USAGE = "\n" +
                "Usage:\n" +
                "    ListEvents <clusterId><masterUsername><masterUserPassword> \n\n" +
                "Where:\n" +
                "    clusterId - the id of the cluster. \n" +
                "    eventSourceType - the event type (ie, cluster). \n" ;

        if (args.length != 2) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String clusterId = args[0];
        String eventSourceType = args[1];

        Region region = Region.US_WEST_2;
        RedshiftClient redshiftClient = RedshiftClient.builder()
                .region(region)
                .build();

        listRedShiftEvents(redshiftClient, clusterId, eventSourceType) ;
        redshiftClient.close();
    }

    // snippet-start:[redshift.java2._events.main]
    public static void listRedShiftEvents(RedshiftClient redshiftClient, String clusterId, String eventSourceType) {

        try {
            long oneWeeksAgoMilli = (new Date()).getTime() - (7L*24L*60L*60L*1000L);
            Date oneWeekAgo = new Date();
            oneWeekAgo.setTime(oneWeeksAgoMilli);

            DescribeEventsRequest describeEventsRequest = DescribeEventsRequest.builder()
                    .sourceIdentifier(clusterId)
                    .sourceType(eventSourceType)
                    .startTime(oneWeekAgo.toInstant())
                    .maxRecords(20)
                    .build() ;

            DescribeEventsResponse eventsResponse = redshiftClient.describeEvents(describeEventsRequest);

            List<Event> events = eventsResponse.events();
            for (Event event: events) {
                System.out.println("Source type: "+event.sourceTypeAsString());
                System.out.println("Event message: "+event.message());
            }

        } catch (RedshiftException e) {

            System.err.println(e.getMessage());
            System.exit(1);
        }
    // snippet-end:[redshift.java2._events.main]
    }

}
