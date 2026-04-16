#!/bin/sh

DIR=`dirname "$0"`
exec java -jar "$DIR/gradle/wrapper/gradle-wrapper.jar" "$@"
