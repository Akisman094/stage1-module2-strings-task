package com.epam.mjc;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        String name, returnType, accessModifier = null;
        String[] parts = signatureString.split("\\s|\\(|\\)|(, )");
        MethodSignature methodSignature = null;

        try
        {
            //Parse
            int i = 0;
            if(parts[i].equals("private") || parts[i].equals("public") || parts[i].equals("protected")) {
                accessModifier = parts[i];
                i++;
            }
            returnType = parts[i++];
            name = parts[i++];

            //Create method signature
            methodSignature = new MethodSignature(name);
            if(accessModifier != null)
                methodSignature.setAccessModifier(accessModifier);
            methodSignature.setReturnType(returnType);
            for (; i < parts.length; i += 2) {
                methodSignature.getArguments().add(new MethodSignature.Argument(parts[i], parts[i + 1]));
            }
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.err.println("Error: Invalid signature: " + e.getMessage());
        }
        return methodSignature;
    }
}
