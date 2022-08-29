package br.com.stackedu.cdd.storage;

import br.com.stackedu.cdd.ExcludeFromJacocoGeneratedReport;


/**
 * Helper class to print ICP values
 */
public class ICPValue {
    private final String name;
    private final Integer value;

    public ICPValue(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    @ExcludeFromJacocoGeneratedReport
    public String toString() {
        return "[" + name + ", " + value + "]";
    }
}
