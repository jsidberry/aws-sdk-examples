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

namespace DeleteItemsTest 
{
    public class DeleteItemsTest
    {
        private readonly ITestOutputHelper _output;

        public DeleteItemsTest(ITestOutputHelper output)
        {
            this._output = output;
        }

        readonly string _tableName = "testtable";
        readonly string _ids = "1 2 3";
        readonly string _area = "test";

        private IAmazonDynamoDB CreateMockDynamoDbClient()
        {
            var mockDynamoDbClient = new Mock<IAmazonDynamoDB>();

            mockDynamoDbClient.Setup(client => client.BatchWriteItemAsync(
                It.IsAny<BatchWriteItemRequest>(),
                It.IsAny<CancellationToken>()))
                .Callback<BatchWriteItemRequest, CancellationToken>((request, token) =>
                {})
                .Returns((BatchWriteItemRequest r, CancellationToken token) =>
                {
                    return Task.FromResult(new BatchWriteItemResponse { HttpStatusCode = HttpStatusCode.OK });
                });

            return mockDynamoDbClient.Object;
        }

        [Fact]
        public async Task CheckDeleteItems()
        {
            IAmazonDynamoDB client = CreateMockDynamoDbClient();

            var result = await DeleteItems.DeleteItems.RemoveItemsAsync(client, _tableName, _ids, _area);

            bool gotResult = result != null;
            Assert.True(gotResult, "Could NOT get result");

            bool ok = result.HttpStatusCode == HttpStatusCode.OK;
            Assert.True(ok, "Could NOT delete items");

            _output.WriteLine("Deleted items");
        }
    }
}
