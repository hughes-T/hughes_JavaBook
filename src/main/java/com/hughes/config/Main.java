package com.hughes.config;

import org.springframework.util.Assert;

import java.util.*;

/**
 * @author hughes-T
 * @since 2021/10/14 10:10
 */
public class Main {

    public static void main(String[] args) {
        List<String> cellNos = new ArrayList<>();
        cellNos.add("C01-01-01");
        cellNos.add("C02-01-01");
        cellNos.add("C02-07-01");
        cellNos.add("C02-01-01");
        cellNos.add("C02-01-08");
        cellNos.add("C02-05-07");
        cellNos.add("C02-05-04");
        cellNos.add("C03-01-01");
        cellNos.add("C01-01-01");
        String cellNo = "C02-05-05";
        String s = decideCell(cellNos, cellNo);
        Assert.isTrue(s.equals("C02-05-04"),"测试失败非预期的结果" + s);

    }

    private static String decideCell(List<String> cellNos, String cellNo){
        //如 C01-01-01	同排优先 - 层优先 - 行近优先
        int sheet = Integer.parseInt(cellNo.substring(1,3));
        int line = Integer.parseInt(cellNo.substring(4,6));
        int row = Integer.parseInt(cellNo.substring(7,9));
        //移除不同走廊
        if (sheet == 1 || sheet == 2){
            cellNos.removeIf(o -> Integer.parseInt(o.substring(1,3)) == 3 || Integer.parseInt(o.substring(1,3)) == 4);
        }else {
            cellNos.removeIf(o -> Integer.parseInt(o.substring(1,3)) == 1 || Integer.parseInt(o.substring(1,3)) == 2);
        }
        if (cellNos.isEmpty()){
            return null;
        }
        cellNos.sort((o1, o2) -> {
            int sheetCount1 = Integer.parseInt(o1.substring(1,3)) == sheet ? 1 : 2;
            int lineCount1 = Math.abs(Integer.parseInt(o1.substring(4,6)) - line);
            int rowCount1 = Math.abs(Integer.parseInt(o1.substring(7,9)) - row);
            int sheetCount2 = Integer.parseInt(o2.substring(1,3)) == sheet ? 1 : 2;
            int lineCount2 = Math.abs(Integer.parseInt(o2.substring(4,6)) - line);
            int rowCount2 = Math.abs(Integer.parseInt(o2.substring(7,9)) - row);

            if (sheetCount1 != sheetCount2){
                return sheetCount1 - sheetCount2;
            }
            if (lineCount1 != lineCount2){
                return lineCount1 - lineCount2;
            }
            return rowCount1 - rowCount2;
        });
        return cellNos.get(0);
    }

}
