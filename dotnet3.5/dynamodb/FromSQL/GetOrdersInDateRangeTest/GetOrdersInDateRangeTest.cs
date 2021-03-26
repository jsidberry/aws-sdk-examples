// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved. 
// SPDX - License - Identifier: Apache - 2.0
using System.Net;
using System.Threading;
using System.Threading.Tasks;

using Amazon.DynamoDBv2;
using Amazon.DynamoDBv2.Model;

using Moq;

using Xunit;
using Xunit.Abstractions;

namespace GetOrdersInDateRangeTest
{
    public class GetOrdersInDateRangeTest
    {
        private readonly ITestOutputHelper _output;

        public GetOrdersInDateRangeTest(ITestOutputHelper output)
        {
            this._output = output;
        }

        readonly string _tableName = "testtable";
        readonly string _start = "2020-05-04 05:00:00";
        readonly string _end = "2020-08-13 09:00:00";

        private IAmazonDynamoDB CreateMockDynamoDbClient()
        {
            var mockDynamoDbClient = new Mock<IAmazonDynamoDB>();

            mockDynamoDbClient.Setup(client => client.ScanAsync(
                It.IsAny<ScanRequest>(),
                It.IsAny<CancellationToken>()))
                .Callback<ScanRequest, CancellationToken>((request, token) =>
                {
                    if (!string.IsNullOrEmpty(_tableName))
                    {
                        bool areEqual = _tableName == request.TableName;
                        Assert.True(areEqual, "The provided table name is not the one used to access the table");
                    }
                })
                .Returns((ScanRequest r, CancellationToken token) =>
                {
                    return Task.FromResult(new ScanResponse { HttpStatusCode = HttpStatusCode.OK });
                });

            return mockDynamoDbClient.Object;
        }

        [Fact]
        public async Task CheckGetOrdersInDateRange()
        {
            IAmazonDynamoDB client = CreateMockDynamoDbClient();

            var result = await GetOrdersInDateRange.GetOrdersInDateRange.GetOrdersInDateRangeAsync(client, _tableName, _start, _end);

            bool gotResult = result != null;
            Assert.True(gotResult, "Could NOT get results from scanning table");

            bool ok = result.HttpStatusCode == HttpStatusCode.OK;
            Assert.True(ok, "Could NOT get items from scanning table");

            _output.WriteLine("Got items from table");
        }
    }
}
