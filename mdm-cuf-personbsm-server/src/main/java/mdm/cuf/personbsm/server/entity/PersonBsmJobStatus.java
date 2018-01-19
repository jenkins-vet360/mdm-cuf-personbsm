package mdm.cuf.personbsm.server.entity;

public enum PersonBsmJobStatus {

    IN_PROGRESS,

    COMPLETED;

    public String value() {
        return name();
    }

    public static PersonBsmJobStatus fromValue(final String value) {
        return valueOf(value);
    }

}
