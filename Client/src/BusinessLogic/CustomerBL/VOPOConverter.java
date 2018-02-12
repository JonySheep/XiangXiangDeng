package BusinessLogic.CustomerBL;

import PO.CustomerPO;
import VO.CustomerVO;

public class VOPOConverter {
    /**
     * 将CustomerVO转化为PO
     * @param customervo
     * @return CusPO
     */
    CustomerPO voTOpo(CustomerVO customervo){

        //create new CustomerPO
        CustomerPO CusPO=new CustomerPO(customervo.getCustomerID(),customervo.getCustomerName(),customervo.getKind(),customervo.getLevel(),
                customervo.getTelNumber(),customervo.getAddress(),customervo.getZipCode(),customervo.getEmail(),customervo.getQuata(),customervo.getReceivable(),
                customervo.getPayable(),customervo.getClerk(),customervo.getComment());

        return CusPO;
    }


    /**
     * 将CustomerPO转化为VO
     * @param customerpo
     * @return CusVO
     */
    CustomerVO poTOvo(CustomerPO customerpo){

        //create new Customer
        CustomerVO CusVO=new CustomerVO(customerpo.getCustomerId(),customerpo.getCustomerName(),customerpo.getCustomerKinds(),customerpo.getLevel(),
                customerpo.getTelNumber(),customerpo.getAddress(),customerpo.getZipCode(),customerpo.getEmail(),customerpo.getQuata(),customerpo.getReceivable(),
                customerpo.getPayable(),customerpo.getClerk(),customerpo.getComment());

        return CusVO;
    }
}
