/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.jarprocessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.jar.JarInputStream;
import java.util.regex.Matcher;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.model.common.util.BMProperties;

class JarInfo implements IConstants {
	static final String PROP_PACK200_CONDITIONED = "pack200.conditioned"; //$NON-NLS-1$

	static final String PROP_PACK200_ARGS = "pack200.args"; //$NON-NLS-1$

	static final String PROP_PACK200_DEFAULT_ARGS = "pack200.default.args"; //$NON-NLS-1$

	static final String PROP_IS_EXCLUDE = "jarprocessor.exclude"; //$NON-NLS-1$

	static final String PROP_IS_EXCLUDE_PACK = "jarprocessor.exclude.pack"; //$NON-NLS-1$

	static final String PROP_IS_EXCLUDE_SIGN = "jarprocessor.exclude.sign"; //$NON-NLS-1$

	static final String PROP_IS_EXCLUDE_CHILDREN = "jarprocessor.exclude.children"; //$NON-NLS-1$

	static final String PROP_BUCK_IS_EXCLUDE_CHILDREN = "buckminster.exclude.children"; //$NON-NLS-1$

	static final String PROP_IS_EXCLUDE_CHILDREN_PACK = "jarprocessor.exclude.children.pack"; //$NON-NLS-1$

	static final String PROP_IS_EXCLUDE_CHILDREN_SIGN = "jarprocessor.exclude.children.sign"; //$NON-NLS-1$

	static JarInfo getJarInfo(JarInfo parent, String jarName, ZipInputStream input) throws IOException {
		JarInfo jarInfo = new JarInfo(parent, jarName);
		ZipEntry entry;
		HashSet<String> alreadyPacked = new HashSet<String>();
		while ((entry = input.getNextEntry()) != null) {
			String name = entry.getName();
			if (name.endsWith(JAR_SUFFIX)) {
				if (alreadyPacked.contains(name))
					continue;

				JarInfo nested = getJarInfo(jarInfo, name, new JarInputStream(input));
				if (nested.isSigned() && !nested.isConditioned())
					continue;

				if (!(nested.hasClasses() || nested.isNested()))
					continue;

				if (jarInfo.nestedInfos == null)
					jarInfo.nestedInfos = new HashMap<String, JarInfo>();

				jarInfo.nestedInfos.put(name, nested);
				continue;
			}

			if (!jarInfo.classes && name.endsWith(CLASS_SUFFIX)) {
				jarInfo.classes = true;
				continue;
			}

			if (name.endsWith(PACK_SUFFIX)) {
				alreadyPacked.add(name.substring(0, name.length() - PACK_SUFFIX.length()));
				continue;
			}

			if (name.endsWith(PACK_GZ_SUFFIX)) {
				alreadyPacked.add(name.substring(0, name.length() - PACK_GZ_SUFFIX.length()));
				continue;
			}

			if (!name.startsWith(META_INF))
				continue;

			name = name.substring(META_INF.length());
			if (name.indexOf('/') > 0)
				continue;

			if (jarInfo.eclipseInf == null && name.equals(ECLIPSE_INF)) {
				jarInfo.eclipseInf = new BMProperties(input);
				continue;
			}

			if (name.endsWith(SIGNATURE_SUFFIX))
				jarInfo.signed = true;
		}

		if (jarInfo.eclipseInf == null)
			jarInfo.eclipseInf = Collections.emptyMap();

		if (jarInfo.nestedInfos == null || jarInfo.isExcludeChildren())
			jarInfo.nestedInfos = Collections.emptyMap();
		else {
			// Remove info for all nested jars that are already packed. Such
			// infos
			// will only be present in jars where both the canonical and packed
			// form
			// has been retained.
			//
			for (String name : alreadyPacked)
				jarInfo.nestedInfos.remove(name);
		}
		return jarInfo;
	}

	private boolean signed = false;

	private boolean classes = false;

	private final String jarName;

	private final JarInfo parent;

	private Map<String, JarInfo> nestedInfos = null;

	private Map<String, String> eclipseInf = null;

	private JarInfo(JarInfo parentInfo, String jname) {
		parent = parentInfo;
		jarName = jname;
	}

	@Override
	public String toString() {
		StringBuilder bld = new StringBuilder();
		toString(bld);
		return bld.toString();
	}

	void appendArgs(List<String> args) {
		appendArgs(args, true);
	}

	Map<String, String> getEclipseInf() {
		Map<String, String> map = new HashMap<String, String>();
		if (eclipseInf != null)
			map.putAll(eclipseInf);

		List<String> args = new ArrayList<String>();
		appendArgs(args);
		if (!args.isEmpty())
			map.put(PROP_PACK200_ARGS, TextUtils.concat(args, " ")); //$NON-NLS-1$
		return map;
	}

	JarInfo getNestedInfo(String name) {
		return nestedInfos.get(name);
	}

	boolean hasClasses() {
		return classes;
	}

	boolean hasEclipseInf() {
		return eclipseInf != null && !eclipseInf.isEmpty();
	}

	boolean isConditioned() {
		return "true".equalsIgnoreCase(eclipseInf.get(PROP_PACK200_CONDITIONED)); //$NON-NLS-1$
	}

	boolean isExclude() {
		return "true".equalsIgnoreCase(eclipseInf.get(PROP_IS_EXCLUDE)); //$NON-NLS-1$
	}

	boolean isExcludeChildren() {
		String prop = eclipseInf.get(PROP_BUCK_IS_EXCLUDE_CHILDREN);
		if (prop == null)
			prop = eclipseInf.get(PROP_IS_EXCLUDE_CHILDREN);
		return "true".equalsIgnoreCase(prop); //$NON-NLS-1$
	}

	boolean isExcludeChildrenPack() {
		return isExcludeChildren() || "true".equalsIgnoreCase(eclipseInf.get(PROP_IS_EXCLUDE_CHILDREN_PACK)); //$NON-NLS-1$
	}

	boolean isExcludeChildrenSign() {
		return isExcludeChildren() || "true".equalsIgnoreCase(eclipseInf.get(PROP_IS_EXCLUDE_CHILDREN_SIGN)); //$NON-NLS-1$
	}

	boolean isExcludePack() {
		return isExclude() || "true".equalsIgnoreCase(eclipseInf.get(PROP_IS_EXCLUDE_PACK)); //$NON-NLS-1$
	}

	boolean isExcludeSign() {
		return isExclude() || "true".equalsIgnoreCase(eclipseInf.get(PROP_IS_EXCLUDE_SIGN)); //$NON-NLS-1$
	}

	boolean isNested() {
		return !nestedInfos.isEmpty();
	}

	boolean isSigned() {
		return signed;
	}

	void toString(StringBuilder bld) {
		if (parent != null) {
			parent.toString(bld);
			bld.append("!/"); //$NON-NLS-1$
		}
		bld.append(jarName);
	}

	private void appendArgs(List<String> args, boolean atLeaf) {
		if (parent != null) {
			parent.appendArgs(args, false);
			if (parent.isExcludeChildren())
				return;
		}

		boolean hasEffort = false;
		if (atLeaf && !hasClasses()) {
			// This is the only reasonable effort for a jar that contains no
			// classes.
			args.add("-E0"); //$NON-NLS-1$
			hasEffort = true;
		}

		if (eclipseInf != null) {
			for (String arg : TextUtils.splitAndTrim(eclipseInf.get(PROP_PACK200_ARGS), " \t\n")) //$NON-NLS-1$
			{
				Matcher m = RecursivePack200.EFFORT_PATTERN.matcher(arg);
				if (m.matches()) {
					if (hasEffort || !atLeaf)
						continue;
					hasEffort = true;
				}
				args.add(arg);
			}
		}
		if (!hasEffort && atLeaf)
			args.add("-E4"); //$NON-NLS-1$
	}
}
