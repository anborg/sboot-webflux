#!/bin/sh
whoami
uname -a
cat /etc/hosts
# Assumes `java` is on PATH in the base image.
exec java $JAVA_OPTS -cp @/app/jib-classpath-file @/app/jib-main-class-file