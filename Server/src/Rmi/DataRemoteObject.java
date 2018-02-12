package Rmi;

import Data.*;
import DataService.*;
import java.rmi.server.UnicastRemoteObject;
import PO.*;
import Util.ResultMessage;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class DataRemoteObject extends UnicastRemoteObject
        implements UserDataService, AccountDataService, CustomerDataService,
        DocDataService, CommodityDataService, StrategyDataService{
    private static final long serialVersionUID = 1;
    private UserDataService userDataService;
    private AccountDataService accountDataService;
    private CustomerDataService customerDataService;
    private DocDataService docDataService;
    private CommodityDataService commodityDataService;
    private StrategyDataService strategyDataService;

    protected DataRemoteObject() throws RemoteException {
        userDataService = new UserDataImpl();
        accountDataService = new AccountDataImpl();
        customerDataService = new CustomerDataImpl();
        docDataService = new DocDataImpl();
        commodityDataService = new CommodityDataImpl();
        strategyDataService = new StrategyDataImpl();
    }

    //UserData的方法
    @Override
    public ResultMessage insert(UserPO po) throws RemoteException {
        return userDataService.insert(po);
    }

    @Override
    public ResultMessage disguiseDelete(String id) throws RemoteException {
        return userDataService.disguiseDelete(id);
    }

    @Override
    public ResultMessage update(UserPO po) throws RemoteException {
        return userDataService.update(po);
    }

    @Override
    public UserPO find(String id) throws RemoteException {
        return userDataService.find(id);
    }

    @Override
    public ArrayList<UserPO> getAllUser() throws RemoteException {
        return userDataService.getAllUser();
    }

    @Override
    public ArrayList<UserPO> findUsers(String str) throws RemoteException {
        return userDataService.findUsers(str);
    }




    //login
    @Override
    public ResultMessage login(String id, String password) throws RemoteException {
        return userDataService.login(id,password);
    }

    @Override
    public boolean logout(String id) throws RemoteException {
        return userDataService.logout(id);
    }

    @Override
    public boolean resetPassword(String id, String oldPassword, String newPassword) throws RemoteException {
        return userDataService.resetPassword(id,oldPassword,newPassword);
    }


    //AccountData的方法
    @Override
    public ResultMessage disguiseDeleteAccount(String id) throws RemoteException {
        return accountDataService.disguiseDeleteAccount(id);
    }

    @Override
    public ResultMessage insert(AccountPO po) throws RemoteException {
        return accountDataService.insert(po);
    }

    @Override
    public ResultMessage update(AccountPO po) throws RemoteException {
        return accountDataService.update(po);
    }

    @Override
    public AccountPO getInfo(String cardName) throws RemoteException {
        return accountDataService.getInfo(cardName);
    }

    @Override
    public ArrayList<AccountPO> search(String str) throws RemoteException {
        return accountDataService.search(str);
    }


    @Override
    public ArrayList<AccountPO> getAllAccounts() throws RemoteException {
        return accountDataService.getAllAccounts();
    }



    //Customer的方法
    @Override
    public ResultMessage insert(CustomerPO po) throws RemoteException {
        return customerDataService.insert(po);
    }

    @Override
    public ResultMessage disguiseDeleteCustomer(String id) throws RemoteException {
        return customerDataService.disguiseDeleteCustomer(id);
    }

    @Override
    public ResultMessage update(CustomerPO po) throws RemoteException {
        return customerDataService.update(po);
    }

    @Override
    public ArrayList<CustomerPO> searchCustomers(String str) throws RemoteException {
        return customerDataService.searchCustomers(str);
    }

    @Override
    public CustomerPO searchByID(String id) throws RemoteException {
        return customerDataService.searchByID(id);
    }

    @Override
    public ArrayList<CustomerPO> getSupplier() throws RemoteException {
        return customerDataService.getSupplier();
    }

    @Override
    public ArrayList<CustomerPO> getSaler() throws RemoteException {
        return customerDataService.getSaler();
    }

    @Override
    public ArrayList<CustomerPO> getCustomer() throws RemoteException {
        return customerDataService.getCustomer();
    }

    //StrategyData的方法

    @Override
    public ResultMessage insertCusStrategy(CustomerStrategyPO cusstrategypo) throws RemoteException {
        return strategyDataService.insertCusStrategy(cusstrategypo);
    }

    @Override
    public ResultMessage insertPkgStrategy(PackageStrategyPO pkgstrategypo) throws RemoteException {
        return strategyDataService.insertPkgStrategy(pkgstrategypo);
    }

    @Override
    public ResultMessage insertAmouStrategy(AmountStrategyPO amoustrategypo) throws RemoteException {
        return strategyDataService.insertAmouStrategy(amoustrategypo);
    }

    @Override
    public ResultMessage delete(StrategyPO strategypo) throws RemoteException {
        return strategyDataService.delete(strategypo);
    }

    @Override
    public StrategyPO getStrategy(String id) throws RemoteException {
        return strategyDataService.getStrategy(id);
    }

    @Override
    public CustomerStrategyPO getCurrentCusStrategy(LocalDate date, int level) throws RemoteException {
        return strategyDataService.getCurrentCusStrategy(date, level);
    }

    @Override
    public ArrayList<PackageStrategyPO> getCurrentPkgStrategy(LocalDate date) throws RemoteException {
        return strategyDataService.getCurrentPkgStrategy(date);
    }

    @Override
    public AmountStrategyPO getCurrentAmouStrategy(LocalDate date, double amount) throws RemoteException {
        return strategyDataService.getCurrentAmouStrategy(date, amount);
    }

    @Override
    public ArrayList<CustomerStrategyPO> getCusStrategy() throws RemoteException {
        return strategyDataService.getCusStrategy();
    }

    @Override
    public ArrayList<PackageStrategyPO> getPkgStrategy() throws RemoteException {
        return strategyDataService.getPkgStrategy();
    }

    @Override
    public ArrayList<AmountStrategyPO> getAmouStrategy() throws RemoteException {
        return strategyDataService.getAmouStrategy();
    }

    @Override
    public ResultMessage updateCusStrategy(CustomerStrategyPO customerStrategyPO) throws RemoteException {
        return strategyDataService.updateCusStrategy(customerStrategyPO);
    }

    @Override
    public ResultMessage updatePkgStrategy(PackageStrategyPO packageStrategyPO) throws RemoteException {
        return strategyDataService.updatePkgStrategy(packageStrategyPO);
    }

    @Override
    public ResultMessage updateAmouStrategy(AmountStrategyPO amountStrategyPO) throws RemoteException {
        return strategyDataService.updateAmouStrategy(amountStrategyPO);
    }

    @Override
    public GoodTreePO getGoodTree() throws RemoteException {
        return commodityDataService.getGoodTree();
    }

    @Override
    public ResultMessage addCategory(CategoryPO newCategoryPO) throws RemoteException {
        return commodityDataService.addCategory(newCategoryPO);
    }

    @Override
    public ResultMessage delCategory(String delCategoryID) throws RemoteException {
        return commodityDataService.delCategory(delCategoryID);
    }

    @Override
    public ResultMessage updateCategory(CategoryPO categoryPO) throws RemoteException {
        return commodityDataService.updateCategory(categoryPO);
    }

    @Override
    public ResultMessage addGood(GoodPO newGoodPO) throws RemoteException {
        return commodityDataService.addGood(newGoodPO);
    }

    @Override
    public ResultMessage delGood(String delGoodID) throws RemoteException {
        return commodityDataService.delGood(delGoodID);
    }

    @Override
    public ResultMessage updateGood(GoodPO goodPO) throws RemoteException {
        return commodityDataService.updateGood(goodPO);
    }

    @Override
    public ArrayList<GoodPO> searchGood(String input, String CategoryID) throws RemoteException {
        return commodityDataService.searchGood(input, CategoryID);
    }

    @Override
    public ArrayList<CommodityPO> getCommodity(LocalDate start, LocalDate end) throws RemoteException {
        return commodityDataService.getCommodity(start, end);
    }

    @Override
    public ResultMessage updateAmount(String GoodID, int difference, double price,LocalDate date) throws RemoteException {
        return commodityDataService.updateAmount(GoodID, difference,price,date);
    }

    @Override
    public ResultMessage updateRecentSellingPrice(String GoodID, double difference) throws RemoteException {
        return commodityDataService.updateRecentSellingPrice(GoodID, difference);
    }

    @Override
    public ResultMessage updateRecentBuyingPrice(String GoodID, double difference) throws RemoteException {
        return commodityDataService.updateRecentBuyingPrice(GoodID, difference);
    }

    @Override
    public ResultMessage checkExistence(ArrayList<String> idList) throws RemoteException {
        return commodityDataService.checkExistence(idList);
    }

    @Override
    public GoodPO goodsearchByID(String id) throws RemoteException {
        return commodityDataService.goodsearchByID(id);
    }

    @Override
    public ArrayList<GoodDocPO> getGoodOverflowDraftList(String userId) throws RemoteException{
        return docDataService.getGoodOverflowDraftList(userId);
    }

    @Override
    public ArrayList<GoodDocPO> getGoodLossDraftList(String userId) throws RemoteException{
        return docDataService.getGoodLossDraftList(userId);
    }

    @Override
    public ArrayList<GoodDocPO> getGoodGiftDraftList(String userId) throws RemoteException{
        return docDataService.getGoodGiftDraftList(userId);
    }

    @Override
    public ArrayList<PaymentDocPO> getPayingDraftList(String userId) throws RemoteException{
        return docDataService.getPayingDraftList(userId);
    }

    @Override
    public ArrayList<PaymentDocPO> getReceivingDraftList(String userId) throws RemoteException{
        return docDataService.getReceivingDraftList(userId);
    }

    @Override
    public ArrayList<CashDocPO> getCashDraftList(String userId)throws RemoteException {
        return docDataService.getCashDraftList(userId);
    }

    @Override
    public ArrayList<PurchaseDocPO> getPurchaseDraftList(String userId)throws RemoteException {
        return docDataService.getPurchaseDraftList(userId);
    }

    @Override
    public ArrayList<SaleDocPO> getSaleDraftList(String userId)throws RemoteException {
        return docDataService.getSaleDraftList(userId);
    }

    @Override
    public ArrayList<SaleDocPO> getSaleReturnDraftList(String userId) throws RemoteException{
        return docDataService.getSaleReturnDraftList(userId);
    }

    @Override
    public ResultMessage addDocDraft(GoodDocPO goodDoc)throws RemoteException {
        return docDataService.addDocDraft(goodDoc);
    }

    @Override
    public ResultMessage addDocDraft(PaymentDocPO paymentDoc)throws RemoteException {
        return docDataService.addDocDraft(paymentDoc);
    }

    @Override
    public ResultMessage addDocDraft(CashDocPO cashDoc)throws RemoteException {
        return docDataService.addDocDraft(cashDoc);
    }

    @Override
    public ResultMessage addDocDraft(PurchaseDocPO purchaseDoc)throws RemoteException {
        return docDataService.addDocDraft(purchaseDoc);
    }

    @Override
    public ResultMessage addDocDraft(SaleDocPO saleDoc)throws RemoteException {
        return docDataService.addDocDraft(saleDoc);
    }

    @Override
    public ResultMessage editDocDraft(GoodDocPO goodDoc)throws RemoteException {
        return docDataService.editDocDraft(goodDoc);
    }

    @Override
    public ResultMessage editDocDraft(PaymentDocPO paymentDoc)throws RemoteException {
        return docDataService.editDocDraft(paymentDoc);
    }

    @Override
    public ResultMessage editDocDraft(CashDocPO cashDoc)throws RemoteException {
        return docDataService.editDocDraft(cashDoc);
    }

    @Override
    public ResultMessage editDocDraft(PurchaseDocPO purchaseDoc)throws RemoteException {
        return docDataService.editDocDraft(purchaseDoc);
    }

    @Override
    public ResultMessage editDocDraft(SaleDocPO saleDoc)throws RemoteException {
        return docDataService.editDocDraft(saleDoc);
    }

    @Override
    public ResultMessage delDocDraft(GoodDocPO goodDoc) throws RemoteException {
        return docDataService.delDocDraft(goodDoc);
    }

    @Override
    public ResultMessage delDocDraft(PaymentDocPO paymentDoc) throws RemoteException {
        return docDataService.delDocDraft(paymentDoc);
    }

    @Override
    public ResultMessage delDocDraft(CashDocPO cashDoc) throws RemoteException {
        return docDataService.delDocDraft(cashDoc);
    }

    @Override
    public ResultMessage delDocDraft(PurchaseDocPO purchaseDoc) throws RemoteException {
        return docDataService.delDocDraft(purchaseDoc);
    }

    @Override
    public ResultMessage delDocDraft(SaleDocPO saleDoc) throws RemoteException {
        return docDataService.delDocDraft(saleDoc);
    }

    @Override
    public ResultMessage submitDocDraft(GoodDocPO goodDoc) throws RemoteException {
        return docDataService.submitDocDraft(goodDoc);
    }

    @Override
    public ResultMessage submitDocDraft(FinanceDocPO financeDoc) throws RemoteException {
        return docDataService.submitDocDraft(financeDoc);
    }

    @Override
    public ResultMessage submitDocDraft(PurchaseDocPO purchaseDoc) throws RemoteException {
        return docDataService.submitDocDraft(purchaseDoc);
    }

    @Override
    public ResultMessage submitDocDraft(SaleDocPO saleDoc) throws RemoteException {
        return docDataService.submitDocDraft(saleDoc);
    }

    @Override
    public ArrayList<GoodDocPO> getUncheckedGoodDocList()throws RemoteException {
        return docDataService.getUncheckedGoodDocList();
    }

    @Override
    public ArrayList<FinanceDocPO> getUncheckedFinanceDocList()throws RemoteException {
        return docDataService.getUncheckedFinanceDocList();
    }

    @Override
    public ArrayList<PurchaseDocPO> getUncheckedPurchaseDocList() throws RemoteException{
        return docDataService.getUncheckedPurchaseDocList();
    }

    @Override
    public ArrayList<SaleDocPO> getUncheckedSaleDocList()throws RemoteException {
        return docDataService.getUncheckedSaleDocList();
    }

    @Override
    public ResultMessage approveDoc(GoodDocPO doc)throws RemoteException {
        return docDataService.approveDoc(doc);
    }

    @Override
    public ResultMessage approveDoc(FinanceDocPO doc)throws RemoteException {
        return docDataService.approveDoc(doc);
    }

    @Override
    public ResultMessage approveDoc(PurchaseDocPO doc)throws RemoteException {
        return docDataService.approveDoc(doc);
    }

    @Override
    public ResultMessage approveDoc(SaleDocPO doc)throws RemoteException {
        return docDataService.approveDoc(doc);
    }

    @Override
    public ResultMessage rejectDoc(GoodDocPO doc)throws RemoteException {
        return docDataService.rejectDoc(doc);
    }

    @Override
    public ResultMessage rejectDoc(FinanceDocPO doc) throws RemoteException{
        return docDataService.rejectDoc(doc);
    }

    @Override
    public ResultMessage rejectDoc(PurchaseDocPO doc)throws RemoteException {
        return docDataService.rejectDoc(doc);
    }

    @Override
    public ResultMessage rejectDoc(SaleDocPO doc)throws RemoteException {
        return docDataService.rejectDoc(doc);
    }
}
