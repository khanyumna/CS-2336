//Name: Yumna Khan ID: YK220040

public class Driver implements Comparable<Driver> {
    private String name;
    private double area;
    private static String comparison = "name";

    public Driver() {

    }

    public Driver(String name, double area) {
        this.name = name;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public double getArea() {
        return area;
    }

    public static String getComparison() {
        return comparison;
    }

    public static void setComparison(String value) {
        comparison = value;
    }

    @Override
    public int compareTo(Driver other){
        if (comparison.equals("name")){
            return this.name.compareTo(other.getName());
        }
        else if (comparison.equals("area")){
            return Double.compare(this.area, other.getArea());
        }
        else {
            return 0;
        }
    }

    @Override
    public String toString(){
        return "[Name: " + name + ", Area: " + area + "]";
    }
}
