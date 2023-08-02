

import java.util.List;
public class Layout {

    private List<Test> tests;

    public Layout(List<Test> tests) {
        this.tests = tests;
    }
    public Layout() {}

    public List<Test> getTests() {
        return tests;
    }
    public void setTests(List<Test> tests) {
        this.tests = tests;
    }


}
