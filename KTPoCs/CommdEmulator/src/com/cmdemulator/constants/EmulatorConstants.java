package com.cmdemulator.constants;

/**
 * @author sdutta
 * This class contains all the constants needed for the application.
 */
public enum EmulatorConstants {

	HISTORY_FILE_TAG_NAME("HISTORY_FILE"),
	APP_PROPERTIES_FILE_TAG_NAME("APPLICATION_PROPERTIES"),
	HISTORY_PARAM("HISTORY"),
	PROP_SEPARATOR(":"),
	MAX_BUFFER_SIZE("MAX_BUFFER_SIZE");
	
	private String emulatorConstant;
	
	private EmulatorConstants(String emulatorConstant){
		
		this.emulatorConstant = emulatorConstant; 
	}
	
	public String getValue(){
		
		return this.emulatorConstant;
	}
}
