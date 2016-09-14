package com.cmdemulator;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import com.cmdemulator.commands.Command;
import com.cmdemulator.exceptions.AccessViolationException;
import com.cmdemulator.exceptions.DataException;
import com.cmdemulator.exceptions.UsageException;

/**
 * @author sdutta
 * This is a basic commandline emulator
 */
public class CmdEmulator {
	
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		ProgramData pgData = ProgramData.getProgramData();
		try {
			pgData.parseEnvironment();
			pgData.validate();
			pgData.parseAppConfig();
			
			File currentPath = new File(".");
			
			BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Welcome to CMD Emulator");
			String command = null;
			do{
				System.out.print(currentPath.getCanonicalPath()+"> ");
				command = inputReader.readLine();
				pgData.addToHistory(command);
				
				String baseCommand = command.split(" ")[0]+"Impl";
			
				String parameters = (command.contains(" "))?command.substring(command.indexOf(" ")):" ";
				try{
					
					String className = Character.toString(baseCommand.charAt(0)).toUpperCase().concat(baseCommand.substring(1));
					
					Class<Command> commandClass = (Class<Command>) Class.forName("com.cmdemulator.commands."+className);
					Command commandClassObj = (Command) commandClass.newInstance();
					currentPath = commandClassObj.executeCommand(parameters.substring(1),currentPath);
					
					
				}catch (ClassNotFoundException | InstantiationException | IllegalAccessException  e) {
					
					System.err.println(baseCommand.substring(0,baseCommand.length()-4)+" not found");
					System.out.print(currentPath.getCanonicalPath()+"> ");
				}catch(Exception e){
					e.printStackTrace();
					System.out.print(currentPath.getCanonicalPath()+"> ");
				}
				
			}while(!command.equalsIgnoreCase("exit"));
			
		} catch (AccessViolationException | DataException | UsageException | IOException e) {
			e.printStackTrace();
		} 
		
		
	}

}
