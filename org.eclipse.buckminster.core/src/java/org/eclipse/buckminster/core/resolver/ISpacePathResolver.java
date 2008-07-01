/**
 * 
 */
package org.eclipse.buckminster.core.resolver;

/**
 * Expand a space path into its canonical representation
 *
 * @author Thomas Hallgren
 */
public interface ISpacePathResolver
{
	String[] expandSpacePath(String[] spacePath);
}
