package shift;

import shift.arguments.ArgsInterpreter;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ArgsInterpreter arg = new ArgsInterpreter();
        arg.accomplish(args);
    }
}
