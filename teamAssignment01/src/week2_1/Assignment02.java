package week2_1;

import java.util.Scanner;

// 관리자 모드 비번 : 1234

// 학생부 클래스 선언
class Student1 {
   int num; // PK 학생번호(인덱스아님)
   String name;
   int score;

   // 생성자
   Student1(int num, String name, int score) {
      this.num = num;
      this.name = name;
      this.score = score;
      System.out.println(num + "번 " + name + "학생이 저장되었습니다!");
   }
      
   // 학생 목록 출력 메서드
   void printList() {
      System.out.println(num+"번 학생 "+" | "+ this.name + " [" + this.score + "점]");
   }
   
   // 학생 점수 변경
   void setScore(int score) {
      this.score = score;
      System.out.println( this.name + " 학생의 점수가 " + this.score + "점으로 업데이트 되었습니다!\n");
   }
}

public class Assignment02 {
   public static void main(String[] args) {

      Scanner sc = new Scanner(System.in);

      Student1[] data = new Student1[3]; // 학생 배열 객체 생성, 최대 3명

      // 사용자 메인화면
      String userMain = "=== 학생부 프로그램 ===\n"
            + "1. 학생목록 출력\n"
            + "2. 학생 1명 출력\n"
            + "3. 프로그램 종료\n"
            + "================\n "
            + "\n"
            + "번호 입력 : ";

      // 관리자 메인화면
      String managerMain = "=== 관리자 모드 ===\n"
            + "1. 학생 생성\n"
            + "2. 학생 점수 변경\n"
            + "3. 관리자 모드 종료\n"
            + "4. 프로그램 종료\n"
            + "==============\n "
            + "\n"
            + "번호 입력 : ";


      String name = ""; // 학생이름 받을 변수
      int score = 0; // 학생점수 받을 변수
      int index = 0; // 학생목록에 저장되어있는 학생의 수
      
      boolean flag = false;

      while (true) {

         System.out.println();
         System.out.print(userMain); // 사용자 메인화면 출력
         int action = sc.nextInt(); // 메인화면의 번호 입력받음

         // 3) 프로그램 종료
         if (action == 3) { 
            System.out.println("프로그램을 종료합니다...");
            flag = true;
            break;
         }

         // 1) 학생부 목록 출력
         else if (action == 1) { 
            if(index <= 0) { // 유효성 검사
               System.out.println("저장된 학생이 없습니다!");
               continue;
            }
            System.out.println("\n=== 학생부 목록 ===");
            for (int i = 0; i < index; i++) {// 학생배열에 저장되어있는 학생만큼화면에 출력
               data[i].printList();
            }
            System.out.println("===================");

         }

         // 2) 학생 1명 출력
         else if (action == 2) { 
            if(index <= 0) { // 유효성 검사
               // 학생 생성이 안되어있으면
               System.out.println("저장된 학생이 없습니다!");
               continue;
            }

            System.out.println("출력하고 싶은 학생의 번호를 입력하세요.");
            System.out.print("번호 입력 : ");
            
            int num = sc.nextInt(); // 출력할 학생 번호 입력

            if (num < 1 || num > index) { // 유효성 검사
               // 출력할 학생의 번호가 저장되어있는 학생 수 보다 크거나 음수이면
               System.out.println("해당 번호의 학생이 존재하지 않습니다!");
               continue;
            }
//            System.out.print(num + "번 학생");
            data[num - 1].printList();
         }

         else if (action == 1234) { // 관리자 모드로 이동
            System.out.println("관리자 모드로 이동합니다.");
            while (true) {
               System.out.println();
               System.out.print(managerMain);

               action = sc.nextInt(); // 관리자 메인화면의 번호 입력받음

               // 3) 관리자 모드 종료
               if (action == 3) {                
                  System.out.println("관리자 모드를 종료하겠습니다.");
                  System.out.println("사용자 모드로 이동합니다.");
                  break; // 사용자 모드로 이동
               }
               // 1) 학생 생성
               else if (action == 1) { 
                  if (index >= data.length) { // 유효성 검사
                     // 학생 배열의 이상으로 입력받으면
                     System.out.println("정원이 초과되었습니다.");
                  }
                  while (index < data.length) {
                     while (true) {
                        System.out.println((index + 1) + "번 학생의 이름을 입력해주세요.");
                        System.out.print("이름 입력 : ");
                        name = sc.next();

                        System.out.println("'"+ name + "' 학생을 등록하시겠습니까?");
                        System.out.println("1) 네   2) 아니오");
                        System.out.print("번호 입력 : ");
                        action = sc.nextInt();

                        if (action != 1) { // 유효성 검사
                           // 아니오 또는 잘못된 입력이면
                           System.out.println("다시 입력해주세요!");
                           continue;
                        }
                        break;
                     }

                     while (true) {
                        System.out.println("'"+ name + "' 학생의 점수를 입력해주세요.");
                        System.out.print("점수 입력 : ");
                        score = sc.nextInt();

                        if (score < 0 || score > 100) { // 유효성 검사
                           // 점수의 범위를 벗어난 입력이면
                           System.out.println("0 ~ 100점으로 입력해주세요!");
                           continue;
                        }
                        break;
                     }
                     // 학생 배열 객체에 저장
                     data[index++] = new Student1(index, name, score);
                     break;
                  }

               }
               // 2) 학생 점수 변경
               else if (action == 2) {

                  if (index <= 0) { // 유효성 검사
                     // 학생이 생성이 안되어있으면
                     System.out.println("저장된 학생이 없습니다!");
                     continue;
                  }

                  while(true) {
                     System.out.println("변경할 학생의 번호를 입력해주세요 : ");
                     int num = (sc.nextInt()) - 1;

                     if (num >= 0 && num < index) {
                        // 입력받은 학생 번호가 학생 생성된 인덱스 중에 있으면 점수 입력
                        while (true) {
                           System.out.println("몇 점으로 수정하시겠습니까?");
                           System.out.print("점수 입력 : ");

                           score = sc.nextInt();

                           if (score > 100 || score < 0) { // 유효성 검사
                              // 입력받은 점수가 100이상이거나 음수이면
                              System.out.println("0 ~ 100점으로 입력해주세요!");
                              continue;
                           }
                           else {
                              break;
                           }

                        }
                        data[num].setScore(score);
                        break;
                     }
                     else { // 유효성 검사
                        // 입력받은 학생 번호가 학생 생성된 인덱스 중에 없으면
                        System.out.println("해당 번호의 학생이 존재하지 않습니다!");
                        System.out.println("다시 입력해주세요!");
                        continue;
                     }
                  }
               }
               // 4) 프로그램 완전 종료
               else if(action == 4) {
                  System.out.println("프로그램을 종료합니다...");
                  flag = true;
                  break;
               }
               else { // 유효성 검사
                  // 관리자 메인화면의 번호를 잘못 입력했으면
                  System.out.println("잘못 입력하셨습니다!");
                  System.out.println("다시 입력해주세요!");
               }

            }
         }
         else { // 유효성 검사
            // 사용자 메인화면의 번호를 잘못 입력했으면
            System.out.println("잘못 입력하셨습니다!");
            System.out.println("다시 입력해주세요!");
         }
         if(flag) {
            break; // 프로그램 완전 종료
         }
      }
   }
}