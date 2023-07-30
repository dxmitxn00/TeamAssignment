package controller;

import java.util.ArrayList;

import model.FilmDAO;
import model.FilmVO;
import model.MemberDAO;
import model.MemberVO;
//import view.ClientView;
//import view.CommonView;
//import view.ProgramView;
import view.View;

public class Controller {

//	ClientView cView;
//	ProgramView pView;
//	CommonView comView;
	View View;
	MemberDAO mDAO;
	FilmDAO fDAO;

	public Controller() { // 생성자
//		pView = new ProgramView(); // 프로그램 view
//		cView = new ClientView(); // 사용자 view
//		comView = new CommonView(); // 공동 view
		View = new View();
		mDAO = new MemberDAO(); // 회원 DAO
		fDAO = new FilmDAO(); // 영화 DAO
	} // 생성자

	public void startApp() { // (메인 메서드)
		while (true) { // 프로그램 반복

			int select = View.printClientMenu01(); // 프로그램 메뉴 출력
			// 선택값 저장
			MemberVO member; // 로그인한 회원 정보 저장할 변수
			MemberVO mVO; // 임시 변수

			if (select == 1) { // 로그인

				mVO = new MemberVO(null, null, "로그인");
				View.start(mVO);
				String id = View.getId().getMid(); // id 입력
				String pw = View.getPw().getMpw(); // pw 입력
				mVO = new MemberVO(id, pw, null); // 로그인 시도하는 정보 저장

				member = mDAO.selectOne(mVO); // 로그인 결과 저장
				mVO.setName("로그인");
				if (member == null) { // 로그인 실패
					View.fail(mVO); // 로그인 실패 메세지 출력
					continue; // 목차로 돌아가기
				}
				View.success(mVO); // 로그인 성공 메세지 출력

				while (true) { // 사용자 모드 반복

					select = View.printClientMenu02(member); // 사용자 메뉴 출력
					ArrayList<FilmVO> fList; // 영화 목록 저장할 변수
					FilmVO fVO; // 예매할 영화 정보 저장할 변수

					if (select == 1) { // 영화 목록 출력
						fList = fDAO.selectAll(null); // 영화 목록 받기
						View.printMovieList(fList); // 가져온 영화 목록 출력

					} else if (select == 2) { // 인기순위 목록 출력
						fVO = new FilmVO(1, null, null); // 임시변수 생성
						fList = fDAO.selectAll(fVO); // 출력할 목록 저장
						View.printMovieRankList(fList); // 목록 출력

					} else if (select == 3) { // 예매하기
						mVO = new MemberVO(null, null, "예매"); // 예매 저장
						View.start(mVO); // 예매 시작 메세지 출력
						int num = View.getFilmNum().getNum(); // 예매할 영화 번호 저장
						fVO = new FilmVO(num, null, null); // 전달용 형태로 저장
						if (fDAO.selectOne(fVO) == null) { // 만약 해당 영화가 없다면
							View.printNoFilm(); // 영화가 없다고 메세지 출력
							continue; // 목차로
						}
						int cnt = View.getTicket().getNum(); // 구매할 매수 저장
						fVO.setCnt(cnt); // 입력타입으로 저장
						if (!fDAO.update(fVO)) { // 예매에 실패했다면
							View.fail(mVO); // 예메 실패 메세지 출력
							continue; // 목차로
						}
						View.success(mVO); // 예매 성공 메세지 출력

					} else if (select == 4) { // 검색하기
						String name = View.getFilmName().getName(); // 검색할 변수 받기
						fVO = new FilmVO(0, name, null); // 임시변수 생성
						mVO = new MemberVO(null, null, "검색");
						View.start(mVO); // 검색 시작 메세지 출력
						fList = fDAO.selectAll(fVO); // 새로운 목록에 저장
						if (fList.isEmpty()) { // 검색결과가 없다면
							View.fail(mVO); // 검색 실패 메세지 출력
							continue; // 검색 실패
						}
						View.printMovieList(fList); // 검색 결과 출력

					} else if (select == 5) { // 마이페이지
						mVO = new MemberVO(null, null, "마이페이지 로그인"); // 현제 로그인한 사용자의 id정보를 가진 객체 생성
						if (!member.getMpw().equals(View.getPw().getMpw())) {
							View.fail(mVO);
							continue;
						}
						View.printMember(member); // 현제 로그인한 사용자 정보 출력
						mVO.setMid(member.getMid());
						mVO.setMpw(View.changePw().getMpw()); // 변경할 비밀번호 저장
						mVO.setName("비밀번호 변경"); // 용도 저장
						if (!mDAO.update(mVO)) { // 비밀번호 실패라면
							View.fail(mVO); // 비밀번호 변경 실패 메세지 출력
						}
						View.success(mVO); // 비밀번호 변경 성공 메세지 출력

					} else if (select == 6) { // 로그아웃
						View.logout(); // 로그아웃 메세지 출력
						break; // 로그아웃
					}

				} // 사용자 모드 while

			} else if (select == 2) { // 회원 가입
				mVO = new MemberVO(null, null, "회원가입");
				View.start(mVO);
				String id = View.getId().getMid(); // id 입력
				String pw = View.getPw().getMpw(); // pw 입력
				String name = View.getName().getName(); // 이름 입력
				mVO = new MemberVO(id, pw, name); // 입력받은 정보 저장
				boolean flag = mDAO.insert(mVO); // 회원가입 시도 결과를 boolean으로 리턴받아서 저장
				if (flag) { // 성공했다면
					View.signUpSuccess(mVO); // 회원가입 성공 메세지 출력
					continue; // 처음으로
				}
				mVO.setName("회원가입"); // 이름을 회원가입으로 변경
				View.fail(mVO); // 회원가입 실패 메세지 출력

			} else if (select == 3) { // 비회원 예매
				mVO = new MemberVO(null, null, "비회원 예매");

				ArrayList<FilmVO> fList = fDAO.selectAll(null); // 영화 목록 받기
				View.printMovieList(fList); // 가져온 영화 목록 출력

				View.start(mVO); // 예매 시작 메세지 출력
				int num = View.getFilmNum().getNum(); // 예매할 영화 번호 저장
				FilmVO fVO = new FilmVO(num, null, null); // 전달용 형태로 저장
				fDAO.selectOne(fVO); // 영화 검색
				if (fDAO.selectOne(fVO) == null) { // 만약 해당 영화가 없다면
					View.printNoFilm(); // 영화가 없다고 메세지 출력
					continue; // 목차로
				}
				int cnt = View.getTicket().getNum(); // 구매할 매수 저장
				fVO.setCnt(cnt); // 입력타입으로 저장
				if (!fDAO.update(fVO)) { // 예매에 실패했다면
					View.fail(mVO); // 예메 실패 메세지 출력
					continue; // 목차로
				}
				View.success(mVO); // 예매 성공 메세지 출력

			} else if (select == 4) { // 프로그램 종료
				View.printClientEnd(); // 프로그램 종료 안내메세지 출력
				break; // 프로그램 종료
			}

		} // 로그인 while

	} // startApp

} // class
