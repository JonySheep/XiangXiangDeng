package Presentation.DocUI.CheckingDoc;

import Util.DocType;

public interface Previewable {

    public DocType getDocType();

    public String getName();

    public String getComment();

    public String getPrKey();
}
