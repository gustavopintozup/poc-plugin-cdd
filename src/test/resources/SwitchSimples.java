package br.com.zupedu.cdd;

public class SwitchSimples {

    public String exampleOfSwitch(String animal) {
        String result;
        switch (animal) {
            case "DOG":
                result = "domestic animal"; 
                break;
            case "CAT":
                result = "domestic animal";
                break;
            case "TIGER":
                result = "wild animal";
                break;
            default:
                result = "unknown animal";
                break;
        }
        return result;
    }

    public void switchComYield() { 
        var result = switch (month) {
            case JANUARY, JUNE, JULY -> 3;
            case FEBRUARY, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER -> 1;
            case MARCH, MAY, APRIL, AUGUST -> {
                int monthLength = month.toString().length();
                yield monthLength * 4;
            }
            default -> 0;
        }; 
    }

}