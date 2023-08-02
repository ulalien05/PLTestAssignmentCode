

import java.util.List;


public class Value {
    private List<Test> values;

    public List<Test> getValues() {
        return values;
    }

    public void setValues(List<Test> values) {
        this.values = values;
    }

    public Value(List<Test> values) {
        this.values = values;
    }

    public Value() {}
}
