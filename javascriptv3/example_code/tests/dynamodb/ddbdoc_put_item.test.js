const mockPutItem = jest.fn();
jest.mock("@aws-sdk/client-dynamodb/commands/PutItemCommand", () => ({
    DynamoDB: function DynamoDB() {
        this.PutItemCommand = mockPutItem;
    },
}));
const { params, run } = require("../../dynamodb/src/ddb_putitem");

test("has to mock db#putItem", async (done) => {
    await run();
    expect(mockPutItem).toHaveBeenCalled;
    done();
});
