class TernarioEmDoisMetodos {
    public static void main(String[] args) {
        int marks = 10;
        String result = (marks > 40) ? "pass" : "fail";
    }

    public boolean other(Object o) {
       return (1 > 0) ? false : true;
    }
}