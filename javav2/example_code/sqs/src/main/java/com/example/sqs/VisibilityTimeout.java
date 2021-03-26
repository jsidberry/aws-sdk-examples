//snippet-sourcedescription:[VisibilityTimeout.java demonstrates how to change the visibility timeout for messages in an Amazon Simple Queue Service (Amazon SQS) queue.]
//snippet-keyword:[AWS SDK for Java v2]
//snippet-keyword:[Code Sample]
//snippet-service:[Amazon Simple Queue Service]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[11/06/2020]
//snippet-sourceauthor:[scmacdon-aws]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/
package com.example.sqs;

// snippet-start:[sqs.java2.visibility_timeout.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
// snippet-end:[sqs.java2.visibility_timeout.import]

// snippet-start:[sqs.java2.visibility_timeout.main]
public class VisibilityTimeout {

    public static void main(String[] args) {
        final String queueName = "testQueue" + new Date().getTime();
        SqsClient sqs = SqsClient.builder()
                .region(Region.US_WEST_2)
                .build();

        // First, create a queue (unless it exists already)
        CreateQueueRequest createRequest = CreateQueueRequest.builder()
                .queueName(queueName)
                .build();
        try {
            sqs.createQueue(createRequest);
        } catch (SqsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }

        GetQueueUrlRequest getRequest = GetQueueUrlRequest.builder()
                .queueName(queueName)
                .build();

        // Send some messages to the queue
        for (int i = 0; i < 20; i++) {
            SendMessageRequest sendRequest = SendMessageRequest.builder()
                    .queueUrl(queueName)
                    .messageBody("This is message " + i)
                    .build();
            sqs.sendMessage(sendRequest);
        }

        // change visibility timeout (single)
        changeMessageVisibilitySingle(sqs, queueName, 3600);

        // change visibility timeout (multiple)
        changeMessageVisibilityMultiple(sqs, queueName, 2000);
        sqs.close();
    }

    // Change the visibility timeout for a single message
    public static void changeMessageVisibilitySingle(SqsClient sqs, String queueName, int timeout) {

        try {
            // Get the receipt handle for the first message in the queue.
            ReceiveMessageRequest receiveRequest = ReceiveMessageRequest.builder()
                .queueUrl(queueName)
                .build();
            String receipt = sqs.receiveMessage(receiveRequest)
                .messages()
                .get(0)
                .receiptHandle();

            ChangeMessageVisibilityRequest visibilityRequest = ChangeMessageVisibilityRequest.builder()
                .queueUrl(queueName)
                .receiptHandle(receipt)
                .visibilityTimeout(timeout)
                .build();
            sqs.changeMessageVisibility(visibilityRequest);

        } catch (SqsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }

    // Change the visibility timeout for multiple messages.
    public static void changeMessageVisibilityMultiple(SqsClient sqs, String queue_url, int timeout) {

        try {
            List<ChangeMessageVisibilityBatchRequestEntry> entries =
                new ArrayList<ChangeMessageVisibilityBatchRequestEntry>();

            ReceiveMessageRequest receiveRequest = ReceiveMessageRequest.builder()
                .queueUrl(queue_url)
                .build();

            String receipt = sqs.receiveMessage(receiveRequest)
                .messages()
                .get(0)
                .receiptHandle();

            entries.add(ChangeMessageVisibilityBatchRequestEntry.builder()
                .id("unique_id_msg1")
                .receiptHandle(receipt)
                .visibilityTimeout(timeout)
                .build());

            entries.add(ChangeMessageVisibilityBatchRequestEntry.builder()
                .id("unique_id_msg2")
                .receiptHandle(receipt)
                .visibilityTimeout(timeout + 200)
                .build());

            ChangeMessageVisibilityBatchRequest batchRequest = ChangeMessageVisibilityBatchRequest.builder()
                .queueUrl(queue_url)
                .entries(entries)
                .build();

            sqs.changeMessageVisibilityBatch(batchRequest);

        } catch (SqsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }
}
// snippet-end:[sqs.java2.visibility_timeout.main]
