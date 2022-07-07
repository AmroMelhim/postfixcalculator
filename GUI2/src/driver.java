import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class driver extends Application {

	public static LinkStack stack = new LinkStack();
	// two strings to store valid and invalid outputs
	public static StringBuilder Valid = new StringBuilder();
	public static StringBuilder Invalid = new StringBuilder();

	public static void main(String[] args) throws IOException {
		launch(args);

	}

	// method to scan from file
	public static void fileInput(ArrayList<String> eq) throws IOException {
		File file = new File("eq.txt");
		// define a buffer reader to read text
		BufferedReader br = new BufferedReader(new FileReader(file));
		try {
			String line;
			int i = 0;
			while ((line = br.readLine()) != null) {
				eq.add(line);
				System.out.println(eq.get(i));
				splitEq(eq.get(i));
				i++;

			}
		}

		finally {
			br.close();
		}

	}

	public void start(Stage GUI) throws Exception {
		ArrayList<String> eq = new ArrayList<String>();

		Pane pane = new Pane();
		// define first button that calls file input method
		Button b1 = new Button("Read from file");
		b1.setLayoutX(50);
		b1.setLayoutY(50);
		Button b2 = new Button("Print Valid");
		b2.setLayoutX(50);
		b2.setLayoutY(100);
		Button b3 = new Button("Print Invalid");
		b3.setLayoutX(50);
		b3.setLayoutY(150);

		b1.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				try {
					fileInput(eq);
				} catch (IOException e) {

					e.printStackTrace();
				}
				

			}
		});
		b2.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {

				// button 4 for existing the printing window
				Button b4 = new Button("exit");
				VBox vert = new VBox();

				vert.getChildren().add(new Label(Valid.toString()));

				// code for spacing and aligning the vertical box
				vert.setPadding(new Insets(10, 10, 10, 10));
				HBox horz = new HBox(b4);
				// code for alinging the hozrizntal box for the exit button
				horz.setPadding(new Insets(10, 10, 10, 10));
				vert.getChildren().add(horz);
				// centering the exit
				horz.setAlignment(Pos.CENTER);
				Scene scene = new Scene(vert);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.show();
				b4.setOnAction(e1 -> {
					stage.hide();
				});
			}
		});

		b3.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {

				// button 4 for existing the printing window
				Button b4 = new Button("exit");
				VBox vert = new VBox();

				vert.getChildren().add(new Label(Invalid.toString()));

				// code for spacing and aligning the vertical box
				vert.setPadding(new Insets(10, 10, 10, 10));
				HBox horz = new HBox(b4);
				// code for alinging the hozrizntal box for the exit button
				horz.setPadding(new Insets(10, 10, 10, 10));
				vert.getChildren().add(horz);
				// centering the exit
				horz.setAlignment(Pos.CENTER);
				Scene scene = new Scene(vert);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.show();
				b4.setOnAction(e1 -> {
					stage.hide();
				});
			}
		});

		// main pane
		pane.getChildren().addAll(b1, b2, b3);
		// main scene
		Scene scene = new Scene(pane, 200, 200);
		GUI.setScene(scene);
		GUI.show();
	}

	// checks if function is valid or not
	public static Boolean checkValid(String postfix) {
		// stack for each type of bracket
		LinkStack par = new LinkStack();
		LinkStack par2 = new LinkStack();

		boolean flag = true;

		for (int i = 0; i < postfix.length(); i++) {

			if (postfix.charAt(i) == '(') {
				par.push('(');

			}
			if (postfix.charAt(i) == ')') {
				if (par.isEmpty() == true) {
					flag = false;
					break;

				} else
					par.pop();
			}

			if (postfix.charAt(i) == '[') {
				par2.push('[');

			}
			if (postfix.charAt(i) == ']') {
				if (par2.isEmpty() == true) {
					flag = false;
					break;

				} else
					par2.pop();
			}

			if (par.isEmpty() == true && par2.isEmpty() == true) {
				flag = true;
			} else
				flag = false;
		}

		if (flag == true) {
			for (int i = 0; i < postfix.length(); i++) {

				if ((i + 1) != postfix.length() && Character.isDigit(postfix.charAt(i))
						&& postfix.charAt(i + 1) == '(') {
					flag = false;
					break;
				} else if ((i + 1) != postfix.length()
						&& (Character.isDigit(postfix.charAt(i + 1)) && postfix.charAt(i) == ')')) {
					flag = false;
					break;
				}
				if (((i + 1) != postfix.length() && Character.isDigit(postfix.charAt(i))
						&& postfix.charAt(i + 1) == '[')) {
					flag = false;
					break;
				} else if (((i + 1) != postfix.length() && Character.isDigit(postfix.charAt(i + 1))
						&& postfix.charAt(i) == ']')) {
					flag = false;
					break;
				}

				else if (((int) postfix.charAt(i)) > 41 && ((int) postfix.charAt(i)) < 48) {

					if ((postfix.charAt(i + 1)) == '+' || (postfix.charAt(i + 1)) == '*'
							|| (postfix.charAt(i + 1)) == '/') {
						flag = false;
						break;

					}

				}
				else if(Character.isDigit(postfix.charAt(i)) && (i+1) !=postfix.length() && postfix.charAt(i+1) == ' ')
				{
					if( (i+2) != postfix.length() && Character.isDigit(postfix.charAt(i+2)) ) {
						flag = false;
						
					}
					else
						flag = true;
					
					
					
				}
				

			}
		}

		if (flag == false)
			return false;
		//
		if (flag == true) {
			return true;
		}

		return null;

	}

	// method for splitting equation into two
	public static void splitEq(String equation) {
		String first = null;
		String second = null;

		for (int i = 0; i < equation.length(); i++) {
			// split on the = sign
			if (equation.charAt(i) == '=') {
				first = equation.substring(0, i);
				second = equation.substring(i + 1, equation.length());
				// System.out.println(first);
				// System.out.println(second);
				String sf = "";
				String ss = "";
				if (checkValid(first)) {

					sf = postfixConversion(first).toString() + " -> Valid" + "\n" + " = "
							+ postfixCalc(postfixConversion(first)) + "\n";
					Valid.append(sf);

					System.out.println(sf);

				} else {
					System.out.println(first + " -> Invalid");
					Invalid.append(first + " -> Invalid" + "\n");
				}

				if (checkValid(second)) {
					ss = postfixConversion(second).toString() + " -> Valid" + "\n" + " = "
							+ postfixCalc(postfixConversion(second)) + "\n";

					System.out.println(ss);
					Valid.append(ss);

				} else {
					System.out.println(second + " -> Invalid");
					Invalid.append(second + " -> Invalid" + "\n");
				}

				if (postfixCalc(postfixConversion(first)).equals(postfixCalc(postfixConversion(second)))) {
					System.out.println(" two equations are equal");
					Valid.append(" two equations are equal" + "\n");
				} else {
					System.out.println(" two equations are not equal");
					Valid.append(" two equations are not equal" + "\n");
				}

			}

		}

	}

	// check precdencal level
	static int precedenceLevel(char op) {
		if (op == '+' || op == '-')
			return 1;
		else if (op == '*' || op == '/')
			return 2;

		else
			return 0;
	}

	// method to convert from infix to postfix
	public static ArrayList<String> postfixConversion(String input) {
		StringBuilder temp = new StringBuilder();

		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			// If the scanned character is an
			// operand, add it to output.

			if (Character.isDigit(c)) {

				temp.append(c);
				while ((i + 1) != input.length() && (Character.isDigit(input.charAt(i + 1)))) {

					temp.append(input.charAt(++i));
				}

				result.add(temp.toString());
				temp.delete(0, temp.length());
			}
			// if the scanned number is a negative number
			else if (c == '-' && (Character.isDigit(input.charAt(i + 1))) && (i - 1) >= 0
					&& precedenceLevel(input.charAt(i - 1)) != 0) {
				temp.append(c);
				while ((i + 1) != input.length() && (Character.isDigit(input.charAt(i + 1)))) {

					temp.append(input.charAt(++i));
				}

				result.add(temp.toString());
				temp.delete(0, temp.length());

			} else if (c == input.charAt(0) && c == '-' && Character.isDigit(input.charAt(i + 1))) {

				temp.append(c);

				while ((i + 1) != input.length() && (Character.isDigit(input.charAt(i + 1)))) {

					temp.append(input.charAt(++i));
				}

				result.add(temp.toString());
				temp.delete(0, temp.length());

			}

			// If the scanned character is an '(',
			// push it to the stack.

			else if (c == '(')
				stack.push(c);
			else if (c == ')') {
				while (!stack.isEmpty() && (Character) stack.peek() != '(')
					temp.append(stack.pop());
				result.add(temp.toString());
				temp.delete(0, temp.length());

				stack.pop();
			}
			// If the scanned character is an '[',
			// push it to the stack.

			else if (c == '[')
				stack.push(c);
			else if (c == ']') {
				while (!stack.isEmpty() && (Character) stack.peek() != '[')
					temp.append(stack.pop());
				result.add(temp.toString());
				temp.delete(0, temp.length());
				stack.pop();

			}
			// if we got here then the char is an operator
			else {

				while (!stack.isEmpty() && precedenceLevel(c) <= precedenceLevel((Character) stack.peek())) {
					temp.append(stack.pop());
					result.add(temp.toString());
					temp.delete(0, temp.length());
				}
				stack.push(c);

			}

		}

		// pop all the operators from the stack
		while (!stack.isEmpty()) {
			temp.append(stack.pop());
			result.add(temp.toString());
			temp.delete(0, temp.length());

		}

		return result;

	}

	// method to evulate the postfix expression
	public static Object postfixCalc(ArrayList<String> postfix) {
		LinkStack stack = new LinkStack();
		for (int i = 0; i != postfix.size(); ++i) {
			// determines if its a number and push it to the stack
			if (Character.isDigit(postfix.get(i).charAt(0))) {
				stack.push(Double.parseDouble(postfix.get(i)));
			}

			else {
				// determine if its a negative umber and push it into the stack
				if (postfix.get(i).charAt(0) == '-' && postfix.get(i).length() > 1) {

					stack.push(Double.parseDouble(postfix.get(i)));
				} else {
					double tempResult = 0;
					double temp;

					switch (postfix.get(i)) {
					case "+":
						temp = Double.parseDouble(stack.pop().toString());
						tempResult = Double.parseDouble(stack.pop().toString()) + temp;
						break;

					case "-":
						temp = Double.parseDouble(stack.pop().toString());
						tempResult = Double.parseDouble(stack.pop().toString()) - temp;
						break;

					case "*":
						temp = Double.parseDouble(stack.pop().toString());
						tempResult = Double.parseDouble(stack.pop().toString()) * temp;
						break;

					case "/":
						temp = Double.parseDouble(stack.pop().toString());
						if(temp != 0)
						tempResult = Double.parseDouble(stack.pop().toString()) / temp;
						else {
							System.out.println("Cant divide by 0");
						}
						break;

					}
					stack.push(tempResult);
				}
			}
		}

		return (double) stack.pop();
	}

}
