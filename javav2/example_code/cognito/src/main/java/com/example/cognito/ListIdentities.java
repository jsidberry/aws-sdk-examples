//snippet-sourcedescription:[ListIdentities.java demonstrates how to list identities that belong to an Amazon Cognito identity pool.]
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

//snippet-start:[cognito.java2.listidentities.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentity.CognitoIdentityClient;
import software.amazon.awssdk.services.cognitoidentity.model.IdentityDescription;
import software.amazon.awssdk.services.cognitoidentity.model.ListIdentitiesRequest;
import software.amazon.awssdk.services.cognitoidentity.model.ListIdentitiesResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CognitoIdentityProviderException;
//snippet-end:[cognito.java2.listidentities.import]

import java.util.List;

public class ListIdentities {

    public static void main(String[] args) {

        final String USAGE = "\n" +
                "Usage:\n" +
                "    ListIdentities <identityPoolId>\n\n" +
                "Where:\n" +
                "    identityPoolId - the id value of your identity pool (for example, us-east-1:00eb915b-c521-417b-af0d-ebad008axxxx).\n\n" ;

        if (args.length != 1) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String identityPoolId = args[0];
        CognitoIdentityClient cognitoclient = CognitoIdentityClient.builder()
                .region(Region.US_EAST_1)
                .build();

        listPoolIdentities(cognitoclient, identityPoolId);
        cognitoclient.close();
    }

    //snippet-start:[cognito.java2.listidentities.main]
    public static void listPoolIdentities(CognitoIdentityClient cognitoclient, String identityPoolId) {

        try {
            ListIdentitiesRequest identitiesRequest = ListIdentitiesRequest.builder()
                    .identityPoolId(identityPoolId)
                    .maxResults(15)
                    .build() ;

            ListIdentitiesResponse response = cognitoclient.listIdentities(identitiesRequest);
            List<IdentityDescription> identities = response.identities();

            for (IdentityDescription identity: identities) {
                System.out.println("The ID is : "+identity.identityId());
            }
        } catch (CognitoIdentityProviderException e){
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }
    //snippet-end:[cognito.java2.listidentities.main]
}