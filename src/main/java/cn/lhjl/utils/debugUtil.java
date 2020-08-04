package cn.lhjl.utils;

public class debugUtil {
    private int i;
    private boolean flag = true;

    public void showForDebug(String tip) {
        if (flag) {
            System.out.println(tip);
        }
    }

}
