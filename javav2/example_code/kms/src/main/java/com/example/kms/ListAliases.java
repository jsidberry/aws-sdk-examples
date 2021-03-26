//snippet-sourcedescription:[ListAliases.java demonstrates how to get a list of AWS Key Management Service (AWS KMS) aliases.]
//snippet-keyword:[AWS SDK for Java v2]
//snippet-keyword:[Code Sample]
//snippet-service:[AWS Key Management Service]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[11/02/2020]
//snippet-sourceauthor:[scmacdon-aws]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.kms;

// snippet-start:[kms.java2_list_aliases.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.kms.KmsClient;
import software.amazon.awssdk.services.kms.model.AliasListEntry;
import software.amazon.awssdk.services.kms.model.KmsException;
import software.amazon.awssdk.services.kms.model.ListAliasesRequest;
import software.amazon.awssdk.services.kms.model.ListAliasesResponse;
// snippet-end:[kms.java2_list_aliases.import]

import java.util.List;

public class ListAliases {

    public static void main(String[] args) {

        Region region = Region.US_WEST_2;
        KmsClient kmsClient = KmsClient.builder()
                .region(region)
                .build();

        listAllAliases(kmsClient);
        kmsClient.close();
    }

    // snippet-start:[kms.java2_list_aliases.main]
    public static void listAllAliases( KmsClient kmsClient) {

        try {

            ListAliasesRequest aliasesRequest = ListAliasesRequest.builder()
                .limit(15)
                .build();

            ListAliasesResponse aliasesResponse = kmsClient.listAliases(aliasesRequest) ;
            List<AliasListEntry> aliases = aliasesResponse.aliases();

            for (AliasListEntry alias: aliases) {
                System.out.println("The alias name is: "+alias.aliasName());
            }

        } catch (KmsException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
    // snippet-end:[kms.java2_list_aliases.main]
}
