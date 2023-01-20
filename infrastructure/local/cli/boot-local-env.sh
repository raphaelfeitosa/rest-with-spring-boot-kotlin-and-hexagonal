#!/bin/bash
unset http_proxy
unset https_proxy
unset HTTP_PROXY
unset HTTPS_PROXY
export no_proxy="localstack"
export NO_PROXY="localstack"

### SNS
awslocal sns create-topic \
  --name=data-dev-pipeline-ingestion \
  --output text