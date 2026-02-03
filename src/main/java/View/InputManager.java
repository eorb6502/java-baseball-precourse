package view;

import java.util.Scanner;

public class InputManager {
	private final Scanner scanner;

	public InputManager() {
		scanner = new Scanner(System.in);
	}

	public String getInput() {
		return scanner.next();
	}
}
