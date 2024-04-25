package shuhuai.algorithm.utils;

@SuppressWarnings({"unused"})
public class Printer<ElemType> {
    public void printArray(ElemType[] elems) {
        printArray(elems, 0, elems.length);
    }

    public void printArray(ElemType[] elems, int begin, int end) {
        for (int i = begin; i < end; i++) {
            System.out.print(elems[i].toString());
            System.out.print(" ");
        }
        System.out.println();
    }
}