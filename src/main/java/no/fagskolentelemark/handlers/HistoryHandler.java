package no.fagskolentelemark.handlers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import no.fagskolentelemark.EkomMain;
import no.fagskolentelemark.wrapper.Student;

public class HistoryHandler {

	/*
	 * Load already imported users from Excel spreadsheets here
	 * 
	 */

	public static List<Student> alreadyImported = new ArrayList<Student>();

	public static void loadHistory() throws Exception {
		File historyDir = new File(EkomMain.mainDir + File.separator + "Imports");
		for (File file : historyDir.listFiles()) {
			if (file.isFile() && file.getName().endsWith(".csv") && file.getName().startsWith("users")) {
				BufferedReader inputReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
				
				String line;
				while ((line = inputReader.readLine()) != null) {
					String[] row = line.split(",");
					
					String username = row[0];
					String password = row[1];
					String firstName = row[2];
					String lastName = row[3];
					String email = row[4];
					
					Student user = new Student(firstName, lastName, username, 0, email, password);
					alreadyImported.add(user);
					System.out.println(username);
				}
				
				inputReader.close();
			}
		}
	}
	
	public static boolean seenBefore(Student student) {
		for (Student s : alreadyImported) {
			if (s.getUsername().equals(student.getUsername())) {
				return true;
			}
		}
		return false;
	}
}