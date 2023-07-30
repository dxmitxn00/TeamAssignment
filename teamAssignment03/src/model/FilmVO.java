package model;

public class FilmVO {
   private int num;
   private String name;
   private String odate;
   private int cnt;
   public FilmVO(int num,String name,String odate) {
      this.num=num;      // Pk
      this.name=name;      // 영화이름
      this.odate=odate;    // 개봉일
      this.cnt=0;         // 예매수
      System.out.println(" 로그: FilmVO(): 영화 생성");
   }
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
   public String getOdate() {
      return odate;
   }
   public void setOdate(String odate) {
      this.odate = odate;
   }
   public int getCnt() {
      return cnt;
   }
   public void setCnt(int cnt) {
      this.cnt = cnt;
   }
   @Override
   public String toString() {
      return this.num+". "+ this.name+"의 예매수: "+this.cnt+"\n개봉일 : "+ this.odate;
   }
}