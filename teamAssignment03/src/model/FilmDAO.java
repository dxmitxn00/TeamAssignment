package model;

import java.util.ArrayList;
//import java.util.Collections;

public class FilmDAO {
	private ArrayList<FilmVO> datas;
	//	private static int PK=1001;	// num
	//	private static final Crawling craw = new Crawling(); // Crawling 클래스의 객체 craw 생성 - 굳이 필요 x
	public FilmDAO() {				
		this.datas=Crawling.sample();	
		// sample()이라는 메서드를 '객체와 무관하게', 클래스에서 사용
	}

	public ArrayList<FilmVO> selectAll(FilmVO fVO){
		//==============================================================================
		// 영화 목록출력
		// fVO입력값이 null일경우
		// 영화 목록을 출력한다.
		if(fVO==null) {	
			return datas;
		}
		//==============================================================================
		// 인기순 영화 출력

		else if(fVO.getNum()==1) { 
			ArrayList<FilmVO> cdatas = new ArrayList<FilmVO>();
			for(FilmVO data : datas) {
				FilmVO fArg = new FilmVO(data.getNum(), data.getName(), data.getOdate());
				fArg.setCnt(data.getCnt());
				cdatas.add(fArg);
			}
			for (int i=1; i<cdatas.size(); i++) { // 배열 크기만큼 반복
				FilmVO tmp = new FilmVO(0, null, null); // 임시 배열리스트 선언
				tmp.setNum(cdatas.get(i).getNum()); // 임시 배열리스트에 pk값 넣어줌
				tmp.setName(cdatas.get(i).getName()); // 임시 배열리스트에 이름값 넣어줌 
				tmp.setOdate(cdatas.get(i).getOdate()); // 임시 배열리스트에 개봉일값 넣어줌 
				tmp.setCnt(cdatas.get(i).getCnt()); // 임시 배열리스트에 예매수값 넣어줌
				int aux = i-1; // 비교대상 지정용 보조 변수

				while (true) { // 조건을 만족할때까지 무한반복
					if (aux < 0) { // 비교대상이 없을 경우
						break; // 반복 종료
					} else if (cdatas.get(aux).getCnt() < tmp.getCnt()) { // 데이터 예매수가 임시 예매수보다 작을 때
						cdatas.get(aux+1).setNum(cdatas.get(aux).getNum()); // 비교대상을 한자리 뒤로 이동(pk)
						cdatas.get(aux+1).setName(cdatas.get(aux).getName()); // 비교대상을 한자리 뒤로 이동(이름) 
						cdatas.get(aux+1).setOdate(cdatas.get(aux).getOdate()); // 비교대상을 한자리 뒤로 이동(개봉일) 
						cdatas.get(aux+1).setCnt(cdatas.get(aux).getCnt()); // 비교대상을 한자리 뒤로 이동(예매수)
						aux--; // 카운트 감소 = 다음 비교대상의 인덱스
					} else { // 비교대상은 더 있지만 정렬이 되어있어서 교체가 필요하지 않은 경우
						break; // 반복 종료
					} // if 조건식

				} // while
				// 해당 위치에 배열
				cdatas.get(aux+1).setNum(tmp.getNum()); 
				cdatas.get(aux+1).setName(tmp.getName()); 
				cdatas.get(aux+1).setOdate(tmp.getOdate()); 
				cdatas.get(aux+1).setCnt(tmp.getCnt()); 
			} // for
			return cdatas;
		}
		//==============================================================================
		// 이름검색
		// 입력받은 이름 값을 fVO.getName으로 받음
		// fVo.getName을 포함하는 영화들을 cdatas에 추가
		// cdatas를 return
		else {	
			ArrayList<FilmVO> cdatas = new ArrayList<FilmVO>();	
			for (FilmVO data : datas) {
				// 이름 인자값이 포함되어 있으면
				if (data.getName().contains(fVO.getName())) {
					cdatas.add(data);
				}
			}
			return cdatas;
		}	

	}

	//==============================================================================
	// 영화의 존재유무 확인
	// 입력한 fVO 값의 Num과 
	// datas의 Num과 일치하는지 확인
	// 존재하면 fVO 반환
	// 존재하지 않으면 null 반환
	public FilmVO selectOne(FilmVO fVO) {
		for (FilmVO data : datas) {
			if (data.getNum() == fVO.getNum()) {
				return data;
			}
		}
		System.out.println(" 로그: FilmDAO: selectOne: 해당 영화가 존재하지 않습니다.");
		return null;
	}
	// 사용하지 않는 기능
	public boolean insert(FilmVO fVO) {
		return false;
	}
	//==============================================================================
	// 예매하기
	// 예매할 영화 Pk은 fVO.getNum()으로 입력받고
	// 예매할 영화 예매티켓수는 fVO.getCnt()로 입력받는다
	// fVO.getNum()과 일치하는 영화를 찾아서
	// 일치하는 영화의 예매수에 fVO.getCnt만큼 더해준다.
	public boolean update(FilmVO fVO) {	//예매할 영화 Pk,티켓 수 입력
		for (FilmVO data : datas) {
			if (data.getNum() == fVO.getNum()) {	// Pk값이 일치하는 영화 찾기
				data.setCnt(data.getCnt()+fVO.getCnt()); //예매할 수만큼 예매수 증가
				return true; 
			}
		}		
		System.out.println(" 로그: update(): 예매에 실패하였습니다");
		return false;

	}
	// 사용하지 않는 기능
	public boolean delete(FilmVO fVO) {
		return false;
	}
}
