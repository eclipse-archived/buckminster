package org.eclipse.buckminster.ant.types;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.DataType;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Reference;
import org.eclipse.buckminster.core.helpers.FileUtils;

public class FileSetGroup extends DataType implements Cloneable
{
	private ArrayList<FileSet> m_fileSets;
	
	public FileSetGroup()
	{		
	}

    /**
     * Adds a nested <code>&lt;fileset&gt;</code> element.
     */
    public void addFileset(FileSet fs) throws BuildException
    {
        if(this.isReference())
            throw noChildrenAllowed();
        if(m_fileSets == null)
        	m_fileSets = new ArrayList<FileSet>();
        m_fileSets.add(fs);
        setChecked(false);
    }	

    public List<FileSet> getFileSets()
    {
    	if(this.isReference())
    	{
    		Object refObj = this.getRefid().getReferencedObject();
    		if(refObj instanceof FileSetGroup)
    			return ((FileSetGroup)refObj).getFileSets();
    		throw new BuildException("Referenced object is not a FileSetGroup");
    	}
    	return m_fileSets == null ? Collections.<FileSet>emptyList() : m_fileSets;
    }

	public void setValue(String value)
	{
		if(value == null || value.length() < 1)
			return;

		StringTokenizer fileSetDecls = new StringTokenizer(value.substring(1), "?");
		while(fileSetDecls.hasMoreTokens())
		{
			StringTokenizer tokens = new StringTokenizer(fileSetDecls.nextToken(), FileUtils.PATH_SEP);
			if(!tokens.hasMoreTokens())
				continue;

			FileSet fs = new FileSet();
			fs.setProject(getProject());
			fs.setDir(new File(tokens.nextToken()));
			fs.setDefaultexcludes(true);
	
			while(tokens.hasMoreTokens())
				fs.createInclude().setName(tokens.nextToken());
			addFileset(fs);
		}
	}

    /**
     * Makes this instance in effect a reference to another Path instance.
     *
     * <p>You must not set another attribute or nest elements inside
     * this element if you make it a reference.</p>
     */
    @Override
    public void setRefid(Reference r) throws BuildException
    {
        if(m_fileSets != null)
            throw tooManyAttributes();
        super.setRefid(r);
    }
}
