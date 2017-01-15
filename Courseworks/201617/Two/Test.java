class Test {
  public static void main(final String[] args) {
    // Checking Person:
    assert(Person.message == "ehpXdT3tie");
    final Person p1 = new Person("bavsfd");
    assert(p1.name == "bavsfd");
    assert(p1.income() == 0);
    assert(p1.toString().equals("bavsfd: 0"));
    assert(p1.increase(5));
    assert(! p1.increase(-1));
    assert(p1.income() == 5);
    assert(! p1.decrease(-1));
    assert(p1.decrease(2));
    assert(p1.income() == 3);
    assert(p1.equals(new Person("bavsfd")));
    assert(! p1.equals(new Person("bavsf")));
    assert(p1.decrease(4));
    assert(p1.income() == 0);
    assert(p1.increase(Integer.MAX_VALUE));
    assert(p1.income() == Integer.MAX_VALUE);
    assert(! p1.increase(1));
    assert(p1.income() == Integer.MAX_VALUE);
    assert(p1.decrease(Integer.MAX_VALUE));
    assert(p1.income() == 0);

    // Test change.income:
    assert(Admin.change_income(p1,0));
    assert(p1.income() == 0);
    assert(Admin.change_income(p1,-1));
    assert(p1.income() == 0);
    assert(Admin.change_income(p1,13));
    assert(p1.income() == 13);
    assert(Admin.change_income(p1,Integer.MIN_VALUE));
    assert(p1.income() == 0);
    assert(Admin.change_income(p1,Integer.MAX_VALUE));
    assert(p1.income() == Integer.MAX_VALUE);
    assert(! Admin.change_income(p1,1));
    assert(p1.income() == Integer.MAX_VALUE);
    assert(Admin.change_income(p1,-1));
    assert(p1.income() == Integer.MAX_VALUE-1);
    assert(Admin.change_income(p1,Integer.MIN_VALUE));
    assert(p1.income() == 0);

    // Test equal_persons:
    assert(! Admin.equal_persons(null));
    Person[] pa = new Person[0];
    assert(! Admin.equal_persons(pa));
    pa = new Person[1];
    assert(! Admin.equal_persons(pa));
    pa = new Person[2];
    assert(! Admin.equal_persons(pa));
    pa[0] = p1;
    assert(! Admin.equal_persons(pa));
    pa[1] = p1;
    assert(Admin.equal_persons(pa));
    pa[0] = null;
    assert(! Admin.equal_persons(pa));
    final Person p2 = new Person("jsjaj7");
    pa[0] = p2;
    assert(! Admin.equal_persons(pa));
    final Person p3 = new Person(p2.name);
    Admin.change_income(p3,88);
    pa[1] = p3;
    assert(Admin.equal_persons(pa));

    // Test sum_income:
    assert(Admin.sum_income(null) == 0);
    assert(Admin.sum_income(pa) == 88);
    pa = new Person[20];
    assert(Admin.sum_income(pa) == 0);
    pa[0] = new Person("x", Integer.MAX_VALUE);
    assert(Admin.sum_income(pa) == Integer.MAX_VALUE);
    pa[19] = new Person("y", Integer.MAX_VALUE);
    assert(Admin.sum_income(pa) == 2*((long)Integer.MAX_VALUE));

    // Test longest_name:
    assert(Admin.longest_name(null) == null);
    assert(Admin.longest_name(new Person[100]) == null);
    assert(Admin.longest_name(pa) == "x");
    pa[5] = new Person("xy");
    assert(Admin.longest_name(pa) == "xy");
    pa[7] = new Person("xz");
    assert(Admin.longest_name(pa) == "xy");
    pa[2] = new Person("xyz");
    assert(Admin.longest_name(pa) == "xyz");
    pa[19] = new Person("abcd");
    assert(Admin.longest_name(pa) == "abcd");
  }
}