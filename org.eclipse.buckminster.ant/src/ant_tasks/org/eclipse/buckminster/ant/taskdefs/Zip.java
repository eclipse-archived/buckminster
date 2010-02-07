/*****************************************************************************
 * (c) 2004-2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 ****************************************************************************/

package org.eclipse.buckminster.ant.taskdefs;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.zip.ZipOutputStream;
import org.eclipse.buckminster.ant.types.FileSetGroup;

/**
 * @author Thomas Hallgren
 */
public class Zip extends org.apache.tools.ant.taskdefs.Zip {
	private ArrayList<FileSetGroup> fileSetGroups;

	private ArrayList<FileSetGroup> zipGroupFileSetGroups;

	private static final Method File_canExecute;

	static {
		Method fce;
		try {
			fce = File.class.getMethod("canExecute");
		} catch (Exception e) {
			fce = null;
		}
		File_canExecute = fce;
	}

	/**
	 * Adds a nested <code>&lt;filesetgroup&gt;</code> element.
	 */
	public void add(FileSetGroup fsGroup) throws BuildException {
		if (fileSetGroups == null)
			fileSetGroups = new ArrayList<FileSetGroup>();
		fileSetGroups.add(fsGroup);
	}

	/**
	 * Adds a nested <code>&lt;filesetgroup&gt;</code> element targeted for
	 * zipgroupfilesets.
	 */
	public void addZipGroupFilesetGroup(FileSetGroup setGroup) {
		if (zipGroupFileSetGroups == null)
			zipGroupFileSetGroups = new ArrayList<FileSetGroup>();
		zipGroupFileSetGroups.add(setGroup);
	}

	@Override
	public void execute() throws BuildException {
		if (fileSetGroups != null) {
			for (FileSetGroup fsg : fileSetGroups)
				for (FileSet fs : fsg.getFileSets())
					this.addFileset(fs);
			fileSetGroups = null;
		}
		if (zipGroupFileSetGroups != null) {
			for (FileSetGroup fsg : zipGroupFileSetGroups)
				for (FileSet fs : fsg.getFileSets())
					this.addZipGroupFileset(fs);
			zipGroupFileSetGroups = null;
		}
		super.execute();
	}

	@Override
	protected void zipFile(File file, ZipOutputStream zOut, String vPath, int mode) throws IOException {

		if (File_canExecute != null) {
			// We're running Java 1.6 or higher. Check the execution bits
			try {
				if (((Boolean) File_canExecute.invoke(file)).booleanValue())
					mode |= 0111;
			} catch (Exception e) {
			}
		}
		super.zipFile(file, zOut, vPath, mode);
	}
}
