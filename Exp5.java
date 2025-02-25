//EASY 
import java.util.List;
import java.util.ArrayList;

public class SumWithAutoboxing {
    public static void main(String[] args) {
        String[] numberStrings = {"10", "20", "30", "40", "50"};
        List<Integer> numbers = new ArrayList<>();
        int sum = 0;
        
        for (String str : numberStrings) {
            numbers.add(Integer.parseInt(str)); // Autoboxing
        }
        
        for (Integer num : numbers) {
            sum += num; // Unboxing
        }
        
        System.out.println("Sum of numbers: " + sum);
    }
}


//MEDIUM
import java.io.*;

class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private double gpa;

    public Student(int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    public void display() {
        System.out.println("Student ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("GPA: " + gpa);
    }
}

public class StudentSerialization {
    public static void main(String[] args) {
        String fileName = "student.ser";
        Student student = new Student(1, "John Doe", 3.8);
        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(student);
            System.out.println("Student serialized.");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Student deserializedStudent = (Student) ois.readObject();
            System.out.println("Deserialized Student:");
            deserializedStudent.display();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

//HARD
import java.io.*;
import java.util.*;

class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String designation;
    private double salary;

    public Employee(int id, String name, String designation, double salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }

    public void display() {
        System.out.println("Employee ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Designation: " + designation);
        System.out.println("Salary: " + salary);
        System.out.println("---------------------------");
    }
}

public class EmployeeManagement {
    private static final String FILE_NAME = "employees.ser";
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Employee> employees = loadEmployees();
        
        while (true) {
            System.out.println("1. Add an Employee");
            System.out.println("2. Display All");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    System.out.print("Enter Employee ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Designation: ");
                    String designation = scanner.nextLine();
                    System.out.print("Enter Salary: ");
                    double salary = scanner.nextDouble();
                    scanner.nextLine();
                    
                    employees.add(new Employee(id, name, designation, salary));
                    saveEmployees(employees);
                    System.out.println("Employee added successfully!\n");
                    break;
                case 2:
                    System.out.println("Employee Details:");
                    for (Employee emp : employees) {
                        emp.display();
                    }
                    break;
                case 3:
                    System.out.println("Exiting application.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private static void saveEmployees(List<Employee> employees) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(employees);
        } catch (IOException e) {
            System.err.println("Error saving employees: " + e.getMessage());
        }
    }
    
    private static List<Employee> loadEmployees() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Employee>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}
