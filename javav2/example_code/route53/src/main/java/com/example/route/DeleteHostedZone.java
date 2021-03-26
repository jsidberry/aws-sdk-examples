// snippet-comment:[These are tags for the AWS doc team's sample catalog. Do not remove.]
// snippet-sourcedescription:[DeleteHostedZone.java demonstrates how to delete a hosted zone.]
//snippet-keyword:[AWS SDK for Java v2]
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

// snippet-start:[route53.java2.delete_hosted_zone.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.route53.Route53Client;
import software.amazon.awssdk.services.route53.model.DeleteHostedZoneRequest;
import software.amazon.awssdk.services.route53.model.Route53Exception;
// snippet-end:[route53.java2.delete_hosted_zone.import]

public class DeleteHostedZone {

    public static void main(String[] args) {

        final String USAGE = "\n" +
                "Usage:\n" +
                "    DeleteHostedZone <hostedZoneId> \n\n" +
                "Where:\n" +
                "    hostedZoneId - the hosted zone id. \n";

        if (args.length < 1) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String hostedZoneId = args[0];
        Region region = Region.AWS_GLOBAL;
        Route53Client route53Client = Route53Client.builder()
                .region(region)
                .build();

        delHostedZone(route53Client, hostedZoneId) ;
        route53Client.close();
    }

    // snippet-start:[route53.java2.delete_hosted_zone.main]
    public static void delHostedZone(Route53Client route53Client, String hostedZoneId ) {

        try {
            DeleteHostedZoneRequest deleteHostedZoneRequestRequest = DeleteHostedZoneRequest.builder()
                    .id(hostedZoneId)
                    .build();

            route53Client.deleteHostedZone(deleteHostedZoneRequestRequest);
            System.out.println("The hosted zone was deleted");

        } catch (Route53Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
    // snippet-end:[route53.java2.delete_hosted_zone.main]
}

