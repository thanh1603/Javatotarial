package thanh.laptrinh.app;

import java.util.ArrayList;
import java.util.Scanner;

import thanh.laptrinh.core.Student;
import thanh.laptrinh.dao.StudentDAO;

public class App {
	private static Scanner scanner = new Scanner(System.in);
	private static StudentDAO studentDAO = new StudentDAO();

	public static void main(String[] args) {
		System.out.println("The sutent management program ");
		System.out.println("1. Add new student");
		System.out.println("2. Find student name ");
		System.out.println("3. Dislay students ");
		System.out.println("4. Update student");
		System.out.println("5. Delete student by name");
		System.out.println("6. Exit");

		boolean flag = true; // biến cờ để duyệt
		while (flag) {
			System.out.println("Enter your choice");
			int choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 1:
				addNewRealStudent();
				break;
			case 2:
				findStudentByName();
				break;
			case 3:
				dislayStudent();
				break;
			case 4:
				updateStudent();
				break;

			case 5:
				deleteStudentByName();
				break;
			case 6:
				System.out.println("System end");
				studentDAO.closeConnection();
				flag = false;
				break;
			}
		}
	}

	public static void addNewRealStudent() {
		System.out.println("Add new student function");
		try {
			long id = studentDAO.generateId();
			System.out.println("Input name");
			String name = scanner.nextLine();
			System.out.println("Input email");
			String email = scanner.nextLine();
			Student student = new Student(id, name, email);
			studentDAO.addNewStudent(student);

		} catch (Exception e) {
			System.out.println("There is a error when adding new student");
			e.printStackTrace();
		}
	}

	public static void findStudentByName() {
		System.out.println("Find student by name function ");
		System.out.println("In put the name");
		String name = scanner.nextLine();
		Student student = null;
		try {
			student = studentDAO.findStudentByName(name);
		} catch (Exception e) {
			System.out.println("There is a error when finding new student");
			e.printStackTrace();
		}
		if (student != null) {
			System.out.println(student);
		} else {
			System.out.println("Couldn't find the student with name :" + name);
		}

	}

	public static void dislayStudent() {
		try {
			ArrayList<Student> studentList = studentDAO.findAll();
			for (Student student : studentList) {
				System.out.println(student);
			}
		} catch (Exception e) {
			System.out.println("There is a error when dislay all student");
			e.printStackTrace();
		}
	}

	public static void updateStudent() {
		try {
			System.out.println("Update student by id funtion");
			System.out.println("Input id");
			String id = scanner.nextLine();
			Student student = studentDAO.findStudentById(Long.parseLong(id));
			if (student == null) {
				System.out.println("Couldn't find the student id = " + id);
			} else {
				System.out.println("Fond student : " + id);
				System.out.println("With info :" + student);
				System.out.println("Input new name ");
				String name = scanner.nextLine();
				System.out.println("Input new email");
				String email = scanner.nextLine();
				Student newStudent = new Student(Long.parseLong(id), name, email);
				studentDAO.modifyStudent(newStudent);

			}
		} catch (Exception e) {
			System.out.println("There is a error when updating a student");
			e.printStackTrace();
		}
	}

	public static void deleteStudentByName() {
		System.out.println("Delete dtudent by name");
		System.out.println("Input name ");
		String name = scanner.nextLine();
		
		try {
			studentDAO.deleteStudentByName(name);
		} catch (Exception e) {
			System.out.println("There is a error when deleting a student");
			e.printStackTrace();
		}
		
	}

}
