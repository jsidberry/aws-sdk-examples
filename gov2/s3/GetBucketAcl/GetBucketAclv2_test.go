package main

import (
	"context"
	"encoding/json"
	"errors"
	"io/ioutil"
	"testing"
	"time"

	"github.com/aws/aws-sdk-go-v2/aws"
	"github.com/aws/aws-sdk-go-v2/service/s3"
	"github.com/aws/aws-sdk-go-v2/service/s3/types"
)

type S3GetBucketAclImpl struct{}

func (dt S3GetBucketAclImpl) GetBucketAcl(ctx context.Context,
	params *s3.GetBucketAclInput,
	optFns ...func(*s3.Options)) (*s3.GetBucketAclOutput, error) {

	grants := []types.Grant{
		{Grantee: &types.Grantee{DisplayName: aws.String("theuser")}},
	}

	output := &s3.GetBucketAclOutput{
		Grants: grants,
	}

	return output, nil
}

type Config struct {
	BucketName string `json:"BucketName"`
}

var configFileName = "config.json"

var globalConfig Config

func populateConfiguration(t *testing.T) error {
	t.Helper()

	content, err := ioutil.ReadFile(configFileName)
	if err != nil {
		return err
	}

	text := string(content)

	err = json.Unmarshal([]byte(text), &globalConfig)
	if err != nil {
		return err
	}

	if globalConfig.BucketName == "" {
		msg := "You must specify a value for BucketName in " + configFileName
		return errors.New(msg)
	}

	return nil
}

func TestGetBucketAcl(t *testing.T) {
	thisTime := time.Now()
	nowString := thisTime.Format("2006-01-02 15:04:05 Monday")
	t.Log("Starting unit test at " + nowString)

	err := populateConfiguration(t)
	if err != nil {
		t.Fatal(err)
	}

	// Build the request with its input parameters
	input := s3.GetBucketAclInput{
		Bucket: &globalConfig.BucketName,
	}

	api := &S3GetBucketAclImpl{}

	resp, err := FindBucketAcl(context.Background(), *api, &input)
	if err != nil {
		t.Log("Got an error ...:")
		t.Log(err)
		return
	}

	t.Log("Grantee for bucket " + globalConfig.BucketName + ": " + *resp.Grants[0].Grantee.DisplayName)
}
