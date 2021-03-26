// snippet-comment:[These are tags for the AWS doc team's sample catalog. Do not remove.]
// snippet-sourcedescription:[PutEventSelectors.java demonstrates how to configure an event selector for your trail.]
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

//snippet-start:[cloudtrail.java2._selectors.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudtrail.CloudTrailClient;
import software.amazon.awssdk.services.cloudtrail.model.CloudTrailException;
import software.amazon.awssdk.services.cloudtrail.model.PutEventSelectorsRequest;
import software.amazon.awssdk.services.cloudtrail.model.EventSelector;
import java.util.ArrayList;
import java.util.List;
//snippet-end:[cloudtrail.java2._selectors.import]

public class PutEventSelectors {

    public static void main(String[] args) {

       final String USAGE = "\n" +
                "Usage:\n" +
                "    PutEventSelectors <trailName> \n\n" +
                "Where:\n" +
                "    trailName - the name of the trail. \n" ;

        if (args.length != 1) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String trailName = args[0];
        Region region = Region.US_EAST_1;
        CloudTrailClient cloudTrailClient = CloudTrailClient.builder()
                .region(region)
                .build();

        setSelector(cloudTrailClient, trailName);
        cloudTrailClient.close();
    }

    //snippet-start:[cloudtrail.java2._selectors.main]
    public static void setSelector(CloudTrailClient cloudTrailClientClient, String trailName) {

        try {
            EventSelector selector = EventSelector.builder()
                .readWriteType("All")
                .build();

            List<EventSelector> selList = new ArrayList<>();
            selList.add(selector);

            PutEventSelectorsRequest selectorsRequest = PutEventSelectorsRequest.builder()
                .trailName(trailName)
                .eventSelectors(selList)
                .build();

            cloudTrailClientClient.putEventSelectors(selectorsRequest);

        } catch (CloudTrailException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
   }
    //snippet-end:[cloudtrail.java2._selectors.main]
}