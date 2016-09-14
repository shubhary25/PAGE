/**
 * 
 */
package com.cmdemulator.commands;

import java.io.File;

/**
 * @author sdutta
 *
 */
public class CatImpl extends Command {

	/**
	 * 
	 */
	public CatImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.cmdemulator.commands.Command#validateOptions()
	 */
	@Override
	protected boolean validateOptions() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.cmdemulator.commands.Command#validateParams()
	 */
	@Override
	protected boolean validateParams() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.cmdemulator.commands.Command#executeCommand(java.lang.String, java.io.File)
	 */
	@Override
	public File executeCommand(String parameters, File currentPath)
			throws Exception {
		System.out.println("Cat called");
		return currentPath;
	}

}
