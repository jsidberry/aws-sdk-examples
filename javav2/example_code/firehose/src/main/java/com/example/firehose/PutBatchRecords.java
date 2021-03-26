//snippet-sourcedescription:[PutBatchRecords.java demonstrates how to write multiple data records into a delivery stream and check each record using the response object.]
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

// snippet-start:[firehose.java2.put_batch_records.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.firehose.FirehoseClient;
import software.amazon.awssdk.services.firehose.model.Record;
import software.amazon.awssdk.services.firehose.model.PutRecordBatchRequest;
import software.amazon.awssdk.services.firehose.model.PutRecordBatchResponse;
import software.amazon.awssdk.services.firehose.model.FirehoseException;
import software.amazon.awssdk.services.firehose.model.PutRecordBatchResponseEntry;
import software.amazon.awssdk.core.SdkBytes;
import java.util.ArrayList;
import java.util.List;
// snippet-end:[firehose.java2.put_batch_records.import]


public class PutBatchRecords {

    public static void main(String[] args) {

        final String USAGE = "\n" +
                "Usage:\n" +
                "    PutBatchRecords <streamName> \n\n" +
                "Where:\n" +
                "    streamName - the data stream name \n" ;

        if (args.length != 1) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String streamName = args[0];
        Region region = Region.US_WEST_2;
        FirehoseClient firehoseClient = FirehoseClient.builder()
                .region(region)
                .build();

        addStockTradeData(firehoseClient, streamName);
        firehoseClient.close();
    }

    // snippet-start:[firehose.java2.put_batch_records.main]
    public static void addStockTradeData(FirehoseClient firehoseClient, String streamName) {

        List<Record> recordList = new ArrayList<>();

        try {

            // Repeatedly send stock trades with a 100 milliseconds wait in between
            StockTradeGenerator stockTradeGenerator = new StockTradeGenerator();
            int index = 100;

            // Populate the list with StockTrade data
            for (int x=0; x<index; x++){
                StockTrade trade = stockTradeGenerator.getRandomTrade();
                byte[] bytes = trade.toJsonAsBytes();

                Record myRecord = Record.builder()
                        .data(SdkBytes.fromByteArray(bytes))
                        .build();

                System.out.println("Adding trade: " + trade.toString());
                recordList.add(myRecord);
                Thread.sleep(100);
            }

            PutRecordBatchRequest recordBatchRequest = PutRecordBatchRequest.builder()
                    .deliveryStreamName(streamName)
                    .records(recordList)
                    .build();

            PutRecordBatchResponse recordResponse = firehoseClient.putRecordBatch(recordBatchRequest) ;
            System.out.println("The number of records added is: "+recordResponse.requestResponses().size());

            // Check the details of all records in this batch operation.
            String errorMsg ="";
            String errorCode = "";
            List<PutRecordBatchResponseEntry> results = recordResponse.requestResponses();
            for (PutRecordBatchResponseEntry result: results) {

                // Returns null if there is no error.
                errorCode = result.errorCode();
                if (errorCode == null) {
                    System.out.println("Record "+result.recordId() + " was successfully added!");
                } else {
                    errorMsg = result.errorMessage();
                    System.out.println("Error code for record ID : "+result.recordId() + " is "+errorMsg);
                }
        }

        } catch (FirehoseException | InterruptedException e) {
            System.out.println(e.getLocalizedMessage());
            System.exit(1);
        }
        // snippet-end:[firehose.java2.put_batch_records.main]
    }
}
