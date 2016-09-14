package com.cmdemulator.commands;

import java.io.File;
import java.util.List;

import com.cmdemulator.ProgramData;

public class HistoryImpl extends Command {

	public HistoryImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean validateOptions() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean validateParams() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public File executeCommand(String parameters, File currentPath)
			throws Exception {
		
		List<String> history = ProgramData.getProgramData().getHistoryList();
		for(String entry : history)
			System.out.println(entry);
		
		return currentPath;
	}

}
