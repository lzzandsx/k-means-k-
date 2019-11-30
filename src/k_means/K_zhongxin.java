package k_means;

import java.awt.Window.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class K_zhongxin {

	static Point_zhongxin[] points;
	static Point_zhongxin[] kPoint_zhongxins;

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		
		K_zhongxin k_zhongxin = new K_zhongxin();
		k_zhongxin.execute();
	}

	public void execute() {
		init(); // 随机生成
		updata();
		output();
	}

	public void init() {
		int nums = 30;
		int k_nums = 4;
		Random random = new Random();
		points = new Point_zhongxin[nums];
		kPoint_zhongxins = new Point_zhongxin[k_nums];
		for (int i = 0; i < points.length; i++) {
			points[i] = new Point_zhongxin();
			points[i].x = random.nextInt(100);
			points[i].y = random.nextInt(100);
			points[i].z = random.nextInt(100);
		}

	}

	public void updata() {
		// 在从簇里面随机得到k
		// 先计算距离，然后归类
		int count = 0;
		int nums = points.length;
		Random random = new Random();
		List<Set<Point_zhongxin>> dispartSet = new ArrayList<Set<Point_zhongxin>>();
		for(int i=0;i<4;i++){
			Set<Point_zhongxin> disSet = new HashSet<>();
			dispartSet.add(disSet);
		}
		while (count < 1000) {
			Set<Integer> record = new HashSet<>();
			if (count == 0) {
				for (int i = 0; i < kPoint_zhongxins.length; i++) {
					int center = random.nextInt(nums);
					while (record.contains(center)) {
						center = random.nextInt(nums);
					}
					record.add(center);
					kPoint_zhongxins[i] = points[center].clone();
				}
			}
			else {
				for(int i =0;i<kPoint_zhongxins.length;i++){
					Set<Point_zhongxin> temp = dispartSet.get(i);
					int randomTemp = random.nextInt(nums)%temp.size();
					Iterator<Point_zhongxin> iterator = temp.iterator();
					int j=0;
					Point_zhongxin pointTemp = null;
					while(iterator.hasNext()){
						pointTemp = iterator.next();
						if(j == randomTemp)
							break;
						j++;
					}
					kPoint_zhongxins[i] = pointTemp.clone();
				}
			}		
			for(int i =0;i<nums;i++){
				Point_zhongxin point = points[i];
				int distance = Math.abs(point.x - kPoint_zhongxins[0].x)+Math.abs(point.y - kPoint_zhongxins[0].y)+Math.abs(point.z - kPoint_zhongxins[0].z);
				point.type = 0;
				for(int j =1;j< kPoint_zhongxins.length;j++){
					int temp = Math.abs(point.x - kPoint_zhongxins[j].x)+Math.abs(point.y - kPoint_zhongxins[j].y)+Math.abs(point.z - kPoint_zhongxins[j].z);
					if(distance>temp){
						distance = temp;
						point.type = j;
					}
				}			
				dispartSet.get(point.type).add(point);			
			}
					
			count++;
		}
	}

	public void output() {
		for(int i =0;i<points.length;i++){
			System.out.println(points[i].toString()+" "+points[i].type);
		}
	}

}

class Point_zhongxin implements Cloneable {
	int x;
	int y;
	int z;
	int type;
	
	@Override
	protected Point_zhongxin clone() {
		// TODO 自动生成的方法存根
		Point_zhongxin point_zhongxin = null;
		try {
			point_zhongxin = (Point_zhongxin) super.clone(); // 克隆的方式，调用object的clone并强制转化，然后赋值
			point_zhongxin.x = x;
			point_zhongxin.y = y;
			point_zhongxin.z = z;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return point_zhongxin;
	}

	@Override
	public String toString() {
		// TODO 自动生成的方法存根
		return "(" + x + ", " + y + ", " + z + ")";
	}
}