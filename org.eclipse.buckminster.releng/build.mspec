<?xml version="1.0" encoding="UTF-8"?>
<mspec
	xmlns="http://www.eclipse.org/buckminster/MetaData-1.0"
	name="Buckminster MSPEC"
	materializer="p2"
	installLocation="${targetPlatformPath}"
	url="build-${eclipse.release}.cquery">
	<mspecNode namePattern="^org\.eclipse\.buckminster(\..+)?" materializer="workspace" />
	<mspecNode namePattern="^org\.slf4j\.extendable$" materializer="workspace" />
	<mspecNode namePattern="^org\.eclipse\.equinox\.p2\.director\.(product|feature)$" materializer="workspace" />
</mspec>