const mockDeleteAccountAlias = jest.fn();
jest.mock("@aws-sdk/client-iam/commands/DeleteAccountAliasCommand", () => ({
  IAM: function IAM() {
    this.DeleteAccountAliasCommand = mockDeleteAccountAlias;
  },
}));
const { params, run } = require("../../iam/src/iam_deleteaccountalias.js");

test("has to mock iam#deleteaccountalias", async (done) => {
  await run();
  expect(mockDeleteAccountAlias).toHaveBeenCalled;
  done();
});
