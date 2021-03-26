const mockListIdentities = jest.fn();
jest.mock("@aws-sdk/client-ses/commands/ListIdentitiesCommand", () => ({
  SES: function SES() {
    this.ListIdentitiesCommand = mockListIdentities;
  },
}));
const { run } = require("../../ses/src/ses_listidentities.js");

test("has to mock SES#listidentities", async (done) => {
  await run();
  expect(mockListIdentities).toHaveBeenCalled;
  done();
});
