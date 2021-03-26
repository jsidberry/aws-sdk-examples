const mockListSubscriptionsByTopic = jest.fn();
jest.mock(
  "@aws-sdk/client-sns/commands/ListSubscriptionsByTopicCommand",
  () => ({
    SNS: function SNS() {
      this.ListSubscriptionsByTopicCommand = mockListSubscriptionsByTopic;
    },
  })
);
const { run } = require("../../sns/src/sns_listsubscriptions.js");

test("has to mock SNS#listsubscriptions", async (done) => {
  await run();
  expect(mockListSubscriptionsByTopic).toHaveBeenCalled;
  done();
});
