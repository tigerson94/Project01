import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class Order {
    private ArrayList<Product> shoppingBag = new ArrayList<Product>();
    private HashSet<Product> soldProduct = new HashSet<Product>();
    private int counter = 0;


    public void AddOrder(Product product) {
        Scanner sc = new Scanner(System.in);
        int input;

        if (product.getSizeupPrice() != 0) { //사이즈업 옵션이 있을때
            product.Show();// 메뉴 | W 가격 | 이름
            Product sizeupProduct = new Product(product.getName(), product.getDescription(), product.getSizeupPrice(), product.getCategory());
            System.out.println("위 메뉴의 어떤 옵션으로 추가하시겠습니까?");
            System.out.println("1. 24oz(W " + product.getPrice() + ")          2. 1l(W " + sizeupProduct.getPrice() + ")");
            input = sc.nextInt();
            if (input == 1) { //기본 사이즈
                question(product);
            } else if (input == 2) {//사이즈업
                question(sizeupProduct);
            }
        } else
            question(product);
    }
    public void question (Product product) {
        Scanner sc = new Scanner(System.in);
        int input;
        product.Show();
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1.확인          2.취소");
        input = sc.nextInt();
        if (input == 1) {//장바구니 추가
            System.out.println(product.getName() + "가 장바구니에 추가되었습니다.");
            shoppingBag.add(product);
        } else if (input == 2) {
            System.out.println("장바구니에 추가하지 않았습니다.");
        }
    }
    public double getShoppingBag(){//쇼핑백 내용 보여주기, 가격 return
        double totalprice = 0;
        int EA;
        HashSet<Product> productCountSet = new HashSet<Product>(shoppingBag);
        for (Product item : productCountSet) {
            EA = Collections.frequency(shoppingBag,item);
            item.Show(EA);
            totalprice += item.getPrice() * EA; //가격계산
        }
        totalprice=Math.round((totalprice*100))/100.0;//소수점 둘째자리까지 나타내고 반올림
        return totalprice;
    }
    public int CompleteOrder() {
        soldProduct.addAll(shoppingBag); //총 판매상품 hashset에 장바구나 목록 넣기
        shoppingBag.clear();//
        counter++;          //대기번호 +1
        return counter;
    }

    public void CancelOrder() {
        shoppingBag.clear();
    }
    public void SoldList(){
        System.out.println("[ 총 판매상품 목록 현황 ]");
        System.out.println("현재까지 총 판매된 상품 목록은 아래와 같습니다.");
        for(Product item : soldProduct){
            System.out.printf("%-20s | W %s\n",item.getName(), item.getPrice());

        }


    }
}