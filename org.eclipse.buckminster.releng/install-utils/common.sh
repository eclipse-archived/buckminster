#JAVA_CMD=/shared/webtools/apps/ibm-java-ppc-606/bin/java
JAVA_CMD=/shared/common/sun-jdk1.6.0_21_x64/bin/java
DIRECTOR_HOME=/shared/common/p2.director-3.5
BUCKMINSTER_HOME=/shared/common/buckminster-3.6

find_launcher()
{
	find "$1/plugins" -type f -name "org.eclipse.equinox.launcher_*.jar" -print | {
		LATEST_LAUNCHER=""
		LATEST_MODTIME=0

		while read LAUNCHER; do
			MODTIME=`date +%s --reference="$LAUNCHER"`
			[ $MODTIME -gt $LATEST_MODTIME ] && {
				LATEST_LAUNCHER="$LAUNCHER"
				LATEST_MODTIME=$MODTIME
			}
		done

		[ $LATEST_MODTIME -gt 0 ] || {
			echo "No launcher found in $1" >&2
			exit 1
		}

		echo "$LATEST_LAUNCHER"
	}
}

run_framework()
{
	LAUNCHER="`find_launcher "$1"`"
	[ $? -eq 0 ] || return $?

	shift

	"$JAVA_CMD" "${JAVA_OPTS[@]}" -jar "$LAUNCHER" -data "$SCRIPTDIR" "$@"

	rm -rf "$SCRIPTDIR/.metadata"
}

install()
{
	FRAMEWORKDIR="$1"
	DESTINATION="$2"
	REPOSITORY="$3"
	IUS="$4"

	shift 4

	run_framework "$FRAMEWORKDIR" \
		-application org.eclipse.equinox.p2.director \
		-destination "$DESTINATION" \
		-repository "$REPOSITORY" \
		-installIU "$IUS" \
		"$@"
}
