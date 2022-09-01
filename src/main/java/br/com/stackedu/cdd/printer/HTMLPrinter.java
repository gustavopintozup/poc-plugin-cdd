package br.com.stackedu.cdd.printer;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.stackedu.cdd.config.SupportedRules;

public class HTMLPrinter implements PrettyPrinter {

    private final JSONPrinter printer;

    public HTMLPrinter(JSONPrinter printer) {
        this.printer = printer;

    }

    @Override
    public String print() {
        String json = printer.print();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root;
        try {
            root = mapper.readTree(json);

            Iterator<Map.Entry<String, JsonNode>> nodes = root.fields();

            StringBuilder builder = new StringBuilder();
            builder.append("<table border='1'>");

            init_table(builder);

            while (nodes.hasNext()) {
                Entry<String, JsonNode> entry = nodes.next();

                builder.append("<tr>");
                builder.append("<th style='text-align:left'>");
                builder.append(entry.getKey());
                builder.append("</th>");

                Iterator<Map.Entry<String, JsonNode>> icps = entry.getValue().fields();

                while (icps.hasNext()) {
                    builder.append("<td style='text-align: center;'>");

                    String value = icps.next().toString().split("=")[1];

                    builder.append(value);
                    builder.append("</td>");
                }
                builder.append("</tr>");
            }
            builder.append("</table>");

            return builder.toString();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException("This was not supposed to happen: ", e);
        }
    }

    private void init_table(StringBuilder builder) {
        builder.append("<tr>");
        builder.append("<td>Class</td>");
        for (SupportedRules rule : printer.getConfig().getDefinedRules()) {
            builder.append("<td style='text-align: center;'>");
            builder.append(rule.name());
            builder.append("</td>");
        }
        builder.append("<td style='text-align: center;'>TOTAL</td>");
        builder.append("</tr>");
    }
}