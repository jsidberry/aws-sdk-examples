//snippet-sourcedescription:[CreateUserPoolClient.java demonstrates how to create a user pool client for Amazon Cognito.]
//snippet-keyword:[AWS SDK for Java v2]
//snippet-keyword:[Code Sample]
//snippet-keyword:[Amazon Cognito]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[11/04/2020]
//snippet-sourceauthor:[scmacdon AWS]
/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.cognito;

//snippet-start:[cognito.java2.user_pool.create_user_pool_client.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CognitoIdentityProviderException;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CreateUserPoolClientRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CreateUserPoolClientResponse;
//snippet-end:[cognito.java2.user_pool.create_user_pool_client.import]

public class CreateUserPoolClient {

    public static void main(String[] args) {
        final String USAGE = "\n" +
                "Usage:\n" +
                "    CreateUserPoolClient <clientName> <userPoolId> \n\n" +
                "Where:\n" +
                "    clientName - the name for the user pool client to create.\n\n" +
                "    userPoolId - the ID for the user pool.\n\n" ;

        if (args.length != 2) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String clientName = args[0];
        String userPoolId = args[1];

        CognitoIdentityProviderClient cognitoclient = CognitoIdentityProviderClient.builder()
                .region(Region.US_EAST_1)
                .build();

        createPoolClient (cognitoclient, clientName, userPoolId) ;
        cognitoclient.close();
    }

    //snippet-start:[cognito.java2.user_pool.create_user_pool_client.main]
    public static void createPoolClient ( CognitoIdentityProviderClient cognitoclient,
                                          String clientName,
                                          String userPoolId ) {

        try {

            CreateUserPoolClientResponse repsonse = cognitoclient.createUserPoolClient(
                    CreateUserPoolClientRequest.builder()
                            .clientName(clientName)
                            .userPoolId(userPoolId)
                            .build()
            );

            System.out.println("User pool " + repsonse.userPoolClient().clientName() + " created. ID: " + repsonse.userPoolClient().clientId());

        } catch (CognitoIdentityProviderException e){
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
        //snippet-end:[cognito.java2.user_pool.create_user_pool_client.main]
    }
}