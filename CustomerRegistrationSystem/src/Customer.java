public record Customer(int userID,String name,String address,String contactInfo,String password) {

    private static int idcounter;

    public static Customer create(String name,String contactInfo,String address,String password){
        return new Customer(idcounter++,name,address,contactInfo,password);
    }


}
