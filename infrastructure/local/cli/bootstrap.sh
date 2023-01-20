#!/usr/bin/env sh

sed -i '/nodaemon/d' /etc/supervisord.conf
/usr/bin/supervisord -c "/etc/supervisord.conf"
sleep 5
/opt/bootstrap/cli/boot-local-env.sh
tail -f /tmp/supervisord.log