package org.osate2.aadl2.errormodel.analysis.prism;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osate.aadl2.Element;
import org.osate.aadl2.errormodel.analysis.actions.PRISMAction;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.util.OsateDebug;
import org.osate.xtext.aadl2.errormodel.errorModel.ComponentErrorBehavior;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorBehaviorEvent;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorBehaviorState;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorBehaviorStateMachine;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorBehaviorTransition;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorDetection;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorModelSubclause;
import org.osate.xtext.aadl2.errormodel.util.EM2Util;
import org.osate2.aadl2.errormodel.analysis.prism.expression.*;

public class Module {
	private List<Command> 			commands;
	private ComponentInstance 		aadlComponent;
	private int 					nStates;
	private Model					associatedModel;
	private Map<String,Integer>		statesMap;
	
	public Module (ComponentInstance ci, Model m)
	{
		this.commands 			= new ArrayList<Command>();
		this.associatedModel    = m;
		this.aadlComponent 		= ci;
		this.nStates 			= 0;
		this.statesMap			= new HashMap<String,Integer>();
	}
	
	public Module (ComponentInstance ci, Model m, int ns)
	{
		this (ci, m);
		this.nStates 			= ns;
	}
	
	public String getPrismCode ()
	{
		StringBuffer sb = new StringBuffer ();
		sb.append ("module " + Util.getComponentName (aadlComponent) + "\n");
		if (this.nStates > 0)
		{
			sb.append ("\t" + Util.getComponentName (aadlComponent) + "_state: [ 0 .. "+ (this.nStates - 1) +"] init 0;\n");
		}
		
		
		for (Command command : commands)
		{
			double sum;
			boolean alreadyOne = false;
			sum = 0.0;
			sb.append ("\t[] " + command.getCondition().toString() +" -> ");
			for (Transition t : command.getTransitions())
			{
				sum = sum + t.getProbability();
				if (alreadyOne)
				{
					sb.append (" + ");	
				}
				sb.append (t.getProbability());	
				sb.append (" : ");
				sb.append ("(");
				sb.append (t.getExpression().toString());
				sb.append (")");
				alreadyOne = true;
			}
			
			/*
			 * If the sum of all probability is not reached, we keep the same state, nothing change
			 */
			if (sum < 1.0)
			{
				sb.append (" + ");	
			
				sb.append (1.0 - sum);	
				sb.append (" : ");
				sb.append ("(");
				if (command.getCondition() instanceof Equal)
				{
					Equal e = (Equal)command.getCondition();
					Expression left = e.getLeft();
					if (left instanceof Terminal)
					{
						Terminal t = (Terminal) left;
						t.setUpdate(true);
					}
				}
				sb.append (command.getCondition().toString());
				sb.append (")");
				alreadyOne = true;
			}
			sb.append (";\n");

		}
		sb.append ("endmodule\n");
		return sb.toString();
	}
	
	public Module process ()
	{
		Command command;
		Expression after = null;
		double probability;
		int tmpState;
		
		OsateDebug.osateDebug("[PRISM][Module.java] Process " + aadlComponent.getName());
		
		ErrorBehaviorStateMachine useStateMachine = null;
		ErrorModelSubclause errorModelSubclause = EM2Util.getErrorAnnexClause(aadlComponent);
		ComponentErrorBehavior errorBehavior = EM2Util.getComponentErrorBehavior (aadlComponent.getComponentClassifier());
		ErrorBehaviorStateMachine componentStateMachine = EM2Util.getContainingErrorBehaviorStateMachine(aadlComponent.getComponentClassifier());
		
		if (errorModelSubclause != null)
		{
			useStateMachine = errorModelSubclause.getUseBehavior();
			
			OsateDebug.osateDebug("[PRISM][Module.java] Process error model annex subclause of " + aadlComponent.getName());

			if (useStateMachine != null)
			{
				for (ErrorBehaviorState behaviorState : errorModelSubclause.getUseBehavior().getStates())
				{
					OsateDebug.osateDebug("[PRISM][Module.java]    Use Behavior state " + behaviorState.getName());
				}
			}
		}
		
		if ((useStateMachine == null) && (componentStateMachine == null))
		{
			PRISMAction.reportWarning(aadlComponent,
					"Component " + aadlComponent.getName() + " should have an associated state machine");
		}
		
		if (useStateMachine != null)
		{
			int stateIndex = 1;
			for (ErrorBehaviorState state : useStateMachine.getStates())
			{
				if (state.isIntial())
				{
					statesMap.put(state.getName(), 0);
				}
				else
				{
					statesMap.put(state.getName(), stateIndex++);
				}
			}
			this.nStates = useStateMachine.getStates().size();
		}
		
		if (componentStateMachine != null)
		{
			OsateDebug.osateDebug("[PRISM][Module.java] Process state machine of " + aadlComponent.getName());

			for (ErrorBehaviorState behaviorState : componentStateMachine.getStates())
			{
				OsateDebug.osateDebug("state" + behaviorState);

			}
		}
		
		if (errorBehavior != null)
		{
			OsateDebug.osateDebug("[PRISM][Module.java] Process error behavior of " + aadlComponent.getName());

			for (ErrorDetection ed : errorBehavior.getErrorDetections())
			{
				OsateDebug.osateDebug("[PRISM][Module.java]    ErrorDerection " + ed);

			}
			for (ErrorBehaviorEvent ed : errorBehavior.getEvents())
			{
				OsateDebug.osateDebug("[PRISM][Module.java]    ErrorEvent " + ed);

			}
			
			for (ErrorBehaviorTransition trans : errorBehavior.getTransitions())
			{
				OsateDebug.osateDebug("[PRISM][Module.java]    ErrorTransition " + trans);
				OsateDebug.osateDebug("[PRISM][Module.java]       src= " + trans.getSource().getName());
				OsateDebug.osateDebug("[PRISM][Module.java]       dst= " + trans.getTarget());
				OsateDebug.osateDebug("[PRISM][Module.java]       cond= " + trans.getCondition());
				
				tmpState = statesMap.get(trans.getSource().getName());
				
				Expression before = new Equal (new Terminal (Util.getComponentStateVariableName(aadlComponent)),
											   new Terminal (""+tmpState));
				
				 probability = Util.translateConditionToProbability (aadlComponent, trans.getCondition());
				
				if (trans.getTarget() != null)
				{
					tmpState = statesMap.get(trans.getTarget().getName());
					 after = new Equal (new Terminal (Util.getComponentStateVariableName(aadlComponent), true),
								   new Terminal (""+tmpState));
				}
				command = new Command ();
				command.setCondition (before);
				command.addTransition (new Transition (probability, after));
				
				this.commands.add (command);
			}
			
		}
		
		
		
		return this;
	}
}
