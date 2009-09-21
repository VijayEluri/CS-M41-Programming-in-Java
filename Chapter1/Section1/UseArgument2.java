// Oliver Kullmann, 20.9.2009 (Swansea)

class UseArgument2 {
    public static void main(String[] args) {
        if (args.length == 0) {
          System.out.println("Error[UseArgument2]: At least one parameter is needed!");
        }
        else {
          System.out.print("Hello, Mr. ");
          System.out.print(args[0]);
          System.out.println(". How is it going?");
        }
    }
}
