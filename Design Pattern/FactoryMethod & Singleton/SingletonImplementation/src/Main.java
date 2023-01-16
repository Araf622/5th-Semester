public class Main {
    public static void main(String[] args) {
        CEO ceo = CEO.getCEO();
        System.out.println(ceo.getName());
        ceo.setName("Herbert Heiner");

        CEO ceo2 = CEO.getCEO();
        System.out.println(ceo2.getName());


    }
}