<?xml version='1.0'?>
<siteContribution
	xmlns="http://www.eclipse.org/buckminster/SiteContribution-1.0"
	rmapProviderURL="${downloads}/webtools/milestones/site.xml">
	<cspec name="org.eclipse.wtp">
		<dependencies>
			<dependency name="org.eclipse.wst" versionDesignator="[1.6.0.v200611220050-iA4pdQ1Ztn4xezD]" />
			<dependency name="org.eclipse.wst.sdk" versionDesignator="[1.6.0.v200701030255-5NWlC1DPJMQXp2x]" />
			<dependency name="org.eclipse.jst" versionDesignator="[1.6.0.v200611220050-xEzZktHIhCmcvju]" />
			<dependency name="org.eclipse.jst.sdk" versionDesignator="[1.6.0.v20070103025-Mb9NL7qz7czs6bW]" />
			<dependency name="org.eclipse.jst.jsf.feature" versionDesignator="[0.5.1.v20061211-FhPQFd6kg7O9A99]" />
			<dependency name="org.eclipse.jst.jsf_sdk.feature" versionDesignator="[0.5.1.v20061207-X-abWvD1y9w6rkZ]" />
			<dependency name="org.eclipse.jst.jpa.feature" versionDesignator="[1.0.0.v200701040000--BgwYZqamd3YYYY]" />
			<dependency name="org.eclipse.jst.jpa_sdk.feature" versionDesignator="[1.0.0.v200701040000-8OyBql7s6u8jjgg]" />
		</dependencies>
		<groups>
			<public name="Web and J2EE Development">
				<attribute component="org.eclipse.wst" />
				<attribute component="org.eclipse.wst.sdk" />
				<attribute component="org.eclipse.jst" />
				<attribute component="org.eclipse.jst.sdk" />
				<attribute component="org.eclipse.jst.jsf.feature" />
				<attribute component="org.eclipse.jst.jpa.feature" />
			</public>
			<public name="Software Development Kits">
				<attribute component="org.eclipse.jst.jsf_sdk.feature" />
				<attribute component="org.eclipse.jst.jpa_sdk.feature" />
			</public>
		</groups>
	</cspec>
</siteContribution>
