//snippet-sourcedescription:[GetJobs.java demonstrates how to list all AWS Glue jobs.]
//snippet-keyword:[AWS SDK for Java v2]
//snippet-keyword:[Code Sample]
//snippet-keyword:[AWS Glue]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[11/04/2020]
//snippet-sourceauthor:[scmacdon AWS]
/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.glue;

//snippet-start:[glue.java2.get_jobs.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.glue.GlueClient;
import software.amazon.awssdk.services.glue.model.GetJobsRequest;
import software.amazon.awssdk.services.glue.model.GetJobsResponse;
import software.amazon.awssdk.services.glue.model.GlueException;
import software.amazon.awssdk.services.glue.model.Job;
import java.util.List;
//snippet-end:[glue.java2.get_jobs.import]

public class GetJobs {

    public static void main(String[] args) {

        Region region = Region.US_EAST_1;
        GlueClient glueClient = GlueClient.builder()
                .region(region)
                .build();

        getAllJobs(glueClient);
        glueClient.close();
    }

    //snippet-start:[glue.java2.get_jobs.main]
    public static void getAllJobs(GlueClient glueClient) {
        try {

            GetJobsRequest jobsRequest = GetJobsRequest.builder()
                .maxResults(10)
                .build();

            GetJobsResponse jobsResponse = glueClient.getJobs(jobsRequest);
            List<Job> jobs = jobsResponse.jobs();

            for (Job job: jobs) {
                System.out.println("Job name is : "+job.name());
            }
        } catch (GlueException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }
    //snippet-end:[glue.java2.get_jobs.main]
  }
