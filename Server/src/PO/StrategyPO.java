package PO;

import Util.StrategyType;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by Jason on 2017/10/22.
 */
public class StrategyPO implements Serializable {

    StrategyType type;
    LocalDate begin;
    LocalDate End;
    String id;

    public StrategyPO(StrategyType currentStrategy, LocalDate beginDate, LocalDate EndDate,String ID){
        type=currentStrategy;
        begin=beginDate;
        End=EndDate;
        id=ID;
    }

    //getter
    public StrategyType getType() {
        return type;
    }

    public LocalDate getBegin() {
        return begin;
    }

    public LocalDate getEnd() {
        return End;
    }

    public String getId() {
        return id;
    }

    //setter
    public void setType(StrategyType type) {
        this.type = type;
    }

    public void setBegin(LocalDate begin) {
        this.begin = begin;
    }

    public void setEnd(LocalDate end) {
        End = end;
    }

    public void setId(String id) {
        this.id = id;
    }
}
