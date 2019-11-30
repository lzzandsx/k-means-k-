package k_means;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

public class K_means {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根

		K_means k_means = new K_means();
		k_means.execute();
	}

	public void execute() {
		//随机生成
		int num = 30;
		int k_num = 4;
		Random random = new Random();
		Point[] simplePoints = new Point[num];
		Point[] kPoints = new Point[k_num];
		for (int i = 0; i < num; i++) {
			simplePoints[i] = new Point();
			simplePoints[i].x = random.nextInt(100);
			simplePoints[i].y = random.nextInt(100);
			simplePoints[i].z = random.nextInt(100);
		}
		Set<Integer> kSet = new HashSet<>();
		for (int i = 0; i < k_num; i++) {
			kPoints[i] = new Point();
			int center = random.nextInt(num);
			while (kSet.contains(center)) {
				center = random.nextInt(num);
			}
			kPoints[i].x = simplePoints[center].x;
			kPoints[i].y = simplePoints[center].y;
			kPoints[i].z = simplePoints[center].z;
		}

		handle(simplePoints, kPoints);

	}

	public void handle(Point[] simplePoints, Point[] kPoints) {

		/**
		 * 1.计算点是属于哪个类 2.根据情况更新k点
		 */
		HashMap<Point, Integer> pointDis = new HashMap<>();// 点的分类
		for (int count = 0; count < 100; count++) {
			for (int i = 0; i < simplePoints.length; i++) {
				double minDistance = Math.pow((simplePoints[i].x - kPoints[0].x), 2)
						+ Math.pow((simplePoints[i].y - kPoints[0].y), 2)
						+ Math.pow((simplePoints[i].z - kPoints[0].z), 2);
				pointDis.put(simplePoints[i], 0);
				for (int j = 1; j < kPoints.length; j++) {
					double distance = Math.pow((simplePoints[i].x - kPoints[j].x), 2)
							+ Math.pow((simplePoints[i].y - kPoints[j].y), 2)
							+ Math.pow((simplePoints[i].z - kPoints[j].z), 2);
					if (minDistance > distance) {
						minDistance = distance;
						pointDis.put(simplePoints[i], j);
					}
				}
			}
			updataKPoint(pointDis,simplePoints,kPoints);
		}
		
		output(pointDis);
	}

	public void updataKPoint(HashMap<Point, Integer> pointDis,Point[] simplePoints,Point[] kPoints) {
		//更新
		for(int i=0;i<kPoints.length;i++){
			kPoints[i].x = 0;
			kPoints[i].y = 0;
			kPoints[i].z = 0;
		}
		int[] counters = new int[kPoints.length];
		for(int i =0;i<simplePoints.length;i++){
			int center = pointDis.get(simplePoints[i]);
			kPoints[center].x += simplePoints[i].x;
			kPoints[center].y += simplePoints[i].y;
			kPoints[center].z += simplePoints[i].z;
			counters[center]++;
		}
		for(int i=0;i<kPoints.length;i++){
			if(counters[i] == 0)
				continue;
			kPoints[i].x /= counters[i];
			kPoints[i].y /= counters[i];
			kPoints[i].z /= counters[i];
		}
	}

	public void output(HashMap<Point, Integer> pointDis) {
		Iterator<Entry<Point, Integer>> iterator = pointDis.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<Point, Integer> entry = iterator.next();
			System.out.println(entry.getKey().toString()+" "+entry.getValue());
		}
		
	}
	
}

class Point {
	double x;
	double y;
	double z;
	
	@Override
	public String toString() {
		// TODO 自动生成的方法存根
		return "("+x+", "+y+", "+z+")";
	}
}