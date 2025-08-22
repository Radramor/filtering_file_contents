package base;

import statistics.FloatStats;
import statistics.IntStats;
import statistics.StringStats;

public class StatsPrinter {
    public static void printAll(Config cfg, IntStats is, FloatStats fs, StringStats ss) {
        if (cfg.statMode() == Config.StatMode.SHORT) {
            System.out.println("Целые числа: Количество = " + is.getCount());
            System.out.println("Вещественные числа: Количество = " + fs.getCount());
            System.out.println("Строки: Количество = " + ss.getCount());
        } else {
            System.out.println("Целые числа: Количество = " + is.getCount() +
                    ", мин=" + is.getMin() + ", макс=" + is.getMax() +
                    ", сум=" + is.getSum() + ", сред=" + is.getAverage());
            System.out.println("Вещественные числа: Количество = " + fs.getCount() +
                    ", мин=" + fs.getMin() + ", макс=" + fs.getMax() +
                    ", сум=" + fs.getSum() + ", сред=" + fs.getAverage());
            System.out.println("Строки: Количество = " + ss.getCount() +
                    ", минДлина=" + ss.getMinLen() + ", максДлина=" + ss.getMaxLen());
        }
    }
}
