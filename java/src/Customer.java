public class Customer {
    private String id;
    private String name;
    private String state;
    private boolean loyalty;

    public Customer(String id, String name, String state, boolean loyalty) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.loyalty = loyalty;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public boolean isLoyalty() {
        return loyalty;
    }

    @Override
    public String toString() {
        return name + " (" + state + "), loyalty=" + loyalty;
    }
}
