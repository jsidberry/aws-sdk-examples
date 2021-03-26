//snippet-sourcedescription:[CreateDeploymentGroup.java demonstrates how to create a deployment group.]
//snippet-keyword:[AWS SDK for Java v2]
//snippet-keyword:[Code Sample]
//snippet-keyword:[AWS CodeDeploy
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[11/3/2020]
//snippet-sourceauthor:[scmacdon AWS]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.deploy;

// snippet-start:[codedeploy.java2.create_deployment_group.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.codedeploy.CodeDeployClient;
import software.amazon.awssdk.services.codedeploy.model.*;
import java.util.ArrayList;
import java.util.List;
// snippet-end:[codedeploy.java2.create_deployment_group.import]

/**
 *  Before running this code example, it's recommended that you go through the CodeDeploy tutorials at:
 *
 *  https://docs.aws.amazon.com/codedeploy/latest/userguide/tutorials.html
 *
 */

public class CreateDeploymentGroup {

    public static void main(String[] args) {

        final String USAGE = "\n" +
                "Usage:\n" +
                "    CreateDeploymentGroup <deploymentGroupName> <appName> <serviceRoleArn> <tagKey> <tagValue> \n\n" +
                "Where:\n" +
                "    deploymentGroupName - the name of the deployment group. \n" +
                "    appName - the name of the application. \n" +
                "    serviceRoleArn - a service role Amazon Resource Name (ARN) that allows AWS CodeDeploy to act on the user's behalf.  \n" +
                "    tagKey - the tag filter key (ie, AppName). \n"+
                "    tagValue - the tag filter value (ie, mywebapp).\n";

        if (args.length != 5) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String deploymentGroupName = args[0] ;
        String appName = args[1];
        String serviceRoleArn= args[2];
        String tagKey=args[3];
        String tagValue=args[4];

        Region region = Region.US_EAST_1;
        CodeDeployClient deployClient = CodeDeployClient.builder()
                .region(region)
                .build();

        String groupId = createNewDeploymentGroup(deployClient, deploymentGroupName, appName, serviceRoleArn, tagKey, tagValue );
        System.out.println("The group deployment ID is "+groupId);
        deployClient.close();
    }

    // snippet-start:[codedeploy.java2.create_deployment_group.main]
    public static String createNewDeploymentGroup(CodeDeployClient deployClient,
                                                  String deploymentGroupName,
                                                  String appName,
                                                  String serviceRoleArn,
                                                  String tagKey,
                                                  String tagValue) {

        try {
            DeploymentStyle style = DeploymentStyle.builder()
                .deploymentType(DeploymentType.IN_PLACE)
                .deploymentOption(DeploymentOption.WITHOUT_TRAFFIC_CONTROL)
                .build();

            EC2TagFilter tagFilter =  EC2TagFilter.builder()
                .key(tagKey)
                .value(tagValue)
                .type("KEY_AND_VALUE")
                .build();

            List<EC2TagFilter> tags = new ArrayList<>();
            tags.add(tagFilter);

            CreateDeploymentGroupRequest groupRequest = CreateDeploymentGroupRequest.builder()
                .deploymentGroupName(deploymentGroupName)
                .applicationName(appName)
                .serviceRoleArn(serviceRoleArn)
                .deploymentStyle(style)
                .ec2TagFilters(tags)
                .build();

            CreateDeploymentGroupResponse groupResponse = deployClient.createDeploymentGroup(groupRequest);
            String groupId = groupResponse.deploymentGroupId();
             return groupId;

        } catch (CodeDeployException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
        return "";
    }
    // snippet-end:[codedeploy.java2.create_deployment_group.main]
}
