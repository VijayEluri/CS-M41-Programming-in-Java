class Client {
  public static void main(final String[] args) {
    Array a1 = new Array();
    assert(a1.size() == 0);
    
    final int[] a = new int[3]; a[0] = 3; a[1] = 77; a[2] = 45;
    Array a2 = new Array(a);
    assert(a2.size() == 3);
    assert(a2.get(0) == 3);
    assert(a2.get(1) == 77);
    assert(a2.get(2) == 45);
    assert(!a1.equals(a2));
    a[1] = 777;
    assert(a2.get(1) == 77);
    assert(!a2.equals(a1));
    
    {final int[] a_c = a2.get_array();
     assert(a_c != null);
     assert(a_c.length == 3);
     assert(a_c[0] == 3);
     assert(a_c[1] == 77);
     assert(a_c[2] == 45);
     assert(a2.equals(new Array(a_c)));
     a_c[1] = 111;
     assert(a2.get(1) == 77);
     assert(!a2.equals(new Array(a_c)));
    }
    
    assert(a2.get(-1) == 3);
    assert(a2.get(-4) == 3);
    assert(a1.get(-1) == 0);
    assert(a1.size() == 1);
    
    assert(a2.size() == 3);
    assert(a2.get(10) == 0);
    assert(a2.size() == 11);
    for (int i = 3; i <= 10; ++i) assert(a2.get(i) == 0);
    
    a1.set(0,111);
    assert(a1.size() == 1);
    assert(a1.get(0) == 111);
    a1.set(2,99);
    assert(a1.size() == 3);
    assert(a1.get(0) == 111);
    assert(a1.get(1) == 0);
    assert(a1.get(2) == 99);
    
    assert(! a1.equals(a2));
    assert(a1.equals(a1));
    assert(new Array(a1.get_array()).equals(a1));
    assert(new Array(a2.get_array()).equals(a2));

    assert(a1.toString().equals("3: 111 0 99"));
    assert(a2.toString().equals("11: 3 77 45 0 0 0 0 0 0 0 0"));
    
    a1.set(-3,456);
    assert(a1.toString().equals("3: 456 0 99"));
  }
}

