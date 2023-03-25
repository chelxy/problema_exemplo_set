package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import entities.LogEntry;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

		System.out.print("Enter file full path: ");
		String fullPath = sc.nextLine();

		try (BufferedReader br = new BufferedReader(new FileReader(fullPath))) {

			Set<LogEntry> set = new TreeSet<LogEntry>();

			String line = br.readLine();
			while (line != null) {

				String[] fields = line.split(" ");
				set.add(new LogEntry(fields[0], Date.from(Instant.parse(fields[1]))));
				line = br.readLine();
			}
			for(LogEntry log : set) {
				System.out.println(log.getUsername() + " " + sdf.format(log.getMoment()));
			}
			System.out.println("Total users: " + set.size());
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		sc.close();
	}
}
