package com.gk.study.entity;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 协同过滤推荐算法
 * 参考资料：https://blog.csdn.net/net19880504/article/details/137773127
 */

public class Recommend {
    private Map<Double, String> computeNearestNeighbor(String ip, List<UserCF> users) {
        Map<Double, String> distances = new TreeMap<>();

        UserCF u1 = new UserCF(ip);
        for (UserCF user : users) {
            if (ip.equals(user.ip)) {
                u1 = user;
            }
        }

        for (int i = 0; i < users.size(); i++) {
            UserCF u2 = users.get(i);

            if (!u2.ip.equals(ip)) {
                double distance = pearson_dis(u2.recEntityList, u1.recEntityList);
                distances.put(distance, u2.ip);
            }

        }
//        System.out.println("该用户与其他用户的皮尔森相关系数 -> " + distances);
        return distances;
    }


    /**
     * 计算2个打分序列间的pearson距离
     * 选择公式四进行计算
     *
     * @param rating1
     * @param rating2
     * @return
     */
    private double pearson_dis(List<RecEntity> rating1, List<RecEntity> rating2) {
        int n = Math.min(rating1.size(), rating2.size());
        List<Integer> rating1ScoreCollect = rating1.stream().map(A -> A.score).collect(Collectors.toList());
        List<Integer> rating2ScoreCollect = rating2.stream().map(A -> A.score).collect(Collectors.toList());

        double Ex = rating1ScoreCollect.stream().mapToDouble(x -> x).sum();
        double Ey = rating2ScoreCollect.stream().mapToDouble(y -> y).sum();
        double Ex2 = rating1ScoreCollect.stream().mapToDouble(x -> Math.pow(x, 2)).sum();
        double Ey2 = rating2ScoreCollect.stream().mapToDouble(y -> Math.pow(y, 2)).sum();
        double Exy = IntStream.range(0, n).mapToDouble(i -> rating1ScoreCollect.get(i) * rating2ScoreCollect.get(i)).sum();
        double numerator = Exy - Ex * Ey / n;
        double denominator = Math.sqrt((Ex2 - Math.pow(Ex, 2) / n) * (Ey2 - Math.pow(Ey, 2) / n));
        if (denominator == 0) return 0.0;
        return numerator / denominator;
    }


    public List<RecEntity> recommend(String ip, List<UserCF> users) {
        //找到最近邻
        Map<Double, String> distances = computeNearestNeighbor(ip, users);
        String nearest = distances.values().iterator().next();
//        System.out.println("最近邻 -> " + nearest);

        //找到最近邻看过，但是我们没看过的物品，计算推荐
        UserCF neighborRatings = new UserCF();
        for (UserCF user : users) {
            if (nearest.equals(user.ip)) {
                neighborRatings = user;
            }
        }
//        System.out.println("最近邻看过的物品 -> " + neighborRatings.recEntityList);

        UserCF userRatings = new UserCF();
        for (UserCF user : users) {
            if (ip.equals(user.ip)) {
                userRatings = user;
            }
        }
//        System.out.println("用户看过的物品 -> " + userRatings.recEntityList);

        //根据自己和邻居的物品计算推荐的物品
        List<RecEntity> recommendationMovies = new ArrayList<>();
        for (RecEntity recEntity : neighborRatings.recEntityList) {
            if (userRatings.find(recEntity.thingId) == null) {
                recommendationMovies.add(recEntity);
            }
        }
        Collections.sort(recommendationMovies);
        return recommendationMovies;
    }

}
