public class CEO {

    private String Name = "Oliver Kahn";

    private static CEO ceo = new CEO();


    private CEO(){};

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public static CEO getCEO(){
        return ceo;
    }

    public void show(){
        System.out.println("Get to work!!");
    }
}
