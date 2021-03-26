const mockDeleteTable = jest.fn();
jest.mock("@aws-sdk/client-dynamodb/commands/DeleteTableCommand", () => ({
  DynamoDB: function DynamoDB() {
    this.DeleteTableCommand = mockDeleteTable;
  },
}));
const { params, run } = require("../../dynamodb/src/ddb_deletetable");

test("has to mock db#deleteTable", async (done) => {
  await run();
  expect(mockDeleteTable).toHaveBeenCalled;
  done();
});
