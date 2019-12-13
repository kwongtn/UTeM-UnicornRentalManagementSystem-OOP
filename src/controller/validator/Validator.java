package controller.validator;

public class Validator {
    public static String validate(String field, String value, boolean required, int length)
            throws RequiredFieldException, MaximumLengthException {
        if (required && (value == null || value.trim().isEmpty())) {
            throw new RequiredFieldException(field);
        }

        value = value.trim();

        if (value.length() > length) {
            throw new MaximumLengthException(field, length);
        }

        return value;
    }

    public static double validate(String field, String value, boolean required, boolean hasMinimum, boolean hasMaximum,
            double minimum, double maximum) throws RequiredFieldException, InvalidNumberException, MinimumNumberException, MaximumNumberException {
        if (required && (value == null || value.trim().isEmpty())) {
            throw new RequiredFieldException(field);
        }

        value = value.trim();
        double number = 0;
        
        try {
            number = Double.parseDouble(value);

        } catch (NumberFormatException e) {
            throw new InvalidNumberException(field);
        }

        if (hasMinimum && (number < minimum)) {
            throw new MinimumNumberException(field, minimum);
        }

        if (hasMaximum && (number > maximum)) {
            throw new MaximumNumberException(field, maximum);
        }

        return number;
    }

    public static int validate(String field, String value, boolean required, boolean hasMinimum, boolean hasMaximum,
            int minimum, int maximum) throws RequiredFieldException, InvalidNumberException, MinimumNumberException, MaximumNumberException {
        if (required && (value == null || value.trim().isEmpty())) {
            throw new RequiredFieldException(field);
        }

        value = value.trim();
        int number = 0;

        try {
            number = Integer.parseInt(value);

        } catch (NumberFormatException e) {
            throw new InvalidNumberException(field);
        }

        if (hasMinimum && (number < minimum)) {
            throw new MinimumNumberException(field, minimum);
        }

        if (hasMaximum && (number > maximum)) {
            throw new MaximumNumberException(field, maximum);
        }

        return number;
    }
}