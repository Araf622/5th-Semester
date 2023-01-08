import com.sun.source.tree.BreakTree;

public class TonerSaveMode extends PrintMode{
    String tonerSavingLevel;

    @Override
    public void configure() {
        saveToner(tonerSavingLevel);
    }
    public void saveToner(String tonerSavingLevel){
        this.tonerSavingLevel = tonerSavingLevel;
    }
}

class LowTonerSaveMode{
    public String LowTonerSaveMode(String colorIntensity){
        return colorIntensity;
    }
}

class MediumTonerSaveMode{
    public String MediumTonerSaveMode(String colorIntensity){
        return colorIntensity;
    }
}

class HighTonerSaveMode{
    public String HighTonerSaveMode(String colorIntensity){
        return colorIntensity;
    }
}