const mockListAccountAliases = jest.fn();
jest.mock("@aws-sdk/client-iam/commands/ListAccountAliasesCommand", () => ({
  IAM: function IAM() {
    this.ListAccountAliasesCommand = mockListAccountAliases;
  },
}));
const { params, run } = require("../../iam/src/iam_listusers.js");

test("has to mock iam#listusers", async (done) => {
  await run();
  expect(mockListAccountAliases).toHaveBeenCalled;
  done();
});
