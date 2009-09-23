// Oliver Kullmann, 23.9.2009 (Swansea)

public class LeapYear {
  public static void main(String[] args) {

    int year;
    try {
      year = Integer.parseInt(args[0]);
    }
    catch (ArrayIndexOutOfBoundsException e) {
      System.err.println("ERROR[LeapYear]: One parameter value is needed, an integer.");
      return;
    }
    catch(RuntimeException e) {
      System.err.println("ERROR[LeapYear]: The parameter must be an integer within the range from -2147483648 to 2147483647.");
      return;
    }
    System.out.println("The input is " + year + ".");

    final boolean leap_year =
      (year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0));
    System.out.println("Leap year: " + leap_year);
  }
}
