package br.com.stackedu.cdd.storage;

import br.com.stackedu.cdd.ExcludeFromJacocoGeneratedReport;


/**
 * Helper class to print ICP values
 */
public class ICPValue {
    private final String ICP;
    private final Integer value;

    public ICPValue(String ICP, Integer value) {
        this.ICP = ICP;
        this.value = value;
    }

    public String getICP() {
        return ICP;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    @ExcludeFromJacocoGeneratedReport
    public String toString() {
        return "[" + ICP + ", " + value + "]";
    }
}
