// snippet-sourcedescription:[DetectSyntax demonstrates how to detect syntax in the text.]
//snippet-keyword:[AWS SDK for Java v2]
// snippet-service:[Amazon Comprehend]
// snippet-keyword:[Code Sample]
// snippet-sourcetype:[full-example]
// snippet-sourcedate:[11/04/2020]
// snippet-sourceauthor:[scmacdon - AWS]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/
package com.example.comprehend;

//snippet-start:[comprehend.java2.detect_syntax.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.comprehend.ComprehendClient;
import software.amazon.awssdk.services.comprehend.model.ComprehendException;
import software.amazon.awssdk.services.comprehend.model.DetectSyntaxRequest;
import software.amazon.awssdk.services.comprehend.model.DetectSyntaxResponse;
import software.amazon.awssdk.services.comprehend.model.SyntaxToken;
import java.util.Iterator;
import java.util.List;
//snippet-end:[comprehend.java2.detect_syntax.import]


public class DetectSyntax {

    public static void main(String[] args) {

        String text = "Amazon.com, Inc. is located in Seattle, WA and was founded July 5th, 1994 by Jeff Bezos, allowing customers to buy everything from books to blenders. Seattle is north of Portland and south of Vancouver, BC. Other notable Seattle - based companies are Starbucks and Boeing.";
        Region region = Region.US_EAST_1;
        ComprehendClient comClient = ComprehendClient.builder()
                .region(region)
                .build();

        System.out.println("Calling DetectSyntax");
        detectAllSyntax(comClient, text);
        comClient.close();
    }

    //snippet-start:[comprehend.java2.detect_syntax.main]
    public static void detectAllSyntax(ComprehendClient comClient, String text){

        try {
            DetectSyntaxRequest detectSyntaxRequest = DetectSyntaxRequest.builder()
                .text(text)
                .languageCode("en")
                .build();

        DetectSyntaxResponse detectSyntaxResult = comClient.detectSyntax(detectSyntaxRequest);
        List<SyntaxToken> syntaxTokens = detectSyntaxResult.syntaxTokens();
        Iterator<SyntaxToken> syntaxIterator = syntaxTokens.iterator();

        while(syntaxIterator.hasNext()) {
            SyntaxToken token = syntaxIterator.next();
            System.out.println("Language is " +token.text());
            System.out.println("Part of speech is "+ token.partOfSpeech().tagAsString());
        }

        } catch (ComprehendException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
        //snippet-end:[comprehend.java2.detect_syntax.main]
    }
}
