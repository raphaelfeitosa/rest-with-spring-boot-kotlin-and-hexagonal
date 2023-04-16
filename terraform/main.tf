resource "aws_vpc" "proposalapi_vpc_1" {
  cidr_block           = "10.0.0.0/16"
  enable_dns_hostnames = true
  enable_dns_support   = true

  tags = {
    "Name" = "proposalapi_vpc_1"
  }
}


resource "aws_subnet" "proposalapi_subnet_pub_1a" {
  vpc_id                  = aws_vpc.proposalapi_vpc_1.id
  cidr_block              = "10.0.1.0/24"
  availability_zone       = "us-east-1a"
  map_public_ip_on_launch = true

  tags = {
    "Name" = "proposalapi_subnet_pub_1a"
  }

}

resource "aws_internet_gateway" "proposalapi_igw_1a" {
  vpc_id = aws_vpc.proposalapi_vpc_1.id

  tags = {
    "Name" = "proposalapi_igw_1a"
  }
}

resource "aws_route_table" "proposalapi_rtb_pub" {
  vpc_id = aws_vpc.proposalapi_vpc_1.id

  tags = {
    "Name" = "proposalapi_rtb_pub"
  }
}

resource "aws_route" "proposalapi_default_rtb" {
  route_table_id         = aws_route_table.proposalapi_rtb_pub.id
  destination_cidr_block = "0.0.0.0/0"
  gateway_id             = aws_internet_gateway.proposalapi_igw_1a.id
}

resource "aws_route_table_association" "proposalapi_rtba_pub_1a" {
  route_table_id = aws_route_table.proposalapi_rtb_pub.id
  subnet_id      = aws_subnet.proposalapi_subnet_pub_1a.id
}

resource "aws_instance" "proposalapi_ec2_inst" {
  instance_type          = "t2.micro"
  ami                    = data.aws_ami.proposalapi_server_ami.id
  key_name               = aws_key_pair.proposalapi_key.id
  vpc_security_group_ids = [aws_security_group.proposalapi_sg.id]
  subnet_id              = aws_subnet.proposalapi_subnet_pub_1a.id
  user_data              = file("userdata.tpl")

  root_block_device {
    volume_size = 8
  }

  tags = {
    "Name" = "proposalapi_ec2_inst"
  }
}