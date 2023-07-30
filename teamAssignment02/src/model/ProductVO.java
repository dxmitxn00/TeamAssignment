package model;

//Product의 VO
public class ProductVO {
	private int num; // 상품 번호(PK)
	private String name; // 상품 이름
	private int price; // 상품 가격
	private int cnt; // 상품 재고

	// 상품 생성자
	public ProductVO(int num, String name, int price, int cnt) {
		this.num = num;
		this.name = name;
		this.price = price;
		this.cnt = cnt;
		System.out.println(" 로그: ProductVO(): 상품 생성");
	}

	// getter setter
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	@Override
	public String toString() { // toString 오버라이딩

		return this.num + "." + this.name + " 가격: " + this.price + "원 재고: " + this.cnt + "개";
	}

}
