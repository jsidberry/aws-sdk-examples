//snippet-sourcedescription:[DeadLetterQueues.java demonstrates how to set a queue as a dead letter queue for Amazon Simple Queue Service (Amazon SQS).]
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

// snippet-start:[sqs.java2.delete_letter_queues.import]
import software.amazon.awssdk.services.sqs.model.*;

import java.util.Date;
import java.util.HashMap;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
// snippet-end:[sqs.java2.delete_letter_queues.import]

public class DeadLetterQueues {
    private static final String QueueName = "testQueue" + new Date().getTime();
    private static final String DLQueueName = "DLQueue" + new Date().getTime();

    public static void main(String[] args) {

        SqsClient sqs = SqsClient.builder()
                .region(Region.US_WEST_2)
                .build();

        setDeadLetterQueue(sqs);
        sqs.close();
    }

    // snippet-start:[sqs.java2.delete_letter_queues.main]
    public static void setDeadLetterQueue( SqsClient sqs) {

        try {
            CreateQueueRequest request = CreateQueueRequest.builder()
                .queueName(QueueName).build();

             CreateQueueRequest dlrequest = CreateQueueRequest.builder()
                .queueName(DLQueueName).build();

            sqs.createQueue(dlrequest);

            GetQueueUrlRequest getRequest = GetQueueUrlRequest.builder()
                .queueName(DLQueueName)
                .build();

            // Get dead-letter queue ARN
            String dlQueueUrl = sqs.getQueueUrl(getRequest)
                .queueUrl();

            GetQueueAttributesResponse queueAttrs = sqs.getQueueAttributes(
                GetQueueAttributesRequest.builder()
                        .queueUrl(dlQueueUrl)
                        .attributeNames(QueueAttributeName.QUEUE_ARN).build());

            String dlQueueArn = queueAttrs.attributes().get(QueueAttributeName.QUEUE_ARN);

            // Set dead letter queue with redrive policy on source queue.
            GetQueueUrlRequest getRequestSource = GetQueueUrlRequest.builder()
                .queueName(DLQueueName)
                .build();

            String srcQueueUrl = sqs.getQueueUrl(getRequestSource)
                .queueUrl();

            HashMap<QueueAttributeName, String> attributes = new HashMap<QueueAttributeName, String>();
            attributes.put(QueueAttributeName.REDRIVE_POLICY, "{\"maxReceiveCount\":\"5\", \"deadLetterTargetArn\":\""
                + dlQueueArn + "\"}");

            SetQueueAttributesRequest setAttrRequest = SetQueueAttributesRequest.builder()
                .queueUrl(srcQueueUrl)
                .attributes(attributes)
                .build();

            SetQueueAttributesResponse setAttrResponse = sqs.setQueueAttributes(setAttrRequest);

        } catch (SqsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }
}
// snippet-end:[sqs.java2.delete_letter_queues.main]
