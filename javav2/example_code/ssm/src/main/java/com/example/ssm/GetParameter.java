// snippet-comment:[These are tags for the AWS doc team's sample catalog. Do not remove.]
// snippet-sourcedescription:[GetParameter.java demonstrates how to get a parameter value for Amazon Simple Systems Management (Amazon SSM).]
//snippet-keyword:[AWS SDK for Java v2]
// snippet-keyword:[Amazon Simple Systems Management]
// snippet-keyword:[Code Sample]
// snippet-sourcetype:[full-example]
// snippet-sourcedate:[11/06/2020]
// snippet-sourceauthor:[AWS - scmacdon]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.ssm;

// snippet-start:[ssm.Java2.get_para_value.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;
import software.amazon.awssdk.services.ssm.model.GetParameterResponse;
import software.amazon.awssdk.services.ssm.model.SsmException;
// snippet-end:[ssm.Java2.get_para_value.import]

public class GetParameter {

    public static void main(String[] args) {

        final String USAGE = "\n" +
                "Usage:\n" +
                "    GetParameter <paraName>\n\n" +
                "Where:\n" +
                "    paraName - the name of the parameter.\n";

        if (args.length != 1) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String paraName = args[0];
        Region region = Region.US_EAST_1;
        SsmClient ssmClient = SsmClient.builder()
                .region(region)
                .build();

        getParaValue(ssmClient, paraName);
        ssmClient.close();
    }

    // snippet-start:[ssm.Java2.get_para_value.main]
    public static void getParaValue(SsmClient ssmClient, String paraName) {

        try {
            GetParameterRequest parameterRequest = GetParameterRequest.builder()
                .name(paraName)
                .build();

            GetParameterResponse parameterResponse = ssmClient.getParameter(parameterRequest);
            System.out.println("The parameter value is "+parameterResponse.parameter().value());

        } catch (SsmException e) {
        System.err.println(e.getMessage());
        System.exit(1);
        }
   }
    // snippet-end:[ssm.Java2.get_para_value.main]
}
