package com.cmdemulator.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cmdemulator.exceptions.AccessViolationException;
import com.cmdemulator.exceptions.DataException;
import com.cmdemulator.exceptions.IllegalFormatException;

/**
 * @author sdutta
 * This class contains the basic file utilities
 */
public class FileUtils {

	public static Map<String,String> readPropertyFile(String fileName, String separatorChar) throws AccessViolationException, DataException{
		
		File appProp = new File(fileName);
		if(!appProp.isFile() || !appProp.canRead())
			throw new AccessViolationException();
		
		Map<String,String> propMap =  new HashMap<String,String>();
		
		try(BufferedReader fileReader = new BufferedReader(new FileReader(appProp))){
			
			String str = null;
			while((str = fileReader.readLine()) != null){
				
				String[] line = str.split(separatorChar);
				if(line.length < 2)
					throw new IllegalFormatException("Properties file is in different format");
				
				propMap.put(line[0], str.substring(line[0].length()+1));
			}
			
		}catch(Exception e){
			
			throw new DataException(e.getMessage());
			
		}
		
		return (propMap.size() > 0)?propMap:null;
	}
	
	/**
	 * 
	 * @param historyFileName
	 * @return List<String>
	 * @throws DataException
	 */
	public static List<String> loadHistory(String historyFileName) throws DataException{
		
		File historyFile = new File(historyFileName);
		if(!historyFile.isFile() || !historyFile.canRead()){
			return new ArrayList<String>();
		}
		
		List<String> retList =  new ArrayList<String>();
		
		try(BufferedReader fileReader = new BufferedReader(new FileReader(historyFile))){
			
			String line = null;
			while((line = fileReader.readLine()) != null){
				retList.add(line);
			}
			
		}catch(Exception e){
			
			throw new DataException(e.getMessage());
		}
			
		return (retList== null)?new ArrayList<String>():retList;
		
	}
	
	/**
	 * To write the last maxBuffer entries to history file for next session reference
	 * @param historyFileName
	 * @param history
	 * @param maxBuffer
	 * @throws AccessViolationException 
	 * @throws DataException 
	 */
	public static void writeHistoryFile(String historyFileName,List<String> history, int maxBuffer) throws AccessViolationException, DataException{
		
		if(historyFileName == null || history == null)
			throw new DataException("Either historyFileName is null or history is null");
		
		File historyFile = new File(historyFileName);
		
		if(!historyFile.isFile() || !historyFile.canWrite())
			throw new AccessViolationException("Cannot Access History File for Writing");
		
		int startPos = (history.size() > maxBuffer)?
				history.size()-maxBuffer:
				0;
		
		
		try(BufferedWriter fileWriter = new BufferedWriter(new FileWriter(historyFile))){
			
			for(int i = startPos; i < history.size(); ++i)
				fileWriter.append(history.get(i)+"\n");
			
			fileWriter.flush();
			
		}catch(Exception e){
			
			throw new DataException(e.getMessage());
		}
	}
}
