package BusinessLogic.DocBL;

import BusinessLogic.AccountBL.AccountController;
import BusinessLogic.CommodityBL.CommodityBLInfo;
import BusinessLogic.CommodityBL.CommodityController;
import BusinessLogic.CustomerBL.CusManageController;
import BusinessLogic.CustomerBL.Customer;
import BusinessLogic.UserBL.UserController;
import BusinessLogic.UserBL.UserInfo;
import BusinessLogicService.DocBLService;
import DataService.DocDataService;
import PO.*;
import Rmi.RemoteHelper;
import Util.DocType;
import VO.*;
import Util.ResultMessage;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DocController implements DocBLService {

    private DocDataService docDataService;
    private UserController userInfo;
    private CommodityBLInfo commodityBLInfo;
    private AccountController accountInfo;
    private CusManageController customerBLInfo;

    public DocController() {
        docDataService = RemoteHelper.getInstance().getDocDataService();
        userInfo = new UserController();
        commodityBLInfo = CommodityController.getInstance();
        accountInfo = new AccountController();
        customerBLInfo = new CusManageController();
    }

    private ArrayList<GoodDocVO> goodDocPOListToVOList(ArrayList<GoodDocPO> poList) {
        if (poList == null)
            return null;

        ArrayList<GoodDocVO> ret = new ArrayList<>();
        for (GoodDocPO po : poList) {
            UserVO operatorVO = userInfo.searchByID(po.getOperatorId());
            UserForDocVO operator = new UserForDocVO(operatorVO.getUserID(),
                    operatorVO.getName());

            ArrayList<GoodItemForGoodDocVO> itemList = new ArrayList<>();
            for (GoodItemForGoodDocPO itemPO : po.getItemList()) {
                GoodVO goodVO = commodityBLInfo.goodsearchByID(itemPO.getId());
                itemList.add(new GoodItemForGoodDocVO(goodVO.getId(), goodVO.getName(),
                        goodVO.getType(), goodVO.getNowAmount(), itemPO.getChange()));
            }

            ret.add(new GoodDocVO(po.getPrKey(), operator, po.getType(),
                    itemList, po.getComment()));
        }
        return ret;
    }

    @Override
    public ArrayList<GoodDocVO> getGoodOverflowDraftList(String userId) {
        ArrayList<GoodDocPO> poList = null;
        try {
            poList = docDataService.getGoodOverflowDraftList(userId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return goodDocPOListToVOList(poList);
    }

    @Override
    public ArrayList<GoodDocVO> getGoodLossDraftList(String userId) {
        ArrayList<GoodDocPO> poList = null;
        try {
            poList = docDataService.getGoodLossDraftList(userId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return goodDocPOListToVOList(poList);
    }

    @Override
    public ArrayList<GoodDocVO> getGoodGiftDraftList(String userId) {
        ArrayList<GoodDocPO> poList = null;
        try {
            poList = docDataService.getGoodGiftDraftList(userId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return goodDocPOListToVOList(poList);
    }

    private PaymentDocVO paymentDocPOToVO(PaymentDocPO po) {
        UserVO operatorVO = userInfo.searchByID(po.getOperatorId());
        UserForDocVO operator = new UserForDocVO(operatorVO.getUserID(), operatorVO.getName());
        CustomerVO customerVO = customerBLInfo.searchByID(po.getCustomerId());
        CustomerForDocVO customer = new CustomerForDocVO(customerVO.getCustomerID(),
                customerVO.getCustomerName(), customerVO.getLevel());

        ArrayList<AccountForDocVO> accountList = new ArrayList<>();
        for (AccountForDocPO accountPO : po.getAccountList()) {
            AccountVO accountVO = accountInfo.getInfo(accountPO.getCardNumber());
            accountList.add(new AccountForDocVO(accountPO.getCardNumber(),
                    accountVO.getCardName(), accountPO.getAmount(), accountPO.getComment()));
        }

        return new PaymentDocVO(po.getPrKey(), operator, po.getType(),customer, accountList);
    }

    private ArrayList<PaymentDocVO> paymentDocPOListToVOList(ArrayList<PaymentDocPO> poList) {
        if (poList == null)
            return null;

        ArrayList<PaymentDocVO> ret = new ArrayList<>();
        for (PaymentDocPO po : poList) {
            ret.add(paymentDocPOToVO(po));
        }
        return ret;
    }

    @Override
    public ArrayList<PaymentDocVO> getPayingDraftList(String userId) {
        ArrayList<PaymentDocPO> poList = null;
        try {
            poList = docDataService.getPayingDraftList(userId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return paymentDocPOListToVOList(poList);
    }

    @Override
    public ArrayList<PaymentDocVO> getReceivingDraftList(String userId) {
        ArrayList<PaymentDocPO> poList = null;
        try {
            poList = docDataService.getReceivingDraftList(userId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return paymentDocPOListToVOList(poList);
    }

    private CashDocVO cashDocPOToVO(CashDocPO po) {
        UserVO operatorVO = userInfo.searchByID(po.getOperatorId());
        UserForDocVO operator = new UserForDocVO(operatorVO.getUserID(),
                operatorVO.getName());
        AccountVO accountVO = accountInfo.getInfo(po.getAccount());
        AccountForDocVO account = new AccountForDocVO(accountVO.getCardNumber(),
                accountVO.getCardName());
        ArrayList<CashItemVO> itemList = new ArrayList<>();
        for (CashItemPO itemPO : po.getItemList()) {
            itemList.add(new CashItemVO(itemPO.getName(),
                    itemPO.getAmount(), itemPO.getComment()));
        }

        return new CashDocVO(po.getPrKey(), operator, po.getType(), account, itemList);
    }

    @Override
    public ArrayList<CashDocVO> getCashDraftList(String userId) {
        ArrayList<CashDocVO> ret = null;
        try {
            ArrayList<CashDocPO> poList = docDataService.getCashDraftList(userId);
            if (poList != null) {
                ret = new ArrayList<>();
                for (CashDocPO po : poList) {
                    ret.add(cashDocPOToVO(po));
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return ret;
    }

    private ArrayList<PurchaseDocVO> purchaseDocPOListToVOList(ArrayList<PurchaseDocPO> poList) {
        if (poList == null)
            return null;

        ArrayList<PurchaseDocVO> ret = new ArrayList<>();
        for (PurchaseDocPO po : poList) {
            UserVO operatorVO = userInfo.searchByID(po.getOperatorId());
            UserForDocVO operator = new UserForDocVO(operatorVO.getUserID(),
                    operatorVO.getName());
            CustomerVO customerVO = customerBLInfo.searchByID(po.getCustomerId());
            CustomerForDocVO customer = new CustomerForDocVO(customerVO.getCustomerID(),
                    customerVO.getCustomerName(), customerVO.getLevel());

            ArrayList<GoodItemForPurchaseSaleDocVO> itemList = new ArrayList<>();
            for (GoodItemForPurchaseSaleDocPO itemPO : po.getItemList()) {
                GoodVO goodVO = commodityBLInfo.goodsearchByID(itemPO.getId());
                itemList.add(new GoodItemForPurchaseSaleDocVO(goodVO.getId(),
                        goodVO.getName(), goodVO.getType(), goodVO.getNowAmount(),
                        itemPO.getNumber(), itemPO.getUnitPrice(), itemPO.getComment()));
            }

            ret.add(new PurchaseDocVO(po.getPrKey(), operator,
                    po.getType(), customer, itemList, po.getComment()));
        }
        return ret;
    }

    @Override
    public ArrayList<PurchaseDocVO> getPurchaseDraftList(String userId) {
        ArrayList<PurchaseDocPO> poList = null;
        try {
            poList = docDataService.getPurchaseDraftList(userId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return purchaseDocPOListToVOList(poList);
    }

    @Override
    public ArrayList<SaleDocVO> getSaleDraftList(String userId) {
        return null;
    }

    @Override
    public ArrayList<SaleDocVO> getSaleReturnDraftList(String userId) {
        return null;
    }

    @Override
    public ArrayList<CustomerVO> getSupplier() {
        return customerBLInfo.getSupplier();
    }

    @Override
    public ArrayList<CustomerVO> getSaler() {
        return customerBLInfo.getSaler();
    }

    @Override
    public ResultMessage addDocDraft(GoodDocVO goodDoc) {
        ResultMessage res = ResultMessage.FAILED;
        try {
            res = docDataService.addDocDraft(goodDoc.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public ResultMessage addDocDraft(PaymentDocVO paymentDoc) {
        ResultMessage res = ResultMessage.FAILED;
        try {
            res = docDataService.addDocDraft(paymentDoc.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public ResultMessage addDocDraft(CashDocVO cashDoc) {
        ResultMessage res = ResultMessage.FAILED;
        try {
            res = docDataService.addDocDraft(cashDoc.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public ArrayList<AccountVO> getAccountList() {
        return accountInfo.getAccountList();
    }

    @Override
    public ResultMessage addDocDraft(PurchaseDocVO purchaseDoc) {
        ResultMessage res = ResultMessage.FAILED;
        try {
            res = docDataService.addDocDraft(purchaseDoc.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public ResultMessage addDocDraft(SaleDocVO saleDoc) {
        ResultMessage res = ResultMessage.FAILED;
        try {
            res = docDataService.addDocDraft(saleDoc.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public ResultMessage editDocDraft(GoodDocVO goodDoc) {
        ResultMessage res = ResultMessage.FAILED;
        try {
            res = docDataService.editDocDraft(goodDoc.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public ResultMessage editDocDraft(PaymentDocVO paymentDoc) {
        ResultMessage res = ResultMessage.FAILED;
        try {
            res = docDataService.editDocDraft(paymentDoc.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public ResultMessage editDocDraft(CashDocVO cashDoc) {
        ResultMessage res = ResultMessage.FAILED;
        try {
            res = docDataService.editDocDraft(cashDoc.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public ResultMessage editDocDraft(PurchaseDocVO purchaseDoc) {
        ResultMessage res = ResultMessage.FAILED;
        try {
            res = docDataService.editDocDraft(purchaseDoc.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public ResultMessage editDocDraft(SaleDocVO saleDoc) {
        ResultMessage res = ResultMessage.FAILED;
        try {
            res = docDataService.editDocDraft(saleDoc.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public ResultMessage delDocDraft(GoodDocVO goodDoc) {
        ResultMessage res = ResultMessage.FAILED;
        try {
            res = docDataService.delDocDraft(goodDoc.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public ResultMessage delDocDraft(PaymentDocVO paymentDoc) {
        ResultMessage res = ResultMessage.FAILED;
        try {
            res = docDataService.delDocDraft(paymentDoc.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public ResultMessage delDocDraft(CashDocVO cashDoc) {
        ResultMessage res = ResultMessage.FAILED;
        try {
            res = docDataService.delDocDraft(cashDoc.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public ResultMessage delDocDraft(PurchaseDocVO purchaseDoc) {
        ResultMessage res = ResultMessage.FAILED;
        try {
            res = docDataService.delDocDraft(purchaseDoc.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public ResultMessage delDocDraft(SaleDocVO saleDocVO) {
        ResultMessage res = ResultMessage.FAILED;
        try {
            res = docDataService.delDocDraft(saleDocVO.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public ResultMessage submitDocDraft(GoodDocVO goodDoc) {
        ResultMessage res = ResultMessage.FAILED;
        try {
            res = docDataService.submitDocDraft(goodDoc.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public ResultMessage submitDocDraft(PaymentDocVO paymentDoc) {
        ResultMessage res = ResultMessage.FAILED;
        try {
            res = docDataService.submitDocDraft(paymentDoc.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public ResultMessage submitDocDraft(CashDocVO cashDoc) {
        ResultMessage res = ResultMessage.FAILED;
        try {
            res = docDataService.submitDocDraft(cashDoc.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public ResultMessage submitDocDraft(PurchaseDocVO purchaseDoc) {
        ResultMessage res = ResultMessage.FAILED;
        try {
            res = docDataService.submitDocDraft(purchaseDoc.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public ResultMessage submitDocDraft(SaleDocVO saleDoc) {
        ResultMessage res = ResultMessage.FAILED;
        try {
            res = docDataService.submitDocDraft(saleDoc.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public ArrayList<GoodDocVO> getUncheckedGoodDocList() {
        ArrayList<GoodDocPO> poList = null;
        try {
            poList = docDataService.getUncheckedGoodDocList();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        ArrayList<GoodDocVO> goods=goodDocPOListToVOList(poList);
        return goodDocPOListToVOList(poList);
    }

    @Override
    public ArrayList<FinanceDocVO> getUncheckedFinanceDocList() {
        ArrayList<FinanceDocVO> ret = null;
        try {
            ArrayList<FinanceDocPO> poList = docDataService.getUncheckedFinanceDocList();
            if (poList != null) {
                ret = new ArrayList<>();
                for (FinanceDocPO po : poList) {
                    if (po.getType() == DocType.CASH) {
                        ret.add(cashDocPOToVO((CashDocPO)po));
                    } else {
                        ret.add(paymentDocPOToVO((PaymentDocPO)po));
                    }
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public ArrayList<PurchaseDocVO> getUncheckedPurchaseDocList() {
        ArrayList<PurchaseDocPO> poList = null;
        try {
            poList = docDataService.getUncheckedPurchaseDocList();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return purchaseDocPOListToVOList(poList);
    }

    @Override
    public ArrayList<SaleDocVO> getUncheckedSaleDocList() {
        return null;
    }

    @Override
    public ResultMessage approveDoc(GoodDocVO doc) {
        int coeff = 1;
        if (doc.getType() != DocType.GOOD_OVERFLOW)
            coeff = -1;
        ResultMessage ret = ResultMessage.SUCCESS;

        for (GoodItemForGoodDocVO item : doc.getItemList()) {
            if (commodityBLInfo.updateAmount(item.getId(), coeff * item.getChange(),
                    0.0, LocalDate.now()) != ResultMessage.SUCCESS) {
                ret = ResultMessage.FAILED;
                break;
            }
        }

        try {
            if (docDataService.approveDoc(doc.toPO()) != ResultMessage.SUCCESS)
                ret = ResultMessage.FAILED;
        } catch (RemoteException e) {
            e.printStackTrace();
            ret = ResultMessage.FAILED;
        }
        return ret;
    }

    @Override
    public ResultMessage approveDoc(FinanceDocVO doc) {
        ResultMessage ret = ResultMessage.SUCCESS;

        if (doc.getType() == DocType.CASH) {
            CashDocVO cashDoc = (CashDocVO)doc;
            AccountForDocVO account = cashDoc.getAccount();
            if (accountInfo.updateBalance(-1 * cashDoc.getTotal(),
                    account.getCardNumber()) != ResultMessage.SUCCESS)
                ret = ResultMessage.FAILED;
        } else {
            PaymentDocVO paymentDoc = (PaymentDocVO)doc;
            double coeff = 1;
            CustomerForDocVO customer = paymentDoc.getCustomer();
            if (paymentDoc.getType() == DocType.PAYING) {
                coeff = -1;
                if (customerBLInfo.updatePayable(customer.getId(),
                        -1 * paymentDoc.getTotal()) != ResultMessage.SUCCESS)
                    ret = ResultMessage.FAILED;
            } else {
                if (customerBLInfo.updateReceivable(customer.getId(),
                        -1 * paymentDoc.getTotal()) != ResultMessage.SUCCESS)
                    ret = ResultMessage.FAILED;
            }
            for (AccountForDocVO item : paymentDoc.getAccountList()) {
                if (accountInfo.updateBalance(coeff * item.getAmount(),
                        item.getCardNumber()) != ResultMessage.SUCCESS)
                    ret = ResultMessage.FAILED;
            }
        }

        try {
            if (docDataService.approveDoc(doc.toPO()) != ResultMessage.SUCCESS)
                ret = ResultMessage.FAILED;
        } catch (RemoteException e) {
            e.printStackTrace();
            ret = ResultMessage.FAILED;
        }

        return ret;
    }

    @Override
    public ResultMessage approveDoc(PurchaseDocVO doc) {
        int coeff;
        CustomerForDocVO customer = doc.getCustomer();
        ResultMessage ret = ResultMessage.SUCCESS;

        if (doc.getType() == DocType.PURCHASE) {
            coeff = 1;
            if (customerBLInfo.updatePayable(customer.getId(), doc.getTotal())
                != ResultMessage.SUCCESS)
                ret = ResultMessage.FAILED;
        } else {
            coeff = -1;
            if (customerBLInfo.updateReceivable(customer.getId(), doc.getTotal())
                != ResultMessage.SUCCESS)
                ret = ResultMessage.FAILED;
        }

        for (GoodItemForPurchaseSaleDocVO item : doc.getItemList()) {
            if (commodityBLInfo.updateAmount(item.getId(), coeff * item.getNumber(),
                    item.getUnitPrice(), LocalDate.now()) != ResultMessage.SUCCESS)
                ret = ResultMessage.FAILED;
            // TODO
            // if (doc.getType() == DocType.PURCHASE
            //     && commodityBLInfo.updateRecentBuyingPrice()
        }

        return ret;
    }

    @Override
    public ResultMessage approveDoc(SaleDocVO doc) {
        return null;
    }

    @Override
    public ResultMessage rejectDoc(GoodDocVO doc) {
        ResultMessage ret = ResultMessage.FAILED;
        try {
            ret = docDataService.rejectDoc(doc.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public ResultMessage rejectDoc(FinanceDocVO doc) {
        ResultMessage ret = ResultMessage.FAILED;
        try {
            ret = docDataService.rejectDoc(doc.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public ResultMessage rejectDoc(PurchaseDocVO doc) {
        ResultMessage ret = ResultMessage.FAILED;
        try {
            ret = docDataService.rejectDoc(doc.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public ResultMessage rejectDoc(SaleDocVO doc) {
        ResultMessage ret = ResultMessage.FAILED;
        try {
            ret = docDataService.rejectDoc(doc.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
