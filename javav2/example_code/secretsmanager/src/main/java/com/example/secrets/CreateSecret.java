//snippet-sourcedescription:[CreateSecret.java demonstrates how to create a secret for AWS Secrets Manager.]
//snippet-keyword:[AWS SDK for Java v2]
//snippet-keyword:[Code Sample]
//snippet-service:[AWS Secrets Manager]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[11/6/2020]
//snippet-sourceauthor:[scmacdon-AWS]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.secrets;

//snippet-start:[secretsmanager.java2.create_secret.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.CreateSecretRequest;
import software.amazon.awssdk.services.secretsmanager.model.CreateSecretResponse;
import software.amazon.awssdk.services.secretsmanager.model.SecretsManagerException;
//snippet-end:[secretsmanager.java2.create_secret.import]

/**
 * To run this AWS code example, ensure that you have setup your development environment, including your AWS credentials.
 *
 * For information, see this documentation topic:
 *
 *https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html
 */


public class CreateSecret {

    public static void main(String[] args) {

       final String USAGE = "\n" +
                "Usage:\n" +
                "    CreateSecret  <secretName> <secretValue> \n\n" +
                "Where:\n" +
                "    secretName - the name of the secret (for example, tutorials/MyFirstSecret). \n"+
                "    secretValue - the secret value. \n";

        if (args.length != 2) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String secretName = args[0];
        String secretValue= args[1];

        Region region = Region.US_EAST_1;
        SecretsManagerClient secretsClient = SecretsManagerClient.builder()
                .region(region)
                .build();

       String secretARN = createNewSecret(secretsClient, secretName, secretValue);
       System.out.println("The secret ARN is "+ secretARN);
       secretsClient.close();
    }

    //snippet-start:[secretsmanager.java2.create_secret.main]
    public static String createNewSecret( SecretsManagerClient secretsClient, String secretName, String secretValue) {

        try {
            CreateSecretRequest secretRequest = CreateSecretRequest.builder()
                .name(secretName)
                .description("This secret was created by the AWS Secret Manager Java API")
                .secretString(secretValue)
                .build();

            CreateSecretResponse secretResponse = secretsClient.createSecret(secretRequest);
            return secretResponse.arn();

        } catch (SecretsManagerException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
        return "";
    }
    //snippet-end:[secretsmanager.java2.create_secret.main]
}
