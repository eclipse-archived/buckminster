/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common.util;

import java.lang.CharSequence;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import org.eclipse.buckminster.model.common.CommonPackage;
import org.eclipse.buckminster.model.common.ComponentIdentifier;
import org.eclipse.buckminster.model.common.ComponentName;
import org.eclipse.buckminster.model.common.ComponentRequest;
import org.eclipse.buckminster.model.common.Constant;
import org.eclipse.buckminster.model.common.Documentation;
import org.eclipse.buckminster.model.common.Format;
import org.eclipse.buckminster.model.common.Match;
import org.eclipse.buckminster.model.common.Properties;
import org.eclipse.buckminster.model.common.PropertyRef;
import org.eclipse.buckminster.model.common.Replace;
import org.eclipse.buckminster.model.common.RxAssembly;
import org.eclipse.buckminster.model.common.RxGroup;
import org.eclipse.buckminster.model.common.RxPart;
import org.eclipse.buckminster.model.common.RxPattern;
import org.eclipse.buckminster.model.common.Split;
import org.eclipse.buckminster.model.common.SplitType;
import org.eclipse.buckminster.model.common.ToLower;
import org.eclipse.buckminster.model.common.ToUpper;
import org.eclipse.buckminster.model.common.Value;
import org.eclipse.buckminster.model.common.ValueFilter;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.ecore.xml.type.util.XMLTypeUtil;
import org.eclipse.emf.ecore.xml.type.util.XMLTypeValidator;
import org.eclipse.equinox.p2.metadata.IVersionFormat;
import org.eclipse.equinox.p2.metadata.IVersionedId;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.metadata.VersionRange;

/**
 * <!-- begin-user-doc --> The <b>Validator</b> for the model. <!-- end-user-doc
 * -->
 * 
 * @see org.eclipse.buckminster.model.common.CommonPackage
 * @generated
 */
public class CommonValidator extends EObjectValidator {
	/**
	 * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final CommonValidator INSTANCE = new CommonValidator();

	/**
	 * A constant for the
	 * {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of
	 * diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes}
	 * from this package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "org.eclipse.buckminster.model.common";

	/**
	 * A constant with a fixed name that can be used as the base value for
	 * additional hand written constants. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

	/**
	 * A constant with a fixed name that can be used as the base value for
	 * additional hand written constants in a derived class. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * The cached base package validator. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected XMLTypeValidator xmlTypeValidator;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @see #validatePropertyKey_Pattern
	 */
	public static final PatternMatcher[][] PROPERTY_KEY__PATTERN__VALUES = new PatternMatcher[][] { new PatternMatcher[] { XMLTypeUtil
			.createPatternMatcher("[A-Za-z0-9_.${}/]+") } };

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @see #validateUuid_Pattern
	 */
	public static final PatternMatcher[][] UUID__PATTERN__VALUES = new PatternMatcher[][] { new PatternMatcher[] { XMLTypeUtil
			.createPatternMatcher("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}") } };

	/**
	 * Creates an instance of the switch. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public CommonValidator() {
		super();
		xmlTypeValidator = XMLTypeValidator.INSTANCE;
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this
	 * validator's diagnostics. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to
		// this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateAbstractDocumentRoot(EObject abstractDocumentRoot, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(abstractDocumentRoot, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateCharSequence(CharSequence charSequence, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateComparable(Comparable<?> comparable, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject) comparable, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateComponentIdentifier(ComponentIdentifier componentIdentifier, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(componentIdentifier, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateComponentName(ComponentName componentName, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(componentName, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateComponentRequest(ComponentRequest componentRequest, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(componentRequest, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateConstant(Constant constant, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(constant, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateCoreException(CoreException coreException, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateDocumentation(Documentation documentation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(documentation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateFilter(Filter filter, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateFormat(Format format, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(format, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateIProgressMonitor(IProgressMonitor iProgressMonitor, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject) iProgressMonitor, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateIStatus(IStatus iStatus, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject) iStatus, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateIStatusArray(IStatus[] iStatusArray, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateIVersionedId(IVersionedId iVersionedId, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject) iVersionedId, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateIVersionFormat(IVersionFormat iVersionFormat, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateList(List<?> list, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateMatch(Match match, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(match, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validatePattern(Pattern pattern, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateProperties(Properties properties, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(properties, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validatePropertyConstant(Map.Entry<?, ?> propertyConstant, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject) propertyConstant, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validatePropertyElement(Map.Entry<?, ?> propertyElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject) propertyElement, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validatePropertyKey(String propertyKey, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validatePropertyKey_Pattern(propertyKey, diagnostics, context);
		return result;
	}

	/**
	 * Validates the Pattern constraint of '<em>Property Key</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validatePropertyKey_Pattern(String propertyKey, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validatePattern(CommonPackage.Literals.PROPERTY_KEY, propertyKey, PROPERTY_KEY__PATTERN__VALUES, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validatePropertyRef(PropertyRef propertyRef, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(propertyRef, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateReplace(Replace replace, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(replace, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateRxAssembly(RxAssembly rxAssembly, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(rxAssembly, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateRxGroup(RxGroup rxGroup, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(rxGroup, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateRxPart(RxPart rxPart, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(rxPart, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateRxPattern(RxPattern rxPattern, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(rxPattern, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateSplit(Split split, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(split, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateSplitType(SplitType splitType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateStringBuilder(StringBuilder stringBuilder, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateThrowable(Throwable throwable, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateToLower(ToLower toLower, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(toLower, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateToUpper(ToUpper toUpper, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(toUpper, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateURL(URL url, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateUuid(UUID uuid, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validateUuid_Pattern(uuid, diagnostics, context);
		return result;
	}

	/**
	 * Validates the Pattern constraint of '<em>Uuid</em>'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateUuid_Pattern(UUID uuid, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validatePattern(CommonPackage.Literals.UUID, uuid, UUID__PATTERN__VALUES, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateValue(Value value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(value, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateValueFilter(ValueFilter valueFilter, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(valueFilter, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateVersion(Version version, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateVersionRange(VersionRange versionRange, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * Returns the package of this validator switch. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	protected EPackage getEPackage() {
		return CommonPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the
	 * model. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
			case CommonPackage.ABSTRACT_DOCUMENT_ROOT:
				return validateAbstractDocumentRoot((EObject) value, diagnostics, context);
			case CommonPackage.COMPARABLE:
				return validateComparable((Comparable<?>) value, diagnostics, context);
			case CommonPackage.COMPONENT_IDENTIFIER:
				return validateComponentIdentifier((ComponentIdentifier) value, diagnostics, context);
			case CommonPackage.COMPONENT_NAME:
				return validateComponentName((ComponentName) value, diagnostics, context);
			case CommonPackage.COMPONENT_REQUEST:
				return validateComponentRequest((ComponentRequest) value, diagnostics, context);
			case CommonPackage.CONSTANT:
				return validateConstant((Constant) value, diagnostics, context);
			case CommonPackage.DOCUMENTATION:
				return validateDocumentation((Documentation) value, diagnostics, context);
			case CommonPackage.FORMAT:
				return validateFormat((Format) value, diagnostics, context);
			case CommonPackage.IPROGRESS_MONITOR:
				return validateIProgressMonitor((IProgressMonitor) value, diagnostics, context);
			case CommonPackage.ISTATUS:
				return validateIStatus((IStatus) value, diagnostics, context);
			case CommonPackage.IVERSIONED_ID:
				return validateIVersionedId((IVersionedId) value, diagnostics, context);
			case CommonPackage.MATCH:
				return validateMatch((Match) value, diagnostics, context);
			case CommonPackage.PROPERTIES:
				return validateProperties((Properties) value, diagnostics, context);
			case CommonPackage.PROPERTY_CONSTANT:
				return validatePropertyConstant((Map.Entry<?, ?>) value, diagnostics, context);
			case CommonPackage.PROPERTY_ELEMENT:
				return validatePropertyElement((Map.Entry<?, ?>) value, diagnostics, context);
			case CommonPackage.PROPERTY_REF:
				return validatePropertyRef((PropertyRef) value, diagnostics, context);
			case CommonPackage.REPLACE:
				return validateReplace((Replace) value, diagnostics, context);
			case CommonPackage.RX_ASSEMBLY:
				return validateRxAssembly((RxAssembly) value, diagnostics, context);
			case CommonPackage.RX_GROUP:
				return validateRxGroup((RxGroup) value, diagnostics, context);
			case CommonPackage.RX_PART:
				return validateRxPart((RxPart) value, diagnostics, context);
			case CommonPackage.RX_PATTERN:
				return validateRxPattern((RxPattern) value, diagnostics, context);
			case CommonPackage.SPLIT:
				return validateSplit((Split) value, diagnostics, context);
			case CommonPackage.TO_LOWER:
				return validateToLower((ToLower) value, diagnostics, context);
			case CommonPackage.TO_UPPER:
				return validateToUpper((ToUpper) value, diagnostics, context);
			case CommonPackage.VALUE:
				return validateValue((Value) value, diagnostics, context);
			case CommonPackage.VALUE_FILTER:
				return validateValueFilter((ValueFilter) value, diagnostics, context);
			case CommonPackage.SPLIT_TYPE:
				return validateSplitType((SplitType) value, diagnostics, context);
			case CommonPackage.CHAR_SEQUENCE:
				return validateCharSequence((CharSequence) value, diagnostics, context);
			case CommonPackage.CORE_EXCEPTION:
				return validateCoreException((CoreException) value, diagnostics, context);
			case CommonPackage.FILTER:
				return validateFilter((Filter) value, diagnostics, context);
			case CommonPackage.ISTATUS_ARRAY:
				return validateIStatusArray((IStatus[]) value, diagnostics, context);
			case CommonPackage.IVERSION_FORMAT:
				return validateIVersionFormat((IVersionFormat) value, diagnostics, context);
			case CommonPackage.LIST:
				return validateList((List<?>) value, diagnostics, context);
			case CommonPackage.PATTERN:
				return validatePattern((Pattern) value, diagnostics, context);
			case CommonPackage.PROPERTY_KEY:
				return validatePropertyKey((String) value, diagnostics, context);
			case CommonPackage.STRING_BUILDER:
				return validateStringBuilder((StringBuilder) value, diagnostics, context);
			case CommonPackage.THROWABLE:
				return validateThrowable((Throwable) value, diagnostics, context);
			case CommonPackage.URL:
				return validateURL((URL) value, diagnostics, context);
			case CommonPackage.UUID:
				return validateUuid((UUID) value, diagnostics, context);
			case CommonPackage.VERSION_RANGE:
				return validateVersionRange((VersionRange) value, diagnostics, context);
			case CommonPackage.VERSION:
				return validateVersion((Version) value, diagnostics, context);
			default:
				return true;
		}
	}

} // CommonValidator
