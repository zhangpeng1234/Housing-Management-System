package com.pengzhang.rent.service;

import com.pengzhang.rent.domain.House;

/**
 * @author zhang
 * @version 1.0
 * 业务层，定义House[],保存House对象
 * 响应HouseView的调用
 * 完成对房屋信息的各种操作(增删改查)
 */
public class HouseService {
    private House[] houses;//创建House数组，保存House数据
    private int houseNums;//记录当前有多少个房屋信息
    private int idCounter;//记录当前id增长到那个值了

    //构造器
    public HouseService(int size) {
        houses = new House[size];//当创建HouseService对象，指定数组大小
    }

    /**find方法，返回House对象或者null**/
    public House findById(int findId) {
        //遍历数组
        for (int i = 0; i <houseNums ; i++) {
            if(findId==houses[i].getId()){
                return  houses[i];
            }
        }
        return null;
    }

    /**
     * del方法，删除一个房屋信息
     **/
    public boolean del(int delId) {
        //应当先找到要删除的房屋信息对应的下标，
        // 经过增加删除以后 ，下标和id不是一回事
        int index = -1;
        for (int i = 0; i < houseNums; i++) {
            if (delId == houses[i].getId()) {//要删除的房屋(id)，是数组下标为i的元素
                index = i;
            }
        }
        if (index == -1) {//说明delId在数组中不存在(有点绕)
            return false;
        }
        //如果找到
        for (int i = index; i < houseNums - 1; i++) {
            houses[i] = houses[i + 1];
            houses[--houseNums] = null;
        }
        return true;
    }

    /**
     * add方法，添加新对象，返回boolean
     **/
    public boolean add(House newHouse) {
        //判断是否可以继续添加(暂时不考虑数组扩容问题)
        if (houseNums == houses.length) {//不能再添加
            System.out.println("数组已满，不能再添加了....");
            return false;
        }
        //把newHouse对象加入
        houses[houseNums++] = newHouse;
        //我们程序员需要设计一个id自增长的机制
        idCounter++;
        newHouse.setId(idCounter);
        return true;
    }

    /**
     * list方法，返回houses
     **/
    public House[] list() {
        return houses;
    }
}
