package com.cmdemulator.commands;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sdutta
 * This is the base class of all the commands that are going to be 
 * there in the CMD Environment
 */
public abstract class Command {

	protected List<String> parameters;
	protected List<String> options;
	
	protected Command(){
		
		this.parameters = new ArrayList<String>();
		this.options = new ArrayList<String>();
	}
	
	protected void parseParameters(String parameterList){
		
		if(parameterList == null)
			return;
		
		String[] params = parameterList.split(" ");
		for(String param : params){
			
			if(param.startsWith("-")){
				this.parseOptions(param);
				continue;
			}
			
			this.parameters.add(param);
		}
	}
	
	private void parseOptions(String parameter){
		
		for(int i = 1; i < parameter.length(); ++i){
			
			this.options.add(Character.toString(parameter.charAt(i)));
		}
	}
	
	protected String getCommandName(){
		
		String className=this.getClass().getCanonicalName();
		className = className.substring(className.lastIndexOf(".")+1,className.length() - 4).toLowerCase();
		
		return className;
	}
	
	protected abstract boolean validateOptions();
	protected abstract boolean validateParams();
	public abstract File executeCommand(String parameters, File currentPath) throws Exception;
}
