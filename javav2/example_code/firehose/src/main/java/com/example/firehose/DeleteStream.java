//snippet-sourcedescription:[DeleteStream.java demonstrates how to delete a delivery stream.]
//snippet-keyword:[AWS SDK for Java v2]
//snippet-keyword:[Code Sample]
//snippet-service:[Amazon Kinesis Data Firehose]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[11/04/2020]
//snippet-sourceauthor:[scmacdon - aws]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.firehose;

// snippet-start:[firehose.java2.delete_stream.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.firehose.FirehoseClient;
import software.amazon.awssdk.services.firehose.model.FirehoseException;
import software.amazon.awssdk.services.firehose.model.DeleteDeliveryStreamRequest;
// snippet-end:[firehose.java2.delete_stream.import]

public class DeleteStream {

    public static void main(String[] args) {

        final String USAGE = "\n" +
                "Usage:\n" +
                "    DeleteStream <streamName> \n\n" +
                "Where:\n" +
                "    streamName - the data stream name to delete. \n";

        if (args.length != 1) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String streamName = args[0];
        Region region = Region.US_WEST_2;
        FirehoseClient firehoseClient = FirehoseClient.builder()
                .region(region)
                .build();

        delStream(firehoseClient, streamName) ;
        firehoseClient.close();
    }

    // snippet-start:[firehose.java2.delete_stream.main]
    public static void delStream(FirehoseClient firehoseClient, String streamName) {

        try {
            DeleteDeliveryStreamRequest deleteDeliveryStreamRequest = DeleteDeliveryStreamRequest.builder()
                    .deliveryStreamName(streamName)
                    .build();

             firehoseClient.deleteDeliveryStream(deleteDeliveryStreamRequest);
            System.out.println("Delivery Stream "+streamName +" is deleted");

        } catch (FirehoseException e) {
            System.out.println(e.getLocalizedMessage());
            System.exit(1);
        }
        // snippet-end:[firehose.java2.delete_stream.main]
    }
}

