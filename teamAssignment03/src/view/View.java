package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.MemberVO;
import model.FilmVO;

public class View {
	private static Scanner sc = new Scanner(System.in);
	// 프로그램 메뉴의 항목 4개
	private static final int maxMenuNum1 = 4;
	// 사용자 메뉴의 항목 6개
	private static final int maxMenuNum2 = 6;

	
	//  =========================== 모듈화  ================================
	// try-catch 예외처리 모듈화
	private int tryCatch() {
		while (true) {
			try {
				// 입력된 값이 정수면 그대로 반환
				int action = sc.nextInt();
				return action;
			}
			// 정수 이외의 값이 들어오면 정수로 입력할 때까지 재입력 시키기
			catch (Exception e) {
				sc.nextLine();
				System.out.println("정수만 입력 가능합니다");
				System.out.println("다시 입력해주세요!");
			}
		}
	}

	// 최종 확인 모듈화
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
					// 1을 입력받았다면 입력받은 값 리턴해서 코드 계속 진행
					return action; 

				}
				// 입력받은 값이 1,2가 아니라면
				// 번호 재입력 시키기
				System.out.println("유효하지 않은 값입니다");
				System.out.println("다시 입력해주세요!"); 
				
			// 정수 이외의 값이 들어오면 재입력 시키기
			} catch (Exception e) {
				sc.nextLine();
				System.out.println("정수로 입력해주세요!"); 
			}
		}
	}

	// 메뉴 번호 받기 모듈화
	private int getMenuNum(int num) {
		while (true) {
			try {
				System.out.print("입력) ");
				int menuNum = sc.nextInt();

				// num이 1일때, 프로그램 메뉴 출력의 번호 입력 받기
				if (num == 1) {
					// 입력받은 값이 1 이상, 프로그램 메뉴 개수 이하일 때 (정상적인 입력값일 때)
					// 입력받은 값 리턴
					if (0 < menuNum && menuNum <= maxMenuNum1) {
						return menuNum;
					}
				}
				// num이 2일때, 사용자 메뉴 출력의 번호 입력 받기
				else if(num == 2) {
					// 입력받은 값이 1 이상, 사용자 메뉴 개수 이하일 때 (정상적인 입력값일 때)
					// 입력받은 값 리턴
					if (0 < menuNum && menuNum <= maxMenuNum2) {
						return menuNum;
					}
				}
				// 유효하지 않은 정수가 들어오면 재입력 시키기
				System.out.println("유효하지 않은 번호입니다");
				System.out.println("다시 입력해주세요!");
				
			// 정수 이외의 값이 들어오면 재입력 시키기
			} catch (Exception e) {
				sc.nextLine();
				System.out.println("정수로 입력해주세요!");
			}
		}
	}

	// ===================================================================
	

	// 프로그램 메뉴 출력 1 & 메뉴 번호 입력받기
	public int printClientMenu01() {
		System.out.println();
		System.out.println("=== 프 로 그 램 모 드 ===");
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		System.out.println("3. 비회원 예매");
		System.out.println("4. 프로그램 종료");
		int menuNum = getMenuNum(1);
		return menuNum;
	}

	// 아이디 입력
	// "회원가입", "로그인"에서 사용
	public MemberVO getId() {
		System.out.println();
		System.out.print("아이디 입력) ");
		String mid = sc.next();
		// 입력된 값을 mVO에 id를 저장하여 보내줌
		MemberVO mVO = new MemberVO(mid, null, null);
		return mVO;
	}

	// 비밀번호 입력
	// "회원가입", "로그인"에서 사용
	public MemberVO getPw() {
		System.out.print("비밀번호 입력) ");
		String mpw = sc.next();
		// 입력된 값을 mVO에 pw를 저장하여 보내줌
		MemberVO mVO = new MemberVO(null, mpw, null);
		return mVO;

	}

	// 이름 입력
	// "회원가입"에서 사용
	public MemberVO getName() {
		while (true) {
			System.out.print("이름 입력) ");
			String mname = sc.next();
			// 이름을 입력받고
			// 최종확인을 위해 ok() 모듈로 다시 한번 확인받기
			System.out.println("[" + mname + "]이(가) 맞다면 1번, 아니면 2번을 눌러주세요.");
			System.out.print("입력) ");
			int num = ok();
			if (num == 1) {	// 1번 선택시 최종 선택된 name
				// 유효한 값이 들어오면 mVO에 name을 저장하여 보내줌
				MemberVO mVO = new MemberVO(null, null, mname);
				return mVO;
			}
		}
	}

	// 로그아웃
	public void logout() {
		System.out.println();
		System.out.println("=== 로 그 아 웃 ===");
	}


	// ==================================================================

	// 사용자 메뉴 출력 2 & 메뉴 번호 입력받기
	// > 프로그램 로그인 후 나오는 메뉴
	public int printClientMenu02(MemberVO mVO) {
		System.out.println();
		System.out.println("=== 사 용 자 모 드 ===");
		System.out.println(" [" + mVO.getName() + "]님 환영합니다!");
		System.out.println("1. 영화 목록 출력");
		System.out.println("2. 인기순위 목록 출력");
		System.out.println("3. 예매하기");
		System.out.println("4. 검색하기");
		System.out.println("5. 마이페이지");
		System.out.println("6. 로그아웃");
		int menuNum = getMenuNum(2);
		return menuNum;
	}

	// 영화 목록 출력
	public void printMovieList(ArrayList<FilmVO> fdatas) {
		// fdatas의 배열이 비어있다면 출력할 영화 없음 출력
		if (fdatas.isEmpty()) {
			System.out.println();
			System.out.println("=== 출력할 영화가 없습니다 ===");
			return;
		}
		// fdatas에 저장된 영화가 있다면
		// for-each문으로 인기순위로 영화 목록 출력
		System.out.println();
		System.out.println("=== 영화 목록 출력 ===");
		for (FilmVO v : fdatas) {
			System.out.println(v.toString());
		}
		System.out.println();
		System.out.println("영화 목록 출력 완료!");
	}

	
	// 인기순위로 영화 목록 출력
	public void printMovieRankList(ArrayList<FilmVO> fdatas) {
		// fdatas의 배열이 비어있다면 출력할 영화 없음 출력
		if (fdatas.isEmpty()) {
			System.out.println();
			System.out.println("=== 출력할 영화가 없습니다 ===");
			return;
		}
		// fdatas에 저장된 영화가 있다면
		// for-each문으로 인기순위로 영화 목록 출력
		System.out.println();
		System.out.println("=== 인기순위로 영화 목록 출력 ===");
		for (FilmVO v : fdatas) {
			System.out.println(v.toString());
		}
		System.out.println();
		System.out.println("인기순위로 영화 목록 출력 완료!");
	}

	// 영화 번호 받기
	// "예매하기"에서 사용
	public FilmVO getFilmNum() {
		while (true) {
			System.out.println();
			System.out.print("예매하실 영화 번호 입력) ");
			// tryCatch로 입력값이 정수인지 확인하기
			int num = tryCatch();
			// PK 시작값인 1001 미만 값이 입력되면 유효하지 않은 번호 
			if (num < 1001) {
				System.out.println("유효하지 않은 번호입니다!");
				continue;
			}
			// 유효한 값이 들어오면 fVO에 num을 저장하여 보내줌
			FilmVO fVO = new FilmVO(num, null, null);
			return fVO;
		}
	}

	// 해당 번호의 영화가 없을 때
	// "예매하기"에서 사용
	public void printNoFilm() {
		System.out.println();
		System.out.println("해당 번호의 영화가 없습니다");
	}

	// 티켓 개수 입력 받기
	// "예매하기"에서 사용
	public FilmVO getTicket() {
		while (true) {
			System.out.print("예매하실 티켓 개수 입력) ");
			// tryCatch로 입력값이 정수인지 확인하기
			int ticket = tryCatch();
			// 티켓값이 음수라면 유효하지 않은 개수
			if (ticket < 1) {
				System.out.println("유효하지 않은 개수입니다!");
				continue;
			}
			// 유효한 값이 들어오면 fVO에 ticket을 저장하여 보내줌
			FilmVO fVO = new FilmVO(ticket, null, null);
			return fVO;
		}
	}


	// 영화 이름 받기
	// "검색하기"에서 사용
	public FilmVO getFilmName() {
		while (true) {
			System.out.println();
			System.out.print("검색하실 영화 이름 입력) ");
			String name = sc.next();
			// 이름을 입력받고
			// 최종확인을 위해 ok() 모듈로 다시 한번 확인받기
			System.out.println("검색하실 이름이 [" + name + "]이(가) 맞다면 1번, 아니면 2번을 눌러주세요.");
			System.out.print("입력) ");
			int num = ok();
			if (num == 1) {	// 1번 선택시 최종 선택된 name
				// 유효한 값이 들어오면 mVO에 name을 저장하여 보내줌
				FilmVO fVO = new FilmVO(0, name, null);
				return fVO;
			}
		}
	}

	
	// 비밀번호 변경
	// "마이페이지"에서 사용
	public MemberVO changePw() {
		System.out.print("변경하실 비밀번호 입력) ");
		String mpw=sc.next();
		// 입력된 값을 mVO에 mpw를 저장하여 보내줌
		MemberVO mVO = new MemberVO(null, mpw, null);
		return mVO;
	}
	
	// 사용자 정보 출력
	// "마이페이지"에서 사용
	public void printMember(MemberVO mVO) {
		System.out.println(mVO.toString());
	}


	// 모든 실행 시작
	public void start(MemberVO mVO) {
		System.out.println();
		System.out.println(mVO.getName() + "를 진행합니다!");
	}

	// 실행 성공 멘트
	public void success(MemberVO mVO) {
		System.out.println();
		System.out.println(mVO.getName() + " 성공!");
	}
	
	// 회원가입 성공 멘트
	public void signUpSuccess(MemberVO mVO) {
		System.out.println();
		System.out.println(mVO.getName() + "님 회원가입 성공!");
	}

	// 실행 실패 멘트
	public void fail(MemberVO mVO) {
		System.out.println();
		System.out.println(mVO.getName() + " 실패..");
	}

	// 프로그램 종료 멘트
	public void printClientEnd() {
		System.out.println();
		System.out.println("=== 프 로 그 램 종 료 ===");
	}

}
