package DataStore.Enums;

public enum FileStoreExt {
    JSON("JSON"), 
    XML("XML"), 
    OBJ("OBJ");
    
    private String exString;
    
    private FileStoreExt(String extString) {
        this.exString = extString;
    }
    
    @Override
    public String toString() {
        return exString;
    }
}
