import javax.print.Doc;

public class PrintRequest {
    Document document;
    TonerSaveMode tonerSaveMode;
    PageSaveMode pageSaveMode;
    BoosterMode boosterMode;
    public PrintRequest(Document document, TonerSaveMode tonerSaveMode, PageSaveMode pageSaveMode, BoosterMode boosterMode){
        this.document=document;
        this.tonerSaveMode=tonerSaveMode;
        this.pageSaveMode=pageSaveMode;
        this.boosterMode=boosterMode;
    }
}
