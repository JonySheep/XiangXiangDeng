package Util;

/**
 * 包装销售进货单据中已选中的商品
 */
public class SelectedGoodItem {

    String name;
    int number;
    double price;
    String comment;

    public SelectedGoodItem(String name, int number, double price, String comment) {
        this.name = name;
        this.number = number;
        this.price = price;
        this.comment = comment;
    }


    //getter
    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public double getPrice() {
        return price;
    }

    public String getComment() {
        return comment;
    }

    //setter

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
