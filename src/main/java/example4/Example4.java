package example4;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sergey Stol
 * 2023-09-27
 */
public class Example4 {
   public static void main(String[] args) throws Exception {
      Employee employee1 = new Employee("personName1", "personSurname1", null);
      Employee employee2 = new Employee("personName2", "personSurname2", null);
      Manager manager = new Manager("ManagerName1", "ManagerSurname1", List.of(employee1, employee2));
      employee1.manager = manager;
      employee2.manager = manager;

      saveManager(manager);
      saveEmployees(employee1, employee2);
      savePersons(manager, employee1, employee2);
   }

   private static void saveManager(Manager manager) throws Exception {
      System.out.println("Manager before: " + manager);
      FileOutputStream fileOutputStream = new FileOutputStream("example4-manager.txt");
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
      objectOutputStream.writeObject(manager);

      FileInputStream fileInputStream = new FileInputStream("example4-manager.txt");
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
      Manager managerDeserialized = (Manager) objectInputStream.readObject();

      System.out.println("Manager after: " + managerDeserialized);
   }

   private static void saveEmployees(Employee ...employees) throws Exception {
      System.out.println("Employees before: " + listToString(employees));
      FileOutputStream fileOutputStream = new FileOutputStream("example4-employees.txt");
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
      objectOutputStream.writeObject(Arrays.stream(employees).toList());

      FileInputStream fileInputStream = new FileInputStream("example4-employees.txt");
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
      List<Employee> employeesDeserialized = (List<Employee>) objectInputStream.readObject();

      System.out.println("Employees after: " + employeesDeserialized);
   }

   private static void savePersons(Person ...persons) throws Exception {
      System.out.println("Persons before: " + listToString(persons));
      FileOutputStream fileOutputStream = new FileOutputStream("example4-persons.txt");
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
      objectOutputStream.writeObject(Arrays.stream(persons).toList());

      FileInputStream fileInputStream = new FileInputStream("example4-persons.txt");
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
      List<Manager> personsDeserialized = (List<Manager>) objectInputStream.readObject();

      System.out.println("Persons after: " + personsDeserialized);
   }

   public static String listToString(Object ...employees) {
      return Arrays.stream(employees)
        .map(Object::toString)
        .collect(Collectors.joining(", "));
   }
}

@AllArgsConstructor
@ToString
class Person implements Serializable {
   String name;
   String surname;
}

class Employee extends Person {
   public Employee(String name, String surname, Manager manager) {
      super(name, surname);
      this.manager = manager;
   }

   Manager manager;

   @Override
   public String toString() {
      return "Employee{" +
        "manager=" + manager.name + " " + manager.surname +
        ", name='" + name + '\'' +
        ", surname='" + surname + '\'' +
        '}';
   }
}

class Manager extends Person {
   List<Employee> subordinates;

   public Manager(String name, String surname, List<Employee> subordinates) {
      super(name, surname);
      this.subordinates = subordinates;
   }

   @Override
   public String toString() {
      return "Manager{" +
        "subordinates=[" + getSubordinatesAsString(subordinates) + "]" +
        ", name='" + name + '\'' +
        ", surname='" + surname + '\'' +
        '}';
   }

   private String getSubordinatesAsString(List<Employee> subordinates) {
      StringBuilder stringBuilder = new StringBuilder();
      for (Employee subordinate : subordinates) {
         stringBuilder.append(subordinate.name);
         stringBuilder.append(" ");
         stringBuilder.append(subordinate.surname);
         stringBuilder.append(", ");
      }
      String subordinatesString = stringBuilder.toString();
      String trimmed = subordinatesString.trim();
      return trimmed.substring(0, trimmed.length() - 1);
   }
}
