package engine.history;

import machine.component.code.CodeSnapShot;

public class CodeHistory {
    private final CodeSnapShot codeSnapShot;

    public CodeHistory(CodeSnapShot codeSnapShot) {
        this.codeSnapShot = codeSnapShot;
    }

    public CodeSnapShot getCodeSnapShot() {
        return this.codeSnapShot;
    }
}
