import java.util.Objects;

public class Product extends Menu {

    //필수요구사항
    //상품 클래스는 이름, 가격, 설명 필드를 가지는 클래스로 만들어주세요.
    //상품 클래스의 이름, 설명 필드는 메뉴 클래스를 상속받아 사용하는 구조로 개발해주세요.
    private double price;
    private double sizeupPrice;
    private String category;

    public Product(String name, String description, double price, String category) {
        super(name, description);
        this.price = price;
        this.category = category;
        this.sizeupPrice = 0;
    }
    public Product(String name, String description, double price, double sizeupPrice, String category)
    {
        super(name, description);
        this.price = price;
        this.sizeupPrice = sizeupPrice;
        this.category = category;
    }
    public double getPrice() {
        return price;
    }
    public double getSizeupPrice(){
        return sizeupPrice;
    }
    public String getCategory(){
        return category;
    }

    @Override
    public void Show()
    {
        System.out.printf("%-20s | W %s |%s\n", getName(), price,getDescription());
    }
    public void Show(int EA)
    {
        System.out.printf("%-20s | W %s | %s개 |%s\n", getName(), price,EA,getDescription());
    }


    //equals, hashcode 재정의
    //HashSet이 새롭게 선언하고 추가한 사이즈업메뉴를 동일객체로 인식하지 못함.
    //이유 :기존노드와 같은지 확인하려는 작업으로 추가할 노드의 equals()와 hashCode()를 호출하게 되는데, 이 부분에서 같은 객체로 인식하지 못함
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Product) {
            Product temp = (Product)obj;
            return this.getName().equals(temp.getName()) &&
                    this.getPrice() == temp.getPrice() &&
                    this.getSizeupPrice() == temp.getSizeupPrice() &&
                    this.getDescription().equals(temp.getDescription()) &&
                    this.getCategory().equals(temp.getCategory());
        }
        return false;
    }
    public int hashCode(){
        return Objects.hash(getName(),getDescription(),getPrice(),getSizeupPrice(),getCategory());
    }
}