/**
 * 
 */
package com.cmdemulator.commands;

import java.io.File;

/**
 * @author sdutta
 *
 */
public class CdImpl extends Command {
	
	private boolean absoluteFlag = false;

	/* (non-Javadoc)
	 * @see com.cmdemulator.commands.Command#validateParams()
	 */
	@Override
	protected boolean validateParams() {
		if(super.parameters.size() != 1)
			return false;
		
		return true;
	}

	/* (non-Javadoc)
	 * @see com.cmdemulator.commands.Command#executeCommand(java.lang.String)
	 */
	@Override
	public File executeCommand(String parameters, File currentPath) throws Exception {

		super.parseParameters(parameters);
		if(!this.validateParams()){
			System.err.println("Failed in validate Params");
			return currentPath;
		}
		if(!this.validateOptions()){
			System.err.println("Failed in validate optiions");
			return currentPath;
		}
		
		String destinationDirectory = super.parameters.get(0);
		if(destinationDirectory.startsWith("/"))
			this.absoluteFlag = true;
		
		if(this.absoluteFlag)
			destinationDirectory = destinationDirectory.substring(1);
		
		String[] path = destinationDirectory.split("/");
		for(String part : path){
			File temp = new File(currentPath.getCanonicalPath());
			if(this.absoluteFlag){
				
				temp = new File(part+":\\");
				this.absoluteFlag = false;
				if(!temp.isDirectory() && !temp.canRead()){
					System.err.println("Invalid directory");
					break;
				}
				currentPath = temp;
				continue;
			}
			
			temp = new File(temp.getCanonicalPath()+File.separator+part);
			if(!temp.isDirectory() && !temp.canRead()){
				System.err.println("Invalid directory");
				break;
			}
			
			currentPath = temp;
			
		}
		
		
		return currentPath;
	}

	@Override
	protected boolean validateOptions() {
		if(super.options.size() != 0)
			return false;
		
		return true;
	}

}
