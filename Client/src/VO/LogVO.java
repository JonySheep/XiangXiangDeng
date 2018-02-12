package VO;

import PO.LogPO;
import Util.EmptyValue;
import Util.OperationType;

public class LogVO {
    private OperationType operationType = OperationType.EMPTY;
    private String user = EmptyValue.getString();
    private String remark = EmptyValue.getString();

    public LogVO(OperationType operationType, String user, String remark) {
        this.operationType = operationType;
        this.user = user;
        this.remark = remark;
    }

    public LogVO(LogPO logPO){
        this.operationType = logPO.getOperationType();
        this.user = logPO.getUser();
        this.remark = logPO.getRemark();
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
