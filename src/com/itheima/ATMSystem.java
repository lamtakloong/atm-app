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

    public static void showMain(ArrayList<Account> accounts) {
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
                    login(accounts, sc);
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
     * 完成用戶登錄
     *
     * @param accounts
     */
    private static void login(ArrayList<Account> accounts, Scanner sc) {
        // 必須系統存在賬長才可以登錄
        if (accounts.size() == 0) {
            //沒有任何賬戶
            System.out.println("當前系統中沒有任何賬戶，你需要先註冊賬戶");
            return; // 直接結束當前方法
        }
        //讓用戶鍵盤錄入卡號
        while (true) {
            System.out.println("請輸入登錄的卡號");
            String cardID = sc.next();
            // 根據卡號查詢賬戶對象
            Account acc = getAccountByCardID(cardID, accounts);
            // 判斷賬戶對象是否存在, 存在說明卡號沒問題
            if (acc != null) {
                // 讓用戶繼續輸入密碼
                System.out.println("請輸入登錄的密碼");
                String password = sc.next();
                // 判斷密碼是否正確
                if (acc.getPassword().equals(password)) {
                    // 密碼正確, 登錄成功
                    // 展示系統登錄後的操作界面
                    System.out.println("恭喜你, " + acc.getUserName() + "先生/女士進入糸統, 你的卡號是 " + acc.getCardID());
                    // 展示操作頁面
                    showUserCommand(sc, acc);
                    return; // 結束登錄方法
                }else {
                    System.out.println("你的密碼有誤，請確認! ");
                }
            }else {
                System.out.println("對不起, 不存在該卡號的賬戶");
            }
        }
    }

    private static void showUserCommand(Scanner sc, Account acc) {
        System.out.println("=============用戶操作首頁================");
        System.out.println("1. 查詢賬戶");
        System.out.println("2. 存款");
        System.out.println("3. 提款");
        System.out.println("4. 轉賬");
        System.out.println("5. 修改密碼");
        System.out.println("6. 退出");
        System.out.println("7. 注銷賬戶");
        while (true) {
            System.out.println("請輸入你的操作指令: ");
            int command = sc.nextInt();
            switch (command){
                case 1:
                    // 查詢賬戶
                    showAccount(acc);
                    break;
                case 2:
                    // 存款

                    break;
                case 3:
                    // 提款
                    break;
                case 4:
                    // 轉賬
                    break;
                case 5:
                    // 修改密碼
                    break;
                case 6:
                    // 退出
                    System.out.println("歡迎下次光臨!!");
                    return;
                case 7:
                    // 注銷賬戶
                    break;
            }
        }
    }

    private static void showAccount(Account acc) {
        System.out.println("==================當前賬戶詳情==================");
        System.out.println("卡號: " + acc.getCardID());
        System.out.println("姓名: " + acc.getUserName());
        System.out.println("餘額: " + acc.getMoney());
        System.out.println("限額: " + acc.getQuotaMoney());
        return;
    }

    /**
     * 用戶開戶功能
     *
     * @param accounts 賬戶集合對象
     */
    private static void register(ArrayList<Account> accounts, Scanner sc) {
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
            if (okPassword.equals(password)) {
                break;
            } else {
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

    public static String createCardID(ArrayList<Account> accounts) {
        while (true) {
            //生成8位隨機數字代表卡號
            String cardID = "";
            Random r = new Random();
            for (int i = 0; i < 8; i++) {
                cardID += r.nextInt(10);
            }

            //判斷有沒有重復卡號
            Account acc = getAccountByCardID(cardID, accounts);
            if (acc == null) {
                // 說明沒有重復卡號
                return cardID;
            }
        }
    }

    public static Account getAccountByCardID(String cardID, ArrayList<Account> accounts) {
        //根據卡號查詢賬號對象
        for (int i = 0; i < accounts.size(); i++) {
            Account acc = accounts.get(i);
            if (acc.getCardID().equals(cardID)) {
                return acc;
            }
        }
        return null; // 查無此賬戶，說明沒有重復此卡號
    }
}
