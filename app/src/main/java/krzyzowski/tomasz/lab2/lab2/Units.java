package krzyzowski.tomasz.lab2.lab2;

public enum Units {
    METRICAL(1), IMPERIAL(2);
    private final int id;
    Units(int id) {
        this.id = id;
    }

    public static Units byId(int id){
        switch(id) {
            case 1:
                return METRICAL;
            case 2:
                return IMPERIAL;
            default:
                throw new IllegalArgumentException("There is no units with provided id.");
        }
    }

    public int getId() {
        return id;
    }
}
