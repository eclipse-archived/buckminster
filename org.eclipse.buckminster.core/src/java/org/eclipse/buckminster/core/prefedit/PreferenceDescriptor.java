/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.core.prefedit;

/**
 * @author Thomas Hallgren
 * 
 */
public class PreferenceDescriptor implements IPreferenceDescriptor {
	private Enum<?>[] enums;

	private String label;

	private final String preferenceName;

	private int textWidth = -1;

	private int[] integerRange;

	private final PreferenceType type;

	private IPreferenceValidator validator;

	public PreferenceDescriptor(String preferenceName, PreferenceType type) {
		this(preferenceName, type, preferenceName);
	}

	public PreferenceDescriptor(String preferenceName, PreferenceType type, String label) {
		super();
		this.preferenceName = preferenceName;
		if (type == PreferenceType.Integer)
			textWidth = 10;
		this.type = type;
		this.label = label;
	}

	public Enum<?>[] getEnums() {
		return enums;
	}

	public int[] getIntegerRange() {
		return integerRange;
	}

	public String getLabel() {
		return label;
	}

	public String getName() {
		return preferenceName;
	}

	public int getTextWidth() {
		return textWidth;
	}

	public PreferenceType getType() {
		return type;
	}

	public IPreferenceValidator getValidator() {
		return validator;
	}

	public void setEnums(Enum<?>[] enums) {
		this.enums = enums;
	}

	public void setIntegerRange(int min, int max) {
		this.integerRange = new int[] { min, max };
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setTextWidth(int textWidth) {
		this.textWidth = textWidth;
	}

	public void setValidator(IPreferenceValidator validator) {
		this.validator = validator;
	}
}
