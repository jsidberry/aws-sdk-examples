// snippet-comment:[These are tags for the AWS doc team's sample catalog. Do not remove.]
// snippet-sourcedescription:[ListHealthChecks.java demonstrates how to list health checks.]
// snippet-keyword:[AWS SDK for Java v2]
// snippet-service:[Amazon Route 53]
// snippet-keyword:[Code Sample]
// snippet-sourcetype:[full-example]
// snippet-sourcedate:[2020-09-28]
// snippet-sourceauthor:[AWS - scmacdon]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/
package com.example.route;

// snippet-start:[route53.java2.list_health_checks.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.route53.Route53Client;
import software.amazon.awssdk.services.route53.model.HealthCheck;
import software.amazon.awssdk.services.route53.model.Route53Exception;
import software.amazon.awssdk.services.route53.model.ListHealthChecksResponse;
import java.util.List;
// snippet-end:[route53.java2.list_health_checks.import]

public class ListHealthChecks {

    public static void main(String[] args) {

        Region region = Region.AWS_GLOBAL;
        Route53Client route53Client = Route53Client.builder()
                .region(region)
                .build();

        listAllHealthChecks(route53Client);
        route53Client.close();
    }

    // snippet-start:[route53.java2.list_health_checks.main]
    public static void listAllHealthChecks(Route53Client route53Client) {

        try {
            ListHealthChecksResponse checksResponse = route53Client.listHealthChecks();
            List<HealthCheck> checklist = checksResponse.healthChecks();

            for (HealthCheck check: checklist) {
                System.out.println("The health check id is: "+check.id());
                System.out.println("The health threshold is: "+check.healthCheckConfig().healthThreshold());
                System.out.println("The type is: "+check.healthCheckConfig().typeAsString());
            }

        } catch (Route53Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
    // snippet-end:[route53.java2.list_health_checks.main]
}