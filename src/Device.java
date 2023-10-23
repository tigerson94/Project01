import java.util.ArrayList;
import java.util.Scanner;

public class Device {
    private Order order = new Order();
    private ArrayList<Menu> categoryMenu = new ArrayList<Menu>(); // 카테고리 메뉴판
    private ArrayList<Product> allProducts = new ArrayList<Product>(); // 전체상품
    private ArrayList<Product> categoryProduct = new ArrayList<Product>(); // 카테고리 상품 메뉴판
    private double revenue;

    public void LoadMenu() { //카테고리메뉴와 상품메뉴를 리스트에 담기
        Menu CoffeMenu = new Menu("Coffe", "스파르탄만의 특별한 원두로 만들어진 프리미엄 커피");
        Menu CakeMenu = new Menu("Cake", "체코 프라하에서 '국내최초' 직수입한 유럽 매장용 최고급 케이크");
        categoryMenu.add(CoffeMenu);
        categoryMenu.add(CakeMenu);

        Product Americano = new Product("Americano", "브라질원두는 절대 들어가지 않는 최고급 원두로 추출한 아메리카노", 2.5, 3.5,"Coffe");
        Product Einspänner = new Product("Einspänner", "100% 동물성 생크림으로 만든 특제 생크림과 최고급 원두의 조화", 4.5, 5.5,"Coffe");
        Product Dualbrew = new Product("Dual brew", "스파르탄만의 원두로 추출한 핫브루의 풍부한 맛과 콜드브루의 깔끔한 맛의 케미", 4.5, 5.5,  "Coffe");
        Product Caffelatte = new Product("Caffe latte", "1++등급 우유와 최고급 에스프레소의 조합", 4.0,5.0, "Coffe");
        allProducts.add(Americano);
        allProducts.add(Einspänner);
        allProducts.add(Dualbrew);
        allProducts.add(Caffelatte);

        Product Cheesecake = new Product("Cheese cake", "비스킷이 함유된 식감과 높은 밀도의 치즈가 조합을 이룬 치즈 케이크", 6.9, "Cake");
        Product Chocolatecake = new Product("Chocolate cake", "초콜릿 플레이크 베이스로 부드럽고 독특한 식감의 초콜릿 케이크", 6.9, "Cake");
        Product Redvelvetcake = new Product("Red velvet cake", "높은 커드치즈 함유량으로 특유의 부드러운 맛과 시나몬을 함유한 레드벨벳 케이크", 6.9, "Cake");
        Product Carrotcake = new Product("Carrot cake", "설탕대신 달달한 당근을 넣어 만든 당근 케이크", 6.9, "Cake");
        allProducts.add(Cheesecake);
        allProducts.add(Chocolatecake);
        allProducts.add(Redvelvetcake);
        allProducts.add(Carrotcake);

    }

    public void Display() throws InterruptedException {
        while (true) {
            int numbering;          // 번호매김을 위한 변수 numbering 선언 (카테고리나 메뉴 갯수 변경 따른 유동적인 번호부여를 고려하여 numbering 사용)
            int selectCategoryNum;  // 카테고리메뉴판에서 선택한 카테고리번호
            int selectProductNum;   // 상품메뉴판에서 선택한 상품번호

            //(1)카테고리 메뉴판
            numbering = ShowMenu();                                     //1. 카테고리메뉴판 보여주기(번호매김 후 숫자 값 리턴)
            ShowOption(numbering);                                      //옵션메뉴(order/cancel) 보여주기 (order/cancel에 부여할 동적 번호를 인자값으로 전달)
            selectCategoryNum = getResponse(numbering, categoryMenu);   //사용자 응답.사용자 응답에 따른 결과를 전달받음

            if (selectCategoryNum >= numbering || selectCategoryNum == 0) {                       //order/cancel 번호 또는 옵션(0) 선택하였을 경우  : 초기로 돌아가기
                continue;
            }
            //(2)상세 메뉴판
            numbering = ShowMenu(selectCategoryNum);                     //2.선택카테고리의 메뉴판 보여주기 (번호매김 후 숫자 값 리턴)
            ShowOption(numbering);                                       //옵션메뉴(order/cancel) 보여주기 (order/cancel에 부여할 동적 번호를 인자값으로 전달)
            selectProductNum = getResponse(numbering, categoryProduct); //사용자 응답. 응답에 따라 메뉴추가 또는 order/cancel

            if (selectProductNum >= numbering || selectCategoryNum == 0) {                         //order/cancel 번호를 선택하였을 경우  : 초기로 돌아가기
                continue;
            }
            order.AddOrder(categoryProduct.get(selectProductNum - 1)); //선택한 상품 객체를 Addorder메서드의 인자값으로 전달
        }
    }

    public int ShowMenu() { //메뉴판
        int numbering = 1;
        System.out.println("\"스파르탄에 오신 여러분 환영합니다.\"");
        System.out.println("메뉴판의 메뉴를 골라 입력해주세요.");
        System.out.println("[ MENU ]");
        for (Menu item : categoryMenu) { // 카테고리 보여주기 categoryMenu -  Arraylist.
            System.out.print(numbering + ". ");//번호매김 1. 2. 3. (카테고리 추가를 고려하여 numbering 사용)
            item.Show();
            numbering++;
        }
        return numbering;
    }

    public int ShowMenu(int selectCategoryNum) { //상품메뉴판. 선택카테고리에 대한 ShowMenu()
        int numbering = 1;
        String menuName;
        categoryProduct.clear(); // 이전 카테고리메뉴가 남아 있을 시 지우기
        System.out.println("\"스파르탄에 오신 여러분 환영합니다.\"");
        System.out.println("메뉴판의 메뉴를 골라 입력해주세요.");
        menuName = categoryMenu.get(selectCategoryNum - 1).getName(); //선택한 카테고리명 가져오기
        System.out.println("[ " + menuName + " MENU ]");
        for (Product item : allProducts) { //전체 상품에서 선택한 카테고리의 상품들을 뽑아오기
            if (menuName.equals(item.getCategory())) {
                categoryProduct.add(item);
                System.out.print(numbering + ". ");
                item.Show();
                numbering++;
            }
        }
        return numbering;
    }
    public void ShowOption(int numbering) { //옵션 메뉴
        System.out.println("[ ORDER MENU ]");
        System.out.printf(numbering + ". %-15s | %s\n", "Order", "장바구니를 확인 후 주문을 완료합니다.");
        System.out.printf(numbering + 1 + ". %-15s | %s\n", "Cancel", "진행중인 주문을 취소합니다.");
    }

    public <T extends Menu> int getResponse(int numbering, ArrayList<T> list) throws InterruptedException { //응답을 받고 처리
        int input;
        int optionInput;
        double totalPrice;
        Scanner sc = new Scanner(System.in);
        input = sc.nextInt();
        if (1 <= input && input < numbering) { //카테고리 범위 내 번호 선택 시 - select
            System.out.println(list.get(input - 1).getName() + " 선택하셨습니다.");
        } else if (input == numbering) {//Order주문하기 선택 시
            System.out.println("선택하신 메뉴가 맞는지 확인해 주세요");
            System.out.println("[ Orders ]");
            totalPrice = order.getShoppingBag(); //장바구니에 담긴 내용물을 보여주고, 총가격 리턴받음
            System.out.println("[ Total ]");
            System.out.println("W " + totalPrice + "\n");
            System.out.println("1. 주문     2. 메뉴판");
            optionInput = sc.nextInt();
            if (optionInput == 1 && totalPrice !=0) {
                System.out.println("주문이 완료되었습니다!");
                System.out.println("대기번호는 [ " + order.CompleteOrder() + " ]번 입니다."); //장바구니를 비우고, 대기번호 리턴받음
                revenue +=totalPrice; // 주문한 가격만큼 수익에 계산
                System.out.println("(5초 후 초기 화면으로 돌아갑니다.)");
                Thread.sleep(1000);
                System.out.println("(4초 후 초기 화면으로 돌아갑니다.)");
                Thread.sleep(1000);
                System.out.println("(3초 후 초기 화면으로 돌아갑니다.)");
                Thread.sleep(1000);
                System.out.println("(2초 후 초기 화면으로 돌아갑니다.)");
                Thread.sleep(1000);
                System.out.println("(1초 후 초기 화면으로 돌아갑니다.)");
                Thread.sleep(1000);
            } else if (optionInput == 2) {
                System.out.println("주문이 완료되지 않았습니다.");
                System.out.println("(초기 메뉴판으로 돌아갑니다.)");
                Thread.sleep(500);
            }
            else if(totalPrice==0){
                System.out.println("주문하신 메뉴가 없습니다.");
                System.out.println("(초기 메뉴판으로 돌아갑니다.)");
                Thread.sleep(500);
            }
        } else if (input == numbering + 1) {//Cancel취소하기 선택시
            System.out.println("주문을 취소하겠습니까?");
            System.out.println("1. 확인     2. 취소");
            optionInput= sc.nextInt();
            if (optionInput == 1) {
                order.CancelOrder();
                System.out.println("주문이 취소되었습니다.");
                Thread.sleep(500);
            }
            if (optionInput == 2) {
                System.out.println("주문이 취소되지 않았습니다.");
                Thread.sleep(500);
            }
        }
        else if (input == 0) { //옵션 기능 선택 시
            System.out.println("[ 판매금액 현황 ]");
            System.out.println(" 판매된 금액은 [ W "+Math.round((revenue*100))/100.0 +"] 입니다.\n"); //소수점 둘째자리까지 나타내고 반올림
            order.SoldList();
            while(true) {
                System.out.println("1. 돌아가기");
                optionInput = sc.nextInt();
                if (optionInput == 1) {
                    System.out.println("이전 화면으로 돌아갑니다.");
                    break;
                }
                else{
                    System.out.println("다시 입력해주세요.");
                }
            }
        }

        return input;//선택한 번호 전달
    }
}