/**
 * 
 */
package com.cmdemulator.commands;

import java.io.File;

import com.cmdemulator.ProgramData;
import com.cmdemulator.constants.EmulatorConstants;
import com.cmdemulator.utils.FileUtils;

/**
 * @author sdutta
 *
 */
public class ExitImpl extends Command {

	/**
	 * 
	 */
	public ExitImpl() {
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
		// TODO Auto-generated method stub
		String maxBuffer = ProgramData.getProgramData().getParam(EmulatorConstants.MAX_BUFFER_SIZE.getValue()).toString();
		String histoyFileName = ProgramData.getProgramData().getParam(EmulatorConstants.HISTORY_FILE_TAG_NAME.getValue()).toString();
		
		FileUtils.writeHistoryFile(histoyFileName, ProgramData.getProgramData().getHistoryList(),Integer.parseInt(maxBuffer));
		return currentPath;
	}

}
