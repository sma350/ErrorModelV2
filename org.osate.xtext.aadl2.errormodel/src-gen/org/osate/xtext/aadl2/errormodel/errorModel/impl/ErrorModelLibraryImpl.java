/**
 */
package org.osate.xtext.aadl2.errormodel.errorModel.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.osate.aadl2.PropertyAssociation;

import org.osate.aadl2.impl.AnnexLibraryImpl;

import org.osate.xtext.aadl2.errormodel.errorModel.ErrorBehaviorStateMachine;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorModelLibrary;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorModelPackage;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorType;
import org.osate.xtext.aadl2.errormodel.errorModel.TypeMappingSet;
import org.osate.xtext.aadl2.errormodel.errorModel.TypeSet;
import org.osate.xtext.aadl2.errormodel.errorModel.TypeTransformationSet;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Library</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.osate.xtext.aadl2.errormodel.errorModel.impl.ErrorModelLibraryImpl#getExtends <em>Extends</em>}</li>
 *   <li>{@link org.osate.xtext.aadl2.errormodel.errorModel.impl.ErrorModelLibraryImpl#getTypes <em>Types</em>}</li>
 *   <li>{@link org.osate.xtext.aadl2.errormodel.errorModel.impl.ErrorModelLibraryImpl#getTypesets <em>Typesets</em>}</li>
 *   <li>{@link org.osate.xtext.aadl2.errormodel.errorModel.impl.ErrorModelLibraryImpl#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.osate.xtext.aadl2.errormodel.errorModel.impl.ErrorModelLibraryImpl#getBehaviors <em>Behaviors</em>}</li>
 *   <li>{@link org.osate.xtext.aadl2.errormodel.errorModel.impl.ErrorModelLibraryImpl#getMappings <em>Mappings</em>}</li>
 *   <li>{@link org.osate.xtext.aadl2.errormodel.errorModel.impl.ErrorModelLibraryImpl#getTransformations <em>Transformations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ErrorModelLibraryImpl extends AnnexLibraryImpl implements ErrorModelLibrary
{
  /**
   * The cached value of the '{@link #getExtends() <em>Extends</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExtends()
   * @generated
   * @ordered
   */
  protected EList<ErrorModelLibrary> extends_;

  /**
   * The cached value of the '{@link #getTypes() <em>Types</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTypes()
   * @generated
   * @ordered
   */
  protected EList<ErrorType> types;

  /**
   * The cached value of the '{@link #getTypesets() <em>Typesets</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTypesets()
   * @generated
   * @ordered
   */
  protected EList<TypeSet> typesets;

  /**
   * The cached value of the '{@link #getProperties() <em>Properties</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProperties()
   * @generated
   * @ordered
   */
  protected EList<PropertyAssociation> properties;

  /**
   * The cached value of the '{@link #getBehaviors() <em>Behaviors</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBehaviors()
   * @generated
   * @ordered
   */
  protected EList<ErrorBehaviorStateMachine> behaviors;

  /**
   * The cached value of the '{@link #getMappings() <em>Mappings</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMappings()
   * @generated
   * @ordered
   */
  protected EList<TypeMappingSet> mappings;

  /**
   * The cached value of the '{@link #getTransformations() <em>Transformations</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTransformations()
   * @generated
   * @ordered
   */
  protected EList<TypeTransformationSet> transformations;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ErrorModelLibraryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return ErrorModelPackage.Literals.ERROR_MODEL_LIBRARY;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ErrorModelLibrary> getExtends()
  {
    if (extends_ == null)
    {
      extends_ = new EObjectResolvingEList<ErrorModelLibrary>(ErrorModelLibrary.class, this, ErrorModelPackage.ERROR_MODEL_LIBRARY__EXTENDS);
    }
    return extends_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ErrorType> getTypes()
  {
    if (types == null)
    {
      types = new EObjectContainmentEList<ErrorType>(ErrorType.class, this, ErrorModelPackage.ERROR_MODEL_LIBRARY__TYPES);
    }
    return types;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<TypeSet> getTypesets()
  {
    if (typesets == null)
    {
      typesets = new EObjectContainmentEList<TypeSet>(TypeSet.class, this, ErrorModelPackage.ERROR_MODEL_LIBRARY__TYPESETS);
    }
    return typesets;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<PropertyAssociation> getProperties()
  {
    if (properties == null)
    {
      properties = new EObjectContainmentEList<PropertyAssociation>(PropertyAssociation.class, this, ErrorModelPackage.ERROR_MODEL_LIBRARY__PROPERTIES);
    }
    return properties;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ErrorBehaviorStateMachine> getBehaviors()
  {
    if (behaviors == null)
    {
      behaviors = new EObjectContainmentEList<ErrorBehaviorStateMachine>(ErrorBehaviorStateMachine.class, this, ErrorModelPackage.ERROR_MODEL_LIBRARY__BEHAVIORS);
    }
    return behaviors;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<TypeMappingSet> getMappings()
  {
    if (mappings == null)
    {
      mappings = new EObjectContainmentEList<TypeMappingSet>(TypeMappingSet.class, this, ErrorModelPackage.ERROR_MODEL_LIBRARY__MAPPINGS);
    }
    return mappings;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<TypeTransformationSet> getTransformations()
  {
    if (transformations == null)
    {
      transformations = new EObjectContainmentEList<TypeTransformationSet>(TypeTransformationSet.class, this, ErrorModelPackage.ERROR_MODEL_LIBRARY__TRANSFORMATIONS);
    }
    return transformations;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case ErrorModelPackage.ERROR_MODEL_LIBRARY__TYPES:
        return ((InternalEList<?>)getTypes()).basicRemove(otherEnd, msgs);
      case ErrorModelPackage.ERROR_MODEL_LIBRARY__TYPESETS:
        return ((InternalEList<?>)getTypesets()).basicRemove(otherEnd, msgs);
      case ErrorModelPackage.ERROR_MODEL_LIBRARY__PROPERTIES:
        return ((InternalEList<?>)getProperties()).basicRemove(otherEnd, msgs);
      case ErrorModelPackage.ERROR_MODEL_LIBRARY__BEHAVIORS:
        return ((InternalEList<?>)getBehaviors()).basicRemove(otherEnd, msgs);
      case ErrorModelPackage.ERROR_MODEL_LIBRARY__MAPPINGS:
        return ((InternalEList<?>)getMappings()).basicRemove(otherEnd, msgs);
      case ErrorModelPackage.ERROR_MODEL_LIBRARY__TRANSFORMATIONS:
        return ((InternalEList<?>)getTransformations()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case ErrorModelPackage.ERROR_MODEL_LIBRARY__EXTENDS:
        return getExtends();
      case ErrorModelPackage.ERROR_MODEL_LIBRARY__TYPES:
        return getTypes();
      case ErrorModelPackage.ERROR_MODEL_LIBRARY__TYPESETS:
        return getTypesets();
      case ErrorModelPackage.ERROR_MODEL_LIBRARY__PROPERTIES:
        return getProperties();
      case ErrorModelPackage.ERROR_MODEL_LIBRARY__BEHAVIORS:
        return getBehaviors();
      case ErrorModelPackage.ERROR_MODEL_LIBRARY__MAPPINGS:
        return getMappings();
      case ErrorModelPackage.ERROR_MODEL_LIBRARY__TRANSFORMATIONS:
        return getTransformations();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case ErrorModelPackage.ERROR_MODEL_LIBRARY__EXTENDS:
        getExtends().clear();
        getExtends().addAll((Collection<? extends ErrorModelLibrary>)newValue);
        return;
      case ErrorModelPackage.ERROR_MODEL_LIBRARY__TYPES:
        getTypes().clear();
        getTypes().addAll((Collection<? extends ErrorType>)newValue);
        return;
      case ErrorModelPackage.ERROR_MODEL_LIBRARY__TYPESETS:
        getTypesets().clear();
        getTypesets().addAll((Collection<? extends TypeSet>)newValue);
        return;
      case ErrorModelPackage.ERROR_MODEL_LIBRARY__PROPERTIES:
        getProperties().clear();
        getProperties().addAll((Collection<? extends PropertyAssociation>)newValue);
        return;
      case ErrorModelPackage.ERROR_MODEL_LIBRARY__BEHAVIORS:
        getBehaviors().clear();
        getBehaviors().addAll((Collection<? extends ErrorBehaviorStateMachine>)newValue);
        return;
      case ErrorModelPackage.ERROR_MODEL_LIBRARY__MAPPINGS:
        getMappings().clear();
        getMappings().addAll((Collection<? extends TypeMappingSet>)newValue);
        return;
      case ErrorModelPackage.ERROR_MODEL_LIBRARY__TRANSFORMATIONS:
        getTransformations().clear();
        getTransformations().addAll((Collection<? extends TypeTransformationSet>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case ErrorModelPackage.ERROR_MODEL_LIBRARY__EXTENDS:
        getExtends().clear();
        return;
      case ErrorModelPackage.ERROR_MODEL_LIBRARY__TYPES:
        getTypes().clear();
        return;
      case ErrorModelPackage.ERROR_MODEL_LIBRARY__TYPESETS:
        getTypesets().clear();
        return;
      case ErrorModelPackage.ERROR_MODEL_LIBRARY__PROPERTIES:
        getProperties().clear();
        return;
      case ErrorModelPackage.ERROR_MODEL_LIBRARY__BEHAVIORS:
        getBehaviors().clear();
        return;
      case ErrorModelPackage.ERROR_MODEL_LIBRARY__MAPPINGS:
        getMappings().clear();
        return;
      case ErrorModelPackage.ERROR_MODEL_LIBRARY__TRANSFORMATIONS:
        getTransformations().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case ErrorModelPackage.ERROR_MODEL_LIBRARY__EXTENDS:
        return extends_ != null && !extends_.isEmpty();
      case ErrorModelPackage.ERROR_MODEL_LIBRARY__TYPES:
        return types != null && !types.isEmpty();
      case ErrorModelPackage.ERROR_MODEL_LIBRARY__TYPESETS:
        return typesets != null && !typesets.isEmpty();
      case ErrorModelPackage.ERROR_MODEL_LIBRARY__PROPERTIES:
        return properties != null && !properties.isEmpty();
      case ErrorModelPackage.ERROR_MODEL_LIBRARY__BEHAVIORS:
        return behaviors != null && !behaviors.isEmpty();
      case ErrorModelPackage.ERROR_MODEL_LIBRARY__MAPPINGS:
        return mappings != null && !mappings.isEmpty();
      case ErrorModelPackage.ERROR_MODEL_LIBRARY__TRANSFORMATIONS:
        return transformations != null && !transformations.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //ErrorModelLibraryImpl
