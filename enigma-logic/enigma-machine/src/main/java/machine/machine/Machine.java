package machine.machine;

import machine.component.code.Code;
import machine.component.code.CodeSnapShot;

public interface Machine {
    String getName();
    Code getCode();
    void setCode (Code code);
    void resetCode();
    char process(char input);
    CodeSnapShot getCurrentCodeSnapShot();
}
