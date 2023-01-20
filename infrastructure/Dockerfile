FROM localstack/localstack:0.11.2

RUN pip install awscli-local

COPY local /opt/bootstrap/

RUN chmod +x /opt/bootstrap/cli/boot-local-env.sh && sed -i -e 's/\r$//' /opt/bootstrap/cli/boot-local-env.sh

RUN chmod +x /opt/bootstrap/cli/bootstrap.sh && sed -i -e 's/\r$//' /opt/bootstrap/cli/bootstrap.sh

ENTRYPOINT ["/opt/bootstrap/cli/bootstrap.sh"]
