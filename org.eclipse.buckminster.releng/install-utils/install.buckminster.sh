#!/bin/sh -xe

SCRIPTDIR="`dirname $0`"
[ "${SCRIPTDIR:0:1}" = "/" ] || SCRIPTDIR="`pwd`/$SCRIPTDIR"

. "$SCRIPTDIR/common.sh"

install_buckminster()
{
	install "$1" "$2" \
		http://download.eclipse.org/tools/buckminster/headless-3.6,http://download.cloudsmith.com/buckminster/external-3.6 \
		org.eclipse.buckminster.cmdline.product,org.eclipse.buckminster.core.headless.feature.feature.group,org.eclipse.buckminster.cmdline.product,org.eclipse.buckminster.core.headless.feature.feature.group,org.eclipse.buckminster.subversive.headless.feature.feature.group,org.eclipse.buckminster.cvs.headless.feature.feature.group,org.eclipse.buckminster.git.headless.feature.feature.group,org.eclipse.buckminster.maven.headless.feature.feature.group,org.eclipse.buckminster.pde.headless.feature.feature.group,org.eclipse.buckminster.emma.headless.feature.feature.group \
		-profile Buckminster \
		-roaming
}

if [ -d "$BUCKMINSTER_HOME" ]; then
	rm -rf "$BUCKMINSTER_HOME-bak-bak"
	[ -d "$BUCKMINSTER_HOME-bak" ] && mv "$BUCKMINSTER_HOME-bak" "$BUCKMINSTER_HOME-bak-bak"
	mv "$BUCKMINSTER_HOME" "$BUCKMINSTER_HOME-bak"
fi

install_buckminster "$DIRECTOR_HOME" "$BUCKMINSTER_HOME"
#install "$BUCKMINSTER_HOME-bak" "$BUCKMINSTER_HOME-temp"

# configure proxy settings in the installed Buckminster
cp -R "$SCRIPTDIR/buckminster.settings" "$BUCKMINSTER_HOME/configuration/.settings"
