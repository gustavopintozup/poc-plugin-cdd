package br.com.stackedu.cdd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Stores ICP metrics
 * 
 * @author gustavopinto
 */
public class StoreMetrics {

    private Map<String, List<ICPValue>> dataset = new HashMap<>();

    public Map<String, List<ICPValue>> getDataset() {
        return dataset;
    }

    public void save(String classQualifiedName, String ICP) {
        if (dataset.containsKey(classQualifiedName)) {
            List<ICPValue> existingValues = dataset.get(classQualifiedName);
            List<ICPValue> newValues = null;

            if (checkIfICPExists(ICP, existingValues)) {
                newValues = updateExistingValues(ICP, existingValues);
            } else {
                newValues = addNewValue(ICP, existingValues);
            }

            dataset.replace(classQualifiedName, newValues);
        } else {
            dataset.put(classQualifiedName, Arrays.asList(new ICPValue(ICP, 1)));
        }
    }

    private static List<ICPValue> addNewValue(String ICP, List<ICPValue> existingValues) {
        List<ICPValue> v = new ArrayList<>(existingValues);
        v.add(new ICPValue(ICP, 1));
        return v;
    }

    private static List<ICPValue> updateExistingValues(String ICP, List<ICPValue> existingValues) {
        List<ICPValue> v = new ArrayList<>(existingValues);

        for (int i = 0; i < existingValues.size(); i++) {
            ICPValue actual = existingValues.get(i);

            if (ICP.equals(actual.getICP())) {
                ICPValue newValue = new ICPValue(ICP, actual.getValue() + 1);
                v.set(i, newValue);
            }
        }

        return v;
    }

    private static boolean checkIfICPExists(String ICP, List<ICPValue> existingValues) {
        boolean ICPExists = false;
        for (ICPValue value : existingValues) {
            if (value.getICP().equals(ICP)) {
                ICPExists = true;
            }
        }
        return ICPExists;
    }
}

/**
 * Helper class to print ICP values
 */
class ICPValue {
    private final String ICP;
    private final Integer value;

    protected ICPValue(String ICP, Integer value) {
        this.ICP = ICP;
        this.value = value;
    }

    protected String getICP() {
        return ICP;
    }

    protected Integer getValue() {
        return value;
    }

    @Override
    @ExcludeFromJacocoGeneratedReport
    public String toString() {
        return "[" + ICP + ", " + value + "]";
    }
}
