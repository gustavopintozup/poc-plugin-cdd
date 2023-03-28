package br.com.stackedu.cdd.printer;

public class SummaryPrinter implements PrettyPrinter {

    private final JSONPrinter printer;

    public SummaryPrinter(JSONPrinter printer) {
        this.printer = printer;

    }

    @Override
    public String print() {
        String json = printer.print();
        StringBuilder builder = new StringBuilder();

        var total_classes = 1;
        var total_classes_over_limit = 0 ;

        if (json.contains("TOTAL")) { 
            var is_over_limit = json.split("TOTAL")[1]
                                            .replaceAll("}", "")
                                            .replaceAll("\"", "")
                                            .replace(":", "");

            if(Double.parseDouble(is_over_limit) > printer.getConfig().limit()) {
                total_classes_over_limit++;
            }
        }

        builder.append("Total Classes: " + total_classes);
        builder.append("\n");
        builder.append("Total Classes over limit: " + total_classes_over_limit);

        return builder.toString();
    }

}