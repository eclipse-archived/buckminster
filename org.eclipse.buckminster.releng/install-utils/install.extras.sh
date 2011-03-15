#!/bin/sh -xe

SCRIPTDIR="`dirname $0`"
[ "${SCRIPTDIR:0:1}" = "/" ] || SCRIPTDIR="`pwd`/$SCRIPTDIR"

. "$SCRIPTDIR/common.sh"

EXTRAS_RELENG_PROJECT_NAME=org.eclipse.buckminster.extras.releng
EXTRAS_RELENG_PROJECT_DIRECTORY="$SCRIPTDIR/$EXTRAS_RELENG_PROJECT_NAME"
EXTRAS_REPOSITORY="$SCRIPTDIR/extras.repository"

rm -rf "$EXTRAS_REPOSITORY"

pushd "$SCRIPTDIR"
JAVA_OPTS=(-Dextras.repository="$EXTRAS_REPOSITORY" -Declipse.download="file:/home/data/httpd/download.eclipse.org")
run_framework "$BUCKMINSTER_HOME" \
	-S "$EXTRAS_RELENG_PROJECT_DIRECTORY/build.extras.repo.bmscript"
unset JAVA_OPTS[@]
popd

install "$DIRECTOR_HOME" "$BUCKMINSTER_HOME" \
	"file:/$EXTRAS_REPOSITORY" \
	org.eclipse.equinox.p2.repository.tools,org.eclipse.help.base \
	-profile Buckminster

rm -rf "$EXTRAS_REPOSITORY"
