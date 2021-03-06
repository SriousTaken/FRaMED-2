/**
 */
package org.framed.iorm.featuremodel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>FRa MED Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.framed.iorm.featuremodel.FRaMEDConfiguration#getFeatures <em>Features</em>}</li>
 * </ul>
 *
 * @see org.framed.iorm.featuremodel.FeaturemodelPackage#getFRaMEDConfiguration()
 * @model
 * @generated
 */
public interface FRaMEDConfiguration extends EObject {
	/**
	 * Returns the value of the '<em><b>Features</b></em>' containment reference list.
	 * The list contents are of type {@link org.framed.iorm.featuremodel.FRaMEDFeature}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Features</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Features</em>' containment reference list.
	 * @see org.framed.iorm.featuremodel.FeaturemodelPackage#getFRaMEDConfiguration_Features()
	 * @model containment="true"
	 * @generated
	 */
	EList<FRaMEDFeature> getFeatures();

} // FRaMEDConfiguration
