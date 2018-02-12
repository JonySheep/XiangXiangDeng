package DataService;

import PO.*;
import Util.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface DocDataService extends Remote {

    // 草稿相关

    /**
     * 根据用户ID，获取其库存报溢单的草稿列表.
     *
     * @param userId 用户ID
     * @return 包含该用户所有库存报溢单草稿的列表
     */
    ArrayList<GoodDocPO> getGoodOverflowDraftList(String userId) throws RemoteException;

    /**
     * 根据用户ID，获取其库存报损单的草稿列表.
     *
     * @param userId 用户ID
     * @return 包含该用户所有库存报损单草稿的列表
     */
    ArrayList<GoodDocPO> getGoodLossDraftList(String userId) throws RemoteException;

    /**
     * 根据用户ID，获取其库存赠送单的草稿列表.
     *
     * @param userId 用户ID
     * @return 包含该用户所有库存赠送单草稿的列表
     */
    ArrayList<GoodDocPO> getGoodGiftDraftList(String userId) throws RemoteException;

    /**
     * 根据用户ID，获取其付款单的草稿列表.
     *
     * @param userId 用户ID
     * @return 包含该用户所有付款单草稿的列表
     */
    ArrayList<PaymentDocPO> getPayingDraftList(String userId) throws RemoteException;

    /**
     * 根据用户ID，获取其收款单的草稿列表.
     *
     * @param userId 用户ID
     * @return 包含该用户所有收款单草稿的列表
     */
    ArrayList<PaymentDocPO> getReceivingDraftList(String userId) throws RemoteException;

    /**
     * 根据用户ID，获取其现金费用单的草稿列表.
     *
     * @param userId 用户ID
     * @return 包含该用户所有现金费用单草稿的列表
     */
    ArrayList<CashDocPO> getCashDraftList(String userId) throws RemoteException;

    /**
     * 根据用户ID，获取其进货类单据的草稿列表.
     *
     * @param userId 用户ID
     * @return 包含该用户所有进货类单据草稿的列表
     */
    ArrayList<PurchaseDocPO> getPurchaseDraftList(String userId) throws RemoteException;

    /**
     * 根据用户ID，获取其销售单的草稿列表.
     *
     * @param userId 用户ID
     * @return 包含该用户所有销售单草稿的列表
     */
    ArrayList<SaleDocPO> getSaleDraftList(String userId) throws RemoteException;

    /**
     * 根据用户ID，获取其销售退货单的草稿列表.
     *
     * @param userId 用户ID
     * @return 包含该用户所有销售退货单草稿的列表
     */
    ArrayList<SaleDocPO> getSaleReturnDraftList(String userId) throws RemoteException;

    /**
     * 新建库存类单据草稿.
     *
     * @param goodDoc 库存类单据草稿数据
     * @return 新建草稿结果
     */
    ResultMessage addDocDraft(GoodDocPO goodDoc) throws RemoteException;

    /**
     * 新建收款、付款单草稿.
     *
     * @param paymentDoc 收款、付款单草稿数据
     * @return 新建草稿结果
     */
    ResultMessage addDocDraft(PaymentDocPO paymentDoc) throws RemoteException;

    /**
     * 新建现金费用单草稿.
     *
     * @param cashDoc 现金费用单草稿数据
     * @return 新建草稿结果
     */
    ResultMessage addDocDraft(CashDocPO cashDoc) throws RemoteException;

    /**
     * 新建进货类单据草稿.
     *
     * @param purchaseDoc 进货类单据草稿数据
     * @return 新建草稿结果
     */
    ResultMessage addDocDraft(PurchaseDocPO purchaseDoc) throws RemoteException;

    /**
     * 新建销售类单据草稿.
     *
     * @param saleDoc 销售类单据草稿数据
     * @return 新建草稿结果
     */
    ResultMessage addDocDraft(SaleDocPO saleDoc) throws RemoteException;

    /**
     * 编辑库存类单据草稿.
     *
     * @param goodDoc 库存类单据草稿数据
     * @return 编辑草稿结果
     */
    ResultMessage editDocDraft(GoodDocPO goodDoc) throws RemoteException;

    /**
     * 编辑收款、付款单草稿.
     *
     * @param paymentDoc 收款、付款单草稿数据
     * @return 编辑草稿结果
     */
    ResultMessage editDocDraft(PaymentDocPO paymentDoc) throws RemoteException;

    /**
     * 编辑现金费用单草稿.
     *
     * @param cashDoc 现金费用单草稿数据
     * @return 编辑草稿结果
     */
    ResultMessage editDocDraft(CashDocPO cashDoc) throws RemoteException;

    /**
     * 编辑进货类单据草稿.
     *
     * @param purchaseDoc 进货类单据草稿数据
     * @return 编辑草稿结果
     */
    ResultMessage editDocDraft(PurchaseDocPO purchaseDoc) throws RemoteException;

    /**
     * 编辑销售类单据草稿.
     *
     * @param saleDoc 销售类单据草稿数据
     * @return 编辑草稿结果
     */
    ResultMessage editDocDraft(SaleDocPO saleDoc) throws RemoteException;

    /**
     * 删除库存类单据草稿.
     *
     * @param goodDoc 需删除的库存类单据草稿数据
     * @return 删除草稿结果
     */
    ResultMessage delDocDraft(GoodDocPO goodDoc) throws RemoteException;

    /**
     * 删除收/付款单草稿.
     *
     * @param paymentDoc 需删除的收/付款单草稿数据
     * @return 删除草稿结果
     */
    ResultMessage delDocDraft(PaymentDocPO paymentDoc) throws RemoteException;

    /**
     * 删除现金费用单草稿.
     *
     * @param cashDoc 需删除的现金费用单草稿数据
     * @return 删除草稿结果
     */
    ResultMessage delDocDraft(CashDocPO cashDoc) throws RemoteException;

    /**
     * 删除进货类单据草稿.
     *
     * @param purchaseDoc 需删除的进货类单据草稿数据
     * @return 删除草稿结果
     */
    ResultMessage delDocDraft(PurchaseDocPO purchaseDoc) throws RemoteException;

    /**
     * 删除销售类单据草稿.
     *
     * @param saleDoc 需删除的销售类单据草稿数据
     * @return 删除草稿结果
     */
    ResultMessage delDocDraft(SaleDocPO saleDoc) throws RemoteException;

    /**
     * 提交库存类单据草稿.
     *
     * @param goodDoc 需提交的库存类单据草稿数据
     * @return 提交草稿结果
     */
    ResultMessage submitDocDraft(GoodDocPO goodDoc) throws RemoteException;

    /**
     * 提交财务类单据草稿.
     *
     * @param financeDoc 需提交的财务类单据草稿数据
     * @return 提交草稿结果
     */
    ResultMessage submitDocDraft(FinanceDocPO financeDoc) throws RemoteException;

    /**
     * 提交进货类单据草稿.
     *
     * @param purchaseDoc 需提交的进货类单据草稿数据
     * @return 提交草稿结果
     */
    ResultMessage submitDocDraft(PurchaseDocPO purchaseDoc) throws RemoteException;

    /**
     * 提交销售类单据草稿.
     *
     * @param saleDoc 需提交的销售类单据草稿数据
     * @return 提交草稿结果
     */
    ResultMessage submitDocDraft(SaleDocPO saleDoc) throws RemoteException;

    // 审批相关

    /**
     * 获取待审批的库存类单据列表.
     *
     * @return 待审批的库存类单据列表
     */
    ArrayList<GoodDocPO> getUncheckedGoodDocList() throws RemoteException;

    /**
     * 获取待审批的财务类单据列表.
     *
     * @return 待审批的财务类单据列表
     */
    ArrayList<FinanceDocPO> getUncheckedFinanceDocList() throws RemoteException;

    /**
     * 获取待审批的进货类单据列表.
     *
     * @return 待审批的进货类单据列表
     */
    ArrayList<PurchaseDocPO> getUncheckedPurchaseDocList() throws RemoteException;

    /**
     * 获取待审批的销售类单据列表.
     *
     * @return 待审批的销售类单据列表
     */
    ArrayList<SaleDocPO> getUncheckedSaleDocList() throws RemoteException;

    /**
     * 待审批的库存类单据审批通过.
     *
     * @param doc 待审批的库存类单据数据
     * @return "审批通过"结果
     */
    ResultMessage approveDoc(GoodDocPO doc) throws RemoteException;

    /**
     * 待审批的财务类单据审批通过.
     *
     * @param doc 待审批的财务类单据数据
     * @return "审批通过"结果
     */
    ResultMessage approveDoc(FinanceDocPO doc) throws RemoteException;

    /**
     * 待审批的进货类单据审批通过.
     *
     * @param doc 待审批的进货类单据数据
     * @return "审批通过"结果
     */
    ResultMessage approveDoc(PurchaseDocPO doc) throws RemoteException;

    /**
     * 待审批的销售类单据审批通过.
     *
     * @param doc 待审批的销售类单据数据
     * @return "审批通过"结果
     */
    ResultMessage approveDoc(SaleDocPO doc) throws RemoteException;

    /**
     * 待审批的库存类单据审批不通过.
     *
     * @param doc 待审批的库存类单据数据
     * @return "审批不通过"结果
     */
    ResultMessage rejectDoc(GoodDocPO doc) throws RemoteException;

    /**
     * 待审批的财务类单据审批不通过.
     *
     * @param doc 待审批的财务类单据数据
     * @return "审批不通过"结果
     */
    ResultMessage rejectDoc(FinanceDocPO doc) throws RemoteException;

    /**
     * 待审批的进货类单据审批不通过.
     *
     * @param doc 待审批的进货类单据数据
     * @return "审批不通过"结果
     */
    ResultMessage rejectDoc(PurchaseDocPO doc) throws RemoteException;

    /**
     * 待审批的销售类单据审批不通过.
     *
     * @param doc 待审批的销售类单据数据
     * @return "审批不通过"结果
     */
    ResultMessage rejectDoc(SaleDocPO doc) throws RemoteException;

}
