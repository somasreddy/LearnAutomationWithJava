package Test;

class A{

int x=10;
static int y=20;

public void m1(){
System.out.println("A.m1()");
}

public static void m2(){
System.out.println("A.m2()");
}
}

class B extends A{

int x=30;
static int y=40;

public void m1(){
System.out.println("B.m1()");
}

public static void m2(){
System.out.println("B.m2()");
}
}

class TestClient {
public static void main(String[] args) {

A a= new B();

a.m1();
A.m2();

System.out.println(a.x);
System.out.println(A.y);

}
}