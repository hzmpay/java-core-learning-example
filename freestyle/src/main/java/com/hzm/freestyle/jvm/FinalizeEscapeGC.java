package com.hzm.freestyle.jvm;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年07月27日
 */
public class FinalizeEscapeGC {

    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive() {
        System.out.println("yes,i am still alive:)");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize mehtod executed!");
        Thread.sleep(1000);
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC();
        // 对象第一次成功拯救自己
        gc();
        // 下面这段代码与上面的完全相同,但是这次自救却失败了
        gc();
    }

    private static void gc() throws InterruptedException {
        SAVE_HOOK = null;
        System.gc();
        // 因为finalize方法优先级很低,所以暂停0.5秒以等待它
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no,i am dead:(");
        }
    }
}
