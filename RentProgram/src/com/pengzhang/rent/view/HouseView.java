package com.pengzhang.rent.view;

import com.pengzhang.rent.domain.House;
import com.pengzhang.rent.service.HouseService;

import java.util.Scanner;

/**
 * @author zhang
 * @version 1.0
 */
public class HouseView {

    Scanner input = new Scanner(System.in);
    private boolean loop = true;//控制显示菜单
    private int key;//接收用户选择
    private HouseService houseService = new HouseService(10);//设置数组大小为10

    /**
     * 根据id修改房屋信息
     */
    public  void update(){
        System.out.println("========修改房屋信息=========");
        System.out.println("请选择待修改房屋编号(-1表示退出)");
        int choice=input.nextInt();
        if(choice==-1){
            System.out.println("========放弃修改房屋信息=========");
            return;
        }

        //根据输入的id编号得到updateId,查找对象
        House house=houseService.findById(choice);
        if(house==null){
            System.out.println("========修改房屋信息编号不存在=========");
            return;
        }
        System.out.println("姓名("+house.getName()+"):");
        String name=input.next();
        if(name!=null){
            house.setName(name);
        }
        System.out.println("电话("+house.getPhone()+"):");
        String phone=input.next();
        if(phone!=null){
            house.setPhone(phone);
        }
        System.out.println("地址("+house.getAddress()+"):");
        String address=input.next();
        if(address!=null){
            house.setAddress(address);
        }
        System.out.println("租金("+house.getRent()+"):");
        int newrent=input.nextInt();
        if(newrent!=house.getRent()){
            house.setRent(newrent);
        }
        System.out.println("状态("+house.getState()+"):");
        String newstate=input.next();
        if(newstate!=house.getState()){
            house.setState(newstate);
        }
        System.out.println("========修改房屋信息成功=========");
    }

    /**
     * 根据id查找房屋信息
     */
public void findHouse(){
    System.out.println("========查找房屋信息=========");
    System.out.println("请输入要查找的id");
    int findId=input.nextInt();
    //调用方法
    House house=houseService.findById(findId);
    if(house !=null){
        System.out.println(house);
    }else{
        System.out.println("========查找房屋信息id不存在=========");
    }
}

    /**
     * 完成退出确认
     **/
    public void exit() {
        System.out.println("请确认是否退出(y/n)");
        String choice=input.next();
        if("y".equals(choice)){
            loop=false;
        }
    }

    /**
     * 编写deleteHouse方法,接收输入的id,嗲用Service的del方法
     */
    public void delHouse() {
        System.out.println("==============删除房屋==============");
        System.out.println("请输入待删除房屋编号(-1退出)：");
        int delId = input.nextInt();
        if (delId == -1) {
            System.out.println("======放弃删除房屋信息======");
            return;
        }
        System.out.println("请确认是否删除(y/n),请小心选择");
        String choice = input.next();
        if ("y".equals(choice)) {//真的删除
            if (houseService.del(delId)) {
                System.out.println("删除房屋信息成功");
            } else {
                System.out.println("删除房屋信息失败");
            }
        } else {
            System.out.println("==============放弃删除房屋信息==============");
        }
    }

    /**
     * 编写addHouse()方法接收输入，创建House对象，调用add方法
     */
    public void addHouse() {
        System.out.println("==============添加房屋==============");
        System.out.println("姓名:");
        String name = input.next();
        System.out.println("电话:");
        String phone = input.next();
        System.out.println("地址:");
        String address = input.next();
        System.out.println("月租:");
        int rent = input.nextInt();
        System.out.println("状态:");
        String state = input.toString();
        //创建一个新的House对象，注意编号是系统分配，用户不能输入
        House newHouse = new House(0, name, phone, address, rent, state);
        if (houseService.add(newHouse)) {
            System.out.println("添加房屋成功");
        } else {
            System.out.println("添加失败");
        }
    }

    /**
     * 编写listHouses()界面显示房屋列表
     **/
    public void listHouses() {
        System.out.println("==============房屋列表==============");
        System.out.println("编号\t\t房主\t\t电话\t\t地址\t\t月租\t\t状态(未出租/已出租)");
        House[] houses = houseService.list();//得到房屋信息
        for (int i = 0; i < houses.length; i++) {
            if (houses[i] == null) {
                break;
            }
            System.out.println(houses[i]);
        }
        System.out.println("==============房屋列表显示完毕==============");
    }

    //显示主菜单
    public void mainMenu() {
        showmenu();
        do {
            key = input.nextInt();
            switch (key) {
                case 1:
                    addHouse();
                    break;
                case 2:
                   findHouse();
                    break;
                case 3:
                    delHouse();
                    break;
                case 4:
                    update();
                    break;
                case 5:
                    listHouses();
                    break;
                case 6:
                    exit();
                    break;
            }
        } while (loop);
    }

    public void showmenu() {
        System.out.println("==============房屋出租系统菜单==============");
        System.out.println("              1.新 增 房 源 信 息                 ");
        System.out.println("              2.查 找 房 屋 信 息                ");
        System.out.println("              3.删 除 房 屋 信 息                  ");
        System.out.println("              4.修 改 房 屋 信 息                  ");
        System.out.println("              5.显 示 房 屋 信 息                  ");
        System.out.println("              6.    退      出                     ");
        System.out.println("请输入您的选择(1-6)：");
    }
}
