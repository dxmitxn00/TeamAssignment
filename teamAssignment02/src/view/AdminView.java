package view;

import java.util.Scanner;

import model.ProductVO;

public class AdminView {
	private static Scanner sc = new Scanner(System.in);

	// 관리자 메뉴 총 4개
	private static final int maxMenuNum = 4;

	// try-catch 예외처리
	private int tryCatch() {
		while (true) {
			try {
				int action = sc.nextInt();
				return action;
			}
			// 정수 이외의 값이 들어오면 정수로 입력할 때까지 재입력 시키기
			catch (Exception e) {
				sc.nextLine();
				System.out.println("정수로 입력해주세요!");
			}
		}
	}

	// 관리자 메뉴 번호 입력
	private int getAdminMenuNum() {
		while (true) {
			try {
				System.out.print("입력) ");
				int menuNum = sc.nextInt();

				// 입력받은 값이 1 이상, 관리자 메뉴 개수 이하일 때 (정상적인 입력값일 때)
				// 입력받은 값 리턴
				if (1 <= menuNum && menuNum <= maxMenuNum) {
					return menuNum;
				}
			}
			// 정수 이외의 값이 들어오면 재입력 시키기
			catch (Exception e) {
				sc.nextLine();
				System.out.println("정수로 입력해주세요!");
			}
		}
	}

	// 최종결정 확인
	private int ok() {
		while (true) {
			try {
				int action = sc.nextInt();
				// 입력받은 값이 1과 2만 허용
				if (action <= 2 && action >= 1) {
					// 2를 입력받았다면 다시 입력
					if (action == 2) {
						System.out.println("다시 입력해주세요!");
					}
					// 1을 입력받았다면 입력받은 값 리턴해서 계속 진행
					return action;

				}
				// 입력받은 값이 1,2가 아니라면
				// 번호 재입력 시키기
				System.out.println("유효하지 않은 값입니다");
				System.out.println("다시 입력해주세요!");
			}
			// 정수 이외의 값이 들어오면 재입력 시키기
			catch (Exception e) {
				sc.nextLine();
				System.out.println("정수로 입력해주세요!");
			}
		}
	}

	// 관리자 메뉴 출력 & 메뉴 번호 입력받기
	public ProductVO printAdminMenu() {
		System.out.println();
		System.out.println("=== 관 리 자 모 드 ===");
		System.out.println("1. 상품 추가");
		System.out.println("2. 재고 변경");
		System.out.println("3. 상품 삭제");
		System.out.println("4. 관리자 모드 종료");
		int menuNum = getAdminMenuNum();
		ProductVO pVO = new ProductVO(menuNum, null, 0, 0);
		return pVO;
	}

	// 상품번호 입력 받기
	// "재고 변경", "상품 삭제"에서 사용
	public ProductVO getNum() {
		int num;
		while (true) {
			System.out.print("상품 번호 입력) ");
			num = tryCatch(); // tryCatch로 입력값이 정수인지 확인하고,
			if (num < 1001) { // num(상품번호)이 PK 시작값보다 작은지 확인
				System.out.println("유효하지 않은 상품번호입니다!");
				continue;
			}
			// 유효한 값이 들어오면 pVO에 num을 저장하여 보내줌
			ProductVO pVO = new ProductVO(num, null, 0, 0);
			return pVO;
		}
	}

	// 상품이름 입력 받기
	// "상품 추가"에서 사용
	public ProductVO getName() {
		while (true) {
			System.out.print("상품 이름: ");
			String name = sc.next();
			// 이름을 입력받고
			// 최종확인을 위해 ok() 모듈로 다시 한번 확인받기
			System.out.println("상품 이름: " + name + " 맞으면 1번, 틀리면 2번을 눌러주세요.");
			int num = ok();
			if (num == 1) { // 최종확인 num이 1이라면 이름 확정
				// 유효한 값이 들어오면 pVO에 name을 저장하여 보내줌
				ProductVO pVO = new ProductVO(0, name, 0, 0);
				return pVO;
			}

		}
	}

	// 상품가격 입력 받기
	// "상품 추가"에서 사용
	public ProductVO getPrice() {
		int price;
		while (true) {
			System.out.print("상품 가격 입력) ");
			price = tryCatch(); // tryCatch로 입력값이 정수인지 확인하고,
			if (price < 1) { // 입력받은 price가 음수값이라면 유효하지 않은 가격
				System.out.println("유효하지 않은 가격입니다!");
				continue;
			}
			// 유효한 값이 들어오면 pVO에 price를 저장하여 보내줌
			ProductVO pVO = new ProductVO(0, null, price, 0);
			return pVO;
		}
	}

	// 상품재고 입력 받기
	// "상품 추가"에서 사용
	public ProductVO getCnt() {
		int cnt;
		while (true) {
			System.out.print("상품 재고 입력) ");
			cnt = tryCatch(); // tryCatch로 입력값이 정수인지 확인하고,
			if (cnt < 1) { // 입력받은 cnt가 음수값이라면 유효하지 않은 재고
				System.out.println("유효한 재고값을 입력해주세요!");
				continue;
			}
			break;
		}
		// 유효한 값이 들어오면 pVO에 cnt를 저장하여 보내줌
		ProductVO pVO = new ProductVO(0, null, 0, cnt);
		return pVO;
	}

//	// 상품 목록 출력
//	public void printProductList(ArrayList<ProductVO> pdatas) {
//		// pdatas의 배열이 비어있다면 출력할 상품 없음 출력
//		if(pdatas.isEmpty()) { 
//			System.out.println("상품 목록이 없습니다!");
//			return;
//		}
//		// pdatas에 저장된 상품이 있다면
//		// for-each문으로 저장되어 있는 상품 목록 출력
//		for(ProductVO data:pdatas) {
//			System.out.println(data); 
//		}
//	}

	// 상품 "하나" 출력
	public void printProduct(ProductVO pVO) {
		System.out.println();
		if (pVO == null) {
			System.out.println("해당 상품이 없습니다!");
			return;
		}
		System.out.println("=== 해당 상품 출력 ===");
		System.out.println(pVO);
	}

	// 변경할 재고 개수 입력 받기
	// "재고 변경"에서 사용
	public ProductVO addCnt(ProductVO inputpVO) {
		int cnt;
		while (true) {
			System.out.print("증가 혹은 감소할 수량 입력) ");
			cnt = tryCatch(); // tryCatch로 입력값이 정수인지 확인하기
								// 재고 변경은 +,- 둘다 가능하므로 음수값 확인은 하지 않는다.
			// 유효한 값이 들어오면 pVO에 cnt를 저장하여 보내줌
			if (cnt < 0) {
				if ((cnt * -1) > inputpVO.getCnt()) {
					System.out.println("남은 재고보다 줄이는 재고가 많습니다");
					System.out.println("다시 입력해주세요");
					continue;
				}
			}
			break;
		}
		ProductVO pVO = new ProductVO(0, null, 0, cnt);
		return pVO;
	}

	// 상품 삭제 & 삭제 재확인
	public ProductVO deleteCheck(ProductVO inputpVO) {
		int pNum = inputpVO.getNum();
//			System.out.println("삭제할 번호 입력) ");
//			pNum=sc.nextInt();

		System.out.println(pNum + "번을 삭제하시겠습니까?");
		System.out.println("맞으면 1번, 틀리면 2번을 눌러주세요.");
		while (true) {
			try {
				int num = sc.nextInt();
				// 입력받은 값이 2라면 삭제 취소
				ProductVO pVO = new ProductVO(num, null, 0, 0);
				if (num == 2) {
					System.out.println("삭제가 취소되었습니다!");
					return pVO;
				}
				// 입력받은 값이 1이라면 삭제
				else if (num == 1) {
					// 유효한 값이 들어오면 pVO에 num을 저장하여 보내줌
					return pVO;
				}
				// 입력받은 값이 1,2가 아니라면
				// 번호 재입력 시키기
				System.out.println("유효하지 않은 값입니다");
				System.out.println("다시 입력해주세요!");
			}
			// 정수 이외의 값이 들어오면 재입력 시키기
			catch (Exception e) {
				sc.nextLine();
				System.out.println("정수로 입력해주세요!");
			}
		}

	}

	// 모든 실행 시작
	public void start(ProductVO pVO) {
		System.out.println(pVO.getName() + "을(를) 진행합니다!");
	}

	// 결과 성공
	public void suc(ProductVO pVO) {
		System.out.println(pVO.getName() + " 성공!");
	}

	// 결과 실패
	public void fail(ProductVO pVO) {
		System.out.println(pVO.getName() + " 실패!");
	}

	// 저장된 상품목록이 없을 경우
	public void noProduct() {
		System.out.println("저장된 상품이 없습니다!");
	}

	// 관리자 모드 종료
	public void printAdminEnd() {
		System.out.println();
		System.out.println("=== 관 리 자 모 드 종 료 ===");
	}

}
