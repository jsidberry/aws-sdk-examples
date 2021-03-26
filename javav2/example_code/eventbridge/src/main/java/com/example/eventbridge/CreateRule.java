//snippet-sourcedescription:[CreateRule.java demonstrates how to create an Amazon EventBridge rule.]
//snippet-keyword:[AWS SDK for Java v2]
//snippet-keyword:[Code Sample]
//snippet-service:[Amazon EventBridge]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[11/04/2020]
//snippet-sourceauthor:[scmacdon-aws]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.eventbridge;

// snippet-start:[eventbridge.java2._create_rule.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.eventbridge.EventBridgeClient;
import software.amazon.awssdk.services.eventbridge.model.PutRuleRequest;
import software.amazon.awssdk.services.eventbridge.model.PutRuleResponse;
import software.amazon.awssdk.services.eventbridge.model.EventBridgeException;
// snippet-end:[eventbridge.java2._create_rule.import]

public class CreateRule {

    public static void main(String[] args) {

        final String USAGE = "\n" +
                "Usage:\n" +
                "    DescribeRule <ruleName> \n\n" +
                "Where:\n" +
                "    ruleName - the name of the rule to create. \n";

        if (args.length != 1) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String ruleName = args[0];
        Region region = Region.US_WEST_2;
        EventBridgeClient eventBrClient = EventBridgeClient.builder()
                .region(region)
                .build();

        createEBRule(eventBrClient, ruleName);
        eventBrClient.close();
    }

    // snippet-start:[eventbridge.java2._create_rule.main]
    public static void createEBRule(EventBridgeClient eventBrClient, String ruleName) {

    try {

        PutRuleRequest ruleRequest = PutRuleRequest.builder()
                 .name(ruleName)
                .eventBusName("default")
                .eventPattern("{\"source\":[\"aws.s3\"],\"detail-type\":[\"AWS API Call via CloudTrail\"],\"detail\":{\"eventSource\":[\"s3.amazonaws.com\"],\"eventName\":[\"DeleteBucket\"]}}")
                .description("A test rule created by the Java API")
                .build();

        PutRuleResponse ruleResponse = eventBrClient.putRule(ruleRequest);
        System.out.println("The ARN of the new rule is "+ ruleResponse.ruleArn());

    } catch (EventBridgeException e) {

        System.err.println(e.awsErrorDetails().errorMessage());
        System.exit(1);
    }
    // snippet-end:[eventbridge.java2._create_rule.main]
    }
}
