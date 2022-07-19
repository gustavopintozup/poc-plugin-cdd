class TryComFinally {
    public static void main(String[] args) {
        try {
            System.out.println("bla");
        }
        catch (Exception | CDDException e) {
            System.out.println("bla bla");
        }
        finally {
            System.out.println("bla bla bla");
        }
    }
}