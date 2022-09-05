package br.com.stackedu.cdd.storage;

import br.com.stackedu.cdd.ExcludeFromJacocoGeneratedReport;


/**
 * Helper class to print ICP values
 */
public class ICPValue {
    private final String name;
    private final Integer value;
    private final Integer cost;

    public ICPValue(String name, Integer value, Integer cost) {
        this.name = name;
        this.value = value;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    public Integer getCost() {
        return cost;
    }

    @Override
    @ExcludeFromJacocoGeneratedReport
    public String toString() {
        return "[" + name + ", " + value + "]";
    }
}
