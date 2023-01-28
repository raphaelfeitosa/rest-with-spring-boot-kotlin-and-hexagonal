#!/bin/bash

### SNS
awslocal sns create-topic \
  --name=data-dev-pipeline-ingestion \
  --output text