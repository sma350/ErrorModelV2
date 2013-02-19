/*
 * <copyright>
 * Copyright  2012 by Carnegie Mellon University, all rights reserved.
 *
 * Use of the Open Source AADL Tool Environment (OSATE) is subject to the terms of the license set forth
 * at http://www.eclipse.org/org/documents/epl-v10.html.
 *
 * NO WARRANTY
 *
 * ANY INFORMATION, MATERIALS, SERVICES, INTELLECTUAL PROPERTY OR OTHER PROPERTY OR RIGHTS GRANTED OR PROVIDED BY
 * CARNEGIE MELLON UNIVERSITY PURSUANT TO THIS LICENSE (HEREINAFTER THE "DELIVERABLES") ARE ON AN "AS-IS" BASIS.
 * CARNEGIE MELLON UNIVERSITY MAKES NO WARRANTIES OF ANY KIND, EITHER EXPRESS OR IMPLIED AS TO ANY MATTER INCLUDING,
 * BUT NOT LIMITED TO, WARRANTY OF FITNESS FOR A PARTICULAR PURPOSE, MERCHANTABILITY, INFORMATIONAL CONTENT,
 * NONINFRINGEMENT, OR ERROR-FREE OPERATION. CARNEGIE MELLON UNIVERSITY SHALL NOT BE LIABLE FOR INDIRECT, SPECIAL OR
 * CONSEQUENTIAL DAMAGES, SUCH AS LOSS OF PROFITS OR INABILITY TO USE SAID INTELLECTUAL PROPERTY, UNDER THIS LICENSE,
 * REGARDLESS OF WHETHER SUCH PARTY WAS AWARE OF THE POSSIBILITY OF SUCH DAMAGES. LICENSEE AGREES THAT IT WILL NOT
 * MAKE ANY WARRANTY ON BEHALF OF CARNEGIE MELLON UNIVERSITY, EXPRESS OR IMPLIED, TO ANY PERSON CONCERNING THE
 * APPLICATION OF OR THE RESULTS TO BE OBTAINED WITH THE DELIVERABLES UNDER THIS LICENSE.
 *
 * Licensee hereby agrees to defend, indemnify, and hold harmless Carnegie Mellon University, its trustees, officers,
 * employees, and agents from all claims or demands made against them (and any related losses, expenses, or
 * attorney's fees) arising out of, or relating to Licensee's and/or its sub licensees' negligent use or willful
 * misuse of or negligent conduct or willful misconduct regarding the Software, facilities, or other rights or
 * assistance granted by Carnegie Mellon University under this License, including, but not limited to, any claims of
 * product liability, personal injury, death, damage to property, or violation of any laws or regulations.
 *
 * Carnegie Mellon Carnegie Mellon University Software Engineering Institute authored documents are sponsored by the U.S. Department
 * of Defense under Contract F19628-00-C-0003. Carnegie Mellon University retains copyrights in all material produced
 * under this contract. The U.S. Government retains a non-exclusive, royalty-free license to publish or reproduce these
 * documents, or allow others to do so, for U.S. Government purposes only pursuant to the copyright license
 * under the contract clause at 252.227.7013.
 * </copyright>
 */
package org.osate.aadl2.errormodel.analysis.actions;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.osate.aadl2.BasicPropertyAssociation;
import org.osate.aadl2.ContainedNamedElement;
import org.osate.aadl2.Element;
import org.osate.aadl2.ModalPropertyValue;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.PropertyExpression;
import org.osate.aadl2.RealLiteral;
import org.osate.aadl2.RecordValue;
import org.osate.aadl2.Subcomponent;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.InstanceObject;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.modelsupport.util.AadlUtil;
import org.osate.aadl2.util.OsateDebug;
import org.osate.ui.actions.AaxlReadOnlyActionAsJob;
import org.osate.ui.dialogs.Dialog;
import org.osate.xtext.aadl2.errormodel.errorModel.CompositeErrorBehavior;
import org.osate.xtext.aadl2.errormodel.errorModel.CompositeState;
import org.osate.xtext.aadl2.errormodel.errorModel.ConditionElement;
import org.osate.xtext.aadl2.errormodel.errorModel.ConditionExpression;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorBehaviorState;
import org.osate.xtext.aadl2.errormodel.errorModel.SAndExpression;
import org.osate.xtext.aadl2.errormodel.errorModel.SOrExpression;
import org.osate.xtext.aadl2.errormodel.errorModel.SubcomponentElement;
import org.osate.xtext.aadl2.errormodel.errorModel.TypeSet;
import org.osate.xtext.aadl2.errormodel.errorModel.TypeToken;
import org.osate.xtext.aadl2.errormodel.util.EM2Util;

public final class RBDAction extends AaxlReadOnlyActionAsJob {
	protected String getMarkerType() {
		return "org.osate.analysis.errormodel.FaultImpactMarker";
	}

	protected String getActionName() {
		return "RBD";
	}

	protected ContainedNamedElement getOccurenceDistributionProperty(ComponentInstance ci, NamedElement localContext,NamedElement target, TypeSet ts){
		ContainedNamedElement result =  EM2Util.getProperty("EMV2::OccurrenceDistribution",ci,localContext,target,ts);
		return result;
	}
	
	private static ComponentInstance findInstance (EList<ComponentInstance> instances, String name)
	{
		for (ComponentInstance ci : instances)
		{
			if (ci.getName().equalsIgnoreCase(name))
			{
				return ci;
			}
		}
		
		return null;
	}
	
	private double processOccurence (final ContainedNamedElement PAContainmentPath)
	{
		double result;
		
		result = 0;
		
		if (PAContainmentPath == null)
		{
			return 0;
		}
		
		for (ModalPropertyValue modalPropertyValue : AadlUtil.getContainingPropertyAssociation(PAContainmentPath).getOwnedValues()) {
			PropertyExpression val = modalPropertyValue.getOwnedValue();
			if (val instanceof RecordValue){
	
				RecordValue rv = (RecordValue)val;
				EList<BasicPropertyAssociation> fields = rv.getOwnedFieldValues();
				// for all error types/aliases in type set or the element identified in the containment clause 
				for (BasicPropertyAssociation bpa : fields)
				{
					if (bpa.getProperty().getName().equalsIgnoreCase("probabilityvalue"))
					{
						if (bpa.getValue() instanceof RealLiteral)
						{
							RealLiteral rl = (RealLiteral)bpa.getValue();
							result = rl.getScaledValue();
						}
					}
					//OsateDebug.osateDebug("bpa prop =" + bpa.getProperty());

					//OsateDebug.osateDebug("bpa value=" + bpa.getValue());
				}
			}
		}
		return result;
	}
	
	private double handleElement (final ConditionElement conditionElement, final EList<ComponentInstance> componentInstances)
	{
		double result = 0;
		
		ErrorBehaviorState behaviorState = conditionElement.getReference();

		for (SubcomponentElement subcomponentElement : conditionElement.getSubcomponents())
		{
			Subcomponent subcomponent = subcomponentElement.getSubcomponent();
			OsateDebug.osateDebug("      subcomponent " + subcomponent);
			ComponentInstance relatedInstance = findInstance(componentInstances, subcomponent.getName());
			OsateDebug.osateDebug("         instance " + relatedInstance);
			if (behaviorState != null)
			{
				//OsateDebug.osateDebug("         behaviorState " + behaviorState);

				TypeSet ts = behaviorState.getTypeSet();
				ContainedNamedElement PA = getOccurenceDistributionProperty(relatedInstance,null,behaviorState,null);
				//OsateDebug.osateDebug("         PA " + PA);
				result = processOccurence (PA);
			}
		}
		
		return result;
	}
	
	
	private double handleCondition (final ConditionExpression cond, final EList<ComponentInstance> componentInstances)
	{
		double result = 0;
		OsateDebug.osateDebug("cond="+cond);
		
		if (cond instanceof ConditionElement)
		{
			return handleElement((ConditionElement)cond, componentInstances);
		}
		
		if (cond instanceof SOrExpression)
		{
			SOrExpression sor = (SOrExpression)cond;
			for (ConditionExpression conditionExpression : sor.getOperands())
			{
				OsateDebug.osateDebug("      operand=" + conditionExpression);
				result = handleCondition (conditionExpression, componentInstances);
			}
		}
		
		if (cond instanceof SAndExpression)
		{
			SAndExpression sae = (SAndExpression)cond;
			for (ConditionExpression conditionExpression : sae.getOperands())
			{
				result = handleCondition (conditionExpression, componentInstances);
			}
		}
		return result;
	}
	
	public void processRootSystem (SystemInstance systemInstance)
	{
		EList<CompositeState> 		states;
		CompositeErrorBehavior 		ceb;
		EList<ComponentInstance> 	componentInstances;
		double						probabilityTotal;
		double						probabilityTemp;

		ceb = EM2Util.getCompositeErrorBehavior (systemInstance);
		
		componentInstances = EM2Util.getComponentInstancesWithErrorPropagations (systemInstance);
		
		states = ceb.getStates();
		
		probabilityTotal = 0;
		probabilityTemp  = 0;
		for (CompositeState state : states)
		{
			if (state.getState().getName().equalsIgnoreCase("failed"))
			{
				probabilityTemp = handleCondition (state.getCondition(), componentInstances);
				OsateDebug.osateDebug("temp=" + probabilityTemp);
			}
		}
	}
	
	public void doAaxlAction(IProgressMonitor monitor, Element obj) {
		SystemInstance si;
		
		monitor.beginTask("RBD", IProgressMonitor.UNKNOWN);

		si = null;
		

		if (obj instanceof InstanceObject){
			si = ((InstanceObject)obj).getSystemInstance();
		}
		
		if (si == null)
		{
			Dialog.showInfo("RDB", "Please choose an instance model");	
			monitor.done();
		}
		
		if (! EM2Util.hasCompositeErrorBehavior (si))
		{
			Dialog.showInfo("RDB", "Your system instance does not have a composite error behavior");	
			monitor.done();
		}
		
		processRootSystem (si);
		
		monitor.done();
	}

}
