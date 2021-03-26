# Guidelines for contributing

Thank you for your interest in contributing to AWS documentation! We greatly value feedback and contributions from our community.

Please read through this document before you submit any pull requests or issues. It will help us work together more effectively.

## What to expect when you contribute

When you submit a pull request, our team is notified and will respond as quickly as we can. We'll do our best to work with you to ensure that your pull request adheres to our style and standards. If we merge your pull request, we might make additional edits later for style or clarity.

The AWS documentation source files on GitHub aren't published directly to the official documentation website. If we merge your pull request, we'll publish your changes to the documentation website as soon as we can, but they won't appear immediately or automatically.

We look forward to receiving your pull requests for:

* New content you'd like to contribute (such as new code examples or tutorials)
* Inaccuracies in the content
* Information gaps in the content that need more detail to be complete
* Typos or grammatical errors
* Suggested rewrites that improve clarity and reduce confusion

**Note:** We all write differently, and you might not like how we've written or organized something currently. We want that feedback. But please be sure that your request for a rewrite is supported by the previous criteria. If it isn't, we might decline to merge it.

## Where to put a new code example

If you are contributing a new, single-API code example,
add it to the appropriate *language*/*service*/*action* directory,
where *action* follows the naming convention in the service reference docs for that service's action.
For example, a new code example that lists your Amazon ECS clusters using version 2 
of the AWS SDK for Go would be
**gov2/ecs/ListClusters/ListClusters.go**.

If your example uses multiple APIs from a single service,
use the action that you consider most important.
For example, if you are listing resources, then adding a resource,
and listing the resources once again, use the action that adds the resource.

If your example uses multiple services and you aren't sure where to add it to the repo,
create an issue and describe what your code example does. 
One of the AWS SDK code example team members will follow up with you in that issue.

## How to contribute

To contribute, send us a pull request. For small changes, such as fixing a typo or adding a link, you can use the [GitHub Edit Button](https://blog.github.com/2011-04-26-forking-with-the-edit-button/). For larger changes:

1. [Fork the repository](https://help.github.com/articles/fork-a-repo/).
2. In your fork, make your change in a branch that's based on this repo's **master** branch.
3. Commit the change to your fork, using a clear and descriptive commit message.
4. [Create a pull request](https://help.github.com/articles/creating-a-pull-request-from-a-fork/), answering any questions in the pull request form.

Before you send us a pull request, please be sure that:

1. You're working from the latest source on the **master** branch.
2. You check [existing open](https://github.com/awsdocs/aws-doc-sdk-examples/pulls), and [recently closed](https://github.com/awsdocs/aws-doc-sdk-examples/pulls?q=is%3Apr+is%3Aclosed), pull requests to be sure that someone else hasn't already addressed the problem.
3. You [create an issue](https://github.com/awsdocs/aws-doc-sdk-examples/issues/new) before working on a contribution that will take a significant amount of your time.

For contributions that will take a significant amount of time, [open a new issue](https://github.com/awsdocs/aws-doc-sdk-examples/issues/new) to pitch your idea before you get started. Explain the problem and describe the content you want to see added to the documentation. Let us know if you'll write it yourself or if you'd like us to help. We'll discuss your proposal with you and let you know whether we're likely to accept it. We don't want you to spend a lot of time on a contribution that might be outside the scope of the documentation or that's already in the works.

## Guidelines for contributing code examples

Help us raise the bar for code examples, so that your code example will provide the most value it can to users. 

When you submit a new code example to us, we strongly encourage you to include the following:

* **Provide a README.md file at the root level of your submission to help users save time and effort when they work with your example.** 
  At a minimum, your README.md file should describe what your example demonstrates, call out any prerequisites needed to run it, and then tell users how to run it. 
  [Here's a good example](https://github.com/awsdocs/aws-doc-sdk-examples/blob/master/gov2/s3/README.md) 
  of a useful README.md file.
* **Write your code in a modular style to help users more easily copy and reuse it in their own solutions.** 
  By "modular," we mean that your code should accept inputs from the caller and return outputs to the caller. Provide comments in the code that describe these inputs and outputs. Also, don't hard-code input values in modularized code. Instead, provide these values through your unit tests, as described in the next point. 
  [Here's a good example](https://github.com/awsdocs/aws-doc-sdk-examples/blob/master/gov2/sts/AssumeRole/AssumeRolev2.go) 
  of code written in a modular style.
* **Add some type of [unit tests](https://en.wikipedia.org/wiki/Unit_testing ) to help users more easily run your example.** These unit tests can use hard-coded input values (or input values provided by the user) to call your example code. 
  [Here's a good example](https://github.com/awsdocs/aws-doc-sdk-examples/blob/master/dotnet3.5/dynamodb/GetItemTest/GetItemTest.cs) 
  of a unit test that use hard-coded input values. 
* **Add standard error or exception handling to your code to enable easier troubleshooting and recovery.** [
  Here's a good example](https://github.com/awsdocs/aws-doc-sdk-examples/blob/master/javav2/example_code/lambda/src/main/java/com/example/lambda/LambdaInvoke.java) 
  of standard error/exception handling.  
* **Don't include personal account data, keys, or IDs in your examples**. Code should obtain access keys from the standard AWS SDK credentials and configuration files, use environment variables or external data files, or query the user for this information.
* **Format code lines to 80 characters wherever possible**. Long lines can often spill off the side of the screen in the PDF versions of the documentation, making the code unreadable. If your code includes long text strings, consider breaking these into smaller chunks and concatenating them.
* **Use spaces, not tabs, for indentation**. Tabs are variable length in most editors, but will usually render as 8 characters wide in printed documentation. To ensure consistent formatting in printed code, we recommend using *4 spaces*, unless the target language has a different convention. You can ignore this rule for makefiles, which might *require* the use of tabs. But these are typically used only for building examples, and aren't  included in documentation.
* **All code must be submitted under the [Apache 2.0 license](https://www.apache.org/licenses/LICENSE-2.0).**
                                                      
If your code example submission is missing any of these things, we might ask you to include them before we merge. 

Although many older code examples in this repo don't contain all of these things, we're working to ensure that all newer ones do.

## Finding contributions to work on

If you'd like to contribute, but don't have a project in mind, look at the [open issues](https://github.com/awsdocs/aws-doc-sdk-examples/issues) in this repository for some ideas. Any issues with the [help wanted](https://github.com/awsdocs/aws-doc-sdk-examples/labels/help%20wanted) or [enhancement](https://github.com/awsdocs/aws-doc-sdk-examples/labels/enhancement) labels are a great place to start.

In addition to written content, we really appreciate new examples for our documentation, such as examples for different platforms or environments, and examples in additional programming languages.

## Code of conduct

This project has adopted the [Amazon Open Source Code of Conduct](https://aws.github.io/code-of-conduct). For more information, see the [Code of Conduct FAQ](https://aws.github.io/code-of-conduct-faq) or contact [opensource-codeofconduct@amazon.com](mailto:opensource-codeofconduct@amazon.com) with any additional questions or comments.

## Security issue notifications

If you discover a potential security issue, please notify AWS Security via our [vulnerability reporting page](http://aws.amazon.com/security/vulnerability-reporting/). Please do **not** create a public issue on GitHub.

## Licensing

See the [LICENSE](https://github.com/awsdocs/aws-doc-sdk-examples/blob/master/LICENSE) file for this project's licensing. We will ask you to confirm the licensing of your contribution. We may ask you to sign a [Contributor License Agreement (CLA)](http://en.wikipedia.org/wiki/Contributor_License_Agreement) for larger changes.
