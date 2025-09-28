public class Menu {

    private String[] food;
    private String[] beverages;

    public Menu(String[] food,String[] beverages){
        this.food = food;
        this.beverages = beverages;
    }

    public String listMenu(){
        for(int i = 0; i< food.length; i++){
            return i+". "+food+"\n"+beverages;
        }
        return "";

    }

}
