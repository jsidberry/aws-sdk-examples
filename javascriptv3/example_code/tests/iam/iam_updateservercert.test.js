const mockUpdateServerCert = jest.fn();
jest.mock(
  "@aws-sdk/client-iam/commands/UpdateServerCertificateCommand",
  () => ({
    IAM: function IAM() {
      this.UpdateServerCertificateCommand = mockUpdateServerCert;
    },
  })
);
const { params, run } = require("../../iam/src/iam_updateservercert.js");

test("has to mock iam#updateservercert", async (done) => {
  await run();
  expect(mockUpdateServerCert).toHaveBeenCalled;
  done();
});
