package BusinessLogicService;

import Presentation.AccountUI.model.Account;
import Presentation.DocUI.GoodDoc.model.GoodDoc;
import Util.ResultMessage;
import VO.*;

import java.util.ArrayList;

public interface DocBLService {

    // 草稿相关

    /**
     * 根据用户ID，获取其库存报溢单的草稿列表.
     *
     * @param userId 用户ID
     * @return 包含该用户所有库存报溢单草稿的列表
     */
    ArrayList<GoodDocVO> getGoodOverflowDraftList(String userId);

    /**
     * 根据用户ID，获取其库存报损单的草稿列表.
     *
     * @param userId 用户ID
     * @return 包含该用户所有库存报损单草稿的列表
     */
    ArrayList<GoodDocVO> getGoodLossDraftList(String userId);

    /**
     * 根据用户ID，获取其库存赠送单的草稿列表.
     *
     * @param userId 用户ID
     * @return 包含该用户所有库存赠送单草稿的列表
     */
    ArrayList<GoodDocVO> getGoodGiftDraftList(String userId);

    /**
     * 根据用户ID，获取其付款单的草稿列表.
     *
     * @param userId 用户ID
     * @return 包含该用户所有付款单草稿的列表
     */
    ArrayList<PaymentDocVO> getPayingDraftList(String userId);

    /**
     * 根据用户ID，获取其收款单的草稿列表.
     *
     * @param userId 用户ID
     * @return 包含该用户所有收款单草稿的列表
     */
    ArrayList<PaymentDocVO> getReceivingDraftList(String userId);

    /**
     * 根据用户ID，获取其现金费用单的草稿列表.
     *
     * @param userId 用户ID
     * @return 包含该用户所有现金费用单草稿的列表
     */
    ArrayList<CashDocVO> getCashDraftList(String userId);

    /**
     * 根据用户ID，获取其进货类单据的草稿列表.
     *
     * @param userId 用户ID
     * @return 包含该用户所有进货类单据草稿的列表
     */
    ArrayList<PurchaseDocVO> getPurchaseDraftList(String userId);

    /**
     * 根据用户ID，获取其销售单的草稿列表.
     *
     * @param userId 用户ID
     * @return 包含该用户所有销售单草稿的列表
     */
    ArrayList<SaleDocVO> getSaleDraftList(String userId);

    /**
     * 根据用户ID，获取其销售退货单的草稿列表.
     *
     * @param userId 用户ID
     * @return 包含该用户所有销售退货单草稿的列表
     */
    ArrayList<SaleDocVO> getSaleReturnDraftList(String userId);

    /**
     * 新建库存类单据草稿.
     *
     * @param goodDoc 库存类单据草稿数据
     * @return 新建草稿结果
     */
    ResultMessage addDocDraft(GoodDocVO goodDoc);

    /**
     * 新建收款、付款单草稿.
     *
     * @param paymentDoc 收款、付款单草稿数据
     * @return 新建草稿结果
     */
    ResultMessage addDocDraft(PaymentDocVO paymentDoc);

    /**
     * 新建现金费用单草稿.
     *
     * @param cashDoc 现金费用单草稿数据
     * @return 新建草稿结果
     */
    ResultMessage addDocDraft(CashDocVO cashDoc);

    /**
     * 获取账户列表.
     *
     * @return 银行账户列表
     */
    ArrayList<AccountVO> getAccountList();

    /**
     * 新建进货类单据草稿.
     *
     * @param purchaseDoc 进货类单据草稿数据
     * @return 新建草稿结果
     */
    ResultMessage addDocDraft(PurchaseDocVO purchaseDoc);

    /**
     * 新建销售类单据草稿.
     *
     * @param saleDoc 销售类单据草稿数据
     * @return 新建草稿结果
     */
    ResultMessage addDocDraft(SaleDocVO saleDoc);

    /**
     * 获取供应商列表.
     *
     * @return 供应商列表
     */
    ArrayList<CustomerVO> getSupplier();

    /**
     * 获取销售商列表.
     *
     * @return 销售商列表
     */
    ArrayList<CustomerVO> getSaler();

    /**
     * 编辑库存类单据草稿.
     *
     * @param goodDoc 库存类单据草稿数据
     * @return 编辑草稿结果
     */
    ResultMessage editDocDraft(GoodDocVO goodDoc);

    /**
     * 编辑收款、付款单草稿.
     *
     * @param paymentDoc 收款、付款单草稿数据
     * @return 编辑草稿结果
     */
    ResultMessage editDocDraft(PaymentDocVO paymentDoc);

    /**
     * 编辑现金费用单草稿.
     *
     * @param cashDoc 现金费用单草稿数据
     * @return 编辑草稿结果
     */
    ResultMessage editDocDraft(CashDocVO cashDoc);

    /**
     * 编辑进货类单据草稿.
     *
     * @param purchaseDoc 进货类单据草稿数据
     * @return 编辑草稿结果
     */
    ResultMessage editDocDraft(PurchaseDocVO purchaseDoc);

    /**
     * 编辑销售类单据草稿.
     *
     * @param saleDoc 销售类单据草稿数据
     * @return 编辑草稿结果
     */
    ResultMessage editDocDraft(SaleDocVO saleDoc);

    /**
     * 删除库存类单据草稿.
     *
     * @param goodDoc 需删除的库存类单据草稿数据
     * @return 删除草稿结果
     */
    ResultMessage delDocDraft(GoodDocVO goodDoc);

    /**
     * 删除收/付款单草稿.
     *
     * @param paymentDoc 需删除的收/付款单草稿数据
     * @return 删除草稿结果
     */
    ResultMessage delDocDraft(PaymentDocVO paymentDoc);

    /**
     * 删除现金费用单草稿.
     *
     * @param cashDoc 需删除的现金费用单草稿数据
     * @return 删除草稿结果
     */
    ResultMessage delDocDraft(CashDocVO cashDoc);

    /**
     * 删除进货类单据草稿.
     *
     * @param purchaseDoc 需删除的进货类单据草稿数据
     * @return 删除草稿结果
     */
    ResultMessage delDocDraft(PurchaseDocVO purchaseDoc);

    /**
     * 删除销售类单据草稿.
     *
     * @param saleDoc 需删除的销售类单据草稿数据
     * @return 删除草稿结果
     */
    ResultMessage delDocDraft(SaleDocVO saleDoc);

    /**
     * 提交库存类单据草稿.
     *
     * @param goodDoc 需提交的库存类单据草稿数据
     * @return 提交草稿结果
     */
    ResultMessage submitDocDraft(GoodDocVO goodDoc);

    /**
     * 提交收/付款单草稿.
     *
     * @param paymentDoc 需提交的收/付款单草稿数据
     * @return 提交草稿结果
     */
    ResultMessage submitDocDraft(PaymentDocVO paymentDoc);

    /**
     * 提交现金费用单草稿.
     *
     * @param cashDoc 需提交的现金费用单草稿数据
     * @return 提交草稿结果
     */
    ResultMessage submitDocDraft(CashDocVO cashDoc);

    /**
     * 提交进货类单据草稿.
     *
     * @param purchaseDoc 需提交的进货类单据草稿数据
     * @return 提交草稿结果
     */
    ResultMessage submitDocDraft(PurchaseDocVO purchaseDoc);

    /**
     * 提交销售类单据草稿.
     *
     * @param saleDoc 需提交的销售类单据草稿数据
     * @return 提交草稿结果
     */
    ResultMessage submitDocDraft(SaleDocVO saleDoc);

    // 审批相关

    /**
     * 获取待审批的库存类单据列表.
     *
     * @return 待审批的库存类单据列表
     */
    ArrayList<GoodDocVO> getUncheckedGoodDocList();

    /**
     * 获取待审批的财务类单据列表.
     *
     * @return 待审批的财务类单据列表
     */
    ArrayList<FinanceDocVO> getUncheckedFinanceDocList();

    /**
     * 获取待审批的进货类单据列表.
     *
     * @return 待审批的进货类单据列表
     */
    ArrayList<PurchaseDocVO> getUncheckedPurchaseDocList();

    /**
     * 获取待审批的销售类单据列表.
     *
     * @return 待审批的销售类单据列表
     */
    ArrayList<SaleDocVO> getUncheckedSaleDocList();

    /**
     * 待审批的库存类单据审批通过.
     *
     * @param doc 待审批的库存类单据数据
     * @return "审批通过"结果
     */
    ResultMessage approveDoc(GoodDocVO doc);

    /**
     * 待审批的财务类单据审批通过.
     *
     * @param doc 待审批的财务类单据数据
     * @return "审批通过"结果
     */
    ResultMessage approveDoc(FinanceDocVO doc);

    /**
     * 待审批的进货类单据审批通过.
     *
     * @param doc 待审批的进货类单据数据
     * @return "审批通过"结果
     */
    ResultMessage approveDoc(PurchaseDocVO doc);

    /**
     * 待审批的销售类单据审批通过.
     *
     * @param doc 待审批的销售类单据数据
     * @return "审批通过"结果
     */
    ResultMessage approveDoc(SaleDocVO doc);

    /**
     * 待审批的库存类单据审批不通过.
     *
     * @param doc 待审批的库存类单据数据
     * @return "审批不通过"结果
     */
    ResultMessage rejectDoc(GoodDocVO doc);

    /**
     * 待审批的财务类单据审批不通过.
     *
     * @param doc 待审批的财务类单据数据
     * @return "审批不通过"结果
     */
    ResultMessage rejectDoc(FinanceDocVO doc);

    /**
     * 待审批的进货类单据审批不通过.
     *
     * @param doc 待审批的进货类单据数据
     * @return "审批不通过"结果
     */
    ResultMessage rejectDoc(PurchaseDocVO doc);

    /**
     * 待审批的销售类单据审批不通过.
     *
     * @param doc 待审批的销售类单据数据
     * @return "审批不通过"结果
     */
    ResultMessage rejectDoc(SaleDocVO doc);

}
