public class Outlet_Receive {
    private static BTReceive2 duckFactory;
 
    public static void main(String[] args) {
    	duckFactory = new BTReceive2();
        duckFactory.start();
    }
}