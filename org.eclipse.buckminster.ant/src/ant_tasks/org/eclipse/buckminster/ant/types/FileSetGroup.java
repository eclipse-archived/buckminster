package org.eclipse.buckminster.ant.types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.DataType;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Reference;

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
