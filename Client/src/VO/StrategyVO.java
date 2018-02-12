package VO;

import Util.EmptyValue;
import Util.StrategyType;

import java.time.LocalDate;
import java.util.Date;

public class StrategyVO {

    StrategyType type=StrategyType.EMPTY;
    LocalDate begin;
    LocalDate End;
    String id= EmptyValue.getString();

    public StrategyVO(StrategyType type, LocalDate begin, LocalDate end) {
        this.type = type;
        this.begin = begin;
        End = end;
    }
    public StrategyVO(StrategyType type, LocalDate begin, LocalDate end,String id) {
        this.type = type;
        this.begin = begin;
        End = end;
        this.id=id;
    }

    //get methods
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

    //set methods

    public void setType(StrategyType type) {
        this.type = type;
    }

    public void setBegin(LocalDate beginn) {
        this.begin = begin;
    }

    public void setEnd(LocalDate end) {
        End = end;
    }
}
