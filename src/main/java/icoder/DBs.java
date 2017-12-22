package icoder;

public enum DBs {

    DB1("db1"),
    DB2("db2");

    private String type;

    DBs(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }

    public String type() {
        return type;
    }

    public static DBs fromString(String stringOperator) {
        if (stringOperator != null) {
            for (DBs operator : DBs.values()) {
                if (stringOperator.equalsIgnoreCase(operator.type)) {
                    return operator;
                }
            }
        }
        return null;
    }
}
