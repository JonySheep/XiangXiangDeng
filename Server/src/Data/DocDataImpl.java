package Data;

import DataService.DocDataService;
import DataUtil.DBUtil;
import DataUtil.SqlHelper;
import PO.*;
import Util.DocType;
import Util.EmptyValue;
import Util.ResultMessage;

import javax.print.Doc;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class DocDataImpl implements DocDataService {

    private Connection conn;

    // 草稿相关

    private SqlHelper goodDocDraft;
    private SqlHelper goodDraftItemList;

    private SqlHelper financeDocDraft;
    private SqlHelper paymentDraftItemList;
    private SqlHelper cashDraftItemList;

    private SqlHelper purchaseDocDraft;
    private SqlHelper saleDocDraft;
    private SqlHelper purSaleDraftItemList;
    private SqlHelper saleDraftItemList;

    public DocDataImpl() {
        conn = null;

        goodDocDraft = new SqlHelper("GoodDocDraft");
        goodDraftItemList = new SqlHelper("GoodDraftItemList");

        financeDocDraft = new SqlHelper("FinanceDocDraft");
        paymentDraftItemList = new SqlHelper("PaymentDraftItemList");
        cashDraftItemList = new SqlHelper("CashDraftItemList");

        purchaseDocDraft = new SqlHelper("PurchaseDocDraft");
        saleDocDraft = new SqlHelper("SaleDocDraft");
        purSaleDraftItemList = new SqlHelper("PurSaleDraftItemList");
        saleDraftItemList = new SqlHelper("SaleDraftItemList");
    }

    private void getConnection(){
        if (conn == null){
            conn = DBUtil.open();
        }
    }

    // 草稿相关

    /**
     * 根据用户ID，获取其库存报溢单的草稿列表.
     *
     * @param userId 用户ID
     * @return 包含该用户所有库存报溢单草稿的列表
     */
    @Override
    public ArrayList<GoodDocPO> getGoodOverflowDraftList(String userId) {
        goodDocDraft.select()
                .key("Operator", userId)
                .key("Type", DocType.GOOD_OVERFLOW.toString())
                .key("Checked", 0).keyEnd()
                .orderBy(Arrays.asList("DateTime"), Arrays.asList(true)).end();
        return getGoodDocDraftList(goodDocDraft.toString());
    }

    /**
     * 根据用户ID，获取其库存报损单的草稿列表.
     *
     * @param userId 用户ID
     * @return 包含该用户所有库存报损单草稿的列表
     */
    @Override
    public ArrayList<GoodDocPO> getGoodLossDraftList(String userId) {
        goodDocDraft.select()
                .key("Operator", userId)
                .key("Type", DocType.GOOD_LOSS.toString())
                .key("Checked", 0).keyEnd()
                .orderBy(Arrays.asList("DateTime"), Arrays.asList(true)).end();
        return getGoodDocDraftList(goodDocDraft.toString());
    }

    /**
     * 根据用户ID，获取其库存赠送单的草稿列表.
     *
     * @param userId 用户ID
     * @return 包含该用户所有库存赠送单草稿的列表
     */
    @Override
    public ArrayList<GoodDocPO> getGoodGiftDraftList(String userId) {
        goodDocDraft.select()
                .key("Operator", userId)
                .key("Type", DocType.GOOD_GIFT.toString())
                .key("Checked", 0).keyEnd()
                .orderBy(Arrays.asList("DateTime"), Arrays.asList(true)).end();
        return getGoodDocDraftList(goodDocDraft.toString());
    }

    private ArrayList<GoodDocPO> getGoodDocDraftList(String sql) {
        getConnection();
        ArrayList<GoodDocPO> ret = new ArrayList<>();
        try {
            Statement stat = conn.createStatement();
            ResultSet docSet = stat.executeQuery(sql);
            while (docSet.next()) {
                String operator = docSet.getString(1);
                String docId = operator + "-" + docSet.getString(2);
                String type = docSet.getString(3);
                String comment = docSet.getString(4);

                ArrayList<GoodItemForGoodDocPO> itemList = new ArrayList<>();
                goodDraftItemList.select().key("DocID", docId).keyEnd().end();
                Statement itemStat = conn.createStatement();
                ResultSet itemSet = itemStat.executeQuery(goodDraftItemList.toString());
                while (itemSet.next())
                    itemList.add(new GoodItemForGoodDocPO
                            (itemSet.getString(2), itemSet.getInt(3)));

                ret.add(new GoodDocPO(docId, operator, DocType.parse(type), itemList, comment));
            }
            stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return ret;
    }

    /**
     * 根据用户ID，获取其付款单的草稿列表.
     *
     * @param userId 用户ID
     * @return 包含该用户所有付款单草稿的列表
     */
    @Override
    public ArrayList<PaymentDocPO> getPayingDraftList(String userId) {
        financeDocDraft.select()
                .key("Operator", userId)
                .key("Type", DocType.PAYING.toString())
                .key("Checked", 0).keyEnd()
                .orderBy(Arrays.asList("DateTime"), Arrays.asList(true)).end();
        return getPaymentDocDraftList(financeDocDraft.toString());
    }

    /**
     * 根据用户ID，获取其收款单的草稿列表.
     *
     * @param userId 用户ID
     * @return 包含该用户所有收款单草稿的列表
     */
    @Override
    public ArrayList<PaymentDocPO> getReceivingDraftList(String userId) {
        financeDocDraft.select()
                .key("Operator", userId)
                .key("Type", DocType.RECEIVING.toString())
                .key("Checked", 0).keyEnd()
                .orderBy(Arrays.asList("DateTime"), Arrays.asList(true)).end();
        return getPaymentDocDraftList(financeDocDraft.toString());
    }

    private ArrayList<PaymentDocPO> getPaymentDocDraftList(String sql) {
        getConnection();
        ArrayList<PaymentDocPO> ret = new ArrayList<>();

        try {
            Statement stat = conn.createStatement();
            ResultSet docSet = stat.executeQuery(sql);
            while (docSet.next()) {
                String operator = docSet.getString(1);
                String docId = operator + "-" + docSet.getString(2);
                String type = docSet.getString(3);
                String customer = docSet.getString(4);

                ArrayList<AccountForDocPO> itemList = new ArrayList<>();
                paymentDraftItemList.select().key("DocID", docId).keyEnd().end();
                Statement itemStat = conn.createStatement();
                ResultSet itemSet = itemStat.executeQuery(paymentDraftItemList.toString());
                while (itemSet.next()) {
                    itemList.add(new AccountForDocPO(itemSet.getString(2),
                            itemSet.getDouble(3), itemSet.getString(4)));
                }

                ret.add(new PaymentDocPO(docId, operator, DocType.parse(type),
                        customer, itemList));
            }
            stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return ret;
    }

    /**
     * 根据用户ID，获取其现金费用单的草稿列表.
     *
     * @param userId 用户ID
     * @return 包含该用户所有现金费用单草稿的列表
     */
    @Override
    public ArrayList<CashDocPO> getCashDraftList(String userId) {
        financeDocDraft.select()
                .key("Operator", userId)
                .key("Type", DocType.CASH.toString())
                .key("Checked", 0).keyEnd()
                .orderBy(Arrays.asList("DateTime"), Arrays.asList(true)).end();

        getConnection();
        ArrayList<CashDocPO> ret = new ArrayList<>();
        try {
            Statement stat = conn.createStatement();
            ResultSet docSet = stat.executeQuery(financeDocDraft.toString());
            while (docSet.next()) {
                String operator = docSet.getString(1);
                String docId = operator + "-" + docSet.getString(2);
                String type = docSet.getString(3);
                String account = docSet.getString(5);

                ArrayList<CashItemPO> itemList = new ArrayList<>();
                cashDraftItemList.select().key("DocID", docId).keyEnd().end();
                Statement itemStat = conn.createStatement();
                ResultSet itemSet = itemStat.executeQuery(cashDraftItemList.toString());
                while (itemSet.next()) {
                    itemList.add(new CashItemPO(itemSet.getString(2),
                            itemSet.getDouble(3), itemSet.getString(4)));
                }

                ret.add(new CashDocPO(docId, operator, DocType.parse(type),
                        account, itemList));
            }
            stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return ret;
    }

    /**
     * 根据用户ID，获取其进货类单据的草稿列表.
     *
     * @param userId 用户ID
     * @return 包含该用户所有进货类单据草稿的列表
     */
    @Override
    public ArrayList<PurchaseDocPO> getPurchaseDraftList(String userId) {
        purchaseDocDraft.select()
                .key("Operator", userId)
                .custom("( Type = '").custom(DocType.PURCHASE.toString())
                .custom("' or Type = '").custom(DocType.PURCHASE_RETURN.toString())
                .custom("' ) and ")
                .key("Checked", 0).keyEnd()
                .orderBy(Arrays.asList("DateTime"), Arrays.asList(true)).end();

        getConnection();
        ArrayList<PurchaseDocPO> ret = new ArrayList<>();

        try {
            Statement stat = conn.createStatement();
            ResultSet docSet = stat.executeQuery(purchaseDocDraft.toString());
            while (docSet.next()) {
                String operator = docSet.getString(1);
                String docId = operator + "-" + docSet.getString(2);
                String type = docSet.getString(3);
                String customer = docSet.getString(4);
                String comment = docSet.getString(5);

                ArrayList<GoodItemForPurchaseSaleDocPO> itemList = new ArrayList<>();
                purSaleDraftItemList.select().key("DocID", docId).keyEnd().end();
                Statement itemStat = conn.createStatement();
                ResultSet itemSet = itemStat.executeQuery(purSaleDraftItemList.toString());
                while (itemSet.next()) {
                    itemList.add(new GoodItemForPurchaseSaleDocPO(
                            itemSet.getString(2), itemSet.getInt(3),
                            itemSet.getDouble(4), itemSet.getString(5)));
                }

                ret.add(new PurchaseDocPO(docId, operator, DocType.parse(type),
                        customer, itemList, comment));
            }
            stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return ret;
    }

    /**
     * 根据用户ID，获取其销售单的草稿列表.
     *
     * @param userId 用户ID
     * @return 包含该用户所有销售单草稿的列表
     */
    @Override
    public ArrayList<SaleDocPO> getSaleDraftList(String userId) {
        saleDocDraft.select()
                .key("Operator", userId)
                .key("Type", DocType.SALE.toString())
                .key("Checked", 0).keyEnd()
                .orderBy(Arrays.asList("DateTime"), Arrays.asList(true)).end();
        return getSaleClassDraftList(saleDocDraft.toString());
    }

    /**
     * 根据用户ID，获取其销售退货单的草稿列表.
     *
     * @param userId 用户ID
     * @return 包含该用户所有销售退货单草稿的列表
     */
    @Override
    public ArrayList<SaleDocPO> getSaleReturnDraftList(String userId) {
        saleDocDraft.select()
                .key("Operator", userId)
                .key("Type", DocType.SALE_RETURN.toString())
                .key("Checked", 0).keyEnd()
                .orderBy(Arrays.asList("DateTime"), Arrays.asList(true)).end();
        return getSaleClassDraftList(saleDocDraft.toString());
    }

    private ArrayList<SaleDocPO> getSaleClassDraftList(String sql) {
        getConnection();
        ArrayList<SaleDocPO> ret = new ArrayList<>();

        try {
            Statement stat = conn.createStatement();
            ResultSet docSet = stat.executeQuery(sql);
            while (docSet.next()) {
                String operator = docSet.getString(1);
                String docId = operator + "-" + docSet.getString(2);
                DocType type = DocType.parse(docSet.getString(3));
                String customer = docSet.getString(4);
                String salesman = docSet.getString(5);
                String comment = docSet.getString(8);

                ArrayList<GoodItemForPurchaseSaleDocPO> itemList = new ArrayList<>();
                purSaleDraftItemList.select().key("DocID", docId).keyEnd().end();
                Statement itemStat = conn.createStatement();
                ResultSet itemSet = itemStat.executeQuery(purSaleDraftItemList.toString());
                while (itemSet.next()) {
                    itemList.add(new GoodItemForPurchaseSaleDocPO(
                            itemSet.getString(2), itemSet.getInt(3),
                            itemSet.getDouble(4), itemSet.getString(5)));
                }

                if (type == DocType.SALE) {
                    double rebate = docSet.getDouble(6);
                    int voucher = docSet.getInt(7);

                    ArrayList<GiftItemPO> giftList = new ArrayList<>();
                    saleDraftItemList.select().key("DocID", docId).keyEnd().end();
                    Statement giftStat = conn.createStatement();
                    ResultSet giftSet = giftStat.executeQuery(saleDraftItemList.toString());
                    while (giftSet.next()) {
                        giftList.add(new GiftItemPO(
                                giftSet.getString(2), giftSet.getInt(3)));
                    }

                    ret.add(new SaleDocPO(docId, operator, type, customer,
                            itemList, salesman, giftList, rebate, voucher, comment));
                } else {
                    ret.add(new SaleDocPO(docId, operator, type, customer,
                            itemList, salesman, comment));
                }
            }
            stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return ret;
    }

    /**
     * 新建库存类单据草稿.
     *
     * @param goodDoc 库存类单据草稿数据
     * @return 新建草稿结果
     */
    @Override
    public ResultMessage addDocDraft(GoodDocPO goodDoc) {
        getConnection();
        try {
            Statement stat = conn.createStatement();
            goodDocDraft.insert()
                    .val(goodDoc.getOperatorId())
                    .val(goodDoc.getPrKey().split("-")[1])
                    .val(goodDoc.getType().toString())
                    .val(goodDoc.getComment())
                    .val(0).valEnd().end();
            stat.executeUpdate(goodDocDraft.toString());
            for (GoodItemForGoodDocPO item : goodDoc.getItemList()) {
                goodDraftItemList.insert()
                        .val(goodDoc.getPrKey())
                        .val(item.getId())
                        .val(item.getChange()).valEnd().end();
                stat.executeUpdate(goodDraftItemList.toString());
            }
            stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
        return ResultMessage.SUCCESS;
    }

    /**
     * 新建收款、付款单草稿.
     *
     * @param paymentDoc 收款、付款单草稿数据
     * @return 新建草稿结果
     */
    @Override
    public ResultMessage addDocDraft(PaymentDocPO paymentDoc) {
        getConnection();
        try {
            Statement stat = conn.createStatement();
            financeDocDraft.insert()
                    .val(paymentDoc.getOperatorId())
                    .val(paymentDoc.getPrKey().split("-")[1])
                    .val(paymentDoc.getType().toString())
                    .val(paymentDoc.getCustomerId())
                    .val("")
                    .val(0).valEnd().end();
            stat.executeUpdate(financeDocDraft.toString());
            for (AccountForDocPO account : paymentDoc.getAccountList()) {
                paymentDraftItemList.insert()
                        .val(paymentDoc.getPrKey())
                        .val(account.getCardNumber())
                        .val(account.getAmount())
                        .val(account.getComment()).valEnd().end();
                stat.executeUpdate(paymentDraftItemList.toString());
            }
            stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
        return ResultMessage.SUCCESS;
    }

    /**
     * 新建现金费用单草稿.
     *
     * @param cashDoc 现金费用单草稿数据
     * @return 新建草稿结果
     */
    @Override
    public ResultMessage addDocDraft(CashDocPO cashDoc) {
        getConnection();
        try {
            Statement stat = conn.createStatement();
            financeDocDraft.insert()
                    .val(cashDoc.getOperatorId())
                    .val(cashDoc.getPrKey().split("-")[1])
                    .val(cashDoc.getType().toString())
                    .val("")
                    .val(0).val(cashDoc.getAccount()).valEnd().end();
            stat.executeUpdate(financeDocDraft.toString());
            for (CashItemPO item : cashDoc.getItemList()) {
                cashDraftItemList.insert()
                        .val(cashDoc.getPrKey())
                        .val(item.getName())
                        .val(item.getAmount())
                        .val(item.getComment()).valEnd().end();
                stat.executeUpdate(cashDraftItemList.toString());
            }
            stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
        return ResultMessage.SUCCESS;
    }

    /**
     * 新建进货类单据草稿.
     *
     * @param purchaseDoc 进货类单据草稿数据
     * @return 新建草稿结果
     */
    @Override
    public ResultMessage addDocDraft(PurchaseDocPO purchaseDoc) {
        getConnection();
        try {
            Statement stat = conn.createStatement();
            purchaseDocDraft.insert()
                    .val(purchaseDoc.getOperatorId())
                    .val(purchaseDoc.getPrKey().split("-")[1])
                    .val(purchaseDoc.getType().toString())
                    .val(purchaseDoc.getCustomerId())
                    .val(purchaseDoc.getComment())
                    .val(0).valEnd().end();
            stat.executeUpdate(purchaseDocDraft.toString());
            for (GoodItemForPurchaseSaleDocPO item : purchaseDoc.getItemList()) {
                purSaleDraftItemList.insert()
                        .val(purchaseDoc.getPrKey())
                        .val(item.getId())
                        .val(item.getNumber())
                        .val(item.getUnitPrice())
                        .val(item.getComment()).valEnd().end();
                stat.executeUpdate(purSaleDraftItemList.toString());
            }
            stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
        return ResultMessage.SUCCESS;
    }

    /**
     * 新建销售类单据草稿.
     *
     * @param saleDoc 销售类单据草稿数据
     * @return 新建草稿结果
     */
    @Override
    public ResultMessage addDocDraft(SaleDocPO saleDoc) {
        getConnection();
        try {
            Statement stat = conn.createStatement();
            saleDocDraft.insert()
                    .val(saleDoc.getOperatorId())
                    .val(saleDoc.getPrKey().split("-")[1])
                    .val(saleDoc.getType().toString())
                    .val(saleDoc.getCustomerId())
                    .val(saleDoc.getSalesmanId())
                    .val(saleDoc.getRebate())
                    .val(saleDoc.getVoucher())
                    .val(saleDoc.getComment())
                    .val(0).valEnd().end();
            stat.executeUpdate(saleDocDraft.toString());

            for (GoodItemForPurchaseSaleDocPO item : saleDoc.getItemList()) {
                purSaleDraftItemList.insert()
                        .val(saleDoc.getPrKey())
                        .val(item.getId())
                        .val(item.getNumber())
                        .val(item.getUnitPrice())
                        .val(item.getComment()).valEnd().end();
                stat.executeUpdate(purSaleDraftItemList.toString());
            }

            for (GiftItemPO gift : saleDoc.getGiftList()) {
                saleDraftItemList.insert()
                        .val(saleDoc.getPrKey())
                        .val(gift.getId())
                        .val(gift.getNumber()).valEnd().end();
                stat.executeUpdate(saleDraftItemList.toString());
            }

            stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
        return ResultMessage.SUCCESS;
    }

    /**
     * 编辑库存类单据草稿.
     *
     * @param goodDoc 库存类单据草稿数据
     * @return 编辑草稿结果
     */
    @Override
    public ResultMessage editDocDraft(GoodDocPO goodDoc) {
        getConnection();
        try {
            Statement stat = conn.createStatement();

            if (EmptyValue.getString().equals(goodDoc.getComment())) {
                saleDocDraft.update()
                        .set("Comment", goodDoc.getComment()).setEnd()
                        .key("Operator", goodDoc.getOperatorId())
                        .key("DateTime", goodDoc.getPrKey().split("-")[1])
                        .keyEnd().end();
                stat.executeUpdate(goodDocDraft.toString());
            }

            if (goodDoc.getItemList() != null) {
                goodDraftItemList.delete()
                        .key("DocID", goodDoc.getPrKey())
                        .keyEnd().end();
                stat.executeUpdate(goodDraftItemList.toString());

                for (GoodItemForGoodDocPO item : goodDoc.getItemList()) {
                    goodDraftItemList.insert()
                            .val(goodDoc.getPrKey())
                            .val(item.getId())
                            .val(item.getChange()).valEnd().end();
                    stat.executeUpdate(goodDraftItemList.toString());
                }
            }
            stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
        return ResultMessage.SUCCESS;
    }

    /**
     * 编辑收款、付款单草稿.
     *
     * @param paymentDoc 收款、付款单草稿数据
     * @return 编辑草稿结果
     */
    @Override
    public ResultMessage editDocDraft(PaymentDocPO paymentDoc) {
        getConnection();
        try {
            Statement stat = conn.createStatement();

            if (EmptyValue.getString().equals(paymentDoc.getCustomerId())) {
                financeDocDraft.update()
                        .set("Customer", paymentDoc.getCustomerId()).setEnd()
                        .key("Operator", paymentDoc.getOperatorId())
                        .key("DateTime", paymentDoc.getPrKey().split("-")[1])
                        .keyEnd().end();
                stat.executeUpdate(financeDocDraft.toString());
            }

            if (paymentDoc.getAccountList() != null) {
                paymentDraftItemList.delete()
                        .key("DocID", paymentDoc.getPrKey())
                        .keyEnd().end();
                stat.executeUpdate(paymentDraftItemList.toString());

                for (AccountForDocPO account : paymentDoc.getAccountList()) {
                    paymentDraftItemList.insert()
                            .val(paymentDoc.getPrKey())
                            .val(account.getCardNumber())
                            .val(account.getAmount())
                            .val(account.getComment()).valEnd().end();
                    stat.executeUpdate(paymentDraftItemList.toString());
                }
            }

            stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
        return ResultMessage.SUCCESS;
    }

    /**
     * 编辑现金费用单草稿.
     *
     * @param cashDoc 现金费用单草稿数据
     * @return 编辑草稿结果
     */
    @Override
    public ResultMessage editDocDraft(CashDocPO cashDoc) {
        getConnection();
        try {
            Statement stat = conn.createStatement();

            if (EmptyValue.getString().equals(cashDoc.getAccount())) {
                financeDocDraft.update()
                        .set("Account", cashDoc.getAccount()).setEnd()
                        .key("Operator", cashDoc.getOperatorId())
                        .key("DateTime", cashDoc.getPrKey().split("-")[1])
                        .keyEnd().end();
                stat.executeUpdate(financeDocDraft.toString());
            }

            if (cashDoc.getItemList() != null) {
                cashDraftItemList.delete()
                        .key("DocID",  cashDoc.getPrKey())
                        .keyEnd().end();
                stat.executeUpdate(cashDraftItemList.toString());

                for (CashItemPO item : cashDoc.getItemList()) {
                    cashDraftItemList.insert()
                            .val(cashDoc.getPrKey())
                            .val(item.getName())
                            .val(item.getAmount())
                            .val(item.getComment()).valEnd().end();
                    stat.executeUpdate(cashDraftItemList.toString());
                }
            }

            stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
        return ResultMessage.SUCCESS;
    }

    /**
     * 编辑进货类单据草稿.
     *
     * @param purchaseDoc 进货类单据草稿数据
     * @return 编辑草稿结果
     */
    @Override
    public ResultMessage editDocDraft(PurchaseDocPO purchaseDoc) {
        getConnection();
        try {
            Statement stat = conn.createStatement();

            purchaseDocDraft.update();
            boolean isModified = false;
            if (EmptyValue.getString().equals(purchaseDoc.getCustomerId())) {
                isModified = true;
                purchaseDocDraft.set("Customer", purchaseDoc.getCustomerId());
            }
            if (EmptyValue.getString().equals(purchaseDoc.getComment())) {
                isModified = true;
                purchaseDocDraft.set("Comment", purchaseDoc.getComment());
            }
            if (isModified) {
                purchaseDocDraft.setEnd()
                        .key("Operator", purchaseDoc.getOperatorId())
                        .key("DateTime", purchaseDoc.getPrKey().split("-")[1])
                        .keyEnd().end();
                stat.executeUpdate(purchaseDocDraft.toString());
            }

            if (purchaseDoc.getItemList() != null) {
                purSaleDraftItemList.delete()
                        .key("DocID", purchaseDoc.getPrKey())
                        .keyEnd().end();
                stat.executeUpdate(purSaleDraftItemList.toString());

                for (GoodItemForPurchaseSaleDocPO item : purchaseDoc.getItemList()) {
                    purSaleDraftItemList.insert()
                            .val(purchaseDoc.getPrKey())
                            .val(item.getId())
                            .val(item.getNumber())
                            .val(item.getUnitPrice())
                            .val(item.getComment()).valEnd().end();
                    stat.executeUpdate(purSaleDraftItemList.toString());
                }
            }

            stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
        return ResultMessage.SUCCESS;
    }

    /**
     * 编辑销售类单据草稿.
     *
     * @param saleDoc 销售类单据草稿数据
     * @return 编辑草稿结果
     */
    @Override
    public ResultMessage editDocDraft(SaleDocPO saleDoc) {
        getConnection();
        try {
            Statement stat = conn.createStatement();

            saleDocDraft.update();
            boolean isModified = false;
            if (EmptyValue.getString().equals(saleDoc.getCustomerId())) {
                isModified = true;
                saleDocDraft.set("Customer", saleDoc.getCustomerId());
            }
            if (EmptyValue.getString().equals(saleDoc.getSalesmanId())) {
                isModified = true;
                saleDocDraft.set("Salesman", saleDoc.getSalesmanId());
            }
            if (saleDoc.getRebate() != EmptyValue.getDouble()) {
                isModified = true;
                saleDocDraft.set("Rebate", saleDoc.getRebate());
            }
            if (saleDoc.getVoucher() != EmptyValue.getInteger()) {
                isModified = true;
                saleDocDraft.set("Voucher", saleDoc.getVoucher());
            }
            if (EmptyValue.getString().equals(saleDoc.getComment())) {
                isModified = true;
                saleDocDraft.set("Comment", saleDoc.getComment());
            }
            if (isModified) {
                saleDocDraft.setEnd()
                        .key("Operator", saleDoc.getOperatorId())
                        .key("DateTime", saleDoc.getPrKey().split("-")[1])
                        .keyEnd().end();
                stat.executeUpdate(saleDocDraft.toString());
            }

            if (saleDoc.getItemList() != null) {
                purSaleDraftItemList.delete()
                        .key("DocID", saleDoc.getPrKey())
                        .keyEnd().end();
                stat.executeUpdate(purSaleDraftItemList.toString());

                for (GoodItemForPurchaseSaleDocPO item : saleDoc.getItemList()) {
                    purSaleDraftItemList.insert()
                            .val(saleDoc.getPrKey())
                            .val(item.getId())
                            .val(item.getNumber())
                            .val(item.getUnitPrice())
                            .val(item.getComment()).valEnd().end();
                    stat.executeUpdate(purSaleDraftItemList.toString());
                }
            }

            if (saleDoc.getGiftList() != null) {
                saleDraftItemList.delete()
                        .key("DocID", saleDoc.getPrKey())
                        .keyEnd().end();
                stat.executeUpdate(saleDraftItemList.toString());

                for (GiftItemPO gift : saleDoc.getGiftList()) {
                    saleDraftItemList.insert()
                            .val(saleDoc.getPrKey())
                            .val(gift.getId())
                            .val(gift.getNumber()).valEnd().end();
                    stat.executeUpdate(saleDraftItemList.toString());
                }
            }

            stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
        return ResultMessage.SUCCESS;
    }

    private ResultMessage delDocDraft(SqlHelper helper, DocPO doc) {
        getConnection();
        try {
            Statement stat = conn.createStatement();
            helper.delete()
                    .key("Operator", doc.getOperatorId())
                    .key("DateTime", doc.getPrKey().split("-")[1])
                    .keyEnd().end();
            stat.executeUpdate(helper.toString());
            stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
        return ResultMessage.SUCCESS;
    }

    private ResultMessage delDocDraftItem(SqlHelper helper, String docID) {
        getConnection();
        try {
            Statement stat = conn.createStatement();
            helper.delete().key("DocID", docID).keyEnd().end();
            stat.executeUpdate(helper.toString());
            stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
        return ResultMessage.SUCCESS;
    }

    /**
     * 删除库存类单据草稿.
     *
     * @param goodDoc 需删除的库存类单据草稿数据
     * @return 删除草稿结果
     */
    @Override
    public ResultMessage delDocDraft(GoodDocPO goodDoc) {
        if (delDocDraft(goodDocDraft, goodDoc) == ResultMessage.SUCCESS
         && delDocDraftItem(goodDraftItemList, goodDoc.getPrKey()) == ResultMessage.SUCCESS)
            return ResultMessage.SUCCESS;
        else
            return ResultMessage.FAILED;
    }

    /**
     * 删除收/付款单草稿.
     *
     * @param paymentDoc 需删除的收/付款单草稿数据
     * @return 删除草稿结果
     */
    @Override
    public ResultMessage delDocDraft(PaymentDocPO paymentDoc) {
        if (delDocDraft(financeDocDraft, paymentDoc) == ResultMessage.SUCCESS
         && delDocDraftItem(paymentDraftItemList, paymentDoc.getPrKey()) == ResultMessage.SUCCESS)
            return ResultMessage.SUCCESS;
        else
            return ResultMessage.FAILED;
    }

    /**
     * 删除现金费用单草稿.
     *
     * @param cashDoc 需删除的现金费用单草稿数据
     * @return 删除草稿结果
     */
    @Override
    public ResultMessage delDocDraft(CashDocPO cashDoc) {
        if (delDocDraft(financeDocDraft, cashDoc) == ResultMessage.SUCCESS
         && delDocDraftItem(cashDraftItemList, cashDoc.getPrKey()) == ResultMessage.SUCCESS)
            return ResultMessage.SUCCESS;
        else
            return ResultMessage.FAILED;
    }

    /**
     * 删除进货类单据草稿.
     *
     * @param purchaseDoc 需删除的进货类单据草稿数据
     * @return 删除草稿结果
     */
    @Override
    public ResultMessage delDocDraft(PurchaseDocPO purchaseDoc) {
        if (delDocDraft(purchaseDocDraft, purchaseDoc) == ResultMessage.SUCCESS
         && delDocDraftItem(purSaleDraftItemList, purchaseDoc.getPrKey()) == ResultMessage.SUCCESS)
            return ResultMessage.SUCCESS;
        else
            return ResultMessage.FAILED;
    }

    /**
     * 删除销售类单据草稿.
     *
     * @param saleDoc 需删除的销售类单据草稿数据
     * @return 删除草稿结果
     */
    @Override
    public ResultMessage delDocDraft(SaleDocPO saleDoc) {
        if (delDocDraft(saleDocDraft, saleDoc) == ResultMessage.SUCCESS
         && delDocDraftItem(purSaleDraftItemList, saleDoc.getPrKey()) == ResultMessage.SUCCESS
         && delDocDraftItem(saleDraftItemList, saleDoc.getPrKey()) == ResultMessage.SUCCESS)
            return ResultMessage.SUCCESS;
        else
            return ResultMessage.FAILED;
    }

    private ResultMessage submitDocDraft(SqlHelper helper, DocPO doc) {
        getConnection();
        try {
            Statement stat = conn.createStatement();
            helper.update().set("Checked", 1).setEnd()
                    .key("Operator", doc.getOperatorId())
                    .key("DateTime", doc.getPrKey().split("-")[1])
                    .keyEnd().end();
            stat.executeUpdate(helper.toString());
            stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
        return ResultMessage.SUCCESS;
    }

    /**
     * 提交库存类单据草稿.
     *
     * @param goodDoc 需提交的库存类单据草稿数据
     * @return 提交草稿结果
     */
    @Override
    public ResultMessage submitDocDraft(GoodDocPO goodDoc) {
        return submitDocDraft(goodDocDraft, goodDoc);
    }

    /**
     * 提交财务类单据草稿.
     *
     * @param financeDoc 需提交的财务类单据草稿数据
     * @return 提交草稿结果
     */
    @Override
    public ResultMessage submitDocDraft(FinanceDocPO financeDoc) {
        return submitDocDraft(financeDocDraft, financeDoc);
    }

    /**
     * 提交进货类单据草稿.
     *
     * @param purchaseDoc 需提交的进货类单据草稿数据
     * @return 提交草稿结果
     */
    @Override
    public ResultMessage submitDocDraft(PurchaseDocPO purchaseDoc) {
        return submitDocDraft(purchaseDocDraft, purchaseDoc);
    }

    /**
     * 提交销售类单据草稿.
     *
     * @param saleDoc 需提交的销售类单据草稿数据
     * @return 提交草稿结果
     */
    @Override
    public ResultMessage submitDocDraft(SaleDocPO saleDoc) {
        return submitDocDraft(saleDocDraft, saleDoc);
    }

    // 审批相关

    /**
     * 获取待审批的库存类单据列表.
     *
     * @return 待审批的库存类单据列表
     */
    @Override
    public ArrayList<GoodDocPO> getUncheckedGoodDocList() {
        goodDocDraft.select().key("Checked", 1).keyEnd().end();
        return getGoodDocDraftList(goodDocDraft.toString());
    }

    /**
     * 获取待审批的财务类单据列表.
     *
     * @return 待审批的财务类单据列表
     */
    @Override
    public ArrayList<FinanceDocPO> getUncheckedFinanceDocList() {
        financeDocDraft.select().key("Checked", 1).keyEnd().end();
        ArrayList<FinanceDocPO> ret = new ArrayList<>();

        getConnection();
        try {
            Statement stat = conn.createStatement();
            ResultSet docSet = stat.executeQuery(financeDocDraft.toString());
            while (docSet.next()) {
                String operator = docSet.getString(1);
                String docId = operator + "-" + docSet.getString(2);
                DocType type = DocType.parse(docSet.getString(3));

                if (type == DocType.CASH) {
                    String account = docSet.getString(5);

                    ArrayList<CashItemPO> itemList = new ArrayList<>();
                    cashDraftItemList.select().key("DocID", docId).keyEnd().end();
                    Statement itemStat = conn.createStatement();
                    ResultSet itemSet = itemStat.executeQuery(cashDraftItemList.toString());
                    while (itemSet.next()) {
                        itemList.add(new CashItemPO(
                                itemSet.getString(2),
                                itemSet.getDouble(3),
                                itemSet.getString(4)));
                    }

                    ret.add(new CashDocPO(docId, operator, type, account, itemList));
                } else {
                    String customer = docSet.getString(4);

                    ArrayList<AccountForDocPO> itemList = new ArrayList<>();
                    paymentDraftItemList.select().key("DocID", docId).keyEnd().end();
                    Statement itemStat = conn.createStatement();
                    ResultSet itemSet = itemStat.executeQuery(paymentDraftItemList.toString());
                    while (itemSet.next()) {
                        itemList.add(new AccountForDocPO(
                                itemSet.getString(2),
                                itemSet.getDouble(3),
                                itemSet.getString(4)));
                    }

                    ret.add(new PaymentDocPO(docId, operator, type, customer, itemList));
                }
            }
            stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return ret;
    }

    /**
     * 获取待审批的进货类单据列表.
     *
     * @return 待审批的进货类单据列表
     */
    @Override
    public ArrayList<PurchaseDocPO> getUncheckedPurchaseDocList() {
        purchaseDocDraft.select().key("Checked", 1).keyEnd().end();
        return getPurchaseDraftList(purchaseDocDraft.toString());
    }

    /**
     * 获取待审批的销售类单据列表.
     *
     * @return 待审批的销售类单据列表
     */
    @Override
    public ArrayList<SaleDocPO> getUncheckedSaleDocList() {
        saleDocDraft.select().key("Checked", 1).keyEnd().end();
        return getSaleDraftList(saleDocDraft.toString());
    }

    /**
     * 待审批的库存类单据审批通过.
     *
     * @param doc 待审批的库存类单据数据
     * @return "审批通过"结果
     */
    @Override
    public ResultMessage approveDoc(GoodDocPO doc) {
        // TODO
        return delDocDraft(doc);
    }

    /**
     * 待审批的财务类单据审批通过.
     *
     * @param doc 待审批的财务类单据数据
     * @return "审批通过"结果
     */
    @Override
    public ResultMessage approveDoc(FinanceDocPO doc) {
        // TODO
        if (doc.getType() == DocType.CASH)
            return delDocDraft((CashDocPO)doc);
        else
            return delDocDraft((PaymentDocPO)doc);
    }

    /**
     * 待审批的进货类单据审批通过.
     *
     * @param doc 待审批的进货类单据数据
     * @return "审批通过"结果
     */
    @Override
    public ResultMessage approveDoc(PurchaseDocPO doc) {
        // TODO
        return delDocDraft(doc);
    }

    /**
     * 待审批的销售类单据审批通过.
     *
     * @param doc 待审批的销售类单据数据
     * @return "审批通过"结果
     */
    @Override
    public ResultMessage approveDoc(SaleDocPO doc) {
        // TODO
        return delDocDraft(doc);
    }

    private ResultMessage rejectDoc(SqlHelper helper, DocPO doc) {
        getConnection();
        try {
            Statement stat = conn.createStatement();
            helper.update().set("Checked", 0).setEnd()
                    .key("Operator", doc.getOperatorId())
                    .key("DateTime", doc.getPrKey().split("-")[1])
                    .keyEnd().end();
            stat.executeUpdate(helper.toString());
            stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
        return ResultMessage.SUCCESS;
    }

    /**
     * 待审批的库存类单据审批不通过.
     *
     * @param doc 待审批的库存类单据数据
     * @return "审批不通过"结果
     */
    @Override
    public ResultMessage rejectDoc(GoodDocPO doc) {
        // TODO
        return rejectDoc(goodDocDraft, doc);
    }

    /**
     * 待审批的财务类单据审批不通过.
     *
     * @param doc 待审批的财务类单据数据
     * @return "审批不通过"结果
     */
    @Override
    public ResultMessage rejectDoc(FinanceDocPO doc) {
        // TODO
        return rejectDoc(financeDocDraft, doc);
    }

    /**
     * 待审批的进货类单据审批不通过.
     *
     * @param doc 待审批的进货类单据数据
     * @return "审批不通过"结果
     */
    @Override
    public ResultMessage rejectDoc(PurchaseDocPO doc) {
        // TODO
        return rejectDoc(purchaseDocDraft, doc);
    }

    /**
     * 待审批的销售类单据审批不通过.
     *
     * @param doc 待审批的销售类单据数据
     * @return "审批不通过"结果
     */
    @Override
    public ResultMessage rejectDoc(SaleDocPO doc) {
        // TODO
        return rejectDoc(saleDocDraft, doc);
    }
}
