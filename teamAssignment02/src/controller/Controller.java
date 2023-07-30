package controller;

import java.util.ArrayList;
import model.ProductDAO;
import model.ProductVO;
import view.AdminView;
import view.ClientView;

public class Controller {

	private ProductDAO pDAO;
	private ClientView cView;
	private AdminView aView;
	private final String stockChange = "재고변경"; // DAO의 기능선택용
	private final String stockCheck = "재고검사"; // DAO의 기능선택용

	public Controller() { // 기본생성자
		pDAO = new ProductDAO(); // 객체 생성
		cView = new ClientView(); // 객체 생성
		aView = new AdminView(); // 객체 생성
	}

	public void startApp() {
		while (true) { // 프로그램 종료할때까지 반복
			int select = cView.printClientMenu01().getNum(); // 목차 출력 및 선택값 저장
			ProductVO pVO = new ProductVO(0, null, 0, 0); // 변수 반환용 저장객체

			if (select == 1) {
				// 상품목록출력
				ArrayList<ProductVO> list = pDAO.selectAll(null); // 상품목록 불러오기
				if (list.size() == 0) {
					cView.printList(null); // 불러온 목록 출력					
				}
				cView.printList(list); // 불러온 목ㅎㅎ록 출력

			}

			else if (select == 2) { // 구매하기
				pVO.setName("구매"); // "구매" 저장
				cView.start(pVO); // 구매 시작 메세지 출력
				int num = cView.getNum().getNum(); // 상품 번호 받기
				pVO.setNum(num); // 상품 번호 저장

				if (pDAO.selectOne(pVO) == null) { // 해당상품이 존재하지 않는다면
					cView.noProduct(); // 상품이 없다는 메세지 출력
					continue; // 구매 실패
				}

				int sellStock = cView.getCnt().getCnt(); // 구매할 개수 정보 받기
				pVO.setCnt(-sellStock); // 개수 저장
				pVO.setName(stockCheck); // "재고검사" 저장
				int sellPrice = pDAO.update(pVO).getPrice(); // 재고 구매가능여부 확인
				pVO.setName("구매");

				if (sellPrice == 0) { // 재고가 부족할 경우
					cView.errorCnt(); // 재고 부족 메세지 출력
					cView.fail(pVO); // 구매 실패 메세지 출력
					continue; // 구매 실패
				}

				pVO.setPrice(sellPrice); // 가격정보 저장
				int result = cView.sellInfo(pVO).getNum(); // 가격 정보 출력 및 구매 여부 저장

				if (result == -1) { // 구매 취소일 경우
					continue; // 구매 취소
				}

				pVO.setName(stockChange); // "재고변경" 저장
				pDAO.update(pVO); // 재고 변경

			}

			else if (select == 3) { // 상품이름검색
				cView.getSearchNameStart(); // 상품 이름 검색 시작 msg
				String name = cView.getName().getName(); // 상품 이름 입력받기
				ProductVO content = new ProductVO(0, name, 0, 0);
				ArrayList<ProductVO> pdatas = pDAO.selectAll(content); // 상품이름 대조
				if (pdatas.size() == 0) {
					cView.printList(null); // 검색 해당 상품 출력	
					continue; // 출력완료
				}
				cView.printList(pdatas); // 검색 해당 상품 출력

			}

			else if (select == 4) { // 상품 범위 검색
				cView.getSearchFilterStart(); // 상품 범위 검색 시작 msg
				pVO.setName("최소");
				int minPrice = cView.getPrice(pVO).getPrice(); // 최소가격 입력받기
				pVO.setName("최대");
				pVO.setPrice(minPrice); // 최소값 저장
				int maxPrice = cView.getPrice(pVO).getPrice(); // 최대가격 입력받기
				pVO.setCnt(maxPrice); // 최대값 저장
				pVO.setNum(1); // 범위검색이라고 저장
				ArrayList<ProductVO> pdatas = pDAO.selectAll(pVO); // 검색 리스트 저장
				if (pdatas.size() == 0) {
					cView.printList(null); // 검색 해당 상품 출력	
					continue; // 출력 완료
				}
				cView.printList(pdatas); // 리스트 출력

			}

			else if (select == 5) { // 프로그램 종료
				cView.printClientEnd(); // 종료메세지 출력
				break; // 프로그램 종료

			}

			else if (select == 0) { // 관리자모드
				while (true) { // 관리자모드 종료할떄까지 반복
					pVO = new ProductVO(0, null, 0, 0); // 객체 생성
					select = aView.printAdminMenu().getNum(); // 관리자목차 출력 및 선택값 저장

					if (select == 1) { // 상품 추가
						pVO.setName("상품 추가");
						aView.start(pVO); // 시작 메세지 출력

						String name = aView.getName().getName(); // 상품명 받기
						int price = aView.getPrice().getPrice(); // 가격받기
						int stock = aView.getCnt().getCnt(); // 재고받기
						pVO.setName(name); // 이름 저장
						pVO.setPrice(price); // 가격 저장
						pVO.setCnt(stock); // 재고 저장

						ArrayList<ProductVO> list = pDAO.insert(pVO); // 상품 생성
						pVO.setNum(list.get(list.size() - 1).getNum()); // 추가된 상품 정보 저장
						pVO.setName(list.get(list.size() - 1).getName()); // 추가된 상품 정보 저장
						pVO.setPrice(list.get(list.size() - 1).getPrice()); // 추가된 상품 정보 저장
						pVO.setCnt(list.get(list.size() - 1).getCnt()); // 추가된 상품 정보 저장

						aView.printProduct(pVO); // 상품정보 출력

						pVO.setName("상품 추가");
						aView.suc(pVO); // 상품추가 성공 메세지 출력
					}

					else if (select == 2) { // 재고 변경
						pVO.setName("재고 변경");
						aView.start(pVO); // 재고 변경 시작 메세지 출력
						pVO = aView.getNum(); // 변경할 상품 번호 받아오기
						pVO = pDAO.selectOne(pVO); // 상품 존재 여부 받아오기

						if (pVO == null) { // 상품이 존재하지 않다면
							aView.noProduct(); // 상품이 존재하지 않습니다 출력
							continue; // 변경 실패
						}

						int changestock = aView.addCnt(pVO).getCnt(); // 추가 혹은 삭제할 재고 개수 받아오기
						pVO.setCnt(changestock); // 개수 저장
						pVO.setName(stockChange); // "재고변경" 저장
						ProductVO inputpVO = pDAO.update(pVO); // 재고변경 실행
						pVO.setNum(inputpVO.getNum()); // 추가된 상품 정보 저장
						pVO.setName(inputpVO.getName()); // 추가된 상품 정보 저장
						pVO.setPrice(inputpVO.getPrice()); // 추가된 상품 정보 저장
						pVO.setCnt(inputpVO.getCnt()); // 추가된 상품 정보 저장

						if (pVO.getCnt() == 0) { // 재고변경이 실패했다면
							pVO.setName("재고 변경");
							aView.fail(pVO); // 재고변경 실패 메세지 출력
						}

						aView.printProduct(pVO); // 재고 변경된 상품
						pVO.setName("재고 변경");
						aView.suc(pVO); // 변경 성공 메세지 출력

					}

					else if (select == 3) { // 상품 삭제
						pVO.setName("상품 삭제");
						aView.start(pVO); // 상품 삭제 시작 메세지 출력
						pVO = aView.getNum(); // 삭제할 상품번호 받아오기
						pVO = pDAO.selectOne(pVO); // 상품 있는지 확인
						if (pVO == null) { // 싱품이 없다면
							aView.noProduct(); // 상품이 없다고 출력
							continue; // 삭제 실패
						}
						ProductVO inputpVO = aView.deleteCheck(pVO); // 정말로 삭제할건지 재확인

						if (inputpVO.getNum() == 2) { // 삭제가 취소되었다면
							continue; // 삭제취소
						}
						pDAO.delete(pVO); // 이거 굳이 true false 리턴할 필요 없음 (미리 있는지 확인하고 삭제할거)
						pVO.setName("상품 삭제");
						aView.suc(pVO); // 삭제 성공 메세지 출력

					}

					else if (select == 4) { // 관리자모드 종료
						aView.printAdminEnd();
						break;
					}
				} // while(관리자모드)

			} // 관리자모드

		} // while

	} // startApp

} // class





/*   한글 코딩
package controller;

import model.ProductDAO;
import view.AdminView;
import view.ClientView;

public class Controller_KR {

	private ProductDAO pDAO;
	private ClientView cView;
	private AdminView aView;

	public Controller_KR() {
		pDAO = new ProductDAO();
		cView = new ClientView();
		aView = new AdminView();
	}

	public void startApp() {
		
		while () {
			메뉴 출력하고 메뉴 선택번호 받기
			
			if (1번) { // 상품목록출력
				DAO에서 상품 목록 정보 가져오기
				해당 목록정보를 출력하기 (cView)
				
			} else if (2번 구매하기) {
				cView에서 시작 메세지 출력
				cView에서 구매할 상품 번호를 받아오기
				해당 상품이 존재하는지 DAO에 확인하기
				만약 없다면? -> 상품이 없다고 출력하고 continue;
				몇개를 구매할건지 개수 받아오기 (cView)
				DAO한테 해당 개수가 있는지 여부 확인 및 총 가격이 얼마인지 받아오기
				재고가 부족하다면? -> 재고부족 출력, continue;
				가격 출력해서 살건지 물어보고 돈받기
				취소라면 continue;
				구매했다고 리턴이 오면
				DAO에다가 구매를 했으니 재고를 변경해
				
			} else if (3번 이름으로 검색) {
				cView에서 시작 메세지 출력
				cView한테 검색할 이름을 받아오기
				받아온 정보를 DAO한테 검색 시킴 -> 검색결과를 리턴
				cView 목록출력 -> 비었으면 null, 비어있지 않다면 목록을 input
				
			} else if (4번 가격범위검색) {
				cView에서 시작 메세지 출력
				cView한테 최소값을 받아오기
				해당 정보를 cView한테 주면서
				cView한테 최대값을 받아오기
				해당 정보들로 DAO한테 검색시키기 -> 배열 리턴
				cView 목록출력 -> 비었으면 null, 비어있지 않다면 목록을 input
				
			} else if (5번 프로그램 종료) {
				cView한테 종료메세지 출력
				break;
				
			} else if (0번 관리자모드) {
				while () {
					메뉴 출력하고 메뉴 선택번호 받기
					
					if (1번) { // 상품 추가
						aView에서 시작 메세지 출력
						aView에서 이름 받아오기
						aView에서 가격 받아오기
						aView에서 재고 받아오기
						pVO(0, name, price, cnt);
						해당 정보들을 가진 상품을 DAO에 생성해달라고 요청
						생성결과를 받아서 성공인지 실패인지 aView에서 출력
						
					} else if (2번 ) { // 재고 변경
						aView에서 시작 메세지 출력
						aView에서 번호를 받아오기
						DAO에 해당 상품이 있는지 확인받기
						상품이 없으면 없다고 출력하고 continue;
						aView 변경할 재고를 받아오기
						DAO한테 재고변경 지시
						변경된 상품정보를 출력하고
						변경 완료 메세지 출력
						
					} else if (3번 ) { // 상품 삭제
						aView에서 시작 메세지 출력
						aView에서 번호를 받아오기
						DAO에 해당 상품이 있는지 확인받기
						상품이 없으면 없다고 출력하고 continue;
						이 상품을 진짜로 삭제할건지 aView에서 다시 물어보기
						삭제 취소라면? -> 취소메세지 출력 continue;
						DAO에 삭제명령
						삭제 실패라면? -> 실패 메세지 출력 continue;
						삭제 성공이라면? -> 성공 메세지 출력
						
					} else if (4번 ) { // 관리자 모드 종료
						aView한테 종료메세지 출력
						break;
						
					} // 관리자 if
					
				} // 관리자 while
				
			} // 사용자 if
			
		} // 사용자 while

	} // startApp

}
*/
