public class TestDriver {
    public static void main(String[] args) throws Exception {
        Class.forName("org.postgresql.Driver");
        System.out.println("Driver OK");
    }
}
