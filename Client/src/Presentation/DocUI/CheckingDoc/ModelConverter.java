package Presentation.DocUI.CheckingDoc;

import Presentation.DocUI.GoodDoc.model.GoodDoc;
import Presentation.DocUI.PaymentDoc.model.PaymentDoc;
import Presentation.DocUI.PurchaseSaleDoc.model.PurchaseSaleDoc;
import VO.*;

public class ModelConverter {

    public GoodDoc ToGoodModel(GoodDocVO vo){
        return new GoodDoc(vo.getPrKey(),vo.getOperator(),vo.getDocType(),vo.getItemList(),vo.getComment());
    }

    public PurchaseSaleDoc ToPurModel(PurchaseDocVO vo){
        return new PurchaseSaleDoc(vo.getPrKey(),vo.getType(),vo.getCustomer(),vo.getOperator(),vo.getItemList(),vo.getTotal(),vo.getComment());
    }

    public PurchaseSaleDoc ToSaleModel(SaleDocVO vo){
        return new PurchaseSaleDoc(vo.getPrKey(),vo.getType(),vo.getCustomer(),vo.getOperator(),vo.getItemList(),vo.getTotal(),vo.getComment(),vo.getRebate(),vo.getVoucher(),vo.getGiftList());
    }

    public PurchaseSaleDoc ToSaleReturnModel(SaleDocVO vo){
        return new PurchaseSaleDoc(vo.getPrKey(),vo.getType(),vo.getCustomer(),vo.getOperator(),vo.getItemList(),vo.getTotal(),vo.getComment());
    }

    public PaymentDoc ToPaymentModel(PaymentDocVO vo){
        return new PaymentDoc(vo.getPrKey(),vo.getOperator(),vo.getType(),vo.getCustomer(),vo.getAccountList());
    }

    public PaymentDoc ToPaymentModel(CashDocVO vo){
        return new PaymentDoc(vo.getPrKey(),vo.getOperator(),vo.getType(),vo.getAccount(),vo.getItemList());
    }
}
