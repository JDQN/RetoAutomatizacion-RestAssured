package co.com.restfulbooker.util;


public enum Log4j2Values {

    LOG4J_PROPERTIES_FILE_PATH("\\src\\main\\resources\\log4j.properties");

    private final String value;

    Log4j2Values(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

