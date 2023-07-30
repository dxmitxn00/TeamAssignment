package model;

import java.util.ArrayList;

//회원목록출력
//회원가입,로그인,로그아웃,마이페이지,회원탈퇴
public class MemberDAO {
	private ArrayList<MemberVO> datas;

	// 생성자
	public MemberDAO() {
		datas = new ArrayList<MemberVO>();
		// 테스트 데이터
		datas.add(new MemberVO("admin", "1234", "관리자"));
		datas.add(new MemberVO("coding_helper", "timo", "작은 티모"));
	}

//===============================================================================================	

	// C:
	// 회원가입
	public boolean insert(MemberVO mVO) {
		boolean flag = false;
		for (MemberVO data : datas) {
			if (data.equals(mVO)) { // PK인 아이디가 같다면 아이디 중복 ---> 가입불가
				flag = true;
				System.out.println(" 로그: MemberDAO: insert(): 아이디 중복");
				break;
			}
		}
		if (flag) { // 아이디 중복일 경우 flag=true 되어 가입 실패
			return false;
		}
		// 아이디 중복 아닐 경우 회원가입 insert
		datas.add(new MemberVO(mVO.getMid(), mVO.getMpw(), mVO.getName()));
		/*
		 * datas.add(new MemberVO()); -> datas DB공간에 new 새로운 데이터를 추가
		 * MemberVO(데이터1,데이터2,데이터3) -> 데이터들을 mVO에서 추출하면됨
		 */
		return true;

		// 1. mVO가 의미하는것 == 회원가입할 정보
		// 2. mVO를내가 가지고 있는 datas에 넣음
		// 3. PK이기 때문에 중복검사하고 성공시 add 실패시 반환
	}

//===============================================================================================		
	// R:
	// 로그인
	public MemberVO selectOne(MemberVO mVO) {
		for (MemberVO v : datas) {
			if (mVO.getMid().equals(v.getMid())) { // 아이디 일치
				if (mVO.getMpw().equals(v.getMpw())) { // 비밀번호 일치
					return v; // 로그인 성공
				}
				System.out.println("로그: MemberDAO: selectOne(): 비밀번호가 일치하지 않습니다");
				return null;
			}
		}
		System.out.println("로그: MemberDAO: selectOne(): 해당 아이디가 없습니다");
		return null;
	}
//===============================================================================================		

	// R:
	// 현재 해당 사항 없음
	public ArrayList<MemberVO> selectAll(MemberVO mVO) {
		return null;
		// return datas;
	}

//===============================================================================================	

	// U:
	// 회원 정보 변경
	public boolean update(MemberVO mVO) { //바꿀 비밀번호 pw자리에 받아옴

		// 회원 비밀번호 변경
		for (MemberVO data : datas) {
			if (data.getMid().equals(mVO.getMid())) { // 회원 아이디와 입력받은 아이디가 같다면
				data.setMpw(mVO.getMpw()); // 비밀번호 저장
				return true;
			}
		}
		System.out.println(" 로그: MemberDAO: update(): 해당 회원이 없습니다");
		return false;
	}

//===============================================================================================	

	// D:
	// 회원탈퇴
	public boolean delete(MemberVO mVO) {

		for (int i = 0; i < datas.size(); i++) {
			if (datas.get(i).equals(mVO)) {
				// PK값이 같다면 동일한 객체 ---> 오버라이딩
				datas.remove(i);
				return true;
			}
		}

		System.out.println(" 로그: MemberDAO: delete(): 해당 회원이 없습니다");
		return false;
	}

}
