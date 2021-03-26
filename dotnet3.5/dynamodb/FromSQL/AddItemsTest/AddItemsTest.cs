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

namespace AddItemsTest
{
    public class AddItemsTest
    {
        private readonly ITestOutputHelper _output;

        public AddItemsTest(ITestOutputHelper output)
        {
            this._output = output;
        }

        readonly string _tableName = "testtable";
	    readonly int _id = 3;
        readonly string _keys = "Area,Order_ID,Order_Customer,Order_Product,Order_Date,Order_Status";
        readonly string _values = "Order,1,1,6,2020-07-04 12:00:00,pending";

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
        public async Task CheckAddItems()
        {
            IAmazonDynamoDB client = CreateMockDynamoDbClient();

            var inputs = new string[2];
            inputs[0] = _keys;
            inputs[1] = _values;

            var result = await AddItems.AddItems.AddItemsAsync(false, client, _tableName, inputs, _id);

            bool gotResult = result != null;
            Assert.True(gotResult, "Could NOT get result");

            bool ok = result.HttpStatusCode == HttpStatusCode.OK;
            Assert.True(ok, "Could NOT add items");

            _output.WriteLine("Added items");
        }
    }
}
