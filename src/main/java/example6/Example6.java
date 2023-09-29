package example6;

import java.io.*;

/**
 * Quiz yourself: Serializing a primitive with ObjectOutputStream
 * <a href="https://blogs.oracle.com/javamagazine/post/java-quiz-serialize-primitive-value">Quiz yourself: Serializing a primitive with ObjectOutputStream</a>
 */
public class Example6 {
   static long longVariable = 5L;

   public static void main(String[] args) {

      try (FileOutputStream fileOutputStream = new FileOutputStream("example6.txt");
           ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
//         variantA(objectOutputStream);
//         variantB(objectOutputStream);
//         variantC(objectOutputStream);
//         variantD(objectOutputStream);
         variantE(objectOutputStream);
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }


   private static void variantA(ObjectOutputStream objectOutputStream) throws IOException, ClassNotFoundException {
      objectOutputStream.writeObject(Long.valueOf(longVariable));

      FileInputStream fileInputStream = new FileInputStream("example6.txt");
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
      long longVariableDeserialized = (Long) objectInputStream.readObject();
      System.out.println(longVariableDeserialized);
   }

   private static void variantB(ObjectOutputStream objectOutputStream) throws IOException, ClassNotFoundException {
      objectOutputStream.writeObject(new OuterClass(longVariable));

      FileInputStream fileInputStream = new FileInputStream("example6.txt");
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
      OuterClass outerClass = (OuterClass) objectInputStream.readObject();
      System.out.println(outerClass);
   }

   private static void variantC(ObjectOutputStream objectOutputStream) throws IOException, ClassNotFoundException {
      objectOutputStream.writeObject(longVariable);

      FileInputStream fileInputStream = new FileInputStream("example6.txt");
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
      long longVariableDeserialized = (Long) objectInputStream.readObject();
      System.out.println(longVariableDeserialized);
   }

   private static void variantD(ObjectOutputStream objectOutputStream) throws IOException, ClassNotFoundException {
      objectOutputStream.writeLong(longVariable);
      objectOutputStream.flush();
      objectOutputStream.close();

      FileInputStream fileInputStream = new FileInputStream("example6.txt");
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
      long longVariableDeserialized = objectInputStream.readLong();
      System.out.println(longVariableDeserialized);
   }

   private static void variantE(ObjectOutputStream objectOutputStream) throws IOException {
      objectOutputStream.close();

      try (FileOutputStream fileOutputStream = new FileOutputStream("example6.txt");
           FileInputStream fileInputStream = new FileInputStream("example6.txt")) {
         DataOutputStream dataOutput = new DataOutputStream(fileOutputStream);
         dataOutput.writeLong(5L);
         dataOutput.flush();
         dataOutput.close();

         DataInputStream dataInputStream = new DataInputStream(fileInputStream);
         long l = dataInputStream.readLong();
         System.out.println(l);
      }
   }
}


class OuterClass implements Serializable {
   long longVariable;

   public OuterClass(long longVariable) {
      this.longVariable = longVariable;
   }

   @Override
   public String toString() {
      return "OuterClass{" +
        "longVariable=" + longVariable +
        '}';
   }
}