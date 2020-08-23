package thanh.laptrinh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import thanh.laptrinh.core.Student;

public class StudentDAO {
	private Connection conn;

	public Connection getConnection() throws SQLException {
		if (conn == null) {
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hoc_mysql", "root", "123456");

			return conn;
		}
		return conn;
	}

	public void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean addNewStudent(Student student) throws SQLException {
		String query = "insert into student (`id`,`name`,`email`) " + "values(" + student.getId() + ",'"
				+ student.getName() + "','" + student.getEmail() + "')";
		Statement stmt = getConnection().createStatement();
		int n = stmt.executeUpdate(query);
		if (n != 0)
			return true;
		return false;
	}

	public boolean modifyStudent(Student student) throws SQLException {
		String query = "update student " + "set `name` ='" + student.getName() + "' , `email` = '"
				+ student.getEmail() + "' where `id` =" + student.getId();
		Statement stmt = getConnection().createStatement();
		int n = stmt.executeUpdate(query);

		if (n != 0)
			return true;
		return false;
	}

	public boolean deleteStudentByName(String name) throws SQLException {
		String query = "delete from student where name = ?";
		PreparedStatement preStmt = getConnection().prepareStatement(query);
		preStmt.setString(1, name);

		int n = preStmt.executeUpdate();

		if (n != 0) {
			System.out.println(n + " rows deleted ");
		}
		return false;
	}

	public Student findStudentByName(String name) throws SQLException {
		String query = "select * from student where `name` ='" + name + "'";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);

		if (resultSet.next()) {
			Student student = new Student();
			student.setId(resultSet.getLong("id"));
			student.setName(resultSet.getString("name"));
			student.setEmail(resultSet.getString("email"));
			return student;
		} else {
			return null;
		}
	}

	public Student findStudentById(long id) throws SQLException {
		String query = " select * from student where id= ' " + id + " ' ";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);

		if (resultSet.next()) {
			Student student = new Student();
			student.setId(resultSet.getLong("id"));
			student.setName(resultSet.getString("name"));
			student.setEmail(resultSet.getString("email"));
			return student;
		} else {
			return null;
		}
	}

	public ArrayList<Student> findAll() throws SQLException {
		String query = "select * from student ";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		ArrayList<Student> studentList = new ArrayList<Student>();

		while (resultSet.next()) {
			Student student = new Student();
			student.setId(resultSet.getLong("id"));
			student.setName(resultSet.getString("name"));
			student.setEmail(resultSet.getString("email"));
			studentList.add(student);
		}
		return studentList;
	}

	public long generateId() throws SQLException {
		String query = "select max(id) as maxId from student ";// tim thag id lon nhat va cong them 1
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		ArrayList<Student> studentList = new ArrayList<Student>();

		if (resultSet.next()) {
			return resultSet.getLong("maxId") + 1;
		} else {
			return 0;
		}
	}

}
