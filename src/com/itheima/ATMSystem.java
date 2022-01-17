package com.itheima;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ATMSystem {
    public static void main(String[] args) {
        // 1. 定義系統需要存儲的對象，用於存儲賬戶對象
        ArrayList<Account> accounts = new ArrayList<>();
        // 2. 準備系統的首頁, 登錄, 開戶
        showMain(accounts);
    }

    public static void showMain(ArrayList<Account> accounts){
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("=============歡迎進入首頁================");
            System.out.println("請輸入你想進行的操作：");
            System.out.println("1. 登錄");
            System.out.println("2. 開戶");
            System.out.println("你可以輸入指令了: ");
            int command = sc.nextInt();
            switch (command) {
                case 1:
                    //登錄
                    break;
                case 2:
                    //開戶
                    register(accounts, sc);
                    break;
                default:
                    System.out.println("你當前輸入指令不能支援操作");
            }
        }
    }

    /**
     * 用戶開戶功能
     * @param accounts 賬戶集合對象
     */
    private static void register(ArrayList<Account> accounts,Scanner sc) {
        System.out.println("=============歡迎進入開戶================");
        // 2. 鍵盤輸入姓名、密碼、確認密碼
        System.out.println("請輸入你的開戶名");
        String name = sc.next();

        String password = "";
        while (true) {
            System.out.println("請輸入你的開戶密碼");
            password = sc.next();
            System.out.println("請輸入你的確認密碼");
            String okPassword = sc.next();
            if(okPassword.equals(password)) {
                break;
            }else {
                System.out.println("兩次密碼必須一致");
            }
        }
        System.out.println("請你輸入當次限額");
        double quotaMoney = sc.nextDouble();

        // 3. 生作卡號, 卡號為8位, 而且不能和其他卡號重復
        String cardID = createCardID(accounts);

        // 4. 創造一個賬戶對象封裝賬號的信息
        // public
        Account account = new Account(cardID, name, password, quotaMoney);

        // 5/ 把賬號對象添加到集合中
        accounts.add(account);
        System.out.println("恭喜你，成功開戶，你的卡號是: " + account.getCardID() + ", 請你妥善保管 ");
    }
    public static String createCardID(ArrayList<Account> accounts){
        while (true) {
            //生成8位隨機數字代表卡號
            String cardID = "";
            Random r = new Random();
            for (int i = 0; i < 8; i++) {
                cardID += r.nextInt(10);
            }

            //判斷有沒有重復卡號
            Account acc = getAccountByCardID(cardID, accounts);
            if (acc == null){
                // 說明沒有重復卡號
                return cardID;
            }
        }
    }

    public static Account getAccountByCardID(String cardID, ArrayList<Account> accounts){
        //根據卡號查詢賬號對象
        for (int i = 0; i < accounts.size(); i++) {
            Account acc = accounts.get(i);
            if (acc.getCardID().equals(cardID)){
                return acc;
            }
        }
        return null; // 查無此賬戶，說明沒有重復此卡號
    }
}
