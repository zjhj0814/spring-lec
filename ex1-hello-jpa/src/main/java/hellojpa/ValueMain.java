package hellojpa;

public class ValueMain {
    public static void main(String[] args){
        Integer a = 10;
        //integer a = 10;
        Integer b = a;
        //a = 20; // 공유 X -> 사이드 이펙트 없음
        System.out.println("a = " + a);
        System.out.println("b = " + b);

        Address address1 = new Address("city", "street", "1001");
        Address address2 = new Address("city", "street", "1001");

        System.out.println("address1 == address2: " + (address1 == address2));
        System.out.println("address1 equals address2: " + (address1.equals(address2)));
    }
}
