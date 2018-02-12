package BusinessLogic.StrategyBL;

import Util.GiftItem;

import java.util.ArrayList;

public class MockStrategy extends Strategy {

    /**
     * getGift的mock方法
     * @param level
     * @param amount
     * @return
     */
    ArrayList<GiftItem> getGift(int level , double amount){

        ArrayList<GiftItem> gifts=null;

        //level
        if(level==1){
            gifts.add(new GiftItem("香香灯",2));
        }
        else if(level==2){
            gifts.add(new GiftItem("超级香香灯",2));
        }
        else if(level==3){
            gifts.add(new GiftItem("椒麻灯",2));
            gifts.add(new GiftItem("超级椒麻灯",2));
        }
        else if(level==4){
            gifts.add(new GiftItem("香香灯",2));
            gifts.add(new GiftItem("超级椒麻灯",3));
        }
        else if(level==5){
            gifts.add(new GiftItem("超级香香灯",2));
            gifts.add(new GiftItem("超级椒麻灯",3));
        }

        //amount
        if(amount>=200){
            gifts.add(new GiftItem("超级香香灯",1));
        }

        return gifts;
    }


    /**
     * checkPackage的mock方法
     * @param goods
     * @return
     */
    double checkPackage(ArrayList<String> goods){

        return 200;
    }


    /**
     * getDiscount的mock方法
     * @param level
     * @return
     */
    double getDiscount(int level){
        double discount=0;

        if(level==1){
            discount=50;
        }
        else if(level==2){
            discount=100;
        }
        else if(level==3){
            discount=200;
        }
        else if(level==4){
            discount=300;
        }
        else if(level==5){
            discount=400;
        }

        return discount;
    }
}
