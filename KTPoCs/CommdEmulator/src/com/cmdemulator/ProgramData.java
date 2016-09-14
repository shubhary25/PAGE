package com.cmdemulator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cmdemulator.constants.EmulatorConstants;
import com.cmdemulator.exceptions.AccessViolationException;
import com.cmdemulator.exceptions.DataException;
import com.cmdemulator.exceptions.UsageException;
import com.cmdemulator.utils.FileUtils;

/**
 * @author sdutta
 * This class contains the complete ProgramData
 */
public class ProgramData {

	private Map<String,Object> params;
	private static ProgramData programData;
	
	
	/**
	 * Private Constructor to block Object Creation
	 */
	private ProgramData(){
		
		 this.params = new HashMap<String,Object>();
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("Program Data is a singleton class");
	}
	
	/**
	 * To get the programData
	 * @return ProgramData
	 */
	public static synchronized ProgramData getProgramData(){
		
		if(programData == null)
			programData = new ProgramData();
		
		return programData;
	}
	
	/**
	 * To parse all the environment parameters passed to the Application
	 * @throws DataException 
	 * @throws AccessViolationException 
	 * @throws UsageException 
	 */
	public void parseEnvironment() throws AccessViolationException, DataException, UsageException{
		
//		Loading the application properties file form the environment
		params.put(EmulatorConstants.APP_PROPERTIES_FILE_TAG_NAME.getValue(), 
				System.getenv(EmulatorConstants.APP_PROPERTIES_FILE_TAG_NAME.getValue()));
		
		params.put(EmulatorConstants.MAX_BUFFER_SIZE.getValue(), System.getenv(EmulatorConstants.MAX_BUFFER_SIZE.getValue()));	
		
	}
	
	/**
	 * To validate the parameters received from environment
	 * @throws UsageException
	 */
	public void validate() throws UsageException{
		
		if(params.get(EmulatorConstants.APP_PROPERTIES_FILE_TAG_NAME.getValue()) == null)
			throw new UsageException(EmulatorConstants.APP_PROPERTIES_FILE_TAG_NAME.getValue()+" is not present in the env");
		
		if(params.get(EmulatorConstants.MAX_BUFFER_SIZE.getValue()) == null)
			throw new UsageException(EmulatorConstants.MAX_BUFFER_SIZE.getValue()+" is not present in the env");
	}
	
	/**
	 * Method to parse the application config file
	 * @throws AccessViolationException
	 * @throws DataException
	 */
	public void parseAppConfig() throws AccessViolationException, DataException{
	
		Map<String,String> appConf = FileUtils.readPropertyFile((String)params.get(EmulatorConstants.APP_PROPERTIES_FILE_TAG_NAME.getValue()), 
				EmulatorConstants.PROP_SEPARATOR.getValue());
		
		if(appConf != null)
			this.params.putAll(appConf);
		
		String historyFileName = params.get(EmulatorConstants.HISTORY_FILE_TAG_NAME.getValue()).toString();
		
		this.params.put(EmulatorConstants.HISTORY_PARAM.getValue(), FileUtils.loadHistory(historyFileName));
	}
	
	
	/**
	 * To get a value from the ProgramData 
	 * @param key
	 * @return Object
	 */
	public Object getParam(String key){
		
		return this.params.get(key);
	}
	
	/**
	 * To add value to history file
	 * @param value
	 */
	@SuppressWarnings("unchecked")
	public void addToHistory(String value){
		
		List<String> history = (List<String>)this.params.get(EmulatorConstants.HISTORY_PARAM.getValue());
		history.add(value);
	}
	
	/**
	 * To get the command history
	 * @return List<String> containing command history
	 */
	@SuppressWarnings("unchecked")
	public List<String> getHistoryList(){
		return (List<String>) this.params.get(EmulatorConstants.HISTORY_PARAM.getValue());
	}
}
