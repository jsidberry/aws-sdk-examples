const mockSetTopicAttributes = jest.fn();
jest.mock("@aws-sdk/client-sns/commands/SetTopicAttributesCommand", () => ({
  SNS: function SNS() {
    this.SetTopicAttributesCommand = mockSetTopicAttributes;
  },
}));
const { run } = require("../../sns/src/sns_settopicattributes.js");

test("has to mock SNS#settopicattributes", async (done) => {
  await run();
  expect(mockSetTopicAttributes).toHaveBeenCalled;
  done();
});
