package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.ProductVO;

public class ClientView {

	private static Scanner sc = new Scanner(System.in);
	private static final int maxMenuNum = 5; // 메뉴에서 항목이 5개라 maxMenuNum을 5로 잡는다

	private int tryCatch() {
		// :예외
		// 입력된 값이 정수면 그 값 그대로반환
		// 정수이외의 값이면 무한루프로 재입력
		while (true) {
			try {
				int action = sc.nextInt();
				return action;
			}

			catch (Exception e) {
				sc.nextLine();
				System.out.println("정수로 입력해주세요!");
				System.out.println("다시 입력해주세요");
			}
		}
	}

	private int ok() {
		// : 최종결정 확인
		// 구매와 같이 어떤 행동을 최종 결정하기 전에
		// 한번 더 물어봐준다.
		while (true) {
			try {
				int action = sc.nextInt();
				if (action <= 2 && action >= 1) { // 1번과 2번 값만 허용
					if (action == 2) {
						System.out.println("다시 입력해주세요!"); // 2번 입력시 다시 입력
					}
					return action; // 1번입력시 다음단계로

				}
				System.out.println("유효하지 않은 값입니다"); // 1번과 2번 이외의 번호 입력시
				System.out.println("다시 입력해주세요!"); // 다시 누를 수 있도록 돌아간다
			} catch (Exception e) {
				sc.nextLine();
				System.out.println("정수로 입력해주세요!"); // int 이외의 값 입력시 다시 입력
			}
		}
	}

	private int getClientMenuNum() {
		// : 메뉴 입력 번호
		// 클라이언트 메뉴에서 입력되는 번호
		while (true) {
			try {
//				System.out.print("입력) ");
				int menuNum = sc.nextInt();

				if (0 <= menuNum && menuNum <= maxMenuNum) { // 입력되는 값이 0이상이고 메뉴의 개수 이하일 때
					return menuNum; // 입력되는 값을 반환해준다.
				} // 이외의 값은 다시 입력하도록 한다.
				
				System.out.println("유효하지 않은 번호입니다");
				System.out.println("다시 입력해주세요");
			} catch (Exception e) {
				sc.nextLine();
				System.out.println("정수로 입력해주세요!");
			}
		}
	}

	public ProductVO printClientMenu01() {
		System.out.println();
		System.out.println("=== 프 로 그 램 모 드 ===");
		System.out.println("1. 상품목록출력");
		System.out.println("2. 구매하기");
		System.out.println("3. 상품 이름으로 검색");
		System.out.println("4. 상품 범위 검색");
		System.out.println("5. 프로그램 종료");
		System.out.print("입력) ");
		int menuNum = getClientMenuNum();
		ProductVO pVO = new ProductVO(menuNum, null, 0, 0);
		return pVO;
	}

	public void printList(ArrayList<ProductVO> datas) {
		// :상품 목록 출력
		// 현재 생성된 목록이 없을 경우
		// 상품 목록이 없다고 출력
		if (datas == null) {
			System.out.println("상품목록이 없습니다!");
			return; 
		}

		System.out.println("상품목록을 출력합니다!");
		for (ProductVO v : datas) {
			System.out.println(v.toString());
		}

		System.out.println("상품목록출력을 완료했습니다!");
	} // 상품 목록을 출력할 경우
		// 출력할때와 출력이 끝났을 때 안내멘트

	public ProductVO getName() { // : 이름 입력 받기
		while (true) { // 상품 이름 검색에서 사용
			System.out.print("상품이름: ");
			String name = sc.next();
			// 이름을 입력 받은 후
			// 입력받은 이름이 맞는지
			// ok() 모듈로 확인
			System.out.println("입력하신 이름이 맞으시면 1번 아니시면 2번을 눌러주세요!");
			int num = ok();
			if (num == 1) { // 1번을 입력할 경우 입력값 반환
				ProductVO pVO = new ProductVO(0, name, 0, 0); // 이외의 값은 재입력
				return pVO;
			}

		}
	}

	public ProductVO getCnt() { // : 재고 입력 받기
		int cnt; // 구매하기에서 사용
		while (true) {
			System.out.print("구매할 개수 입력) ");
			cnt = tryCatch(); // 입력받은 재고값이 정수이고
			if (cnt < 1) { // 1이상의 값만 입력되는지 확인
				System.out.println("유효한 개수를 입력해주세요!"); // 이외의 값은 재입력
				continue;
			}
			break;
		}
		ProductVO pVO = new ProductVO(0, null, 0, cnt);
		return pVO;
	}

	public ProductVO getPrice(ProductVO inputpVO) { // : 가격 입력 받기
		int price; // 상품 범위 검색에서 사용
		while (true) {
			System.out.print(inputpVO.getName() + "가격입력) ");
			price = tryCatch(); // 입력받은 가격이 정수이고
			if (price <= 0) { // 0보다 큰 값이 입력되야한다.
				System.out.println("유효하지 않은 가격입니다!"); // 이외의 값 입력시 재입력
				continue;
			}
			if (inputpVO.getName().equals("최대")) { // 최대값을 받을때
				if (price < inputpVO.getPrice()) {
					System.out.println("유효하지 않은 가격입니다!"); // 이외의 값 입력시 재입력
					System.out.println("최소가격 이상의 가격을 입력해 주세요!");
					continue;
				}
			}
			ProductVO pVO = new ProductVO(0, null, price, 0);
			return pVO;
		}
	}

	public ProductVO getNum() { // : 상품 번호 입력
		int num; // 구매하기에서 사용
		while (true) {
			System.out.print("상품번호입력) ");
			num = tryCatch(); // 입력받은 상품 번호가 정수이고
			if (num < 1001) { // 1001이상의 값이 입력되야한다.
				System.out.println("유효하지 않은 번호입니다!"); // 이외의 값 입력시 재입력
				continue;
			}
			ProductVO pVO = new ProductVO(num, null, 0, 0);
			return pVO;
		}
	}

	public ProductVO sellInfo(ProductVO inpVO) {
		// : 구매 정보
		// 구매하기 메뉴에서 재고까지 입력시
		// 총 금액을 안내해주고
		// 구매 확정에 대해서 ok() 모듈화 사용
		System.out.println("총 금액은 " + inpVO.getPrice() + "원 입니다.");
		System.out.println("구매 하시려면 1번을, 취소하시려면 2번을 눌러주세요.");
		int sellOk = ok();
		ProductVO pVO = new ProductVO(0, null, 0, 0);
		if (sellOk == 2) { // 1번 입력시 계산 단계로 넘어가고
			System.out.println("구매가 취소되었습니다."); // 2번 입력시 구매 취소
			pVO.setNum(-1);
			return pVO;
		}
		System.out.print("돈을 지불해주세요 : ");
		int input; // : 입력받은 돈
		while (true) {
			input = tryCatch(); // 입력받은 돈이 정수이며 내야할 돈보다 커야함
			if (input < inpVO.getPrice()) { // 내야할 돈 보다 적을 때에는 금액이 부족해서
				System.out.println("금액이 부족합니다. 다시입력해주세요"); // 다시 입력해야 한다고 안내
				continue;
			}
			break;
		}
		System.out.println("거스름돈은 " + (input - inpVO.getPrice()) + "원 입니다."); // 입력받은돈에서 총금액을 빼서
		System.out.println("구매가 완료되었습니다."); // 거스름돈 계산
		return pVO;
	}

	// : 상품 이름으로 검색 시작 msg
	public void getSearchNameStart() {
		System.out.println("이름으로 검색을 시작합니다!");
	}

	// : 상품 범위 검색 시작 msg
	public void getSearchFilterStart() {
		System.out.println("범위 검색을 시작합니다!");
	}

	// : 상품 범위 검색 가격입력
	public ProductVO getSearchFilter(ProductVO inpVO) { // 최대인지 최소인지 입력받기
		while (true) { // 예외처리 및 유효성 검사
			try { // 예외 처리
				System.out.print(inpVO.getName() + "가격을 입력해주세요 : ");
				int price = sc.nextInt(); // 가격 저장
				sc.nextLine(); // 버퍼처리
				if (price < 0) { // 유효성 검사
					System.out.println("가격이 유효하지 않습니다");
					continue; // 다시 입력
				}
				ProductVO pVO = new ProductVO(0, null, price, 0);
				return pVO; // 입력값 리턴
			} catch (InputMismatchException e) { // 자료형 검사
				sc.nextLine(); // 버퍼처리
				System.out.println("정수로 입력해주세요");
			}
		}
	}

	public void errorCnt() { // 재고 부족
		System.out.println("재고가 부족합니다!");
	}

	public void suc(ProductVO pVO) { // 결과 성공
		System.out.println(pVO.getName() + " 성공!");
	}

	public void fail(ProductVO pVO) { // 결과 실패
		System.out.println(pVO.getName() + " 실패!");
	}

	public void start(ProductVO pVO) { // 모든 실행 시작
		System.out.println(pVO.getName() + "를 진행합니다!");
	}

	public void noProduct() { // 생성된 상품이 없을 경우
		System.out.println("상품이 없습니다!"); // 상품이 없다고 출력
	}

	public void printClientEnd() { // 프로그램 종료
		System.out.println();
		System.out.println("=== 프 로 그 램 종 료 ===");
	}

}
