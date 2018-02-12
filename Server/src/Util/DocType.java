package Util;

public enum DocType {

    GOOD_OVERFLOW("KCBYD"),
    GOOD_LOSS("KCBSD"),
    GOOD_GIFT("KCZSD"),
    PURCHASE("JHD"),
    PURCHASE_RETURN("JHTHD"),
    SALE("XSD"),
    SALE_RETURN("XSTHD"),
    PAYING("FKD"),
    RECEIVING("SKD"),
    CASH("XJFYD");

    public static DocType parse(String str) {
        switch (str) {
            case "KCBYD":   return DocType.GOOD_OVERFLOW;
            case "KCBSD":   return DocType.GOOD_LOSS;
            case "KCZSD":   return DocType.GOOD_GIFT;
            case "JHD":     return DocType.PURCHASE;
            case "JHTHD":   return DocType.PURCHASE_RETURN;
            case "XSD":     return DocType.SALE;
            case "XSTHD":   return DocType.SALE_RETURN;
            case "FKD":     return DocType.PAYING;
            case "SKD":     return DocType.RECEIVING;
            case "XJFYD":   return DocType.CASH;
        }
        return null;
    }

    private String str;

    DocType(String str) {
        this.str = str;
    }

    public String toString() {
        return str;
    }
}
