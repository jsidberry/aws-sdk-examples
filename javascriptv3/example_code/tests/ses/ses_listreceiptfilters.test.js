const mockListReceiptFilters = jest.fn();
jest.mock("@aws-sdk/client-ses/commands/ListReceiptFiltersCommand", () => ({
  SES: function SES() {
    this.ListReceiptFiltersCommand = mockListReceiptFilters;
  },
}));
const { run } = require("../../ses/src/ses_listreceiptfilters.js");

test("has to mock SES#listreceiptfilters", async (done) => {
  await run();
  expect(mockListReceiptFilters).toHaveBeenCalled;
  done();
});
