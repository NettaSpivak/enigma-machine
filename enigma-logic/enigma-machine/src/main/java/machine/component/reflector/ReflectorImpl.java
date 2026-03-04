package machine.component.reflector;

import java.io.Serializable;

public class ReflectorImpl implements Reflector {
    private final String id;
    private final int[] mapping;

    public ReflectorImpl(String id, int[] mapping) {
        this.id = id;
        this.mapping = mapping;
    }

    public ReflectorImpl(ReflectorImpl other) {
        this.id = other.id;
        this.mapping = other.mapping.clone();
    }

    @Override
    public String getId(){
        return this.id;
    }

    @Override
    public int process(int input) {
        return mapping[input];
    }
}
