package model;

public class MemberVO {
	private String mid; // PK
	// :사용자가 등록하기 때문에 중복 검사 필수
	private String mpw;
	private String name;

	// 생성자
	public MemberVO(String mid, String mpw, String name) {
		this.mid = mid;
		this.mpw = mpw;
		this.name = name;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMpw() {
		return mpw;
	}

	public void setMpw(String mpw) {
		this.mpw = mpw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		// 아이디가 같다면 같은 객체
		MemberVO mVO = (MemberVO) obj;
		if (this.mid.equals(mVO.mid)) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		//문구 고민
		return this.name +"님 아이디: " +this.mid;
	}

}
