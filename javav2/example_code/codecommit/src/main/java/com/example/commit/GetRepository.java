// snippet-comment:[These are tags for the AWS doc team's sample catalog. Do not remove.]
// snippet-sourcedescription:[GetRepository.java demonstrates how to obtain information about a repository.]
//snippet-keyword:[AWS SDK for Java v2]
//snippet-keyword:[Code Sample]
// snippet-service:[AWS CodeCommit]
// snippet-sourcetype:[full-example]
//snippet-sourcedate:[11/03/2020]
// snippet-sourceauthor:[AWS - scmacdon]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.commit;

// snippet-start:[codecommit.java2.get_repo.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.codecommit.CodeCommitClient;
import software.amazon.awssdk.services.codecommit.model.CodeCommitException;
import software.amazon.awssdk.services.codecommit.model.GetRepositoryRequest;
import software.amazon.awssdk.services.codecommit.model.GetRepositoryResponse;
// snippet-end:[codecommit.java2.get_repo.import]

public class GetRepository {

    public static void main(String[] args) {

        final String USAGE = "\n" +
                "Usage:\n" +
                "    GetRepository <repoName> \n\n" +
                "Where:\n" +
                "    repoName - the name of the repository.\n" ;

        if (args.length != 1) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String repoName = args[0] ;
        Region region = Region.US_EAST_1;
        CodeCommitClient codeCommitClient = CodeCommitClient.builder()
                .region(region)
                .build();

        getRepInformation(codeCommitClient, repoName);
        codeCommitClient.close();
    }

    // snippet-start:[codecommit.java2.get_repo.main]
    public static void getRepInformation(CodeCommitClient codeCommitClient, String repoName) {

        try {
            GetRepositoryRequest repositoryRequest = GetRepositoryRequest.builder()
                .repositoryName(repoName)
                .build();

            GetRepositoryResponse repositoryResponse = codeCommitClient.getRepository(repositoryRequest);
            System.out.println("The ARN of the "+ repoName +" is " +repositoryResponse.repositoryMetadata().arn());

        } catch (CodeCommitException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
    // snippet-end:[codecommit.java2.get_repo.main]
}
