const mockCreateTable = jest.fn();
jest.mock("@aws-sdk/client-dynamodb/commands/CreateTableCommand", () => ({
  DynamoDB: function DynamoDB() {
    this.CreateTableCommand = mockCreateTable;
  },
}));
const { params, run } = require("../../dynamodb/src/ddb_createtable");

test("has to mock db#createTable", async (done) => {
  await run();
  expect(mockCreateTable).toHaveBeenCalled;
  done();
});
