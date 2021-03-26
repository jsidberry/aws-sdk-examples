//snippet-sourcedescription:[DescribeRegionsAndZones.java demonstrates how to get information about all the Amazon Elastic Compute Cloud (Amazon EC2) Regions and Zones.]
//snippet-keyword:[AWS SDK for Java v2]
//snippet-keyword:[Code Sample]
//snippet-service:[Amazon EC2]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[11/01/2020]
//snippet-sourceauthor:[scmacdon-aws]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/
package com.example.ec2;
// snippet-start:[ec2.java2.describe_region_and_zones.complete]
// snippet-start:[ec2.java2.describe_region_and_zones.import]
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.DescribeRegionsResponse;
import software.amazon.awssdk.services.ec2.model.Region;
import software.amazon.awssdk.services.ec2.model.AvailabilityZone;
import software.amazon.awssdk.services.ec2.model.Ec2Exception;
import software.amazon.awssdk.services.ec2.model.DescribeAvailabilityZonesResponse;
// snippet-end:[ec2.java2.describe_region_and_zones.import]

public class DescribeRegionsAndZones {

    public static void main(String[] args) {
        // snippet-start:[ec2.java2.describe_region_and_zones.main]
        // snippet-start:[ec2.java2.describe_region_and_zones.client]
        Ec2Client ec2 = Ec2Client.create();
        // snippet-end:[ec2.java2.describe_region_and_zones.client]

        describeEC2RegionsAndZones(ec2);
        ec2.close();

    }
    public static void describeEC2RegionsAndZones( Ec2Client ec2) {
        // snippet-start:[ec2.java2.describe_region_and_zones.region]
        try {

            DescribeRegionsResponse regionsResponse = ec2.describeRegions();

            for(Region region : regionsResponse.regions()) {
                System.out.printf(
                        "Found Region %s " +
                                "with endpoint %s",
                        region.regionName(),
                        region.endpoint());
                System.out.println();
                // snippet-end:[ec2.java2.describe_region_and_zones.region]
            }

            // snippet-start:[ec2.java2.describe_region_and_zones.avail_zone]
            DescribeAvailabilityZonesResponse zonesResponse =
                    ec2.describeAvailabilityZones();

            for(AvailabilityZone zone : zonesResponse.availabilityZones()) {
                System.out.printf(
                        "Found Availability Zone %s " +
                                "with status %s " +
                                "in region %s",
                        zone.zoneName(),
                        zone.state(),
                        zone.regionName());
                System.out.println();
                // snippet-end:[ec2.java2.describe_region_and_zones.avail_zone]
            }

        } catch (Ec2Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
        // snippet-end:[ec2.java2.describe_region_and_zones.main]
    }
}

// snippet-end:[ec2.java2.describe_region_and_zones.complete]