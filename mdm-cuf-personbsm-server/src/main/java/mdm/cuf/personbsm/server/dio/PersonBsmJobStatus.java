package mdm.cuf.personbsm.server.dio;

public enum PersonBsmJobStatus {

    PROGESSS,

    COMPLETED;

    public String value() {
        return name();
    }

    public static PersonBsmJobStatus fromValue(final String value) {
        return valueOf(value);
    }

}
