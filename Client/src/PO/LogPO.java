package PO;


import Util.EmptyValue;
import Util.OperationType;

import java.io.Serializable;

/**
 * Created by Jason on 2017/10/22.
 */
public class LogPO implements Serializable {
    private OperationType operationType = OperationType.EMPTY;
    private String user = EmptyValue.getString();
    private String remark = EmptyValue.getString();



    /**
     * LogPO的构造器
     * @param operationType 操作名称
     * @param user 当前操作员
     * @param remark 附加备注
     */
    public LogPO(OperationType operationType, String user, String remark) {
        this.operationType = operationType;
        this.user = user;
        this.remark = remark;
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
