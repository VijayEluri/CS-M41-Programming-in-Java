// Oliver Kullmann, 22.9.2009 (Swansea)

class Ruler {
  public static void main(String[] args) {
    final String ruler1 = "1";
    final String ruler2 = ruler1 + " 2 " + ruler1;
    final String ruler3 = ruler2 + " 3 " + ruler2;
    final String ruler4 = ruler3 + " 4 " + ruler3;
    System.out.println(ruler1);
    System.out.println(ruler2);
    System.out.println(ruler3);
    System.out.println(ruler4);
  }
} 

