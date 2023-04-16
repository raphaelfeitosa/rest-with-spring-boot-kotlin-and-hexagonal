resource "aws_security_group" "proposalapi_sg" {
  name        = "proposalapi_sg"
  description = "proposalapi security group"
  vpc_id      = aws_vpc.proposalapi_vpc_1.id

  tags = {
    "Name" = "proposalapi_sg"
  }
}

resource "aws_security_group_rule" "public_out" {
  type        = "egress"
  from_port   = 0
  to_port     = 0
  protocol    = "-1"
  cidr_blocks = ["0.0.0.0/0"]

  security_group_id = aws_security_group.proposalapi_sg.id
}

resource "aws_security_group_rule" "public_in_ssh" {
  type              = "ingress"
  from_port         = 22
  to_port           = 22
  protocol          = "tcp"
  cidr_blocks       = ["0.0.0.0/0"]
  security_group_id = aws_security_group.proposalapi_sg.id
}

resource "aws_key_pair" "proposalapi_key" {
  key_name   = "proposalapi_key"
  public_key = file("~/.ssh/proposal-api-key.pub")
}

resource "aws_security_group_rule" "public_in_http" {
  type              = "ingress"
  from_port         = 8082
  to_port           = 8082
  protocol          = "tcp"
  cidr_blocks       = ["0.0.0.0/0"]
  security_group_id = aws_security_group.proposalapi_sg.id
}