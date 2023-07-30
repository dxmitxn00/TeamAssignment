package model;

import java.util.ArrayList;

public class ProductDAO {
	public ArrayList<ProductVO> datas;
	private static int PK = 1001; // PK값 1001부터 시작

	// 생성자
	public ProductDAO() {
		this.datas = new ArrayList<ProductVO>();
		this.datas.add(new ProductVO(PK++, "화분", 2000, 20));
		this.datas.add(new ProductVO(PK++, "화장지", 1800, 12));
		this.datas.add(new ProductVO(PK++, "물티슈", 1200, 44));
	}

	// C
	public ArrayList<ProductVO> insert(ProductVO pVO) {

		// : 상품 생성
		// 이름 가격 재고 입력받아 상품 등록
		// PK 자동으로 1씩 증가
		this.datas.add(new ProductVO(PK++, pVO.getName(), pVO.getPrice(), pVO.getCnt()));
		return datas;

	}

	// R
	public ArrayList<ProductVO> selectAll(ProductVO pVO) {

		// : 상품 전체 목록 출력
		// 사용자 입력 없음 > (pVO가 널이면)
		if (pVO == null) {
			return datas;
		}

		// :가격 2개 입력 받아 범위 검색(필터 검색)
		// 가격 입력 2개 > (pVO.getName()이 1이면 "필터검색 기능")
		// > (pVO.getPrice()와 pVO.getCnt()로 가격을 가져옴)
		// 필터 기능: num(PK)인자에 1
		else if (pVO.getNum() == 1) {
			ArrayList<ProductVO> mdatas = new ArrayList<ProductVO>();
			for (ProductVO data : datas) {
				// 가격 인자값보다 크거나 같고 재고인자값보다 작거나 같으면
				if (data.getPrice() >= pVO.getPrice() && data.getPrice() <= pVO.getCnt()) {
					mdatas.add(data); // 임시 데이터(검색결과)에 추가
				}
			}
			return mdatas;
		}

		// : 이름으로 검색
		// 이름 입력값 받음 > (pVO.getName()이 null이 아니면)
		else {
			ArrayList<ProductVO> mdatas = new ArrayList<ProductVO>();
			for (ProductVO data : datas) {
				// 이름 인자값이 포함되어 있으면
				if (data.getName().contains(pVO.getName())) {
					mdatas.add(data);
				}
			}
			return mdatas;
		}

	}

	// R
	public ProductVO selectOne(ProductVO pVO) {

		for (ProductVO data : datas) {
			if (data.getNum() == pVO.getNum()) {
				ProductVO mdatas = new ProductVO(data.getNum(), data.getName(), data.getPrice(), data.getCnt());
				return mdatas;
				// 실제 DB 데이터(==datas)를 전송 xxxxx
				// 새로 vo객체를 new(객체화)해서 전송 O
			}
		}
		System.out.println(" 로그: selectOne(): 해당상품없음");
		return null;
	}

	// U
	public ProductVO update(ProductVO pVO) {
		// : 구매하기(재고 변경과 유사)
		// 번호를 입력 받아서 해당 상품의 재고를 변경 > (pVO.getNum()으로 상품번호, pVO.getCnt 구매할 개수 가져옴)
		// 구매의 경우 -만 재고 변경은 모두 가능하기 때문에 연산이"+"임을 유의 해주세요

		// : 상품 재고 변경
		// 번호를 입력 받아서 해당 상품의 재고를 변경 > (pVO.getNum()으로 상품번호, pVO.getCnt 구매할 개수 가져옴)

		// 해당 상품의 번호를 입력 받음
		// 해당 상품 있는 지 확인
		// 1. 있다 > 가격 리턴
		// 2. 없다 > setPrice(0) 리턴 재고부족 리턴 --->pVO의 가격값 인자 0
		// ctrl한테 잔돈 입력 성공 했는지 입력받음
		// 재고를 실질적으로 변경
		// 변경 성공 여부 리턴
		// 1. 변경 성공 > 재고가 변경된 pVO 반환
		// 2. 변경 실패 > 재고 setCnt(0) 한뒤 반환 --->pVO의 재고값 인자 0

		if (pVO.getName().equals("재고검사")) {
			for (ProductVO data : datas) {
				if (data.getNum() == pVO.getNum()) {// 구매할때
					// 구매할때에는 절대값이랑 내 재고랑 비교
					int cnt = pVO.getCnt() * (-1); // 구매하려는 양
					if (cnt > data.getCnt()) {
						pVO.setPrice(0);
						System.out.println(" 로그: update(): 재고부족");
						return pVO;
					}
					// 재고 있을시(구매가능할시) 총 가격을 리턴
					int price = (data.getPrice()) * (pVO.getCnt() * -1);
					pVO.setPrice(price);
					return pVO;

				}
			}
			pVO.setPrice(0); // 해당 상품 없을시 가격 0 리턴
			System.out.println(" 로그: update(): 해당상품없음");
			return pVO;
		}

		// 돈 입력 성공 했을때
		// 주실때 PK, 이름은 "재고변경"
		if (pVO.getName().equals("재고변경")) {
			for (ProductVO data : datas) {
				if (data.getNum() == pVO.getNum()) {
					data.setCnt(data.getCnt() + pVO.getCnt());
//					System.out.println("data: " + data);
					return data; // 재고변경 성공시 해당 재고 리턴
				}
			}

			pVO.setCnt(0); // 재고변경 실패시 재고 0 리턴
			System.out.println(" 로그: update(): 재고 변경에 실패하였습니다");
			return pVO;

		}
		System.out.println(" 로그: update(): return null 인자값 확인해주세요");
		return null; // 재고검사도 재고변경도 아닌 경우 값에 혼동이 올까봐 null로 반환
		// return pVO; // 변경 용이하게 기존 pVO리턴 남겨놨습니다

//		둘이 같은 기능이므로 View로 안내만 다르게
//		(어떤 상품의 번호를 받아서 입력받은 만큼 제거)
//		단, 재고변경은 +가 되나 구매는 재고가 -만 됩니다
//		구매하기 후 장바구니(목록 출력) --- > 구매내역 출력으로 이해했습니다
	}

	// D
	public boolean delete(ProductVO pVO) {
		// : 상품삭제
		// 번호를 입력 받아서 해당 상품 삭제 > (pVO.getNum()으로 번호 가져옴)
		for (int i = 0; i < this.datas.size(); i++) {
			if (this.datas.get(i).getNum() == pVO.getNum()) {
				this.datas.remove(i);
				return true;
			}
		}

//		ArrayList<ProductVO> datas = new ArrayList<ProductVO>();
//		for (ProductVO data : datas) {
//			if (data.getNum() == pVO.getNum()) {
//				datas.remove(pVO.getNum());
//				return true;
//			}
//		}
		System.out.println("로그: model: ProductDAO: delete(): 삭제할 상품이 없습니다");
		return false;

	}

}
