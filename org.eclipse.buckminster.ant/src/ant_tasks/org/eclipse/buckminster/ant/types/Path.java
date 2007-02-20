package org.eclipse.buckminster.ant.types;

import java.util.ArrayList;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.FileSet;

public class Path extends org.apache.tools.ant.types.Path
{
	private ArrayList<FileSetGroup> m_fileSetGroups;

	public Path(Project proj)
	{
		super(proj);
	}

	@Override
    public String[] list()
    {
    	if(m_fileSetGroups != null)
    	{
    		for(FileSetGroup fsg : m_fileSetGroups)
	    		for(FileSet fs : fsg.getFileSets())
	    			this.addFileset(fs);
    		m_fileSetGroups = null;
    	}
    	return super.list();
    }

	/**
	 * Adds a nested <code>&lt;filesetgroup&gt;</code> element.
	 */
	public void add(FileSetGroup fsGroup) throws BuildException
	{
        if (isReference())
            throw this.noChildrenAllowed();
		if(m_fileSetGroups == null)
			m_fileSetGroups = new ArrayList<FileSetGroup>();
		m_fileSetGroups.add(fsGroup);
	}
}
